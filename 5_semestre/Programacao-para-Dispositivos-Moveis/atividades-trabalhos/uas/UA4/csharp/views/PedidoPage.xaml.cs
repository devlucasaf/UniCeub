using Microsoft.Maui.Controls;

namespace csharp.Views;

public partial class PedidoPage : ContentPage
{
    public PedidoPage()
    {
        InitializeComponent();
        
        SeekBarQuantity.ValueChanged += OnQuantityChanged;
        
        RadioPickup.IsChecked = true;
        
        SpinnerRegion.SelectedIndex = 0;
    }
    
    private void OnQuantityChanged(object sender, ValueChangedEventArgs e)
    {
        int quantity = (int)e.NewValue;
        TextQuantityValue.Text = quantity.ToString();
    }
    
    private async void OnSubmitClicked(object sender, EventArgs e)
    {
        if (string.IsNullOrWhiteSpace(EditProductName.Text))
        {
            await DisplayAlert("Erro", "Por favor, informe o nome do produto", "OK");
            return;
        }
        
        string product = EditProductName.Text.Trim();
        int quantity = (int)SeekBarQuantity.Value;
        
        string deliveryType = "";
        if (RadioPickup.IsChecked) 
        {
            deliveryType = "Retirar na loja";
        }
        else if (RadioDelivery.IsChecked) 
        {
            deliveryType = "Entregar no local";
        }
        else if (RadioMail.IsChecked) 
        {
            deliveryType = "Correios";
        }
        else
        {
            await DisplayAlert("Erro", "Por favor, selecione o tipo de entrega", "OK");
            return;
        }
        
        string region = SpinnerRegion.SelectedItem?.ToString() ?? "";
        bool acceptsPromotions = CheckPromotions.IsChecked;
        
        System.Diagnostics.Debug.WriteLine("=== Dados do pedido ===");
        System.Diagnostics.Debug.WriteLine($"Produto: {product}");
        System.Diagnostics.Debug.WriteLine($"Quantidade: {quantity}");
        System.Diagnostics.Debug.WriteLine($"Tipo de entrega: {deliveryType}");
        System.Diagnostics.Debug.WriteLine($"Região: {region}");
        System.Diagnostics.Debug.WriteLine($"Aceita promoções: {(acceptsPromotions ? "Sim" : "Não")}");
        
        await DisplayAlert("Sucesso", "Pedido cadastrado com sucesso!", "OK");
        
        EditProductName.Text = "";
        SeekBarQuantity.Value = 1;
        RadioPickup.IsChecked = true;
        SpinnerRegion.SelectedIndex = 0;
        CheckPromotions.IsChecked = false;
    }
}