package br.com.eventplanners.cadastros;

import br.com.eventplanners.apoio.Identificacao;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoIdentificacao;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cronograma implements Serializable {

    public static class STATUS_CRONOGRAMA{
        public static final String STATUS_ABERTO = "Aberto";
        public static final String STATUS_EM_ANDAMENTO = "Em Andamento";
        public static final String STATUS_FINALIZADO = "Finalizado";
    }


    private int idCronograma;
    private String dataCronograma;
    private String pessoaCronograma;
    private String tarefaCronograma;
    private double valorCronograma;
    private String inicioCronograma;
    private String terminoCronograma;
    private String status;

    public Cronograma(String dataCronograma,
                      Pessoa pessoaCronograma,
                      Tarefa tarefaCronograma,
                      double valorCronograma,
                      String inicioCronograma,
                      String terminoCronograma) {

        this.dataCronograma = dataCronograma;
        this.pessoaCronograma = pessoaCronograma.getNomePessoa();
        this.tarefaCronograma = tarefaCronograma.getNomeTarefa();
        this.valorCronograma = valorCronograma;
        this.inicioCronograma = inicioCronograma;
        this.terminoCronograma = terminoCronograma;
        this.status = STATUS_CRONOGRAMA.STATUS_ABERTO;
        Identificacao identificacao = ControladorArquivoIdentificacao.lerArquivoIdentificacao();
        this.idCronograma = identificacao.gerarIDCronograma();
        ControladorArquivoIdentificacao.salvarArquivoIdentificacao(identificacao);
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
    }

    public String getDataCronograma() {
        return dataCronograma;
    }

    public void setDataCronograma(String dataCronograma) {
        this.dataCronograma = dataCronograma;
    }

    public String getPessoaCronograma() {
        return pessoaCronograma;
    }

    public void setPessoaCronograma(String pessoaCronograma) {
        this.pessoaCronograma = pessoaCronograma;
    }

    public String getTarefaCronograma() {
        return tarefaCronograma;
    }

    public void setTarefaCronograma(String tarefaCronograma) {
        this.tarefaCronograma = tarefaCronograma;
    }

    public double getValorCronograma() {
        return valorCronograma;
    }

    public void setValorCronograma(double valorCronograma) {
        this.valorCronograma = valorCronograma;
    }

    public String getInicioCronograma() {
        return inicioCronograma;
    }

    public void setInicioCronograma(String inicioCronograma) {
        this.inicioCronograma = inicioCronograma;
    }

    public String getTerminoCronograma() {
        return terminoCronograma;
    }

    public void setTerminoCronograma(String terminoCronograma) {
        this.terminoCronograma = terminoCronograma;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
