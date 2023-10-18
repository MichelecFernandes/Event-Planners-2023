package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.*;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoPessoa;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorTelaEditarPessoas {

    public static Pessoa pessoa;

    @FXML
    private TextField editarNomePessoa;

    @FXML
    private TextField editarCpfPessoa;

    @FXML
    private TextField editarContatoPessoa;

    @FXML
    private TextField editarLoginPessoa;

    @FXML
    private TextField editarSenhaPessoa;

    @FXML
    private Button btnCadastrarPessoa;

    @FXML
    private Button bntEditarPessoa;

    @FXML
    private Button btnExcluirPessoa;

    private ArrayList<Pessoa> pessoas = ControladorArquivoPessoa.lerArquivoPessoas();

    private int idPessoa = -1;

    @FXML
    public void initialize(){
        pessoas = ControladorArquivoPessoa.lerArquivoPessoas();
//
        if(pessoa == null){
            return;
        }

        editarNomePessoa.setText(pessoa.getNomePessoa());
        editarCpfPessoa.setText(pessoa.getCpfPessoa());
        editarContatoPessoa.setText(pessoa.getContatoPessoa());
        editarLoginPessoa.setText(pessoa.getLoginPessoa());
        editarSenhaPessoa.setText(pessoa.getSenhaPessoa());

//        salvarEdicaoPessoa.setOnAction(actionEvent -> {
//            try {
//                editarPessoa(pessoa);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    @FXML
    public void salvarEdicaoPessoa() throws IOException {
        Pessoa pessoa = new Pessoa(editarNomePessoa.getText(),
                editarCpfPessoa.getText(),
                editarContatoPessoa.getText(),
                editarLoginPessoa.getText(),
                editarSenhaPessoa.getText(), false);
        ControladorDeDadosPessoa controladorDeDadosPessoa = new ControladorDeDadosPessoa();
        controladorDeDadosPessoa.atualizarPessoas(pessoa);

        ControladorDeDadosPessoa pessoa1 = new ControladorDeDadosPessoa();
        pessoa1.criarNovaPessoa(pessoa);
        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
    }

    @FXML
    protected void voltarTelaListaPessoa() throws IOException {
        System.out.println("Voltar Tela lista Cronograma");
        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
    }
//
//    private void editarPessoa(Pessoa pessoa) throws IOException{
//
//        pessoa.setNomePessoa(editarNomePessoa.getText());
//        pessoa.setCpfPessoa(editarCpfPessoa.getText());
//        pessoa.setContatoPessoa(editarContatoPessoa.getText());
//        pessoa.setLoginPessoa(editarLoginPessoa.getText());
//        pessoa.setSenhaPessoa(editarSenhaPessoa.getText());
//
//
//        ControladorDeDadosPessoa controladorDeDadosPessoa = new ControladorDeDadosPessoa();
//        if(!controladorDeDadosPessoa.atualizarPessoas(pessoa)){
//            Alert alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setTitle("Error");
//            alerta.setHeaderText("Erro ao editar os dados da pessoa");
//            alerta.setContentText("verifique os seus dados");
//            alerta.show();
//            return;
//        }
//
//        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//        alerta.setTitle("SUCESSO");
//        alerta.setHeaderText("Atualização dos dados da pessoa feita com sucesso!");
//        alerta.setContentText("A atualização foi concluída!");
//        alerta.show();
//        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
//    }

}
