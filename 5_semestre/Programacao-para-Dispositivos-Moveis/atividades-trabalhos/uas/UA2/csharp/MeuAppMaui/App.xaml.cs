using Microsoft.Maui.Controls;

namespace MeuAppMaui;

public partial class App : Application
{
    public App()
    {
        InitializeComponent();
        MainPage = new AppShell();
    }
}