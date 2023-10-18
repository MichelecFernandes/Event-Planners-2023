package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorDeDadosCronograma;
import br.com.eventplanners.controlador.ControladorDeDadosPessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;

public class ControladorTelaCadastroPessoa {

    public static Pessoa pessoa;

    @FXML
    private TextField cadastrarNomePessoa;

    @FXML
    private TextField cadastrarCpfPessoa;

    @FXML
    private TextField cadastrarContatoPessoa;

    @FXML
    private TextField cadastrarLoginPessoa;

    @FXML
    private TextField cadastrarSenhaPessoa;

    @FXML
    private Button salvarNovaPessoa;

    @FXML
    public void initialize(){
        if(pessoa == null){
            return;
        }
        cadastrarNomePessoa.setText(pessoa.getNomePessoa());
        cadastrarCpfPessoa.setText(String.valueOf(pessoa.getCpfPessoa()));
        cadastrarContatoPessoa.setText(String.valueOf(pessoa.getContatoPessoa()));
        cadastrarLoginPessoa.setText(String.valueOf(pessoa.getLoginPessoa()));
        cadastrarSenhaPessoa.setText(String.valueOf(pessoa.getSenhaPessoa()));

        salvarNovaPessoa.setOnAction(actionEvent -> {
            try {
                editarPessoa(pessoa);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void salvarPessoa() throws IOException {
        Pessoa pessoa = new Pessoa(cadastrarNomePessoa.getText(),
                cadastrarCpfPessoa.getText(),
                cadastrarContatoPessoa.getText(),
                cadastrarLoginPessoa.getText(),
                cadastrarSenhaPessoa.getText(),
                false);
        ControladorDeDadosPessoa controladorDeDadosPessoa = new ControladorDeDadosPessoa();
        controladorDeDadosPessoa.criarNovaPessoa(pessoa);
        List<Pessoa> list = controladorDeDadosPessoa.listarPessoas();
        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
    }

    @FXML
    protected void voltarTelaCadastroPessoa() throws IOException {
        System.out.println("Voltar Tela lista Pessoa");
        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
    }

    private void editarPessoa(Pessoa pessoa) throws IOException{
        pessoa.setNomePessoa(cadastrarNomePessoa.getText());
        pessoa.setCpfPessoa(cadastrarCpfPessoa.getText());
        pessoa.setContatoPessoa(cadastrarContatoPessoa.getText());
        pessoa.setLoginPessoa(cadastrarLoginPessoa.getText());
        pessoa.setSenhaPessoa(cadastrarSenhaPessoa.getText());

        ControladorDeDadosPessoa controladorDeDadosPessoa = new ControladorDeDadosPessoa();

        if(!controladorDeDadosPessoa.atualizarPessoas(pessoa)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao editar dados da pessoa");
            alerta.setContentText("Verifique os seus dados");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("Atualiazação da pessoa feita com sucesso!");
        alerta.setContentText("A lista de pessoas foi atualizada com sucesso!");
        alerta.show();

        ControladorDeCena.trocarCena("tela-lista-pessoas.fxml");
    }

}
