import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraSwingOrientadoEventos extends JFrame implements ActionListener {

    private JTextField display;
    private JPanel painelBotoes;

    private String operador = "";
    private double numero1 = 0;
    private double numero2 = 0;
    private boolean novoNumero = true;

    public CalculadoraSwingOrientadoEventos() {
        setTitle("Calculadora");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField();

        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setText("0");

        add(display, BorderLayout.NORTH);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "", "", ""
        };

        for (String texto : botoes) {
            if (texto.equals("")) {
                painelBotoes.add(new JLabel());
            } 
            
            else {
                JButton botao = new JButton(texto);
                botao.setFont(new Font("Arial", Font.BOLD, 18));
                botao.addActionListener(this);
                painelBotoes.add(botao);
            }
        }

        add(painelBotoes, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9]")) {
            if (novoNumero) {
                display.setText(comando);
                novoNumero = false;
            } 
            
            else {
                display.setText(display.getText() + comando);
            }
        } 
        
        else if (comando.equals(".")) {
            if (novoNumero) {
                display.setText("0.");
                novoNumero = false;
            } 
            
            else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } 
        
        else if (comando.matches("[+\\-*/]")) {
            operador = comando;
            numero1 = Double.parseDouble(display.getText());
            novoNumero = true;
        } 
        
        else if (comando.equals("=")) {
            numero2 = Double.parseDouble(display.getText());
            double resultado = calcular();
            display.setText(formatarResultado(resultado));
            novoNumero = true;
        } 
        
        else if (comando.equals("C")) {
            display.setText("0");
            operador = "";
            numero1 = 0;
            numero2 = 0;
            novoNumero = true;
        }
    }

    private double calcular() {
        switch (operador) {
            case "+":
                return numero1 + numero2;
            case "-":
                return numero1 - numero2;
            case "*":
                return numero1 * numero2;
            case "/":
                if (numero2 == 0) {
                    return 0;
                }
                return numero1 / numero2;
            default:
                return numero2;
        }
    }

    private String formatarResultado(double resultado) {
        if (resultado == (long) resultado) {
            return String.valueOf((long) resultado);
        } 
        
        else {
            return String.valueOf(resultado);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSwing());
    }
}
