package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TelaTemasController {

    @FXML private CheckBox cbAnimais;
    @FXML private CheckBox cbAlimentos;
    @FXML private CheckBox cbDiversos;
    @FXML private CheckBox cbEsportes;
    @FXML private CheckBox cbNovelas;
    @FXML private CheckBox cbObjetos;
    @FXML private CheckBox cbPC;
    @FXML private CheckBox cbProfissoes;

    @FXML private Button btnIniciar;

    private static final String CAMINHO_TEMAS = "C:/Users/aline/OneDrive/Documentos/facul/4 semestre/poo/tp/Jogo da Forca/src/temas/"; // Mudar caminho de acordo com o seu 
    @FXML
    private void handleactionbtnIniciar() {
        // Obtem os temas selecionados
        List<String> temasSelecionados = obterTemasSelecionados();

        if (temasSelecionados.isEmpty()) {
            System.out.println("Nenhum tema selecionado!");
            return;
        }

        // Carregar palavras associadas aos temas
        Map<String[], String> palavrasComTemas = carregarPalavrasDosTemas(temasSelecionados);

        if (palavrasComTemas.isEmpty()) {
            System.out.println("Nenhuma palavra encontrada nos arquivos selecionados!");
            return;
        }

        // Sortear uma palavra
        List<String[]> palavras = new ArrayList<>(palavrasComTemas.keySet());
        Random random = new Random();
        String[] palavraESuaDica = palavras.get(random.nextInt(palavras.size()));
        String nomeTema = palavrasComTemas.get(palavraESuaDica); // Tema associado

        // Debug: Exibe palavra, dica e tema
        System.out.println("Palavra: " + palavraESuaDica[0] + ", Dica: " + palavraESuaDica[1] + ", Tema: " + nomeTema);

        // Abrir tela da Forca
        abrirTelaForca(palavraESuaDica[0], palavraESuaDica[1], nomeTema);
    }

    private List<String> obterTemasSelecionados() {
        List<String> temas = new ArrayList<>();
        if (cbAnimais.isSelected()) temas.add("Animais");
        if (cbAlimentos.isSelected()) temas.add("Alimentos");
        if (cbDiversos.isSelected()) temas.add("Diversos");
        if (cbEsportes.isSelected()) temas.add("Esportes");
        if (cbNovelas.isSelected()) temas.add("Novelas");
        if (cbObjetos.isSelected()) temas.add("Objetos");
        if (cbPC.isSelected()) temas.add("Pc");
        if (cbProfissoes.isSelected()) temas.add("Profissoes");
        return temas;
    }

    private Map<String[], String> carregarPalavrasDosTemas(List<String> temasSelecionados) {
        Map<String[], String> palavrasComTemas = new HashMap<>();

        for (String tema : temasSelecionados) {
            String caminhoArquivo = CAMINHO_TEMAS + tema + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String nomeTema = br.readLine(); // Lê a primeira linha do arquivo (nome do tema)
                if (nomeTema == null) continue;

                palavrasComTemas.putAll(
                    br.lines()
                      .map(linha -> linha.split(";"))
                      .filter(partes -> partes.length == 2)
                      .collect(Collectors.toMap(partes -> partes, partes -> nomeTema))
                );
            } catch (IOException e) {
                System.err.println("Erro ao carregar o arquivo do tema: " + tema);
                e.printStackTrace();
            }
        }
        return palavrasComTemas;
    }

    private void abrirTelaForca(String palavra, String dica, String tema) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telaForca.fxml"));
            Parent root = loader.load();

            TelaForcaController forcaController = loader.getController();
            forcaController.inicializarPalavra(palavra, dica, tema);

            Stage stage = new Stage();
            stage.setTitle("Jogo da Forca");
            stage.setScene(new Scene(root));
            stage.show();

            Stage stageAtual = (Stage) btnIniciar.getScene().getWindow();
            stageAtual.close();
        } catch (IOException e) {
            System.err.println("Erro ao abrir a tela da Forca!");
            e.printStackTrace();
        }
    }
}
