from django.db import models

# Create your models here.
class Produto(models.Model):
    nome = models.CharField(max_length=100, db_column='nome')
    preco = models.DecimalField(max_digits=6, decimal_places=2, db_column='preco')
    
    class Meta:        
        db_table = "produto"              # nome da tabela no banco
        ordering = ["nome"]               # ordenação padrão (por nome)
        verbose_name = "Produto"          # nome singular para admin
        verbose_name_plural = "Produtos"  # nome plural para admin
    
    def __str__(self):
        return f"Produto: {self.nome}"