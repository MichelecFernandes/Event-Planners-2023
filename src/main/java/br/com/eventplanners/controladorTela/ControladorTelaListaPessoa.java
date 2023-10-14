package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoPessoa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoTarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorTelaListaPessoa {

    public static final String FXML_PATH = "tela-lista-pessoa.fxml";

    @FXML
    TableView<Pessoa> tabelaPessoas;

    @FXML
    TableColumn<Pessoa, Integer> idPessoa;

    @FXML
    TableColumn<Pessoa, String> nomePessoa;

    @FXML
    TableColumn<Pessoa, String> cpfPessoa;

    @FXML
    TableColumn<Pessoa, String> contatoPessoa;

    @FXML
    TableColumn<Pessoa, String> loginPessoa;

    @FXML
    TableColumn<Pessoa, String> senhaPessoa;


    ArrayList<Pessoa> pessoas = ControladorArquivoPessoa.lerArquivoPessoas();
    ObservableList<Pessoa> pessoaObservableList;

    @FXML
    public void initialize(){
        idPessoa.setCellValueFactory(new PropertyValueFactory<>("idPessoa"));
        nomePessoa.setCellValueFactory(new PropertyValueFactory<>("nomePessoa"));
        cpfPessoa.setCellValueFactory(new PropertyValueFactory<>("cpfPessoa"));
        contatoPessoa.setCellValueFactory(new PropertyValueFactory<>("contatoPessoa"));
        loginPessoa.setCellValueFactory(new PropertyValueFactory<>("loginPessoa"));
        senhaPessoa.setCellValueFactory(new PropertyValueFactory<>("senhaPessoa"));
        pessoaObservableList = FXCollections.observableArrayList(pessoas);
        tabelaPessoas.setItems(pessoaObservableList);
    }

    @FXML
    protected void voltarTelaCronogramaOrganizador() throws IOException {
        System.out.println("Voltar Tela organizador");
        ControladorDeCena.trocarCena("tela-principal-admin.fxml");
    }

    @FXML
    protected void cadastrarPessoa() throws IOException {
        System.out.println("Cadastrar Pessoa");
        ControladorDeCena.trocarCena("tela-cadastro-pessoas.fxml");
    }


}
