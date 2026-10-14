using Microsoft.Maui.Controls;

namespace csharp;

public partial class App : Application
{
    public App()
    {
        InitializeComponent();
        MainPage = new NavigationPage(new Views.MainPage());
    }
}