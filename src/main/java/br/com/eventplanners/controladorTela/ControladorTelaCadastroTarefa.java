package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorDeDadosTarefa;
import javafx.fxml.FXML;
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

//    @FXML
//    public void initialize(){
//        if(tarefa == null){
//            btnSalvarEditarTarefa.setOnAction(actionEvent -> {
//                try {
//                    salvarTarefa();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            return;
//        }
//        btnSalvarEditarTarefa.setOnAction(actionEvent -> {
//            try {
//                salvarTarefa();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        cadastrarTarefa.setText(tarefa.getNomeTarefa());
//        cadastrarValorTarefa.setText(String.valueOf(tarefa.getValorTarefa()));
//    }
//

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



}
