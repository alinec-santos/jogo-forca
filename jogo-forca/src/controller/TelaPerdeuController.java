/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aline
 */
public class TelaPerdeuController implements Initializable {
    @FXML
    private Button btnRecomecar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handlebtnRecomecarAction(ActionEvent event) {
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
            Stage stageAtual = (Stage) btnRecomecar.getScene().getWindow();
            stageAtual.close();
        } catch (IOException e) {
            System.err.println("Erro ao voltar para a tela de temas!");
            e.printStackTrace();
        }
    }
  

    void setPalavraCorreta(String palavraAtual) {
    }

    
    
}
