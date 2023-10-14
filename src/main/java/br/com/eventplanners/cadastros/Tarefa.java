package br.com.eventplanners.cadastros;

import br.com.eventplanners.apoio.Identificacao;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoIdentificacao;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private int idTarefa;
    private String nomeTarefa;
    private double valorTarefa;

    public Tarefa(String nomeTarefa, double valorTarefa) {
        this.nomeTarefa = nomeTarefa;
        this.valorTarefa = valorTarefa;
        Identificacao identificacao = ControladorArquivoIdentificacao.lerArquivoIdentificacao();
        this.idTarefa = identificacao.gerarIDTarefa();
        ControladorArquivoIdentificacao.salvarArquivoIdentificacao(identificacao);
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public double getValorTarefa() {
        return valorTarefa;
    }

    public void setValorTarefa(double valorTarefa) {
        this.valorTarefa = valorTarefa;
    }
}
