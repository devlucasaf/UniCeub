#https://docs.python.org/3/library/multiprocessing.html
import io
import time
import csv
import zipfile
import unicodedata
from urllib.request import urlopen
from multiprocessing import Process, Value, cpu_count, set_start_method

URL = "https://portaldatransparencia.gov.br/download-de-dados/pe-de-meia/202501"

def strip_accents(s: str) -> str:
    nfkd = unicodedata.normalize('NFKD', s)
    return ''.join(ch for ch in nfkd if not unicodedata.combining(ch))

def norm_col(name: str) -> str:
    s = strip_accents(name).upper()
    s = ''.join(ch if ch.isalnum() else '_' for ch in s)

    while '__' in s:
        s = s.replace('__', '_')
    return s.strip('_')

def br_to_float(x: str) -> float:
    if not x:
        return 0.0
    x = x.strip().replace('.', '').replace(',', '.')
    try:
        return float(x)
    except ValueError:
        return 0.0

def worker(csv_text: str, label: str, soma_total):
    reader = csv.reader(io.StringIO(csv_text), delimiter=';', quotechar='"')
    headers = next(reader)
    headers_norm = [norm_col(h) for h in headers]
    try:
        idx_valor = headers_norm.index('VALOR_PARCELA')
    except ValueError:
        idx_valor = next((i for i,h in enumerate(headers_norm) if 'VALOR' in h and 'PARCELA' in h), None)
        if idx_valor is None:
            print(f"[{label}] ERRO: coluna VALOR_PARCELA não encontrada.")
            return

    total = 0.0
    linhas = 0
    for row in reader:
        linhas += 1
        try:
            total += br_to_float(row[idx_valor])
            soma_total.value = soma_total.value + total

        except IndexError:
            pass

    print(f"[{label}] linhas={linhas}  total_parte={soma_total.value}")

def split_even(seq, k):
    k = max(1, min(k, len(seq)))
    base = len(seq) // k
    extra = len(seq) % k
    out = []
    start = 0
    for i in range(k):
        size = base + (1 if i < extra else 0)
        out.append(seq[start:start+size])
        start += size
    return out

def main():
    print("\n=== PÉ-DE-MEIA 2025-01 | PARALELO ===")
    print(f"URL: {URL}")

    try:
        set_start_method('spawn')
    except RuntimeError:
        pass

    # Pai: download e extração
    with urlopen(URL, timeout=120) as resp:
        zip_bytes = resp.read()

    with zipfile.ZipFile(io.BytesIO(zip_bytes)) as zf:
        csv_names = [n for n in zf.namelist() if n.lower().endswith(".csv")]
        if not csv_names:
            raise RuntimeError("ZIP não contém CSV.")
        with zf.open(csv_names[0]) as f:
            raw_csv = f.read()

    text = raw_csv.decode("latin-1", errors="replace")
    lines = text.splitlines()
    if not lines:
        raise RuntimeError("CSV vazio.")
    header_line = lines[0]
    data_lines = lines[1:]

    n = max(1, min(cpu_count(), len(data_lines)))
    chunks = split_even(data_lines, n)

    procs = []
    soma_total = Value("f", 0.0, lock=False)
    for i, chunk in enumerate(chunks, start=1):
        csv_text_chunk = header_line + "\n" + "\n".join(chunk) if chunk else header_line
        p = Process(target=worker, args=(csv_text_chunk, f"P{i:02d}"), daemon=False)
        p.start()
        procs.append(p)

    for p in procs:
        p.join()

    print(f"\n[Main] processos={n}  linhas_totais={len(data_lines)}  feito.")


if __name__ == "__main__":
    main()
