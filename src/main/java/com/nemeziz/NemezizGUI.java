/**
 * NemezizGUI.java
 * 
 * Versão: 2.0
 * Data de Entrega: 17/06/2024
 * 
 * Classe NemezizGUI 
 * Implementa a interface gráfica e instancia toda a lógica do chatbot.
 * 
 * Desenvolvedores: 
 *  - Pedro Pimentel - RA: 1252415776
 * 
 * Projeto: Nemeziz Chatbot
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;


public class NemezizGUI {

    // Declaração dos componentes da GUI e váriaveis.
    private JFrame frame;
    private JTextPane textPane;
    private JTextField textField;
    private NemezizEngine nemeziz;
    private boolean solicitandoNome = true;

    /*
     * Construtor que inicializa a interface gráfica e configura os componentes.
     */
    public NemezizGUI() {
        // Instância do ChatBot
        nemeziz = new NemezizEngine();

        // Configuração da janela principal
        frame = new JFrame("Nemeziz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(459, 520);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        // Configuração da área de texto onde as mensagens são exibidas.
        textPane = new JTextPane();
        textPane.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane.setEditable(false);

        // Scroll pane que contém o JTextPane, permitindo rolagem automática. E ajuste de posição e tamanho.
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 11, 423, 412);
        frame.getContentPane().add(scrollPane);

        // Campo de texto para entrada do usuário e definição de posição e tamanho.
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBounds(10, 434, 351, 32);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        // Botão para enviar mensagens e definição de posição e tamanho.
        JButton botaoEnviar = new JButton("✔️");
        botaoEnviar.setHorizontalAlignment(SwingConstants.TRAILING);
        botaoEnviar.setBounds(371, 434, 62, 32);
        frame.getContentPane().add(botaoEnviar);

        // Adiciona listeners para o botão de envio, campo de texto.
        botaoEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                entradaUsuario();
            }
        });
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                entradaUsuario();
            }
        });

        // Mensagem de boas-vindas
        colocarNegrito("Nemeziz: ");
        colocarTextoNormal("Seja bem-vindo(a) ao Nemeziz! Qual o seu nome?\n");

        // Torna a janela visível
        frame.setVisible(true);
    }

    /*
     * Método para tratar a entrada do usuário, definir seu nome de usuario e gerar
     * a resposta da sua pergunta com estilo personalizado.
     */
    private void entradaUsuario() {
        String userInput = textField.getText().trim();
        if (userInput.isEmpty()) {
            return;
        }

        textField.setText("");
        colocarNegrito("\nVocê: ");
        colocarTextoNormal(userInput + "\n");

        if (solicitandoNome) {
            // Permite apenas letras e espaços, e define o nome do usuário.
            if (userInput.matches("^[\\p{L}\\s]+$")) {
                nemeziz.setUsuario(userInput);
                solicitandoNome = false;
                colocarNegrito("Nemeziz: ");
                colocarTextoNormal("Olá, " + userInput + "! Como posso ajudar você?\n");
            } else {
                colocarTextoNormal("Por favor, insira apenas letras.\n");
            }
        } else {
            // Caso não esteja solicitando o nome do usário, ele gera uma resposta para sua
            // pergunta.
            String resposta = nemeziz.gerarResposta(userInput);
            colocarNegrito("Nemeziz: ");
            colocarTextoNormal(resposta + "\n");
        }
    }

    /**
     * Método auxiliar para adicionar texto em negrito ao JTextPane.
     * Com uma estrutura try-catch para evitar qualquer interrupção por erro.
     * @param text O texto a ser formatado em negrito.
     */
    private void colocarNegrito(String text) {
        try {
            StyledDocument doc = textPane.getStyledDocument();
            Style style = textPane.addStyle("BoldStyle", null);
            StyleConstants.setBold(style, true);
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para adicionar texto normal ao JTextPane.
     * Com uma estrutura try-catch para evitar qualquer interrupção por erro.
     * @param text O texto a ser adicionado sem formatação especial.
     */
    private void colocarTextoNormal(String text) {
        try {
            StyledDocument doc = textPane.getStyledDocument();
            Style style = textPane.addStyle("RegularStyle", null);
            StyleConstants.setBold(style, false);
            doc.insertString(doc.getLength(), text, style);
            // Assegura que o scroll se mova para a posição do texto mais recente.
            textPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método main para iniciar a aplicação.
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NemezizGUI();
            }
        });
    }
}
