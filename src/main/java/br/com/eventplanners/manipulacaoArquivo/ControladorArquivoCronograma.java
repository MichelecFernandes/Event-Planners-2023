package br.com.eventplanners.manipulacaoArquivo;

import br.com.eventplanners.cadastros.Cronograma;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorArquivoCronograma {

    private static final String ARQUIVO_CRONOGRAMA = "arquivoCronograma.ser";

    public void cadastrarCronogramaNoArquivo(Cronograma cronograma){
        ArrayList<Cronograma> cronogramas = this.lerArquivoCronograma();
        cronogramas.add(cronograma);
        this.salvarArquivoCronogramas(cronogramas);
    }

    public void atualizarListaDeCronogramas(ArrayList<Cronograma> cronogramas){
        this.salvarArquivoCronogramas(cronogramas);
    }

    public static ArrayList<Cronograma> lerArquivoCronograma(){
        ArrayList<Cronograma> cronogramas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_CRONOGRAMA))) {
            cronogramas = (ArrayList<Cronograma>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo cronograma não encontrado. Criando um novo arquivo.");
            salvarArquivoCronogramas(cronogramas);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cronogramas;
    }

    private static void salvarArquivoCronogramas(ArrayList<Cronograma> cronogramas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CRONOGRAMA))) {
            oos.writeObject(cronogramas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
