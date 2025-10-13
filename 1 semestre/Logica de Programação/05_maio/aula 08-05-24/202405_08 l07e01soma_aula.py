"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Crie a função retorna_soma para somar dois valores. Ela recebe dois
valores, calcula a soma e retorna o resultado do cálculo.

A função main lê os dois valores inteiros, chama a função retorna_soma
passando os valores lidos e depois mostra o valor retornado pela função
retorna_soma, ou seja, o resultado obtido.

Teste: Entrada: 3 e 2                   Saída: Soma = 5           """

# o def define a função que recebe os parâmetros (valores) v1 e v2.
def retorna_soma(v1, v2):          # Assinatura da função
    soma = v1 + v2                 # A variável soma recebe o cálculo
    return soma                    # Retorna o valor calculado
    # Fim da funçao

# A função (def) só será executada quando for chamada na função main.
# O if abaixo indica o início da execução do programa principal (main)
if __name__ == '__main__':                   # mai + <tab> (atalho)
    valor1 = int(input('Primeiro valor: '))  # Indentação obrigatória.
    valor2 = int(input('Segundo valor: '))
    v_retorno = retorna_soma(valor1, valor2)  # Chama a função
    # A variável v_retorno recebe o valor retornado pela função (def)
    print(f"Soma = {v_retorno}")
''' ----- ALTERAÇÕES:
a. Acrescente, chame a função retorna_soma mais uma vez, passe os 
    valores reais (2.1, 3.3). 
b. Acrescente (crie) a função retorna_soma_2, refaça a função sem usar  
    a variável soma.

    ----- DICAS ABAIXO:
    v_retorno = retorna_soma(2.1, 3.3)  # No final do main      # a.
    print ("Soma = ", v_retorno)

def retorna_soma_2(v1, v2):                                     # b.
    # soma = v1 + v2               
    # return soma                  
    return v1 + v2                                           
    ...                                 # Acrescente no final do main
    v_retorno = retorna_soma_2(valor1, valor2)  
    print("Soma = ", v_retorno)

'''
