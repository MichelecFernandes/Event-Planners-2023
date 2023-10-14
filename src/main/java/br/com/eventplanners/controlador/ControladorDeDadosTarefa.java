package br.com.eventplanners.controlador;

import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoTarefa;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ControladorDeDadosTarefa {

    private String nomeArquivo = "tarefas.dat"; // Nome do arquivo para armazenar as tarefas

    public ArrayList<Tarefa> listarTarefas(){
        ControladorArquivoTarefa controladorArquivoTarefa = new ControladorArquivoTarefa();
        return controladorArquivoTarefa.lerArquivoTarefas();
    }

    public void criarNovaTarefa(Tarefa tarefa){
        ControladorArquivoTarefa controladorArquivoTarefa = new ControladorArquivoTarefa();
        controladorArquivoTarefa.cadastrarTarefaNoArquivo(tarefa);
    }

    public void salvarTarefas(List<Tarefa> tarefas) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            // Escreve a lista de tarefas no arquivo
            outputStream.writeObject(tarefas);
            System.out.println("Tarefas salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar as tarefas: " + e.getMessage());
            throw e; // Re-lança a exceção para ser tratada por quem chamou o método
        }
    }
}
