from django.shortcuts import render
from .models import Autor, Editora, Livro


# Create your views here.

def home_view(request): 
    livro = Livro.objects.all()
    return render(request, 'home.html', {"livros" : livro})                                                                                                           