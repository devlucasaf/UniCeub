from .models import *
from django.shortcuts import render

from .serializers import AutorSerializer, EditoraSerializer, LivroSerializer
from rest_framework import viewsets, filters


class AutorViewSet(viewsets.ModelViewSet):
    queryset= Autor.objects.all()
    serializer_class = AutorSerializer

    def ver_autor(self):
        queryset= Autor.objects.all()

        nome = self.request.query_params.get('nome')
        if nome is not None:
            queryset = queryset.filter(nome__iexact = nome)
        
        return queryset

class EditoraViewSet(viewsets.ModelViewSet):
    queryset= Editora.objects.all()
    serializer_class = EditoraSerializer

    def ver_autor(self):
        queryset= Editora.objects.all()

        nome = self.request.query_params.get('nome')
        if nome is not None:
            queryset = queryset.filter(nome__iexact = nome)
        
        return queryset

class LivroViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Livro.objects.all()
    serializer_class = LivroSerializer
    filter_backends = [filters.SearchFilter]
    search_fields = ['nome', 'autor__nome', 'editora__nome', 'genero', 'isbn']
    
