from rest_framework import serializers

from catalog.models import Autor, Livro, Editora

class AutorSerializer(serializers.ModelSerializer):

    class Meta:
        model = Autor 
        fields = ['nome']  

class EditoraSerializer(serializers.ModelSerializer):

    class Meta:
        model = Editora
        fields = ['nome']

class LivroSerializer(serializers.ModelSerializer):

    class Meta:
        model = Livro
        fields = ['nome','autor','editora','genero','isbn']
    
    autor = AutorSerializer(read_only=True)
    editora = EditoraSerializer(read_only=True)