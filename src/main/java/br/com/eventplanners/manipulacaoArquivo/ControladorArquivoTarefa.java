package br.com.eventplanners.manipulacaoArquivo;

import br.com.eventplanners.cadastros.Tarefa;

import java.io.*;
import java.util.ArrayList;

public class ControladorArquivoTarefa {
    private static final String ARQUIVO_TAREFA = "arquivoTarefa.ser";

    public void cadastrarTarefaNoArquivo(Tarefa tarefa){
        ArrayList<Tarefa> tarefas = this.lerArquivoTarefas();
        tarefas.add(tarefa);
        this.salvarArquivoTarefas(tarefas);
    }

    public static ArrayList<Tarefa> lerArquivoTarefas(){
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_TAREFA))) {
            tarefas = (ArrayList<Tarefa>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de tarefa não encontrado. Criando um novo arquivo.");
            salvarArquivoTarefas(tarefas);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    private static void salvarArquivoTarefas(ArrayList<Tarefa> tarefas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_TAREFA))) {
            oos.writeObject(tarefas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
