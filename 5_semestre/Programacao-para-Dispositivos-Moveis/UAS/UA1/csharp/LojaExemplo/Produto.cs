namespace LojaExemplo
{
    public class Produto
    {
        public int Codigo { get; set; }
        public string Nome { get; set; }
        public double Preco { get; set; }
        public int Quantidade { get; set; }

        public Produto(int codigo, string nome, double preco, int quantidade)
        {
            Codigo      = codigo;
            Nome        = nome;
            Preco       = preco >= 0 ? preco : 0;
            Quantidade  = quantidade >= 0 ? quantidade : 0;
        }

        public double CalcularTotal()
        {
            return Preco * Quantidade;
        }

        public override string ToString()
        {
            return $"({Codigo}) {Nome}\n" +
                   $"Preço: {Preco}\n" +
                   $"Quantidade: {Quantidade}\n" +
                   $"Total: {CalcularTotal()}";
        }
    }
}