import io
import time
import csv
import zipfile
import unicodedata
from urllib.request import urlopen
from multiprocessing import Process, set_start_method, cpu_count

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
    if x is None:
        return 0.0
    x = x.strip()
    if not x:
        return 0.0
    x = x.replace('.', '').replace(',', '.')
    try:
        return float(x)
    except ValueError:
        return 0.0

def split_even(lines, k):
    k = max(1, min(k, len(lines)))
    base = len(lines) // k
    extra = len(lines) % k
    chunks = []
    start = 0
    for i in range(k):
        size = base + (1 if i < extra else 0)
        chunks.append(lines[start:start+size])
        start += size
    return chunks

def process_chunk(csv_text: str, label: str):
    print(f"\n=== Worker {label} ===")
    t0 = time.perf_counter()

    reader = csv.reader(io.StringIO(csv_text), delimiter=';', quotechar='"')
    headers = next(reader)
    headers_norm = [norm_col(h) for h in headers]

    def find_col(target: str, fallback_contains=None):
        target = target.upper()
        try:
            return headers_norm.index(target)
        except ValueError:
            if fallback_contains:
                for i, h in enumerate(headers_norm):
                    if all(sub in h for sub in fallback_contains):
                        return i
            raise KeyError(f"Coluna '{target}' não encontrada.")

    try:
        idx_uf = find_col('UF')
        idx_valor = find_col('VALOR_PARCELA', fallback_contains=['VALOR', 'PARCELA'])
    except KeyError as e:
        print(f"[{label}] ERRO: {e}")
        return

    t_calc0 = time.perf_counter()
    resumo = {}  
    total_linhas = 0
    for row in reader:
        total_linhas += 1
        try:
            uf = row[idx_uf].strip()
        except IndexError:
            uf = ''
        try:
            valor = br_to_float(row[idx_valor])
        except IndexError:
            valor = 0.0
        if uf not in resumo:
            resumo[uf] = [0, 0.0]
        resumo[uf][0] += 1
        resumo[uf][1] += valor

    t_calc = time.perf_counter() - t_calc0
    t_total = time.perf_counter() - t0

    itens = [(uf, cnt, soma) for uf, (cnt, soma) in resumo.items()]
    itens.sort(key=lambda x: x[2], reverse=True)

    print(f"[{label}] linhas_no_chunk      : {total_linhas}")
    print(f"[{label}] UFs_encontradas      : {len(resumo)}")
    print(f"[{label}] tempo_calculo_chunk_s: {t_calc:.3f}")
    print(f"[{label}] tempo_total_worker_s : {t_total:.3f}")

    print(f"{'UF':<4} {'TOTAL_PAGTOS':>14} {'VALOR_TOTAL':>16}")
    for uf, cnt, soma in itens:
        print(f"{(uf or '(vazio)'):<4} {cnt:14d} {soma:16.2f}")

def main(n_procs: int | None = None):
    print("\n=== PÉ-DE-MEIA 2025-01 | PARALELO ===")
    print("URL:", URL)

    try:
        set_start_method('spawn')
    except RuntimeError:
        pass 

    t0_all = time.perf_counter()

    t_dl0 = time.perf_counter()
    with urlopen(URL, timeout=120) as resp:
        zip_bytes = resp.read()
    t_download = time.perf_counter() - t_dl0

    t_ld0 = time.perf_counter()
    with zipfile.ZipFile(io.BytesIO(zip_bytes)) as zf:
        csv_names = [n for n in zf.namelist() if n.lower().endswith('.csv')]
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

    if n_procs is None:
        n_procs = min(cpu_count(), max(1, len(data_lines)))
    chunks = split_even(data_lines, n_procs)

    t_split = time.perf_counter() - t_ld0

    t_spawn0 = time.perf_counter()
    procs = []
    for i, chunk_lines in enumerate(chunks, start=1):
        csv_text_chunk = header_line + "\n" + "\n".join(chunk_lines) if chunk_lines else header_line
        label = f"P{i:02d}"
        p = Process(target=process_chunk, args=(csv_text_chunk, label), daemon=False)
        p.start()
        procs.append(p)

    for p in procs:
        p.join()
    t_spawn_join = time.perf_counter() - t_spawn0

    t_total_all = time.perf_counter() - t0_all

    print("\n[Main] Métricas do pai")
    print(f"- tempo_download_s           : {t_download:.3f}")
    print(f"- tempo_extracao_e_decode_s  : {t_split:.3f}")
    print(f"- tempo_workers              : {t_spawn_join:.3f}")
    print(f"- processos                  : {len(procs)}")
    print(f"- linhas_totais              : {len(data_lines)}")
    print(f"- tempo_total_end_to_end_s   : {t_total_all:.3f}")

if __name__ == "__main__":
    main()
