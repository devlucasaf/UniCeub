using Microsoft.Maui.Controls;
using Microsoft.Maui.Devices;
using System;

namespace MobileAlinhamentos
{
    public partial class ResponsiveLayoutPage : ContentPage
    {
        public ResponsiveLayoutPage()
        {
            InitializeComponent();
            BuildLayout();
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            BuildLayout();
        }

        private void BuildLayout()
        {
            RootLayout.Children.Clear();
            
            double screenWidthDp = DeviceDisplay.Current.MainDisplayInfo.Width / 
                                    DeviceDisplay.Current.MainDisplayInfo.Density;
            bool isMobile = screenWidthDp < 600;

            var toolbar = new Grid
            {
                HeightRequest = 56,
                BackgroundColor = Colors.Gray,
                Children = { new Label { Text = "Exemplo de Layout Flutter", 
                                        VerticalOptions = LayoutOptions.Center,
                                        HorizontalOptions = LayoutOptions.Center,
                                        TextColor = Colors.White } }
            };

            if (isMobile)
            {
                var contentLayout = new VerticalStackLayout();
                contentLayout.Add(HeaderView());
                contentLayout.Add(new BoxView { HeightRequest = 20 });
                
                var contentView = new ContentView
                {
                    BackgroundColor = Colors.LightGray,
                    Content = new Label 
                    { 
                        Text = "Conteúdo Principal",
                        HorizontalOptions = LayoutOptions.Center,
                        VerticalOptions = LayoutOptions.Center
                    }
                };
                contentLayout.Add(contentView, new VerticalStackLayout().Children);
                contentLayout.Add(new BoxView { HeightRequest = 20 });
                contentLayout.Add(FooterView());
                
                RootLayout.RowDefinitions.Clear();
                RootLayout.RowDefinitions.Add(new RowDefinition(GridLength.Auto));
                RootLayout.RowDefinitions.Add(new RowDefinition(GridLength.Star));
                RootLayout.Children.Add(toolbar);
                RootLayout.Children.Add(contentLayout, 0, 1);
            }
            else
            {
                var contentLayout = new Grid
                {
                    ColumnDefinitions = new ColumnDefinitionCollection
                    {
                        new ColumnDefinition(GridLength.Star),
                        new ColumnDefinition(GridLength.Auto),
                        new ColumnDefinition(GridLength.Star)
                    }
                };
                
                var contentView = new ContentView
                {
                    BackgroundColor = Colors.LightGray,
                    Content = new Label 
                    { 
                        Text = "Conteúdo Principal",
                        HorizontalOptions = LayoutOptions.Center,
                        VerticalOptions = LayoutOptions.Center
                    }
                };
                contentLayout.Add(contentView, 0, 0);
                
                var rightColumn = new VerticalStackLayout();
                rightColumn.Add(HeaderView());
                rightColumn.Add(new BoxView { HeightRequest = 20 });
                rightColumn.Add(FooterView());
                contentLayout.Add(rightColumn, 2, 0);
                
                RootLayout.RowDefinitions.Clear();
                RootLayout.RowDefinitions.Add(new RowDefinition(GridLength.Auto));
                RootLayout.RowDefinitions.Add(new RowDefinition(GridLength.Star));
                RootLayout.Children.Add(toolbar);
                RootLayout.Children.Add(contentLayout, 0, 1);
            }
        }

        private ContentView HeaderView()
        {
            return new ContentView
            {
                Content = new Label
                {
                    Text = "Cabeçalho",
                    TextColor = Colors.White,
                    FontSize = 18,
                    BackgroundColor = Colors.Blue,
                    Padding = 16,
                    HorizontalOptions = LayoutOptions.Fill
                }
            };
        }

        private ContentView FooterView()
        {
            return new ContentView
            {
                Content = new Label
                {
                    Text = "Rodapé",
                    TextColor = Colors.White,
                    FontSize = 14,
                    BackgroundColor = Colors.Black,
                    Padding = 16,
                    HorizontalOptions = LayoutOptions.Fill
                }
            };
        }
    }
}