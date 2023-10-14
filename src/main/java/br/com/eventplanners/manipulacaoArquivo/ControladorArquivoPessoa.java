package br.com.eventplanners.manipulacaoArquivo;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.Pessoa;
import java.io.*;
import java.util.ArrayList;

public class ControladorArquivoPessoa {

    private static final String ARQUIVO_PESSOA = "arquivoPessoa.ser";

    public void cadastrarPessoaNoArquivo(Pessoa pessoa){
        ArrayList<Pessoa> pessoas = this.lerArquivoPessoas();
        pessoas.add(pessoa);
        this.salvarArquivoPessoas(pessoas);
    }

    public void atualizarListaDePessoas(ArrayList<Pessoa> pessoas){
        this.salvarArquivoPessoas(pessoas);
    }

    public static ArrayList<Pessoa> lerArquivoPessoas(){
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_PESSOA))) {
            pessoas = (ArrayList<Pessoa>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo pessoa não encontrado. Criando um novo arquivo.");
            salvarArquivoPessoas(pessoas);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    private static void salvarArquivoPessoas(ArrayList<Pessoa> pessoas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_PESSOA))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
