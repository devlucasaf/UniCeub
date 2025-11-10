# inicio/urls.p

from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('sobre', views.sobre, name='sobre'),
    path('list_produtos', views.list_produtos, name='list_produtos'),
    path('detalhar_produto/<int:id>', views.detalhar_produto, name='detalhar_produto'),
    path('create_product', views.create_product, name='create_product'),
    path('update_product/<int:id>', views.update_product, name='update_product'),
    path('delete_product/<int:id>', views.delete_product, name='delete_product'),
    path('list_orders', views.list_orders, name='list_orders'),
    path('create_order', views.create_order, name='create_order'),
    path('delete_order/<int:pedido_id>', views.delete_order, name='delete_order'),
]