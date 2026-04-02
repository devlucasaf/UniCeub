using System;
using System.Drawing;
using System.Windows.Forms;

namespace CalculadoraApp
{
    public class CalculadoraForm : Form
    {
        private TextBox display;

        private double numero1 = 0;
        private double numero2 = 0;
        private string operador = "";
        private bool novoNumero = true;

        public CalculadoraForm()
        {
            this.Text = "Calculadora";
            this.Size = new Size(350, 500);
            this.StartPosition = FormStartPosition.CenterScreen;

            display = new TextBox();
            display.Font = new Font("Arial", 24, FontStyle.Bold);
            display.TextAlign = HorizontalAlignment.Right;
            display.ReadOnly = true;
            display.Text = "0";
            display.Dock = DockStyle.Top;
            display.Height = 60;

            this.Controls.Add(display);

            TableLayoutPanel painel = new TableLayoutPanel();

            painel.RowCount = 5;
            painel.ColumnCount = 4;
            painel.Dock = DockStyle.Fill;

            for (int i = 0; i < 5; i++)
            {
                painel.RowStyles.Add(new RowStyle(SizeType.Percent, 20F));
            }

            for (int i = 0; i < 4; i++)
            {
                painel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 25F));
            }

            string[,] botoes = {
                { "7", "8", "9", "/" },
                { "4", "5", "6", "*" },
                { "1", "2", "3", "-" },
                { "0", ".", "=", "+" },
                { "C", "", "", "" }
            };

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    string texto = botoes[i, j];

                    if (texto == "")
                    {
                        painel.Controls.Add(new Label(), j, i);
                    }

                    else
                    {
                        Button btn = new Button();
                        btn.Text = texto;
                        btn.Font = new Font("Arial", 14, FontStyle.Bold);
                        btn.Dock = DockStyle.Fill;
                        btn.Click += BotaoClick;
                        painel.Controls.Add(btn, j, i);
                    }
                }
            }

            this.Controls.Add(painel);
        }

        private void BotaoClick(object sender, EventArgs e)
        {
            string comando = ((Button)sender).Text;

            if (char.IsDigit(comando, 0))
            {
                if (novoNumero)
                {
                    display.Text = comando;
                    novoNumero = false;
                }

                else
                {
                    display.Text += comando;
                }
            }

            else if (comando == ".")
            {
                if (novoNumero)
                {
                    display.Text = "0.";
                    novoNumero = false;
                }

                else if (!display.Text.Contains("."))
                {
                    display.Text += ".";
                }
            }

            else if (comando == "+" || comando == "-" || comando == "*" || comando == "/")
            {
                operador = comando;
                numero1 = double.Parse(display.Text);
                novoNumero = true;
            }

            else if (comando == "=")
            {
                numero2 = double.Parse(display.Text);
                double resultado = Calcular();
                display.Text = Formatar(resultado);
                novoNumero = true;
            }
            
            else if (comando == "C")
            {
                display.Text = "0";
                operador = "";
                numero1 = 0;
                numero2 = 0;
                novoNumero = true;
            }
        }

        private double Calcular()
        {
            if (operador == "+")
            {
                return numero1 + numero2;
            }
            
            if (operador == "-")
            {
                return numero1 - numero2;
            }
            
            if (operador == "*")
            {
                return numero1 * numero2;
            }

            if (operador == "/")
            {
                if (numero2 == 0)
                {
                    return 0;
                }
                return numero1 / numero2;
            }
            return numero2;
        }

        private string Formatar(double valor)
        {
            if (valor == (long)valor)
            {
                return ((long)valor).ToString();
            }
            return valor.ToString();
        }

        [STAThread]
        public static void Main()
        {
            Application.EnableVisualStyles();
            Application.Run(new CalculadoraForm());
        }
    }
}