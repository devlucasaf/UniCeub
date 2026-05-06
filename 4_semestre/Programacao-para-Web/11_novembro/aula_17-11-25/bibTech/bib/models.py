from django.db import models

class Autor(models.Model):
    nome = models.CharField(max_length=150)

    class Meta:
        db_table = "autor"

class Editora(models.Model):
    nome = models.CharField(max_length=150)

    class Meta:
        db_table = "editora"

class Livro(models.Model):
    nome = models.CharField(max_length=150)
    autor = models.ForeignKey(Autor, on_delete=models.CASCADE)
    editora = models.ForeignKey(Editora, on_delete=models.CASCADE)
    isbn = models.CharField(max_length=13, help_text="13 caracteres")
    genero = models.CharField(max_length=50)
    data_pub = models.DateField()

    class Meta:
        db_table = 'livro'