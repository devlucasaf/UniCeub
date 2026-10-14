using Microsoft.Maui.Controls;
using Microsoft.Maui.Storage;

namespace MobileAlinhamentos
{
    public partial class PostPage : ContentPage
    {
        public PostPage()
        {
            InitializeComponent();
            LoadImages();
        }

        private async void LoadImages()
        {
            BannerImage.Source = new UriImageSource
            {
                Uri = new System.Uri("https://images.unsplash.com/photo-1518770660439-4636190af475"),
                CachingEnabled = true,
                CacheValidity = new System.TimeSpan(5, 0, 0, 0)
            };

            IconImage.Source = new UriImageSource
            {
                Uri = new System.Uri("https://cdn-icons-png.flaticon.com/512/732/732212.png")
            };
        }
    }
}