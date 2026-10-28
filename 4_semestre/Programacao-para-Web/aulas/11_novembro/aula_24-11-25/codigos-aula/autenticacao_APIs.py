"""
Programação para Web
Aula: 24-11-25
Autenticação em APIs - Slide 70

Para incluir a autenticação ao seu projeto Django é preciso instalar uma
nova biblioteca:

        pip install django djangorestframework-simplejwt

Depois é necessário alterar alguns campos no arquivo settings.py:

"""

INSTALLED_APPS = [
    ...,
    'rest_framework',
    'rest_framework_simplejwt', # Adicione esta linha
]

# Slide 71

from datetime import timedelta

REST_FRAMEWORK = {
    'DEFAULT_AUTHENTICATION_CLASSES': (
        'rest_framework_simplejwt.authentication.JWTAuthentication',
    ),
}

SIMPLE_JWT = {
    'ACCESS_TOKEN_LIFETIME': timedelta(minutes=5),
    'REFRESH_TOKEN_LIFETIME': timedelta(days=1),
    'AUTH_HEADER_TYPES': ('Bearer',),
}

# Slide 72

from django.contrib import admin
from django.urls import path, include
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include('api.urls')),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
]

# Slide 73

from rest_framework import permissions
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes

# Endpoint público para teste
@api_view(['GET'])
@permission_classes([permissions.IsAuthenticated])
def home(request):
    return Response({"mensagem": "Bem-vindo à API pública!"})
