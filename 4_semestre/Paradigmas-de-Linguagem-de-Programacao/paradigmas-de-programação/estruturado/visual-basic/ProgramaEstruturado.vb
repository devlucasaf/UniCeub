Module ProgramaEstruturado

    Sub Main()

        Dim notas(4) As Double
        Dim soma As Double = 0
        Dim media As Double
        Dim i As Integer

        Console.WriteLine("===== SISTEMA DE NOTAS =====")

        For i = 0 To 4
            Console.Write("Digite a nota do aluno " & (i + 1) & ": ")
            notas(i) = Convert.ToDouble(Console.ReadLine())
        Next

        For i = 0 To 4
            soma += notas(i)
        Next

        media = soma / 5

        Console.WriteLine()
        Console.WriteLine("===== RESULTADOS =====")

        For i = 0 To 4
            Console.WriteLine("Nota do aluno " & (i + 1) & ": " & notas(i))
        Next

        Console.WriteLine()
        Console.WriteLine("Soma das notas: " & soma)
        Console.WriteLine("Média da turma: " & media)

        If media >= 7 Then
            Console.WriteLine("Situação da turma: APROVADA")
        ElseIf media >= 5 Then
            Console.WriteLine("Situação da turma: RECUPERAÇÃO")
        Else
            Console.WriteLine("Situação da turma: REPROVADA")
        End If

        Console.WriteLine()

        ExibirMensagemFinal()

        Console.WriteLine()
        Console.WriteLine("Pressione qualquer tecla para sair...")
        Console.ReadKey()

    End Sub

    Sub ExibirMensagemFinal()

        Console.WriteLine("===== FIM DO PROGRAMA =====")
        Console.WriteLine("Exemplo de paradigma estruturado em Visual Basic.")
        Console.WriteLine("O programa utiliza:")
        Console.WriteLine("- Variáveis")
        Console.WriteLine("- Vetores")
        Console.WriteLine("- Estruturas de repetição")
        Console.WriteLine("- Estruturas condicionais")
        Console.WriteLine("- Procedimentos")
        Console.WriteLine("- Entrada e saída de dados")

    End Sub

End Module