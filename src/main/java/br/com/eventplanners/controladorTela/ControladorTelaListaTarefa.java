package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorDeDadosTarefa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoTarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorTelaListaTarefa {
    public static final String FXML_PATH = "tela-lista-tarefa.fxml";

    @FXML
    TableView<Tarefa> tabelaTarefas;

    @FXML
    TableColumn<Tarefa, Integer> idTarefa;

    @FXML
    TableColumn<Tarefa, String> nomeTarefa;

    @FXML
    TableColumn<Tarefa, Double> valorTarefa;

    ArrayList<Tarefa> tarefas = ControladorArquivoTarefa.lerArquivoTarefas();
    ObservableList<Tarefa> pessoaObservableList;

    @FXML
    protected void initialize(){
        for (Tarefa tarefa : tarefas){
            System.out.println(tarefa.getNomeTarefa());
        }
        idTarefa.setCellValueFactory(new PropertyValueFactory<>("idTarefa"));
        nomeTarefa.setCellValueFactory(new PropertyValueFactory<>("nomeTarefa"));
        valorTarefa.setCellValueFactory(new PropertyValueFactory<>("valorTarefa"));

        pessoaObservableList = FXCollections.observableArrayList(tarefas);
        tabelaTarefas.setItems(pessoaObservableList);
    }

    @FXML
    protected void voltarTelaCronogramaOrganizador() throws IOException {
        System.out.println("Voltar Tela");
        ControladorDeCena.trocarCena("tela-principal-admin.fxml");
    }

    @FXML
    protected void cadastrarNovaTarefa() throws IOException {
        System.out.println("Cadastrar tarefa");
        ControladorDeCena.trocarCena("tela-cadastro-tarefa.fxml");
    }

    @FXML
    protected void editarTarefa() throws IOException {
        System.out.println("editar tarefa");
        Tarefa tarefa =  ControladorArquivoTarefa.lerArquivoTarefas().get(0);
        ControladorTelaCadastroTarefa.tarefa = tarefa;
        ControladorDeCena.trocarCena("tela-editar-tarefa.fxml");
    }


    @FXML
    protected void voltarTelaListaTarefa() throws IOException {
        System.out.println("Voltar Tela");
        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }
}
