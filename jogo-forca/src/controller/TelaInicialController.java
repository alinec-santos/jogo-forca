package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aline
 */
public class TelaInicialController implements Initializable {

    @FXML
    private Button btnJogar;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    // Método acionado ao clicar no botão JOGAR
    @FXML
    private void handleJogarAction(ActionEvent event) {
        try {
            // Carrega o layout da tela de seleção de temas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telaTemas.fxml"));
            Parent root = loader.load();

            // Cria a nova Stage (janela) modal
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Bloqueia a janela anterior
            stage.setTitle("Seleção de Temas"); // Título da janela
            stage.setScene(new Scene(root)); // Define o conteúdo (Scene)
            
            // Exibe a nova janela
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela de seleção de temas.");
        }
    }
}
