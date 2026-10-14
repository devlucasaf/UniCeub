using MeuAppMaui.ViewModels;
using Microsoft.Maui.Controls;

namespace MeuAppMaui.Views;

public partial class MainPage : ContentPage
{
    public MainPage(MainViewModel viewModel)
    {
        InitializeComponent();
        BindingContext = viewModel;
    }
}