using System;

namespace Empresa;

public class FuncionarioInativoException : Exception
{
    public FuncionarioInativoException(string mensagem) : base(mensagem) { }
}