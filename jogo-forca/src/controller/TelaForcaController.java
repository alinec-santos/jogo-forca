package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class TelaForcaController implements Initializable {

    @FXML private Button btnVoltar;
    @FXML private Button btnDicas;
    @FXML private Label lblPalavraEscondida;
    @FXML private Label lblNomeTema;
    @FXML  Label lblErros;
    @FXML private GridPane gridAlfabeto;
    
    @FXML private ImageView imgCabeca;
    @FXML private ImageView imgcorpo;
    @FXML private ImageView imgbracoesq;
    @FXML private ImageView imgbracodir;
    @FXML private ImageView imgpernadir;
    @FXML private ImageView imgpernaesq;
    
    private String palavraAtual;
    private String dicaAtual;
    private StringBuilder palavraEscondida;
    private int erros;
    private static final int MAX_ERROS = 6;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialmente, esconder todas as partes do boneco
        imgCabeca.setVisible(false);
        imgcorpo.setVisible(false);
        imgbracoesq.setVisible(false);
        imgbracodir.setVisible(false);
        imgpernadir.setVisible(false);
        imgpernaesq.setVisible(false);
        btnDicas.setDisable(true);
        
        // Configurar eventos para todos os botões do alfabeto
        configurarBotoesAlfabeto();
    }
    
    public void inicializarPalavra(String palavra, String dica, String tema) {
        this.palavraAtual = palavra.toUpperCase();
        this.dicaAtual = dica;
        this.erros = 0;

        // Atualizar o tema
        lblNomeTema.setText(tema);

        // Inicializar a palavra escondida com traços
        palavraEscondida = new StringBuilder();
        for (int i = 0; i < palavraAtual.length(); i++) {
            palavraEscondida.append(palavraAtual.charAt(i) == ' ' ? ' ' : '_');
        }

        // Atualizar labels
        lblPalavraEscondida.setText(String.join(" ", palavraEscondida.toString().split("")));
        lblErros.setText("0/" + MAX_ERROS);
    }
    
    private void configurarBotoesAlfabeto() {
        // Percorrer todos os botões do GridPane
        gridAlfabeto.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button btn = (Button) node;
                btn.setOnAction(e -> tentarLetra(btn));
            }
        });
    }
    
    private void tentarLetra(Button btn) {
        if (palavraAtual == null) return;
        
        char letra = btn.getText().charAt(0);
        boolean acertou = false;
        
        // Verificar se a letra existe na palavra
        for (int i = 0; i < palavraAtual.length(); i++) {
            if (palavraAtual.charAt(i) == letra) {
                palavraEscondida.setCharAt(i, letra);
                acertou = true;
            }
        }
        
        // Atualizar a palavra escondida
        lblPalavraEscondida.setText(String.join(" ", palavraEscondida.toString().split("")));
        
        // Desabilitar o botão após o uso
        btn.setDisable(true);
        
        if (!acertou) {
            erros++;
            lblErros.setText(erros + "/" + MAX_ERROS);
            mostrarParteBoneco(erros);

            // Habilitar o botão de dicas quando atingir 5 erros
            if (erros >= 5) {
                btnDicas.setDisable(false);
            }

            if (erros >= MAX_ERROS) {
                finalizarJogo(false);
            }
        }
        
        // Verificar se ganhou
        if (!palavraEscondida.toString().contains("_")) {
            finalizarJogo(true);
        }
    }
    
    private void mostrarParteBoneco(int erros) {
        switch (erros) {
            case 1: imgCabeca.setVisible(true); break;
            case 2: imgcorpo.setVisible(true); break;
            case 3: imgbracoesq.setVisible(true); break;
            case 4: imgbracodir.setVisible(true); break;
            case 5: imgpernaesq.setVisible(true); break;
            case 6: imgpernadir.setVisible(true); break;
        }
    }
    
    private void finalizarJogo(boolean ganhou) {
    // Desabilitar todos os botões
    gridAlfabeto.getChildren().forEach(node -> {
        if (node instanceof Button) {
            node.setDisable(true);
        }
    });
    
    try {
        // Determinar qual tela carregar baseado no resultado
        String telaFXML = ganhou ? "/view/telaGanhou.fxml" : "/view/telaPerdeu.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(telaFXML));
        Parent root = loader.load();
        
        // Se for a tela de derrota, passar a palavra correta
        if (!ganhou) {
            TelaPerdeuController perdeuController = loader.getController();
            perdeuController.setPalavraCorreta(palavraAtual);
        }
        
        // Configurar e exibir a nova tela
        Stage stage = new Stage();
        stage.setTitle(ganhou ? "Parabéns!" : "Game Over");
        stage.setScene(new Scene(root));
        stage.show();
        
        // Fechar a tela atual
        Stage stageAtual = (Stage) btnVoltar.getScene().getWindow();
        stageAtual.close();
        
    } catch (IOException e) {
        System.err.println("Erro ao abrir tela de resultado!");
        e.printStackTrace();
    }
}
    
   @FXML
    private void handleactionbtnDicas() {
        // Verificar se o número de erros é maior ou igual a 5
        if (erros >= 5) {
            try {
                // Carregar o FXML da tela de Dicas
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telaDicas.fxml"));
                Parent root = loader.load();

                // Passar a dica para o controlador da Tela de Dicas
                TelaDicasController dicasController = loader.getController();
                dicasController.exibirDica(dicaAtual);

                // Configurar e exibir a nova tela
                Stage stage = new Stage();
                stage.setTitle("Dica");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (IOException e) {
                System.err.println("Erro ao abrir a tela de dicas!");
                e.printStackTrace();
            }
        } else {
            // Opcional: Mostrar uma mensagem informando que a dica ainda não está disponível
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dica não disponível");
            alert.setHeaderText(null);
            alert.setContentText("A dica só estará disponível após 5 erros!");
            alert.showAndWait();
        }
    }

    
    @FXML
    private void handleactionbtnVoltar() {
        try {
            // Carregar a tela de temas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telaTemas.fxml"));
            Parent root = loader.load();
            
            // Configurar e exibir a nova tela
            Stage stage = new Stage();
            stage.setTitle("Temas");
            stage.setScene(new Scene(root));
            stage.show();
            
            // Fechar a tela atual
            Stage stageAtual = (Stage) btnVoltar.getScene().getWindow();
            stageAtual.close();
        } catch (IOException e) {
            System.err.println("Erro ao voltar para a tela de temas!");
            e.printStackTrace();
        }
    }
}