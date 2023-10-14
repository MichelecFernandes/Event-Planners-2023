package br.com.eventplanners.controladorTela;

import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaLogin {

    //Responsavel por pegar o valor do campo do usuario
    @FXML
    private TextField inputUsuario;

    @FXML
    private PasswordField inputSenha;

    @FXML
    //Se usuario
    protected void entrarNoSistema() throws IOException {
        String usuario = inputUsuario.getText();
        String senha = inputSenha.getText();

        ControladorLogin controladorLogin = new ControladorLogin();

        if (controladorLogin.entrarNoSistema(usuario,senha)){
            ControladorDeCena.trocarCena("tela-principal-admin.fxml");
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao realizar Login");
            alerta.setContentText("Usuário e/ou senha inválido(s)!");
            alerta.show();
        }
    }
}
