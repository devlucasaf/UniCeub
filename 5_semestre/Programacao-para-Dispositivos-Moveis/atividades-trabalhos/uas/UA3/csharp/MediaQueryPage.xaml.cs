using Microsoft.Maui.Controls;
using Microsoft.Maui.Devices;
using System;

namespace MobileAlinhamentos
{
    public partial class MediaQueryPage : ContentPage
    {
        public MediaQueryPage()
        {
            InitializeComponent();
            UpdateLayout();
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            UpdateLayout();
        }

        private void UpdateLayout()
        {
            var displayInfo = DeviceDisplay.Current.MainDisplayInfo;
            double screenWidthPx = displayInfo.Width;
            double screenHeightPx = displayInfo.Height;
            double density = displayInfo.Density;
            double screenWidthDp = screenWidthPx / density;
            
            var orientation = displayInfo.Orientation;
            string orientationText = orientation == DisplayOrientation.Landscape ? "Paisagem" : "Retrato";
            
            float fontScale = (float)(Preferences.Get("font_scale", 1.0f));
            
            InfoLabel.Text = $"Largura: {screenWidthPx:F0}px ({screenWidthDp:F0}dp)\n" +
                            $"Altura: {screenHeightPx:F0}px\n" +
                            $"Densidade: {density:F2}\n" +
                            $"Orientação: {orientationText}\n" +
                            $"Escala de fonte: {fontScale:F1}";
            
            int horizontalMarginDp = screenWidthDp >= 600 ? 32 : 16;
            double marginPx = horizontalMarginDp * density;
            
            AdaptiveLabel.Margin = new Thickness(marginPx);
            
            AdaptiveLabel.FontSize = 14 * fontScale;
        }
    }
}