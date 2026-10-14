using Microsoft.Maui.Controls;
using csharp.Models;

namespace csharp.Views;

public partial class UsuarioPage : ContentPage
{
    public UsuarioPage()
    {
        InitializeComponent();
        CidadeSpinner.SelectedIndex = 0;
    }
    
    private async void OnEnviarClicked(object sender, EventArgs e)
    {
        var usuario = new Usuario
        {
            Nome            = NomeEditText.Text,
            AceitaTermos    = AceitaTermosCheckBox.IsChecked,
            Genero          = MasculinoRadio.IsChecked ? "Masculino" : "Feminino",
            Cidade          = CidadeSpinner.SelectedItem?.ToString() ?? ""
        };
        
        System.Diagnostics.Debug.WriteLine($"Nome: {usuario.Nome}");
        System.Diagnostics.Debug.WriteLine($"Aceita termos: {usuario.AceitaTermos}");
        System.Diagnostics.Debug.WriteLine($"Gênero: {usuario.Genero}");
        System.Diagnostics.Debug.WriteLine($"Cidade: {usuario.Cidade}");
        
        await DisplayAlert("Sucesso", "Formulário enviado com sucesso!", "OK");
        
        NomeEditText.Text = string.Empty;
        AceitaTermosCheckBox.IsChecked = false;
        MasculinoRadio.IsChecked = true;
        CidadeSpinner.SelectedIndex = 0;
    }
}