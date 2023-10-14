package br.com.eventplanners.cadastros;

import br.com.eventplanners.apoio.Identificacao;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoIdentificacao;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private int idPessoa;
    private String nomePessoa;
    private String cpfPessoa;
    private String contatoPessoa;
    private String loginPessoa;
    private String senhaPessoa;
    private boolean usuarioMarter;


    public Pessoa(String nomePessoa, String cpfPessoa, String contatoPessoa, String loginPessoa, String senhaPessoa, boolean usuarioMarter) {
        this.nomePessoa = nomePessoa;
        this.cpfPessoa = cpfPessoa;
        this.contatoPessoa = contatoPessoa;
        this.loginPessoa = loginPessoa;
        this.senhaPessoa = senhaPessoa;
        this.usuarioMarter = usuarioMarter;
        Identificacao identificacao = ControladorArquivoIdentificacao.lerArquivoIdentificacao();
        this.idPessoa = identificacao.gerarIDPessoa();
        ControladorArquivoIdentificacao.salvarArquivoIdentificacao(identificacao);
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getContatoPessoa() {
        return contatoPessoa;
    }

    public void setContatoPessoa(String contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

    public String getLoginPessoa() {
        return loginPessoa;
    }

    public void setLoginPessoa(String loginPessoa) {
        this.loginPessoa = loginPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    public boolean isUsuarioMarter() {
        return usuarioMarter;
    }

    @Override
    public String toString(){
        return nomePessoa;
    }
}
