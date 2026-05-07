' Código no estilo VB 5.0 / Delphi (pseudocódigo)
Module ModuloCliente
    ' Estrutura de dados (registro)
    Type TCliente
        Nome As String
        Email As String
    End Type

    ' Procedimento separado que age sobre a estrutura
    Sub ExibirCliente(ByRef c As TCliente)
        Print "Nome: " & c.Nome
        Print "Email: " & c.Email
    End Sub

    ' Programa principal
    Sub Main()
        Dim cli As TCliente
        cli.Nome = "Ana Souza"
        cli.Email = "ana@email.com"

        ' Chamada a uma função/procedimento externo
        ExibirCliente(cli)
    End Sub
End Module