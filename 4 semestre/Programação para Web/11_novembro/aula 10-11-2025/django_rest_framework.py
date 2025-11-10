"""
Programação para Web
Aula: 10-11-2025
Slide 50: Django REST Framework

Após a instalação da biblioteca:

        "pip install djangorestframework"

É necessário adicionar o rest_framework na lista de aplicativos do
projeto:
"""

# Registrar a app e o DRF no projeto
# Abra o arquivo 'loja_api/settings.py' e adicione 'rest_framework' à lista INSTALLED_APPS:

INSTALLED_APPS = [
    ...,
    'rest_framework',
    'produtos',
]

"""
Django REST Framework

O DRF trabalha com Serialização dos objetos:
    a) Criar a Serialização do modelo (api/serializers.py). O Serializer
    converte os dados do banco para JSON e vice-versa
    b) No exemplo abaixo, a classe Produto é importado de outro app
"""

from rest_framework import serializers
from aulaweb.models import Produto

class ProdutoSerialize(serializers.ModelsSerializer):
    class Meta:
        model = Produto
        fields = ['id', 'nome', 'categoria', 'preco', 'disponivel']

# c) A lógica da API implementada da 'views.py' no app api

from rest_framework import viewsets
from .models import Produto
from .serializers import ProdutoSerializer

class ProdutoViewSet(viewsets.ModelViewSet):
    queryset = Produto.objects.all()
    serializer_class = ProdutoSerializer
    
    # Sobrescrevendo o método para listar os produtos para adicionar o filtro
    def get_queryset(self):
        queryset = Produto.objects.all()

        # --- Exemplo 2: Recebendo Parâmetros de Consulta (Query Parameters) ---
        categoria = self.request.query_params.get('categoria')
        if categoria is not None:
            queryset = queryset.filter(categoria__iexact=categoria)
        
        return queryset

"""
d) Configurar a rota no urls.py do aplicativo. O Router do DRF
gera todas as URLs necessárias para o ViewSet automaticamente.
"""

from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import ProdutoViewSet

router = DefaultRouter()
router.register(r'produtos', ProdutoViewSet, basename='produto')

urlpatterns = [
    path('', include(router.urls))
]

from rest_framework.decorators import action

class ProdutoViewSet(viewsets.ModelViewSet):
    # --- NOSSA NOVA AÇÃO PERSONALIZADA ---
    @action(detail=True, methods=['post'])
    def marcar_indisponivel(self, request, pk=None):
        """
        Ação personalizada para marcar um produto como indisponível.
        """

        # Pega o objeto do produto com base no pk (id) da URL
        produto = self.get_object()

        #Altera o estado do produto
        produto.disponivel = False
        produto.save()

        # Retorna uma resposta de sucesso
        return Response({'status': f'O produto "{produto.nome}" foi marcado como indisponível.'})
