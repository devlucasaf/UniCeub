# Ajax + JQuery + Django

from django.http import JsonResponse

def carregar_categorias_ajax(request):
    """
    View que será chamada via AJAX para buscar as categorias
    Retorna os dados em formato JSON.
    """

    # Usamos .value() para pegar apenas os campos que precisamos (id e nome)
    # Isso é mais performático do que buscar o objeto inteiro.

    categorias = Categoria.objects.all().value('id', 'nome')

    # Convertemos o QuerySet para uma lista para que possa ser serializado em JSON.
    categorias_list = list(categorias)

    """ 
    Retornamos um JsonResponse. O safe=False é necessário porque não estamos
    retornando um dicionário no topo da estrutura, e sim uma lista.
    """
    return JsonResponse(categorias_list, safe=False)
