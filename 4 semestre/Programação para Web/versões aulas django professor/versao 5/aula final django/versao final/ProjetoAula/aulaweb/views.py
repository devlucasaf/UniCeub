from django.shortcuts import get_object_or_404, render, redirect
from .models import Produto, Categoria, Pedido, ItemPedido
from .forms import ProdutoForm, PedidoForm, ItemPedidoForm
from django.forms import inlineformset_factory
from django.contrib import messages

# Create your views here.

def index(request):
    lista_produtos = Produto.objects.all().order_by('-preco')
    lista_produtos = lista_produtos[0: (3 if len(lista_produtos) >= 3 else len(lista_produtos))]
    return render(request, 'aulaweb/index.html', {'list_produtos': lista_produtos})

def sobre(request):
    parametros = {
        'title': 'Sobre o Projeto',
        'professor': 'Felippe Pires'
    }
    return render(request, 'aulaweb/sobre.html', parametros)

def list_produtos(request):
    lista_produtos = Produto.objects.all()
    return render(request, 'aulaweb/product/list_products.html', {'lista_produtos': lista_produtos})

def detalhar_produto(request, id):
    produto = None
    msg = ''
    try:
        produto = Produto.objects.get(pk=id)
    except Produto.DoesNotExist:
        msg = 'Produto não encontrado'
        
    return render(request, 'aulaweb/product/detail_product.html', {'produto': produto, 'msg': msg})

def create_product(request):
    if request.method == 'POST':
        form = ProdutoForm(request.POST)
        if form.is_valid():
            form.save()
            messages.success(request, "Produto cadastrado com sucesso!")
            return redirect('list_produtos')  # redireciona para a lista
        else:
            messages.error(request, "Erro ao cadastrar o produto. Verifique os dados informados.")
    else:
        form = ProdutoForm()
    return render(request, 'aulaweb/product/create_product.html', {'form': form})

def update_product(request, id):
    produto = get_object_or_404(Produto, pk=id)
    if request.method == 'POST':
        form = ProdutoForm(request.POST, instance=produto)
        if form.is_valid():
            form.save()
            messages.success(request, "Produto alterado com sucesso!")
            return redirect('list_produtos')
        else:
            messages.error(request, "Erro ao alterar o produto. Verifique os dados informados.")
    else:
        form = ProdutoForm(instance=produto)
    return render(request, 'aulaweb/product/create_product.html', {'form': form, 'editar': True})

def delete_product(request, id):
    try:
        produto = get_object_or_404(Produto, pk=id)
        if request.method == 'POST':
            produto.delete()
            messages.success(request, "Produto deletado com sucesso!")
            return redirect('list_produtos')
    except Produto.DoesNotExist:
        messages.error(request, "Produto não encontrado.")
        return redirect('list_produtos')
    return render(request, 'aulaweb/product/delete_product.html', {'produto': produto})

### ORDER AND ITEM VIEWS ###

def list_orders(request):
    pedidos = Pedido.objects.all()
    return render(request, "aulaweb/order/list_orders.html", {"pedidos": pedidos})

def create_order(request):
    ItemPedidoFormSet = inlineformset_factory(
        Pedido, ItemPedido,
        form=ItemPedidoForm,
        extra=1,  # começa com 1 item vazio
        can_delete=False  # não permite excluir itens no formulário
    )
    
    if request.method == "POST":
        pedido_form = PedidoForm(request.POST)
        formset = ItemPedidoFormSet(request.POST)

        if pedido_form.is_valid() and formset.is_valid():
            pedido = pedido_form.save()

            itens = formset.save(commit=False)
            # liga os itens ao pedido
            for item in itens:
                item.pedido = pedido
                item.save()
            messages.success(request, "Pedido salvo com sucesso!")
            return redirect("list_orders")
    else:
        pedido_form = PedidoForm()
        formset = ItemPedidoFormSet()

    return render(request, "aulaweb/order/create_order.html", {
        "pedido_form": pedido_form,
        "formset": formset
    })

def delete_order(request, pedido_id):
    pedido = get_object_or_404(Pedido, id=pedido_id)
    if request.method == "POST":
        pedido.delete()
        messages.success(request, "Pedido deletado com sucesso!")
        return redirect("list_orders")
    return render(request, "aulaweb/order/delete_order.html", {"pedido": pedido})