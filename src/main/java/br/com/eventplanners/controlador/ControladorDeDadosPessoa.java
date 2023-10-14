package br.com.eventplanners.controlador;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoCronograma;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoPessoa;

import java.util.ArrayList;

public class ControladorDeDadosPessoa {

    public ArrayList<Pessoa> listarPessoas(){
        ControladorArquivoPessoa controladorArquivoPessoa = new ControladorArquivoPessoa();
        return controladorArquivoPessoa.lerArquivoPessoas();
    }

    public void criarNovaPessoa(Pessoa pessoa){
        ControladorArquivoPessoa controladorArquivoPessoa = new ControladorArquivoPessoa();
        controladorArquivoPessoa.cadastrarPessoaNoArquivo(pessoa);
    }

    public boolean atualizarPessoas(Pessoa pessoa){
        ControladorArquivoPessoa controladorArquivoPessoa = new ControladorArquivoPessoa();
        ArrayList<Pessoa> pessoas = controladorArquivoPessoa.lerArquivoPessoas();

        int index = -1;
        for(Pessoa pessoaEntity : pessoas){
            if(pessoaEntity.getIdPessoa() == pessoa.getIdPessoa()){
                index = pessoas.indexOf(pessoaEntity);
                break;
            }
        }

        if(index == -1) return false;

        pessoas.add(index, pessoa);
        pessoas.remove(index+1);
        controladorArquivoPessoa.atualizarListaDePessoas(pessoas);
        return true;
    }

    public boolean excluirPessoa(Pessoa pessoa){
        ControladorArquivoPessoa controladorArquivoPessoa = new ControladorArquivoPessoa();
        ArrayList<Pessoa> pessoas = ControladorArquivoPessoa.lerArquivoPessoas();

        int index = -1;
        for(Pessoa pessoaEntity : pessoas){
            if(pessoaEntity.getIdPessoa() == pessoa.getIdPessoa()){
                index = pessoas.indexOf(pessoaEntity);
                break;
            }
        }

        if(index == -1) return false;
        pessoas.remove(index);
        controladorArquivoPessoa.atualizarListaDePessoas(pessoas);
        return true;
    }

}
