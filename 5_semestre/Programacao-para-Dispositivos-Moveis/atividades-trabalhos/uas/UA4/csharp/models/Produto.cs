namespace csharp.Models;

public class Produto
{
    public string   Nome            { get; set; }
    public int      Quantidade      { get; set; }
    public string   TipoEntrega     { get; set; }
    public string   Regiao          { get; set; }
    public bool     AceitaPromocoes { get; set; }
}

