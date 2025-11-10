from django.shortcuts import render
from .models import Produto

# Create your views here.

def index(request):
    return render(request, 'aulaweb/index.html', {})

def sobre(request):
    parametros = {
        'title': 'Sobre o Projeto',
        'professor': 'Felippe Pires'
    }
    return render(request, 'aulaweb/sobre.html', parametros)

def list_produtos(request):
    lista_produtos = Produto.objects.all()
    return render(request, 'aulaweb/produto/list_products.html', {'lista_produtos': lista_produtos})

def detalhar_produto(request, id):
    produto = None
    msg = ''
    try:
        produto = Produto.objects.get(pk=id)
    except Produto.DoesNotExist:
        msg = 'Produto não encontrado'
        
    return render(request, 'aulaweb/produto/detail_product.html', {'produto': produto, 'msg': msg})