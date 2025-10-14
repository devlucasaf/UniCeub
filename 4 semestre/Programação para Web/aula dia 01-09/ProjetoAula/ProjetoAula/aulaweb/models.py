from django.db import models

class Produto(models.Model):
    nome = models.CharField(max_legth=100)      # nome do produto
    descricao = models.TextField(blank=True, null=True)     # descrição opcional
    preco = models.DecimalField(max_digits=6, decimal_places=2)     # 2 casas decimais
    quantidade_estoque = models.PositiveIntegerField(default=0)     # estoque inicial
    data_criacao = models.DateTimeField(auto_now_add=True)      # preenchido na criação
    data_atualizacao = models.DateTimeField(auto_now=True)      # atualizado sempre que salvar

    class Meta:
        db_table = "produto"        # nome da tabela no banco
        ordering = ["nome"]         # ordenação padrão (por nome)
        verbose_name = "Produto"    # nome singular para admin
        verbose_name_plural = "Produtos"    # nome plural para admin

    def __str__(self):      # método para mostrar o objeto de forma legível
        return f"{self.nome} - R${self.preco}"
