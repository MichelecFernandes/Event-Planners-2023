package br.com.eventplanners.apoio;

import java.io.Serializable;

public class Identificacao implements Serializable {
    private int ultimoIDPessoa;
    private int ultimoIDTarefa;
    private int ultimoIDCronograma;

    public Identificacao() {
        this.ultimoIDPessoa = 0;
        this.ultimoIDTarefa = 0;
        this.ultimoIDCronograma = 0;
    }

    public int gerarIDPessoa(){
        this.ultimoIDPessoa += 1;
        return this.ultimoIDPessoa;
    }
    public int gerarIDTarefa(){
        this.ultimoIDTarefa += 1;
        return this.ultimoIDTarefa;
    }
    public int gerarIDCronograma(){
        this.ultimoIDCronograma += 1;
        return this.ultimoIDCronograma;
    }

}
