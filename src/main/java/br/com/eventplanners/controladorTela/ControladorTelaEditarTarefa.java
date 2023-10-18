package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.CronogramaPessoa;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorDeDadosCronograma;
import br.com.eventplanners.controlador.ControladorDeDadosCronogramaPessoas;
import br.com.eventplanners.controlador.ControladorDeDadosTarefa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoPessoa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoTarefa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorTelaEditarTarefa {

    public static Tarefa tarefa;

    @FXML
    private TextField editarNomeTarefa;

    @FXML
    private TextField editarValorTarefa;

    @FXML
    private Button btnSalvarEditarTarefa;

    private ArrayList<Tarefa> tarefas = ControladorArquivoTarefa.lerArquivoTarefas();

    private int idTarefa = -1;

    @FXML
    public void initialize(){

        tarefas = ControladorArquivoTarefa.lerArquivoTarefas();

        if(tarefas == null){
            return;
        }
        editarNomeTarefa.setText(tarefa.getNomeTarefa());
        editarValorTarefa.setText(String.valueOf(tarefa.getValorTarefa()));
    }

    @FXML
    public void salvarEdicaoListaTarefa() throws IOException {
        Tarefa tarefa = new Tarefa(
                editarNomeTarefa.getText(),
                Double.parseDouble(editarValorTarefa.getText()));
        ControladorDeDadosTarefa controladorDeDadosTarefa = new ControladorDeDadosTarefa();
        controladorDeDadosTarefa.criarNovaTarefa(tarefa);

        ControladorDeDadosTarefa tarefa1 = new ControladorDeDadosTarefa();
        tarefa1.criarNovaTarefa(tarefa);
        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }

    @FXML
    protected void voltarTelaListaTarefa() throws IOException {
        System.out.println("Voltar Tela lista Tarefas");
        ControladorDeCena.trocarCena("tela-lista-tarefa.fxml");
    }


}
