Module Program

    Sub Main()

        Dim opcao As Integer

        Console.WriteLine("===================================")
        Console.WriteLine(" SISTEMA DE CONTROLE DE ESTOQUE ")
        Console.WriteLine("===================================")

        Do
            Console.WriteLine()
            Console.WriteLine("1 - Cadastrar Produto")
            Console.WriteLine("2 - Calcular Desconto")
            Console.WriteLine("3 - Mostrar Relatório")
            Console.WriteLine("4 - Sair")
            Console.Write("Escolha uma opção: ")

            opcao = Convert.ToInt32(Console.ReadLine())

            Select Case opcao

                Case 1
                    CadastrarProduto()

                Case 2
                    CalcularDesconto()

                Case 3
                    MostrarRelatorio()

                Case 4
                    Console.WriteLine("Encerrando sistema...")

                Case Else
                    Console.WriteLine("Opção inválida!")

            End Select

        Loop While opcao <> 4

    End Sub

    Sub CadastrarProduto()

        Dim nome As String
        Dim preco As Double
        Dim quantidade As Integer

        Console.WriteLine()
        Console.WriteLine("=== Cadastro de Produto ===")

        Console.Write("Nome do produto: ")
        nome = Console.ReadLine()

        Console.Write("Preço do produto: ")
        preco = Convert.ToDouble(Console.ReadLine())

        Console.Write("Quantidade em estoque: ")
        quantidade = Convert.ToInt32(Console.ReadLine())

        Console.WriteLine()
        Console.WriteLine("Produto cadastrado com sucesso!")
        Console.WriteLine("Nome: " & nome)
        Console.WriteLine("Preço: R$ " & preco)
        Console.WriteLine("Quantidade: " & quantidade)

    End Sub

    Sub CalcularDesconto()

        Dim valor As Double
        Dim desconto As Double
        Dim valorFinal As Double

        Console.WriteLine()
        Console.WriteLine("=== Cálculo de Desconto ===")

        Console.Write("Digite o valor do produto: ")
        valor = Convert.ToDouble(Console.ReadLine())

        Console.Write("Digite o percentual de desconto: ")
        desconto = Convert.ToDouble(Console.ReadLine())

        valorFinal = valor - (valor * desconto / 100)

        Console.WriteLine("Valor final com desconto: R$ " & valorFinal)

    End Sub

    Sub MostrarRelatorio()

        Console.WriteLine()
        Console.WriteLine("=== Relatório do Sistema ===")
        Console.WriteLine("Sistema funcionando corretamente.")
        Console.WriteLine("Todos os módulos foram executados.")
        Console.WriteLine("Paradigma procedural em Visual Basic.")
        Console.WriteLine("Utilização de procedimentos (Sub).")

    End Sub

End Module