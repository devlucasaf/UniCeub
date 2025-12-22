import hashlib
import bcrypt
import time
import os

# --- CENÁRIO 1: O jeito ERRADO (MD5) ---
def md5_hash(password):
    return hashlib.md5(password.encode()).hexdigest()

# --- CENÁRIO 2: O jeito SEGURO (Bcrypt) ---
def secure_hash(password):
    # O bcrypt gera um 'salt' aleatório e o inclui no hash final automaticamente.
    # O parâmetro 'rounds' (work factor) determina a lentidão. 
    # 12 é um bom padrão atual (2^12 iterações).
    salt = bcrypt.gensalt(rounds=12)
    hashed = bcrypt.hashpw(password.encode(), salt)
    return hashed

# --- SIMULAÇÃO DE ARMAZENAMENTO (Banco de Dados) ---
# Imagine que vazaram o banco de dados da empresa!
leaked_db_md5 = [
    {'user': 'admin', 'hash': 'e10adc3949ba59abbe56e057f20f883e'}, # 123456
    {'user': 'alice', 'hash': 'e10adc3949ba59abbe56e057f20f883e'}, # 123456 (Hash idêntico!)
    {'user': 'bob',   'hash': '5f4dcc3b5aa765d61d8327deb882cf99'}  # password
]

# Tabela Rainbow simplificada (Dicionário de hashes pré-calculados)
# Hackers baixam isso pronto com bilhões de entradas.
rainbow_table = {
    'e10adc3949ba59abbe56e057f20f883e': '123456',
    '5f4dcc3b5aa765d61d8327deb882cf99': 'password',
    '098f6bcd4621d373cade4e832627b4f6': 'test'
}

def demo_rainbow_attack():
    print("\n--- 1. DEMONSTRAÇÃO DE ATAQUE: RAINBOW TABLE (MD5) ---")
    print("Analisando banco de dados vazado...")
    
    for user in leaked_db_md5:
        h = user['hash']
        # O ataque é O(1) - busca instantânea
        cracked_pass = rainbow_table.get(h)
        
        if cracked_pass:
            print(f"[CRITICAL] Senha de '{user['user']}' QUEBRADA! Hash: {h[:10]}... -> Senha: '{cracked_pass}'")
        else:
            print(f"[INFO] Senha de '{user['user']}' não encontrada na tabela.")
    
    print("\nOBS: Note que Alice e Admin tinham hashes idênticos. Quebrando um, quebrou ambos.")

def demo_timing_attack():
    print("\n--- 2. DEMONSTRAÇÃO DE CUSTO COMPUTACIONAL (BRUTE FORCE) ---")
    password_to_crack = "minhasenha123"
    
    # Teste MD5
    start = time.time()
    for _ in range(1000): # Tenta hashear 1000 vezes
        md5_hash(password_to_crack)
    end = time.time()
    print(f"MD5: Tempo para gerar 1.000 hashes: {end - start:.5f} segundos")
    print("-> Conclusão: Um hacker com uma GPU doméstica pode testar BILHÕES de senhas por segundo.")

    # Teste Bcrypt
    start = time.time()
    # Vamos fazer apenas 1 hash porque é lento!
    secure_hash(password_to_crack)
    end = time.time()
    bcrypt_time = end - start
    print(f"Bcrypt (Work Factor 12): Tempo para gerar 1 hash: {bcrypt_time:.5f} segundos")
    
    # Projeção
    ratio = bcrypt_time / ((end-start)/1000 if (end-start) > 0 else 0.000001) 
    print(f"-> Conclusão: O Bcrypt é propositalmente lento. Ele é aprox. milhares de vezes mais lento que MD5.")
    print(f"-> Isso torna ataques de força bruta inviáveis na prática.")

def demo_salt():
    print("\n--- 3. DEMONSTRAÇÃO DE SALT (SALGANDO A SENHA) ---")
    senha = "segredo_super"
    
    print(f"Senha original: {senha}")
    
    # Bcrypt gera um salt diferente a cada execução
    hash1 = bcrypt.hashpw(senha.encode(), bcrypt.gensalt())
    hash2 = bcrypt.hashpw(senha.encode(), bcrypt.gensalt())
    
    print(f"Hash 1 (Bcrypt): {hash1}")
    print(f"Hash 2 (Bcrypt): {hash2}")
    print("Estado: Hashes DIFERENTES para a MESMA senha.")
    print("Isso impede que dois usuários com a mesma senha tenham o mesmo hash no banco.")

if __name__ == "__main__":
    demo_rainbow_attack()
    demo_timing_attack()
    demo_salt()