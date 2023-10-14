package br.com.eventplanners.controlador;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoCronograma;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoPessoa;

import java.util.ArrayList;

public class ControladorDeDadosCronograma {

    private final ControladorArquivoCronograma controladorArquivoCronograma = new ControladorArquivoCronograma();

    public ArrayList<Cronograma> listarCronograma(){
        return controladorArquivoCronograma.lerArquivoCronograma();
    }

    public void criarNovaCronograma(Cronograma cronograma){
        controladorArquivoCronograma.cadastrarCronogramaNoArquivo(cronograma);
    }

    public boolean atualizarCronograma(Cronograma cronograma){
        ArrayList<Cronograma> cronogramas = controladorArquivoCronograma.lerArquivoCronograma();

        int index = -1;
        for(Cronograma cronogramaEntity : cronogramas){
            if(cronogramaEntity.getIdCronograma() == cronograma.getIdCronograma()){
                index = cronogramas.indexOf(cronogramaEntity);
                break;
            }
        }

        if(index == -1) return false;

        cronogramas.add(index, cronograma);
        cronogramas.remove(index+1);
        controladorArquivoCronograma.atualizarListaDeCronogramas(cronogramas);
        return true;
    }

    public boolean excluirCronograma(Cronograma cronograma){
        ArrayList<Cronograma> cronogramas = controladorArquivoCronograma.lerArquivoCronograma();

        int index = -1;
        for(Cronograma cronogramaEntity : cronogramas){
            if(cronogramaEntity.getIdCronograma() == cronograma.getIdCronograma()){
                index = cronogramas.indexOf(cronogramaEntity);
                break;
            }
        }

        if(index == -1) return false;

        cronogramas.remove(index);
        controladorArquivoCronograma.atualizarListaDeCronogramas(cronogramas);
        return true;
    }

}
