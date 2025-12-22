# from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2   # Sintaxe 2
from conta_aula import Conta, PessoaFisica, PessoaJuridica
if __name__ == '__main__':
    c = Conta('Alice')                  # Chama o construtor da classe Conta
    print(c)                            # print(c.__str__())
    print('- Superclasse:\nNome:', c.get_nome())  # Métodos da superclasse
    print('Saldo:', c.get_saldo())
    c.set_nome('Emily')
    print('Nome alterado:', c.get_nome())
    print(c.__str__())                  # print(c)
    pf1 = PessoaFisica('Ana', 3000.0, 'f')  # Objeto da subclasse
    print(pf1)                          # Chama o método __str__()
    print('- Pessoa Física 1:\nNome:', pf1.get_nome())  # Método na superclasse
    print("Saldo:", pf1.get_saldo())
    pf2 = PessoaFisica('Ailton', 7000.0)    # Objeto da subclasse Conta_PF
    print(pf2)                          # Chama o método __str__()
    print('- Pessoa Física 2:\nNome:', pf2.get_nome())  # Método na superclasse
    print('Saída usando as funções vars e __dict__ do Python:')
    print(vars(pf2))                    # Usa os métodos do Python
    print(pf2.__dict__)
    # {'nome': 'Ana', 'saldo': 3000.0, 'genero': 'm', 'cpf': ''}
    pj1 = PessoaJuridica('Café ABC', 15000.0)  # objeto (instância) de Conta_PJ
    print(pj1)
    print('- Pessoa Jurídica:\nNome:', pj1.get_nome())  # Método da superclasse
    print("Saldo:", pj1.get_saldo())
    print(pj1)
    print(vars(pj1))                                    # Usa os métodos do Python
    print(pj1.__dict__)
    pf1.deposito(11)                                    # Depósito
    print("- Pessoa Física 1:\nSaldo:", pf1.get_saldo())  # Verifica alteração
    pj1.deposito(22)
    print("- Pessoa Jurídica 1:\nSaldo:", pj1.get_saldo())  # Verifica alteração
    pf1.retirada(10)                                        # Retirada sem RN
    print("- Pessoa Física 1:\nSaldo:", pf1.get_saldo())    # Verifica alteração
    pj1.retirada(21)
    print("- Pessoa Jurídica 1:\nSaldo:", pj1.get_saldo())  # Verifica alteração
    pf1.retirada_rn(10)                                     # Retirada com RN
    print("- Pessoa Física 1:\nSaldo:", pf1.get_saldo())    # Verifica alteração
    pj1.retirada_rn(21)
    print("- Pessoa Jurídica 1:\nSaldo:", pj1.get_saldo())  # Verifica alteração
    pj1.retirada_rn(20000.0)                                # Retirada, msg erro
    print('Saldo: ', pj1.get_saldo())                       # Verifica alteração
    print("- Pessoa Física 1, antes:\nSaldo:", pf1.get_saldo())
