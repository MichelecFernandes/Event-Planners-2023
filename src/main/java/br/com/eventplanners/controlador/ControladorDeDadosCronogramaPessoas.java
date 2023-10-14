package br.com.eventplanners.controlador;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.CronogramaPessoa;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoCronogramaPessoa;

import java.util.ArrayList;

public class ControladorDeDadosCronogramaPessoas {

    ControladorArquivoCronogramaPessoa controladorArquivoCronogramaPessoa = new ControladorArquivoCronogramaPessoa();

    public void criarCronogramaPessoas(CronogramaPessoa cronogramaPessoa){
        controladorArquivoCronogramaPessoa.cadastrarCronogramaNoArquivo(cronogramaPessoa);
    }

    public ArrayList<CronogramaPessoa> listarCronogramaPessoa(){
        return controladorArquivoCronogramaPessoa.lerArquivoCronogramaPessoas();
    }

    public ArrayList<CronogramaPessoa> listarCronogramaPessoaDoUsuario(int idUsuario){
        ArrayList<CronogramaPessoa> cronogramaPessoas = listarCronogramaPessoa();

        ArrayList<CronogramaPessoa> cronogramaPessoasDoUsuario = new ArrayList<>();
        for(CronogramaPessoa cronogramaPessoa : cronogramaPessoas){
            if(cronogramaPessoa.getIdPessoa() == idUsuario){
                cronogramaPessoasDoUsuario.add(cronogramaPessoa);
            }
        }

        return cronogramaPessoasDoUsuario;
    }

    public boolean atualizarCronogramaUsuario(Cronograma cronograma, int antigoIdUsuario, int novoIdUsuario){
        ArrayList<CronogramaPessoa> cronogramaPessoas = listarCronogramaPessoa();

        int index = -1;
        for(CronogramaPessoa cronogramaPessoaEntity : cronogramaPessoas){
            if(cronogramaPessoaEntity.getIdPessoa() == antigoIdUsuario &&
                cronogramaPessoaEntity.getIdCronograma() == cronograma.getIdCronograma()){
                index = cronogramaPessoas.indexOf(cronogramaPessoaEntity);
                break;
            }
        }

        if(index == -1) return false;

        CronogramaPessoa cronogramaPessoaAtualizado = cronogramaPessoas.get(index);
        cronogramaPessoaAtualizado.setIdPessoa(novoIdUsuario);
        cronogramaPessoas.add(index, cronogramaPessoaAtualizado);
        cronogramaPessoas.remove(index+1);
        controladorArquivoCronogramaPessoa.atualizarListaDeCronogramaPessoas(cronogramaPessoas);
        return true;
    }

    public boolean excluirCronogramaUsuario(Cronograma cronograma){
        ArrayList<CronogramaPessoa> cronogramaPessoas = listarCronogramaPessoa();

        int index = -1;
        for(CronogramaPessoa cronogramaPessoaEntity : cronogramaPessoas){
            if(cronogramaPessoaEntity.getIdCronograma() == cronograma.getIdCronograma()){
                index = cronogramaPessoas.indexOf(cronogramaPessoaEntity);
                break;
            }
        }

        if(index == -1) return false;

        cronogramaPessoas.remove(index);
        controladorArquivoCronogramaPessoa.atualizarListaDeCronogramaPessoas(cronogramaPessoas);
        return true;
    }

}
