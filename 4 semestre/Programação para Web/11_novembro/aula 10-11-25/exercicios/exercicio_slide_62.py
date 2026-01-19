"""
Programação para Web
Aula: 10-11-2025
Slide 62 - Exercício

Cenário: Você irá construir uma API para um sistema de gerenciamento de projetos. Cada
projeto terá um nome, uma descrição, uma data de início e um status.
Instruções:
Configuração do Projeto Django:
    • Instale as bibliotecas: pip install django djangorestframework
    • Crie um projeto Django chamado gerenciador e uma app chamada projetos.
    • Configure o settings.py para incluir rest_framework и projetos em INSTALLED_APPS.
Passo a Passo da Implementação:
    • a) Criar o Modelo (projetos/models.py):
        • Defina um modelo Projeto com os campos: nome (CharField), descricao (TextField),
        data_inicio (DateField) e status (CharField com escolhas, ex: 'Pendente', 'Em
        Andamento', 'Concluído').
    • b) Criar o Serializer (projetos/serializers.py):
        • Crie um ProjetoSerializer que herde de serializers.ModelSerializer.
        • Na classe Meta, vincule-o ao modelo Projeto e especifique os campos que devem ser
        expostos na API.
Passo a Passo da Implementação:
    • c) Criar a ViewSet (projetos/views.py):
        • Crie uma ProjetoViewSet que herde de viewsets.ModelViewSet.
        • Defina o queryset para buscar todos os objetos Projeto.
        • Defina o serializer_class para usar o ProjetoSerializer.
    • d) Configurar as URLs (gerenciador/urls.py e projetos/urls.py):
        • Use o DefaultRouter do DRF para registrar a ProjetoViewSet. Isso irá gerar
        automaticamente todas as URLs para as operações CRUD (GET, POST, PUT, DELETE).
    • e) Migrar o Banco de Dados:
        • Rode os comandos python manage.py makemigrations e python manage.py migrate.
Como Testar:
    • Inicie o servidor com: python manage.py runserver
    • Abra seu navegador e acesse a API Navegável do DRF, geralmente em
    http://127.0.0.1:8000/api/projetos/ (dependendo de como você configurou suas URLs).
    • Use a interface para:
        • Criar um novo projeto; Listar; Acessar detalhes; Atualizar e Deletar o projeto
"""

# gerenciador/settings.py

"""
Django settings for gerenciador project.
"""

from pathlib import Path

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent.parent

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = 'django-insecure-your-secret-key-here'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True

ALLOWED_HOSTS = []

# Application definition
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'rest_framework',
    'projetos',
]

MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

ROOT_URLCONF = 'gerenciador.urls'

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]

WSGI_APPLICATION = 'gerenciador.wsgi.application'

# Database
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': BASE_DIR / 'db.sqlite3',
    }
}

# Password validation
AUTH_PASSWORD_VALIDATORS = [
    {
        'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',
    },
]

# Internationalization
LANGUAGE_CODE = 'pt-br'
TIME_ZONE = 'America/Sao_Paulo'
USE_I18N = True
USE_TZ = True

# Static files (CSS, JavaScript, Images)
STATIC_URL = 'static/'

# Default primary key field type
DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'

# Django REST Framework configuration
REST_FRAMEWORK = {
    'DEFAULT_PAGINATION_CLASS': 'rest_framework.pagination.PageNumberPagination',
    'PAGE_SIZE': 10
}

# projetos/models.py

from django.db import models

class Projeto(models.Model):
    STATUS_CHOICES = [
        ('Pendente', 'Pendente'),
        ('Em Andamento', 'Em Andamento'),
        ('Concluído', 'Concluído'),
        ('Cancelado', 'Cancelado'),
    ]
    
    nome = models.CharField(max_length=200, verbose_name='Nome do Projeto')
    descricao = models.TextField(verbose_name='Descrição', blank=True)
    data_inicio = models.DateField(verbose_name='Data de Início')
    status = models.CharField(
        max_length=20,
        choices=STATUS_CHOICES,
        default='Pendente',
        verbose_name='Status do Projeto'
    )
    data_criacao = models.DateTimeField(auto_now_add=True, verbose_name='Data de Criação')
    data_atualizacao = models.DateTimeField(auto_now=True, verbose_name='Data de Atualização')
    
    class Meta:
        verbose_name = 'Projeto'
        verbose_name_plural = 'Projetos'
        ordering = ['-data_criacao']
    
    def __str__(self):
        return f"{self.nome} ({self.status})"

# projetos/serializers.py

from rest_framework import serializers
from .models import Projeto

class ProjetoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Projeto
        fields = [
            'id',
            'nome', 
            'descricao',
            'data_inicio',
            'status',
            'data_criacao',
            'data_atualizacao'
        ]
        read_only_fields = ['id', 'data_criacao', 'data_atualizacao']
    
    def validate_nome(self, value):
        """
        Valida se o nome do projeto tem pelo menos 3 caracteres
        """
        if len(value.strip()) < 3:
            raise serializers.ValidationError("O nome do projeto deve ter pelo menos 3 caracteres.")
        return value
    
    def validate_data_inicio(self, value):
        """
        Valida se a data de início não é no futuro (opcional)
        """
        from django.utils import timezone
        if value > timezone.now().date():
            raise serializers.ValidationError("A data de início não pode ser no futuro.")
        return value

# projeto/views.py

from rest_framework import viewsets, filters
from .models import Projeto
from .serializers import ProjetoSerializer

class ProjetoViewSet(viewsets.ModelViewSet):
    """
    ViewSet para operações CRUD de Projetos
    """
    queryset = Projeto.objects.all()
    serializer_class = ProjetoSerializer
    filter_backends = [filters.SearchFilter, filters.OrderingFilter]
    search_fields = ['nome', 'descricao', 'status']
    ordering_fields = ['nome', 'data_inicio', 'status', 'data_criacao']
    ordering = ['-data_criacao']

# projetos/urls.py

from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import ProjetoViewSet

# Cria o router e registra o ViewSet
router = DefaultRouter()
router.register(r'projetos', ProjetoViewSet, basename='projeto')

# As URLs da API são determinadas automaticamente pelo router
urlpatterns = [
    path('api/', include(router.urls)),
]

# gerenciador/urls.py

from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('projetos.urls')),
]

# projetos/admin.py

from django.contrib import admin
from .models import Projeto

@admin.register(Projeto)
class ProjetoAdmin(admin.ModelAdmin):
    list_display = ['nome', 'data_inicio', 'status', 'data_criacao']
    list_filter = ['status', 'data_inicio']
    search_fields = ['nome', 'descricao']
    date_hierarchy = 'data_criacao'
