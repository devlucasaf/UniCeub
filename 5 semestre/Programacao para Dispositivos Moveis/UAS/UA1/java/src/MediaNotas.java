import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaNotas extends JFrame {

    private JTextField inputN1;
    private JTextField inputN2;
    private JLabel resultado;
    private JLabel status;

    public MediaNotas() {
        setTitle("Calculadora de Média");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com margens
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Calculadora de Média");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nota 1
        JLabel labelN1 = new JLabel("Nota 1:");
        labelN1.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelN1.setFont(new Font("Arial", Font.PLAIN, 14));

        inputN1 = new JTextField();
        inputN1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputN1.setFont(new Font("Arial", Font.PLAIN, 14));
        inputN1.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Nota 2
        JLabel labelN2 = new JLabel("Nota 2:");
        labelN2.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelN2.setFont(new Font("Arial", Font.PLAIN, 14));

        inputN2 = new JTextField();
        inputN2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputN2.setFont(new Font("Arial", Font.PLAIN, 14));
        inputN2.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Botão Calcular
        JButton botaoCalcular = new JButton("Calcular Média");
        botaoCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCalcular.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        botaoCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        botaoCalcular.setBackground(new Color(76, 175, 80));
        botaoCalcular.setForeground(Color.WHITE);
        botaoCalcular.setFocusPainted(false);
        botaoCalcular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Botão Limpar
        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoLimpar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLimpar.setBackground(new Color(200, 200, 200));
        botaoLimpar.setForeground(Color.BLACK);
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Label resultado
        resultado = new JLabel("Resultado aparecerá aqui");
        resultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultado.setFont(new Font("Arial", Font.BOLD, 14));

        // Label status
        status = new JLabel(" ");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        status.setFont(new Font("Arial", Font.BOLD, 14));

        // Informações adicionais
        JTextArea info = new JTextArea(
            "Digite duas notas para calcular a média.\n" +
            "A média será exibida junto com o status do aluno.\n" +
            "Este exemplo utiliza eventos e manipulação do DOM com Dart."
        );
        info.setEditable(false);
        info.setBackground(null);
        info.setFont(new Font("Arial", Font.PLAIN, 12));
        info.setForeground(Color.DARK_GRAY);
        info.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Rodapé decorativo
        JPanel decoracao = new JPanel();
        decoracao.setBackground(new Color(76, 175, 80));
        decoracao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 5));
        decoracao.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona componentes ao painel principal
        mainPanel.add(titulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(labelN1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(inputN1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(labelN2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(inputN2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(botaoCalcular);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(botaoLimpar);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(resultado);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(status);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(info);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(decoracao);

        add(mainPanel);

        // Ações dos botões
        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularMedia();
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        // Restaura borda padrão ao digitar
        inputN1.addCaretListener(e -> inputN1.setBorder(UIManager.getBorder("TextField.border")));
        inputN2.addCaretListener(e -> inputN2.setBorder(UIManager.getBorder("TextField.border")));
    }

    private void calcularMedia() {
        String textoN1 = inputN1.getText().trim();
        String textoN2 = inputN2.getText().trim();

        if (textoN1.isEmpty() || textoN2.isEmpty()) {
            resultado.setText("Por favor, insira valores válidos!");
            resultado.setForeground(Color.RED);
            status.setText("");

            return;
        }

        try {
            double n1 = Double.parseDouble(textoN1);
            double n2 = Double.parseDouble(textoN2);

            double media = (n1 + n2) / 2.0;

            resultado.setText(String.format("Média: %.2f", media));
            resultado.setForeground(new Color(0, 128, 0));

            if (media >= 7.0) {
                status.setText("Aprovado");
                status.setForeground(new Color(0, 128, 0));
            } 
            
            else if (media >= 5.0) {
                status.setText("Recuperação");
                status.setForeground(Color.ORANGE);
            } 
            
            else {
                status.setText("Reprovado");
                status.setForeground(Color.RED);
            }
        } 
        
        catch (NumberFormatException ex) {
            resultado.setText("Por favor, insira valores numéricos válidos!");
            resultado.setForeground(Color.RED);
            status.setText("");
        }
    }

    private void limparCampos() {
        inputN1.setText("");
        inputN2.setText("");
        resultado.setText("Resultado aparecerá aqui");
        resultado.setForeground(Color.BLACK);
        status.setText("");
        inputN1.setBorder(UIManager.getBorder("TextField.border"));
        inputN2.setBorder(UIManager.getBorder("TextField.border"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MediaNotas().setVisible(true);
        });
    }
}
