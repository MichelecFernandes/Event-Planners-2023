package br.com.eventplanners.controladorTela;

import br.com.eventplanners.controlador.ControladorDeCena;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControladorTelaPrincipalAdmin {

    @FXML
    protected void cronogramaOrganizador() throws IOException {
        System.out.println("Lista de Pessoas e Tarefas");
        ControladorDeCena.trocarCena("tela-lista-cronograma.fxml");
    }

    @FXML
    protected void controladorCredenciamento() throws IOException {
        System.out.println("Listando pessoas");
        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
    }

    @FXML
    protected void controladorTarefa() throws IOException {
        System.out.println("Listando Tarefas");
        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }

    @FXML
    protected void sair() throws IOException {
        System.out.println("Voltar Tela");
        ControladorDeCena.trocarCena("tela-login.fxml");
    }
}
