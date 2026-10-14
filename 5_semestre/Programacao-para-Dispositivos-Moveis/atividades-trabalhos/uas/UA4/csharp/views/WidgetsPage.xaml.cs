using Microsoft.Maui.Controls;

namespace csharp.Views;

public partial class WidgetsPage : ContentPage
{
    public WidgetsPage()
    {
        InitializeComponent();
        
        SwitchWifi.Toggled += OnSwitchToggled;
        SpinnerPaises.SelectedIndexChanged += OnSpinnerChanged;
        
        RadioOpcaoA.IsChecked = true;
        SpinnerPaises.SelectedIndex = 0;
    }
    
    private void OnSwitchToggled(object sender, ToggledEventArgs e)
    {
        string status = e.Value ? "ligado" : "desligado";
        DisplayAlert("Switch", $"Wi-Fi {status}", "OK");
    }
    
    private void OnRadioChanged(object sender, CheckedChangedEventArgs e)
    {
        if (e.Value)
        {
            string escolha = (sender as RadioButton)?.Content?.ToString() ?? "";
            DisplayAlert("Seleção", $"Opção escolhida: {escolha}", "OK");
        }
    }
    
    private async void OnSpinnerChanged(object sender, EventArgs e)
    {
        if (SpinnerPaises.SelectedIndex != -1)
        {
            string pais = SpinnerPaises.SelectedItem?.ToString() ?? "";
            await DisplayAlert("Seleção", $"País selecionado: {pais}", "OK");
        }
    }
}