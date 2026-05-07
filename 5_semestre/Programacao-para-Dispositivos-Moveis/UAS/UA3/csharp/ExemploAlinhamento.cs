using Microsoft.Maui.Controls;

namespace MobileAlinhamentos
{
    public class ExemploAlinhamento : ContentView
    {
        public static readonly BindableProperty TituloProperty =
            BindableProperty.Create(nameof(Titulo), typeof(string), typeof(ExemploAlinhamento), string.Empty);

        public string Titulo
        {
            get => (string)GetValue(TituloProperty);
            set => SetValue(TituloProperty, value);
        }

        public ExemploAlinhamento()
        {
            var frame = new Frame
            {
                BorderColor = Colors.Gray,
                CornerRadius = 8,
                Padding = 10,
                Margin = new Thickness(0, 0, 0, 10)
            };

            var layout = new VerticalStackLayout();
            
            var tituloLabel = new Label
            {
                FontSize = 16,
                TextColor = Colors.Black,
                Margin = new Thickness(0, 0, 0, 10)
            };
            tituloLabel.SetBinding(Label.TextProperty, new Binding(nameof(Titulo), source: this));
            
            layout.Children.Add(tituloLabel);
            
            var contentLayout = new ContentView();
            contentLayout.SetBinding(ContentView.ContentProperty, new Binding(nameof(Content), source: this));
            contentLayout.BackgroundColor = Colors.LightGray;
            contentLayout.Margin = new Thickness(0);
            
            layout.Children.Add(contentLayout);
            frame.Content = layout;
            Content = frame;
        }
    }
}