package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorDeDadosTarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class ControladorTelaCadastroTarefa {

    public static Tarefa tarefa;

    @FXML
    private TextField cadastrarTarefa;

    @FXML
    private TextField cadastrarValorTarefa;

    @FXML
    private Button btnSalvarEditarTarefa;

    @FXML
    public void initialize(){
        if(tarefa == null){
            return;
        }
        cadastrarTarefa.setText(tarefa.getNomeTarefa());
        cadastrarValorTarefa.setText(String.valueOf(tarefa.getValorTarefa()));

        btnSalvarEditarTarefa.setOnAction(actionEvent -> {
            try {
                salvarTarefa();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @FXML
    public void salvarTarefa() throws IOException {
        double valorTarefa = Double.parseDouble(cadastrarValorTarefa.getText());
        Tarefa tarefa = new Tarefa(cadastrarTarefa.getText(), valorTarefa);
        ControladorDeDadosTarefa controladorDeDadosTarefa = new ControladorDeDadosTarefa();
        controladorDeDadosTarefa.criarNovaTarefa(tarefa);

        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }

    @FXML
    protected void voltarTelaListaTarefa() throws IOException {
        System.out.println("Voltar Tela");
        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }

    private void editarTarefa(Tarefa tarefa) throws IOException{
        tarefa.setNomeTarefa(cadastrarTarefa.getText());
        tarefa.setValorTarefa(Double.parseDouble(cadastrarValorTarefa.getText()));

        ControladorDeDadosTarefa controladorDeDadosTarefa = new ControladorDeDadosTarefa();

        if(!controladorDeDadosTarefa.atualizarTarefa(tarefa)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao editar tarefa");
            alerta.setContentText("verifique os seus dados");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("Atualização da tarefa foi feita com sucesso!");
        alerta.setContentText("A atualização foi concluída!");
        alerta.show();

        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }

}
