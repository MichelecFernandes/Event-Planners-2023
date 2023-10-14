package br.com.eventplanners.cadastros;

import java.io.Serializable;

public class CronogramaPessoa implements Serializable {

    private int idCronograma;
    private int idPessoa;

    public CronogramaPessoa(int idCronograma, int idPessoa) {
        this.idCronograma = idCronograma;
        this.idPessoa = idPessoa;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
}
