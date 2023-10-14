package br.com.eventplanners.controlador;

import br.com.eventplanners.cadastros.Pessoa;

import java.util.ArrayList;

public class ControladorLogin {

    private static Pessoa usuarioAutenticado;

    public boolean entrarNoSistema(String usuario, String senha){
        ControladorDeDadosPessoa controladorDeDadosPessoa = new ControladorDeDadosPessoa();
        ArrayList<Pessoa> pessoas = controladorDeDadosPessoa.listarPessoas();
        if(usuario.equals("admin") && senha.equals("admin")){
            if(pessoas.isEmpty()){
                Pessoa pessoa = new Pessoa("master", "", "", "admin", "admin", true);
                controladorDeDadosPessoa.criarNovaPessoa(pessoa);
            }
            pessoas = controladorDeDadosPessoa.listarPessoas();
        }

        for(Pessoa pessoa : pessoas){
            if(pessoa.getLoginPessoa().equals(usuario) && pessoa.getSenhaPessoa().equals(senha)){
                usuarioAutenticado = pessoa;
                return true;
            }
        }

        return false;
    }

    public static Pessoa getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
