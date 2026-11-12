from condominio import Condominio, linha

# CRIAÇÃO DOS OBJETOS
morador1 = Condominio("Diego Cavalieri", 1200, 41, 1303, 13)
morador2 = Condominio("Leanderson", 1200, 26, 101, 1)
morador3 = Condominio("Everaldo", 1200, 18, 506, 5)

# EXIBIÇÃO DOS DADOS
morador1.mostra_dados()
morador2.mostrar_dados2()

# RETORNO DOS ATRIBUTOS
print(f"\n>>Retorna atributos<<\n\n[MORADOR 3] \n{morador3.retornar_dados()}\n")
linha()

# TESTE DOS SETTERS
print("\n>>Teste do método set para atualizar o primeiro morador<<")
print("\n[MORADOR 1 ATUALIZADO]")

morador1.set_nome("Jhon Arias")
morador1.set_aluguel(1100)
morador1.set_idade(26)
morador1.set_apartamento(1804)
morador1.set_andar(18)

# EXIBE DADOS ATUALIZADOS
print(f"\n[MORADOR NOVO] \nNome do morador: {morador1.nome}")
print(f"Valor do aluguel: R$ {morador1.aluguel},00")
print(f"Idade: {morador1.idade} anos")
print(f"Número do apartamento: {morador1.apartamento}")
print(f"Número do andar: {morador1.andar}\n")

linha()