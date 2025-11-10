from django.shortcuts import render

# Create your views here.

def index(request):
    return render(request, 'aulaweb/index.html', {})

def sobre(request):
    parametros = {
        'title': 'Sobre o Projeto',
        'professor': 'Felippe Pires'
    }
    return render(request, 'aulaweb/sobre.html', parametros)
