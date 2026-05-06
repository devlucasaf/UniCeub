from django.db import models

# Create your models here.
class Categoria(models.Model):
    nome = models.CharField(max_length=100, db_column='nome')
    descricao= models.TextField(blank=True, null=True) # descrição opcional
    data_criacao= models.DateTimeField(auto_now=True) # preenchido na criação
    data_atualizacao= models.DateTimeField(auto_now=True) # atualizado sempre que salvar
    
    class Meta:        
        db_table = "categoria"              # nome da tabela no banco
        ordering = ["nome"]               # ordenação padrão (por nome)
        verbose_name = "Categoria"          # nome singular para admin
        verbose_name_plural = "Categorias"  # nome plural para admin
    
    def __str__(self):
        return f"Categoria: {self.nome}"

# Create your models here.
class Produto(models.Model):
    nome = models.CharField(max_length=100, db_column='nome')
    preco = models.DecimalField(max_digits=6, decimal_places=2, db_column='preco')
    url = models.TextField(blank=True, null=True) # link opcional
    descricao= models.TextField(blank=True, null=True) # descrição opcional
    quantidade_estoque= models.PositiveIntegerField(default=0) # estoque inicial
    categoria = models.ForeignKey(Categoria, on_delete=models.CASCADE, related_name='produtos', null=True)
    data_criacao= models.DateTimeField(auto_now=True) # preenchido na criação
    data_atualizacao= models.DateTimeField(auto_now=True) # atualizado sempre que salvar
    
    class Meta:        
        db_table = "produto"              # nome da tabela no banco
        ordering = ["nome"]               # ordenação padrão (por nome)
        verbose_name = "Produto"          # nome singular para admin
        verbose_name_plural = "Produtos"  # nome plural para admin
    
    def __str__(self):
        return f"Produto: {self.nome}"
    
    
# Create your models here.
class Cliente(models.Model):
    nome = models.CharField(max_length=100, db_column='nome')
    telefone = models.CharField(max_length=15)
    endereco = models.TextField(blank=True, null=True) # descrição opcional
    email = models.CharField(max_length=255) # estoque inicial
    data_criacao= models.DateTimeField(auto_now=True) # preenchido na criação
    data_atualizacao= models.DateTimeField(auto_now=True) # atualizado sempre que salvar
    
    class Meta:        
        db_table = "cliente"              # nome da tabela no banco
        ordering = ["nome"]               # ordenação padrão (por nome)
        verbose_name = "Cliente"          # nome singular para admin
        verbose_name_plural = "Clientes"  # nome plural para admin
    
    def __str__(self):
        return f"Cliente: {self.nome} ({self.email})"
    
    
class Pedido(models.Model):
    cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE, related_name="pedidos")
    data_pedido = models.DateTimeField(auto_now_add=True)
    produtos = models.ManyToManyField(Produto, through="ItemPedido")

    class Meta:        
        db_table = "pedido"              # nome da tabela no banco
        ordering = ["-data_pedido"]      # ordenação padrão (por data_pedido decrescente)
        verbose_name = "Pedido"          # nome singular para admin
        verbose_name_plural = "Pedidos"  # nome plural para admin

    def __str__(self):
        return f"Pedido #{self.id} - {self.cliente.nome}"


class ItemPedido(models.Model):
    pedido = models.ForeignKey(Pedido, on_delete=models.CASCADE)
    produto = models.ForeignKey(Produto, on_delete=models.CASCADE)
    quantidade = models.PositiveIntegerField(default=1)

    class Meta:        
        db_table = "item_pedido"              # nome da tabela no banco
        verbose_name = "Item do Pedido"          # nome singular para admin
        verbose_name_plural = "Itens do Pedido"  # nome plural para admin

    def __str__(self):
        return f"{self.quantidade} x {self.produto.nome} (Pedido {self.pedido.id})"