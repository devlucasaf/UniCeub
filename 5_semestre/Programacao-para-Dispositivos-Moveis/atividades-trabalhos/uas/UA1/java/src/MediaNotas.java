import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaNotas extends JFrame {

    private JTextField  inputNota1;
    private JTextField  inputNota2;
    private JLabel      lblResultado;
    private JLabel      lblStatus;

    public MediaNotas() {
        setTitle("Calculadora de Média");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Calculadora de Média");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nota 1
        JLabel lblNota1 = new JLabel("Nota 1:");
        lblNota1.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblNota1.setFont(new Font("Arial", Font.PLAIN, 14));

        inputNota1 = new JTextField();
        inputNota1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputNota1.setFont(new Font("Arial", Font.PLAIN, 14));
        inputNota1.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Nota 2
        JLabel lblNota2 = new JLabel("Nota 2:");
        lblNota2.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblNota2.setFont(new Font("Arial", Font.PLAIN, 14));

        inputNota2 = new JTextField();
        inputNota2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputNota2.setFont(new Font("Arial", Font.PLAIN, 14));
        inputNota2.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Botão Calcular
        JButton btnCalcularMedia = new JButton("Calcular Média");
        btnCalcularMedia.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCalcularMedia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnCalcularMedia.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalcularMedia.setBackground(new Color(76, 175, 80));
        btnCalcularMedia.setForeground(Color.WHITE);
        btnCalcularMedia.setFocusPainted(false);
        btnCalcularMedia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Botão Limpar
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLimpar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        btnLimpar.setBackground(new Color(200, 200, 200));
        btnLimpar.setForeground(Color.BLACK);
        btnLimpar.setFocusPainted(false);
        btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Label lblResultado
        lblResultado = new JLabel("Resultado aparecerá aqui");
        lblResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));

        // Label lblStatus
        lblStatus = new JLabel(" ");
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

        // Informações adicionais
        JTextArea txtAreaInformacoes = new JTextArea(
                "Digite duas notas para calcular a média.\n" +
                        "A média será exibida junto com o lblStatus do aluno.\n" +
                        "Este exemplo utiliza eventos e manipulação do DOM com Dart."
        );
        txtAreaInformacoes.setEditable(false);
        txtAreaInformacoes.setBackground(null);
        txtAreaInformacoes.setFont(new Font("Arial", Font.PLAIN, 12));
        txtAreaInformacoes.setForeground(Color.DARK_GRAY);
        txtAreaInformacoes.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Rodapé decorativo
        JPanel panelDecoracao = new JPanel();
        panelDecoracao.setBackground(new Color(76, 175, 80));
        panelDecoracao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 5));
        panelDecoracao.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona componentes ao painel principal
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(lblNota1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(inputNota1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(lblNota2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(inputNota2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnCalcularMedia);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnLimpar);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(lblResultado);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(lblStatus);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(txtAreaInformacoes);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(panelDecoracao);

        add(mainPanel);

        // Ações dos botões
        btnCalcularMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularMedia();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        // Restaura borda padrão ao digitar
        inputNota1.addCaretListener(e -> inputNota1.setBorder(UIManager.getBorder("TextField.border")));
        inputNota2.addCaretListener(e -> inputNota2.setBorder(UIManager.getBorder("TextField.border")));
    }

    private void calcularMedia() {
        String txtNota1 = inputNota1.getText().trim();
        String txtNota2 = inputNota2.getText().trim();

        if (txtNota1.isEmpty() || txtNota2.isEmpty()) {
            lblResultado.setText("Por favor, insira valores válidos!");
            lblResultado.setForeground(Color.RED);
            lblStatus.setText("");

            return;
        }

        try {
            double nota1 = Double.parseDouble(txtNota1);
            double nota2 = Double.parseDouble(txtNota2);

            double media = (nota1 + nota2) / 2.0;

            lblResultado.setText(String.format("Média: %.2f", media));
            lblResultado.setForeground(new Color(0, 128, 0));

            if (media >= 7.0) {
                lblStatus.setText("Aprovado");
                lblStatus.setForeground(new Color(0, 128, 0));
            } else if (media >= 5.0) {
                lblStatus.setText("Recuperação");
                lblStatus.setForeground(Color.ORANGE);
            } else {
                lblStatus.setText("Reprovado");
                lblStatus.setForeground(Color.RED);
            }
        } catch (NumberFormatException ex) {
            lblResultado.setText("Por favor, insira valores numéricos válidos!");
            lblResultado.setForeground(Color.RED);
            lblStatus.setText("");
        }
    }

    private void limparCampos() {
        inputNota1.setText("");
        inputNota2.setText("");
        lblResultado.setText("Resultado aparecerá aqui");
        lblResultado.setForeground(Color.BLACK);
        lblStatus.setText("");
        inputNota1.setBorder(UIManager.getBorder("TextField.border"));
        inputNota2.setBorder(UIManager.getBorder("TextField.border"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MediaNotas().setVisible(true);
        });
    }
}
