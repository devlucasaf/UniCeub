import io
import time
import csv
import zipfile
import unicodedata
from urllib.request import urlopen

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

def main():
    print("\n=== PÉ-DE-MEIA 2025-01 ===")
    print("URL:", URL)

    t0 = time.perf_counter()

    t_dl0 = time.perf_counter()
    with urlopen(URL, timeout=120) as resp:
        zip_bytes = resp.read()  # lê TUDO para RAM
    t_download = time.perf_counter() - t_dl0

    t_ld0 = time.perf_counter()
    with zipfile.ZipFile(io.BytesIO(zip_bytes)) as zf:
        csv_names = [n for n in zf.namelist() if n.lower().endswith(".csv")]
        if not csv_names:
            raise RuntimeError("ZIP não contém CSV.")
        with zf.open(csv_names[0]) as f:
            raw_csv = f.read() 

    text = raw_csv.decode("latin-1", errors="replace")
    reader = csv.reader(io.StringIO(text), delimiter=';', quotechar='"')

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

    idx_uf = find_col('UF')
    try:
        idx_valor = find_col('VALOR_PARCELA', fallback_contains=['VALOR', 'PARCELA'])
    except KeyError:
        raise

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

    t_calculo = time.perf_counter() - t_calc0
    t_load = time.perf_counter() - t_ld0
    t_total = time.perf_counter() - t0

    itens = [(uf, cnt, soma) for uf, (cnt, soma) in resumo.items()]

    print("\n[Métricas]")
    print(f"- tempo_download_s         : {t_download:.3f}")
    print(f"- tempo_extracao_e_carga_s : {t_load:.3f}")
    print(f"- tempo_calculo_s          : {t_calculo:.3f}")
    print(f"- tempo_total_s            : {t_total:.3f}")
    print(f"- linhas                   : {total_linhas}")
    print(f"- UFs encontradas          : {len(resumo)}")

    print("\n[Resumo por UF - Top 10 por valor_total]")
    print(f"{'UF':<4} {'TOTAL_PAGTOS':>14} {'VALOR_TOTAL':>16}")
    for uf, cnt, soma in itens[:10]:
        print(f"{(uf or '(vazio)'):<4} {cnt:14d} {soma:16.2f}")


if __name__ == "__main__":
    main()
