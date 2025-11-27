import hashlib
import bcrypt
import time

def quebrar_sha256():
    hash_alvo = "e4ed4d14170e2017c139c958853c66f4cecd6b43c12b0e0c641f4288bd859d93"
    
    print("=== QUEBRANDO SENHA SHA-256 ===")
    start_time = time.time()
    
    for i in range(10000):
        senha = f"{i:04d}"
        hash_calculado = hashlib.sha256(senha.encode()).hexdigest()
        
        if hash_calculado == hash_alvo:
            end_time = time.time()
            tempo_decorrido = end_time - start_time
            print(f"Senha encontrada: {senha}")
            print(f"Hash: {hash_calculado}")
            print(f"Tempo: {tempo_decorrido:.6f} segundos")
            print(f"Velocidade: {i/ tempo_decorrido:.2f} tentativas/segundo")
            return senha, tempo_decorrido
    
    return None, 0

def quebrar_bcrypt():
    hash_alvo = "$2b$12$5lDzOA2pKwd767OdJEXw4ON5Y/zIEZOhz3ljqp0NEP9OwzLPBLp8a"
    
    print("\n=== QUEBRANDO SENHA BCRYPT ===")
    print("AVISO: Isso pode demorar MUITO tempo!")
    
    start_time = time.time()
    tentativas = 0
    
    for i in range(10000):
        senha = f"{i:04d}".encode('utf-8')
        
        try:
            if bcrypt.checkpw(senha, hash_alvo.encode('utf-8')):
                end_time = time.time()
                tempo_decorrido = end_time - start_time
                print(f"Senha encontrada: {senha.decode()}")
                print(f"Tempo: {tempo_decorrido:.2f} segundos")
                print(f"Tentativas: {tentativas}")
                print(f"Velocidade: {tentativas/tempo_decorrido:.2f} tentativas/segundo")
                return senha.decode(), tempo_decorrido
        except Exception as e:
            pass
        
        tentativas += 1
        
        if tentativas % 500 == 0:
            tempo_parcial = time.time() - start_time
            print(f"Progresso: {tentativas}/10000 | Tempo: {tempo_parcial:.2f}s")
    
    return None, 0

def comparar_desempenho():
    print("=== COMPARAÇÃO DE DESEMPENHO ===")
    
    # Primeiro quebra o SHA-256 (rápido)
    senha_sha, tempo_sha = quebrar_sha256()
    
    # Pergunta se quer tentar o Bcrypt
    resposta = input("\nDeseja tentar quebrar o Bcrypt? (Pode demorar horas!) [s/N]: ")
    
    if resposta.lower() == 's':
        senha_bcrypt, tempo_bcrypt = quebrar_bcrypt()
        
        if tempo_bcrypt > 0:
            print(f"\n=== RESULTADO FINAL ===")
            print(f"SHA-256: {tempo_sha:.6f} segundos")
            print(f"Bcrypt: {tempo_bcrypt:.2f} segundos")
            print(f"Bcrypt é {tempo_bcrypt/tempo_sha:.0f}x mais lento que SHA-256!")

# Executar comparação
if __name__ == "__main__":
    comparar_desempenho()