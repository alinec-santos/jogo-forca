package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JogoDaForca extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carregue o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
        Parent root = loader.load();
        
        // Crie a cena
        Scene scene = new Scene(root);
        
        // Adicione o CSS, se necessário
        scene.getStylesheets().add(getClass().getResource("/css/telainicial.css").toExternalForm());
        
        // Configure o Stage (janela)
        primaryStage.setTitle("Jogo da Forca");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Inicia o JavaFX
    }
}
