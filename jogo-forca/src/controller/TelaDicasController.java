package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaDicasController {
    @FXML private Label lblDica;

    // Método para exibir a dica na Label
    public void exibirDica(String dica) {
        lblDica.setText(dica);
    }
}
