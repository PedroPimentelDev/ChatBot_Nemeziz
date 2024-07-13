/**
 * NemezizEngine.java
 * 
 * Versão: 2.0
 * Data de Entrega: 17/06/2024
 * 
 * Classe NemezizEngine
 * Contém a lógica do chatbot, gerenciando as interações e respostas.
 * 
 * Desenvolvedores: 
 *  - Pedro Pimentel - RA: 1252415776
 * 
 * Projeto: Nemeziz Chatbot
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.Normalizer;


public class NemezizEngine {
    // Matrizes para armazenar respostas predefinidas e histórico de interações.
    private String[][] respostas;
    private String usuario;

    /*
     * Construtor da classe ChatBotv2
     * Inicializa as matrizes de histórico e respostas e chama o método Respostas para preencher a matriz de respostas.
     */
    public NemezizEngine() {
        respostas = new String[20][2];
        Respostas();
    }

    /**
     * Setter da classe.
     * @param nome atribui valor a váriavel nome.
     */
    public void setUsuario(String nome) {
        this.usuario = nome;
    }

    /*
     * Matriz preenchida com palavras-chave e respostas predefinidas.
     * A coluna 0 contém as palavras-chave e a coluna 1 contém as respostas.
     */
    private void Respostas() {
        respostas[0][0] = "ola,opa";
        respostas[0][1] = "Olá {usuario}! Como está?";

        respostas[1][0] = "adeus,tchau";
        respostas[1][1] = "Adeus {usuario}! Volte sempre que precisar.";

        respostas[2][0] = "bom dia,bomdia";
        respostas[2][1] = "Bom dia {usuario}! Espero que seu dia seja incrível.";

        respostas[3][0] = "boa tarde,boatarde";
        respostas[3][1] = "Boa tarde {usuario}! Espero que seu fim de tarde e noite sejam incríveis.";

        respostas[4][0] = "boa noite,boanoite";
        respostas[4][1] = "Boa noite {usuario}! Espero que você tenha uma ótima noite e um bom descanso.";

        respostas[5][0] = "tempo,clima,como esta";
        respostas[5][1] = "O tempo está ensolarado.";

        respostas[6][0] = "hora";
        respostas[6][1] = "A hora atual é: {hora_atual}";

        respostas[7][0] = "data,dia,mes,ano";
        respostas[7][1] = "A data de hoje é: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        respostas[8][0] = "faq";
        respostas[8][1] = "Perguntas frequentes: \n" + 
        "1. Quem criou você?\n" + 
        "2. Você pode aprender?\n" + 
        "3. Quem é o presidente do Brasil?\n" +
        "4. Quem foi o primeiro presidente dos Estados Unidos?\n" +
        "5. Quantos continentes existem ?\n" +
        "6. Qual é o livro mais vendido do mundo?\n" +
        "7. Qual é a fórmula química da água?\n" +
        "8. Qual é a velocidade da luz?\n" +
        "9. Qual é o animal mais rápido do mundo?\n" +
        "10. Quem descobriu a gravidade?\n";

        respostas[9][0] = "ajuda";
        respostas[9][1] = "Começe perguntando algo, ou digitando faq para ver as perguntas frequentes.";

        respostas[10][0] = "criou,criado,feito,fez";
        respostas[10][1] = "Fui criado por Pedro, Leo, Luiz e Ezequiel em 2024. Com a finalidade de responder perguntas do cotidiano e fornecer informações gerais.";

        respostas[11][0] = "aprender,aprende";
        respostas[11][1] = "Ainda não. Porém com o avançar das aulas do professor Raul, meus criadores irão implementar essa funcionalidade :)";
    
        respostas[12][0] = "presidente do brasil,brasil";
        respostas[12][1] = "O presidente do Brasil atualmente é Luiz Inácio Lula da Silva.";
    
        respostas[13][0] = "estados unidos,eua";
        respostas[13][1] = "O primeiro presidente dos Estados Unidos foi George Washington.";
    
        respostas[14][0] = "continentes";
        respostas[14][1] = "Existem atualmente sete continentes: África, América do Norte, América do Sul, Antártica, Ásia, Europa e Oceania.";
    
        respostas[15][0] = "livro,vendido";
        respostas[15][1] = "O livro mais vendido do mundo é a Bíblia.";
    
        respostas[16][0] = "agua,formula";
        respostas[16][1] = "A fórmula química da água é H2O.";
    
        respostas[17][0] = "velocidade,luz";
        respostas[17][1] = "A velocidade da luz no vácuo é de aproximadamente 299.792 quilômetros por segundo.";
    
        respostas[18][0] = "animal,rapido";
        respostas[18][1] = "O animal mais rápido do mundo é o guepardo, que pode atingir velocidades de até 112 km/h.";
    
        respostas[19][0] = "gravidade";
        respostas[19][1] = "A gravidade foi descoberta pelo Isaac Newton.";
    }
    
    /**
     * Gera uma resposta com base na entrada do usuário.
     * @param entrada Entrada do usuário.
     * @return Resposta do chatbot(coluna de indice 1 da matriz respostas) ou uma mensagem padrão indicando que a entrada não foi compreendida.
     */
    public String gerarResposta(String entrada) {
        // Pré-processamento da entrada.
        entrada = removeAcentos(entrada.toLowerCase());

        // Iterar através das respostas possíveis
        for (String[] resposta : respostas) {
            String[] palavrasChaves = resposta[0].split(",");
            // Checar se alguma palavra-chave corresponde à entrada.
            for (String palavraChave : palavrasChaves) {
                if (entrada.contains(palavraChave.trim())) {
                    String respostaFinal = resposta[1].replace("{usuario}", usuario);
                    if (respostaFinal.contains("{hora_atual}")) {
                        respostaFinal = respostaFinal.replace("{hora_atual}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    }
                    return respostaFinal;
                }
            }
        }
        // Mensagem padrão caso nenhuma resposta seja encontrada.
        return "Desculpe, não entendi sua pergunta. Pode reformular?";
    }

    /**
     * Remove a acentuação de uma string.
     * @param texto Pergunta do usuário.
     * @return Pergunta sem acentuação.
     */
    private String removeAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
