using Microsoft.Maui.Controls;

namespace FormularioApp.Views;

public partial class MainPage : ContentPage
{
    public MainPage()
    {
        InitializeComponent();
    }
    
    private async void OnMenuSelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (e.CurrentSelection.FirstOrDefault() is string selectedItem)
        {
            switch (selectedItem)
            {
                case "Contador":
                    await Navigation.PushAsync(new ContadorPage());
                    break;
                case "Cadastro de Pedido":
                    await Navigation.PushAsync(new PedidoPage());
                    break;
                case "Exemplo de Widgets":
                    await Navigation.PushAsync(new WidgetsPage());
                    break;
                case "Cadastro de Usuário":
                    await Navigation.PushAsync(new UsuarioPage());
                    break;
            }
            
            ((CollectionView)sender).SelectedItem = null;
        }
    }
}