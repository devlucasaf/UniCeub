using Microsoft.Maui.Controls;
using Microsoft.Maui.Hosting;

namespace MobileAlinhamentos
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();
            MainPage = new NavigationPage(new MainPage());
        }
        
        protected override void OnStart()
        {
        }
        
        protected override void OnSleep()
        {
        }
        
        protected override void OnResume()
        {
        }
    }
}