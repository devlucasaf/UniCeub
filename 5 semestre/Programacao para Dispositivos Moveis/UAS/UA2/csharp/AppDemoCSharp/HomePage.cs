// File: HomePage.cs
using System;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Windows.Forms;

namespace AppDemoCSharp
{
    class HomePage : Form
    {
        private int     _counter = 0;
        private Label   _counterLabel;
        private Panel   _appBarPrincipal;
        private Panel   _appBarSecundario;

        public HomePage()
        {
            InitializeComponent();
            SetupLayout();
        }

        private void InitializeComponent()
        {
            this.Text = "App Demo - Fundamentos C#";
            this.Size = new Size(600, 800);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.White;
        }

        private void SetupLayout()
        {
            TableLayoutPanel mainLayout = new TableLayoutPanel
            {
                Dock = DockStyle.Fill,
                Padding = new Padding(16),
                ColumnCount = 1,
                RowCount = 8,
                AutoSize = true
            };

            Panel containerDecorado = CriarContainerDecorado();
            mainLayout.Controls.Add(containerDecorado, 0, 0);

            mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
            mainLayout.Controls.Add(CriarEspacador(20), 0, 1);

            mainLayout.Controls.Add(CriarLabelTitulo("Layout com Row e Column:"), 0, 2);
            mainLayout.Controls.Add(CriarEspacador(8), 0, 3);

            FlowLayoutPanel rowIcons = CriarRowIcons();
            mainLayout.Controls.Add(rowIcons, 0, 4);
            mainLayout.Controls.Add(CriarEspacador(20), 0, 5);

            mainLayout.Controls.Add(CriarLabelTitulo("Expanded ocupando espaço:"), 0, 6);
            mainLayout.Controls.Add(CriarEspacador(8), 0, 7);

            Panel expandedRow = CriarExpandedRow();
            mainLayout.Controls.Add(expandedRow, 0, 8);
            mainLayout.Controls.Add(CriarEspacador(20), 0, 9);

            mainLayout.Controls.Add(CriarLabelTitulo("Exemplo de Stack:"), 0, 10);
            mainLayout.Controls.Add(CriarEspacador(8), 0, 11);

            Panel stackPanel = CriarStackPanel();
            mainLayout.Controls.Add(stackPanel, 0, 12);
            mainLayout.Controls.Add(CriarEspacador(20), 0, 13);

            mainLayout.Controls.Add(CriarLabelTitulo("Contador interativo:"), 0, 14);
            mainLayout.Controls.Add(CriarEspacador(8), 0, 15);

            Panel contadorPanel = CriarContadorPanel();
            mainLayout.Controls.Add(contadorPanel, 0, 16);
            mainLayout.Controls.Add(CriarEspacador(20), 0, 17);

            mainLayout.Controls.Add(CriarLabelTitulo("Widget customizado (MeuAppBar reutilizado no corpo):"), 0, 18);
            mainLayout.Controls.Add(CriarEspacador(8), 0, 19);

            _appBarSecundario = CriarMeuAppBar("Barra customizada dentro do corpo");
            mainLayout.Controls.Add(_appBarSecundario, 0, 20);

            for (int i = mainLayout.RowCount; i <= 20; i++)
            {
                mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
            }

            this.Controls.Add(mainLayout);

            Button fab = new Button
            {
                Text = "+",
                Font = new Font("Segoe UI", 18, FontStyle.Bold),
                Size = new Size(56, 56),
                BackColor = Color.FromArgb(33, 150, 243),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat
            };
            fab.FlatAppearance.BorderSize = 0;
            fab.Click += (s, e) => IncrementCounter();
            fab.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
            fab.Location = new Point(this.ClientSize.Width - fab.Width - 16, this.ClientSize.Height - fab.Height - 16);
            this.Controls.Add(fab);
            this.Resize += (s, e) => fab.Location = new Point(this.ClientSize.Width - fab.Width - 16, this.ClientSize.Height - fab.Height - 16);

            
            _appBarPrincipal = CriarMeuAppBar("App Demo - C# Fundamentos", true);
            this.Controls.Add(_appBarPrincipal);
            _appBarPrincipal.Dock = DockStyle.Top;
            mainLayout.Top = _appBarPrincipal.Height;
            mainLayout.Height = this.ClientSize.Height - _appBarPrincipal.Height;
        }

        private Panel CriarContainerDecorado()
        {
            Panel panel = new Panel
            {
                Height = 100,
                BackColor = Color.FromArgb(227, 242, 253), 
                Margin = new Padding(0, 0, 0, 0)
            };
            
            panel.Paint += (s, e) =>
            {
                GraphicsPath path = new GraphicsPath();
                path.AddArc(0, 0, 20, 20, 180, 90);
                path.AddArc(panel.Width - 20, 0, 20, 20, 270, 90);
                path.AddArc(panel.Width - 20, panel.Height - 20, 20, 20, 0, 90);
                path.AddArc(0, panel.Height - 20, 20, 20, 90, 90);
                panel.Region = new Region(path);
            };
            Label label = new Label
            {
                Text = "Container Decorado",
                Font = new Font("Segoe UI", 20, FontStyle.Bold),
                ForeColor = Color.FromArgb(33, 150, 243),
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Fill
            };
            panel.Controls.Add(label);
            return panel;
        }

        private FlowLayoutPanel CriarRowIcons()
        {
            FlowLayoutPanel flow = new FlowLayoutPanel
            {
                Height = 80,
                FlowDirection = FlowDirection.LeftToRight,
                WrapContents = false,
                Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right
            };
            flow.Controls.Add(CriarIconCard("Curtir", Icons.thumb_up, Color.Green));
            flow.Controls.Add(CriarIconCard("Comentar", Icons.comment, Color.Orange));
            flow.Controls.Add(CriarIconCard("Compartilhar", Icons.share, Color.Blue));
            return flow;
        }

        private Panel CriarIconCard(string texto, char iconCode, Color cor)
        {
            Panel card = new Panel
            {
                Width = 100,
                Height = 70,
                BackColor = Color.White,
                Margin = new Padding(4)
            };
            
            card.Paint += (s, e) =>
            {
                using (Pen pen = new Pen(Color.LightGray, 1))
                {
                    e.Graphics.DrawRectangle(pen, 0, 0, card.Width - 1, card.Height - 1);
                }
            };
            Label iconLabel = new Label
            {
                Text = iconCode.ToString(),
                Font = new Font("Segoe UI", 24),
                ForeColor = cor,
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Top,
                Height = 40
            };
            Label textLabel = new Label
            {
                Text = texto,
                Font = new Font("Segoe UI", 10),
                ForeColor = cor,
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Fill
            };
            card.Controls.Add(textLabel);
            card.Controls.Add(iconLabel);
            return card;
        }

        private Panel CriarExpandedRow()
        {
            Panel container = new Panel
            {
                Height = 80,
                BackColor = Color.FromArgb(240, 240, 240)
            };
            // Left red
            Panel leftRed = new Panel 
            { 
                Width = 50, 
                BackColor = Color.Red, 
                Dock = DockStyle.Left 
            };
            
            Panel rightRed = new Panel 
            { 
                Width = 50, 
                BackColor = Color.Red, 
                Dock = DockStyle.Right 
            };
            
            Label centerLabel = new Label
            {
                Text = "Expanded ocupa o espaço restante",
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Fill
            };
            container.Controls.Add(leftRed);
            container.Controls.Add(rightRed);
            container.Controls.Add(centerLabel);
            return container;
        }

        private Panel CriarStackPanel()
        {
            Panel stack = new Panel
            {
                Height = 120,
                BackColor = Color.FromArgb(144, 202, 249) 
            };
            
            Label centerText = new Label
            {
                Text = "Texto no centro",
                Font = new Font("Segoe UI", 12, FontStyle.Bold),
                BackColor = Color.FromArgb(200, 255, 255, 255),
                AutoSize = true,
                Location = new Point(stack.Width / 2 - 50, stack.Height / 2 - 10)
            };
            
            Label starLabel = new Label
            {
                Text = "★",
                Font = new Font("Segoe UI", 18),
                ForeColor = Color.FromArgb(255, 193, 7),
                AutoSize = true,
                Location = new Point(stack.Width - 30, stack.Height - 30)
            };
            stack.Controls.Add(centerText);
            stack.Controls.Add(starLabel);
            
            stack.Resize += (s, e) =>
            {
                centerText.Location = new Point(stack.Width / 2 - centerText.Width / 2, stack.Height / 2 - centerText.Height / 2);
                starLabel.Location = new Point(stack.Width - starLabel.Width - 8, stack.Height - starLabel.Height - 8);
            };
            return stack;
        }

        private Panel CriarContadorPanel()
        {
            Panel panel = new Panel { Height = 80 };
            _counterLabel = new Label
            {
                Text = "0",
                Font = new Font("Segoe UI", 36, FontStyle.Bold),
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Fill
            };

            Button resetBtn = new Button
            {
                Text = "⟲",
                Font = new Font("Segoe UI", 18),
                Size = new Size(50, 50),
                Location = new Point(10, 15),
                FlatStyle = FlatStyle.Flat,
                ForeColor = Color.Blue
            };
            resetBtn.Click += (s, e) => ResetCounter();

            Button incBtn = new Button
            {
                Text = "+",
                Font = new Font("Segoe UI", 18),
                Size = new Size(50, 50),
                Location = new Point(panel.Width - 60, 15),
                FlatStyle = FlatStyle.Flat,
                ForeColor = Color.Blue
            };
            incBtn.Click += (s, e) => IncrementCounter();
            panel.Controls.Add(resetBtn);
            panel.Controls.Add(incBtn);
            panel.Controls.Add(_counterLabel);
            panel.Resize += (s, e) => incBtn.Location = new Point(panel.Width - 60, 15);
            return panel;
        }

        private Panel CriarMeuAppBar(string titulo, bool isPrincipal = false)
        {
            Panel appBar = new Panel
            {
                Height = 56,
                BackColor = Color.FromArgb(25, 118, 210), 
                Dock = isPrincipal ? DockStyle.Top : DockStyle.Fill,
                Margin = new Padding(0)
            };
            
            Button menuBtn = new Button
            {
                Text = "☰",
                Font = new Font("Segoe UI", 18),
                FlatStyle = FlatStyle.Flat,
                ForeColor = Color.White,
                Size = new Size(48, 48),
                Location = new Point(0, 4),
                Enabled = false 
            };
            menuBtn.FlatAppearance.BorderSize = 0;

            Label titleLabel = new Label
            {
                Text = titulo,
                Font = new Font("Segoe UI", 16, FontStyle.Bold),
                ForeColor = Color.White,
                TextAlign = ContentAlignment.MiddleLeft,
                AutoSize = true,
                Location = new Point(56, (appBar.Height - 20) / 2)
            };
            
            Button searchBtn = new Button
            {
                Text = "🔍",
                Font = new Font("Segoe UI", 16),
                FlatStyle = FlatStyle.Flat,
                ForeColor = Color.White,
                Size = new Size(48, 48),
                Location = new Point(appBar.Width - 48, 4),
                Enabled = false
            };
            searchBtn.FlatAppearance.BorderSize = 0;
            appBar.Controls.Add(menuBtn);
            appBar.Controls.Add(titleLabel);
            appBar.Controls.Add(searchBtn);
            appBar.Resize += (s, e) =>
            {
                searchBtn.Location = new Point(appBar.Width - 48, 4);
                titleLabel.Location = new Point(56, (appBar.Height - titleLabel.Height) / 2);
            };
            return appBar;
        }

        private Label CriarLabelTitulo(string texto)
        {
            return new Label
            {
                Text = texto,
                Font = new Font("Segoe UI", 12, FontStyle.Bold),
                AutoSize = true
            };
        }

        private Panel CriarEspacador(int altura)
        {
            return new Panel 
            { 
                Height = altura 
            };
        }

        private void IncrementCounter()
        {
            _counter++;
            _counterLabel.Text = _counter.ToString();
        }

        private void ResetCounter()
        {
            _counter = 0;
            _counterLabel.Text = "0";
        }
    }
}