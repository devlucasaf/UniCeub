using Microsoft.Maui.Controls;
using Microsoft.Maui.Devices;
using System;

namespace MobileAlinhamentos
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
            AdjustLayoutByOrientation();
            AdjustFontSizeByScreen();
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            AdjustLayoutByOrientation();
        }

        private void AdjustLayoutByOrientation()
        {
            var orientation = DeviceDisplay.Current.MainDisplayInfo.Orientation;
            
            if (orientation == DisplayOrientation.Landscape)
            {
                ImagesContainer.Direction = FlexDirection.Row;
            }
            else
            {
                ImagesContainer.Direction = FlexDirection.Column;
            }
        }

        private void AdjustFontSizeByScreen()
        {
            double screenWidth = DeviceDisplay.Current.MainDisplayInfo.Width / DeviceDisplay.Current.MainDisplayInfo.Density;
            float textSize = (float)(screenWidth * 0.05);
            TitleLabel.FontSize = Math.Max(16, textSize);
            
            int padding = (int)(screenWidth * 0.02);
            TitleLabel.Padding = new Thickness(padding);
        }

        private async void OnImageTapped1(object sender, EventArgs e)
        {
            await DisplayAlert("Detalhes", "Você tocou na imagem 1", "OK");
        }

        private async void OnImageTapped2(object sender, EventArgs e)
        {
            await DisplayAlert("Detalhes", "Você tocou na imagem 2", "OK");
        }

        private async void OnImageTapped3(object sender, EventArgs e)
        {
            await DisplayAlert("Detalhes", "Você tocou na imagem 3", "OK");
        }
    }
}