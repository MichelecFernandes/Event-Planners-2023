package br.com.eventplanners.manipulacaoArquivo;

import br.com.eventplanners.cadastros.CronogramaPessoa;

import java.io.*;
import java.util.ArrayList;

public class ControladorArquivoCronogramaPessoa {

    private static final String ARQUIVO_CRONOGRAMA_PESSOA = "arquivoCronogramaPessoa.ser";

    public void cadastrarCronogramaNoArquivo(CronogramaPessoa cronograma){
        ArrayList<CronogramaPessoa> cronogramaPessoas = this.lerArquivoCronogramaPessoas();
        cronogramaPessoas.add(cronograma);
        salvarArquivoCronogramaPessoas(cronogramaPessoas);
    }

    public void atualizarListaDeCronogramaPessoas(ArrayList<CronogramaPessoa> cronogramaPessoas){
        salvarArquivoCronogramaPessoas(cronogramaPessoas);
    }

    public ArrayList<CronogramaPessoa> lerArquivoCronogramaPessoas(){
        ArrayList<CronogramaPessoa> cronogramaPessoas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_CRONOGRAMA_PESSOA))) {
            cronogramaPessoas = (ArrayList<CronogramaPessoa>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo cronograma não encontrado. Criando um novo arquivo.");
            salvarArquivoCronogramaPessoas(cronogramaPessoas);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cronogramaPessoas;
    }

    private void salvarArquivoCronogramaPessoas(ArrayList<CronogramaPessoa> cronogramaPessoas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CRONOGRAMA_PESSOA))) {
            oos.writeObject(cronogramaPessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
