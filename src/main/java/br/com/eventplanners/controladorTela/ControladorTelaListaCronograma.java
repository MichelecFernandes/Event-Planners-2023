package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.CronogramaPessoa;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.controlador.ControladorDeCena;
import br.com.eventplanners.controlador.ControladorDeDadosCronograma;
import br.com.eventplanners.controlador.ControladorDeDadosCronogramaPessoas;
import br.com.eventplanners.controlador.ControladorLogin;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoCronograma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ControladorTelaListaCronograma {

    public static final String FXML_PATH = "tela-lista-cronograma.fxml";

    @FXML
    TableView<Cronograma> tabelaCronograma;


    @FXML
    TableColumn<Cronograma, Date> dataCronograma;

    @FXML
    TableColumn<Cronograma, String> pessoaCronograma;

    @FXML
    TableColumn<Cronograma, String> tarefaCronograma;

    @FXML
    TableColumn<Cronograma, DateTimeFormatter> inicioCronograma;

    @FXML
    TableColumn<Cronograma, DateTimeFormatter> terminoCronograma;


    @FXML
    TableColumn<Cronograma, DateTimeFormatter> statusTarefa;

    @FXML
    private Button btnCadastrarCronograma;

    @FXML
    private Button btnEditarCronograma;

    @FXML
    private Button btnExcluirCronograma;

    @FXML
    private Button btnFinalizarTarefa;

    @FXML
    private Button btnIniciarTarefa;

    private Cronograma cronogramaSelecionado;

    private final ControladorDeDadosCronograma controladorDeDadosCronograma = new ControladorDeDadosCronograma();

    ObservableList<Cronograma> cronogramaObservableList;

    @FXML
    public void initialize(){
        btnCadastrarCronograma.setDisable(!ControladorLogin.getUsuarioAutenticado().isUsuarioMarter());

        ArrayList<Cronograma> cronogramasDoUsuario = prepararCronogramasDoUsuario();
        if(cronogramasDoUsuario == null || cronogramasDoUsuario.isEmpty()){
            return;
        }

        dataCronograma.setCellValueFactory(new PropertyValueFactory<>("dataCronograma"));
        pessoaCronograma.setCellValueFactory(new PropertyValueFactory<>("pessoaCronograma"));
        tarefaCronograma.setCellValueFactory(new PropertyValueFactory<>("tarefaCronograma"));
        inicioCronograma.setCellValueFactory(new PropertyValueFactory<>("inicioCronograma"));
        terminoCronograma.setCellValueFactory(new PropertyValueFactory<>("terminoCronograma"));
        statusTarefa.setCellValueFactory(new PropertyValueFactory<>("status"));
        cronogramaObservableList = FXCollections.observableArrayList(cronogramasDoUsuario);
        tabelaCronograma.setItems(cronogramaObservableList);

        tabelaCronograma.getSelectionModel().selectedItemProperty().addListener((observableValue,
                                                                                 oldCronograma, newCronograma) -> {
            if(newCronograma == null) {
                btnExcluirCronograma.setDisable(true);
                btnEditarCronograma.setDisable(true);
                btnFinalizarTarefa.setDisable(true);
                btnIniciarTarefa.setDisable(true);
                tabelaCronograma.getSelectionModel().clearSelection();
                return;
            }

            cronogramaSelecionado = newCronograma;
            btnFinalizarTarefa.setDisable(false);
            btnIniciarTarefa.setDisable(false);
            if(!ControladorLogin.getUsuarioAutenticado().isUsuarioMarter()){
                return;
            }

            btnEditarCronograma.setDisable(false);
            btnExcluirCronograma.setDisable(false);
        });
        cronogramaSelecionado = null;
    }

    private ArrayList<Cronograma> prepararCronogramasDoUsuario(){
        ArrayList<Cronograma> cronogramaArrayList = controladorDeDadosCronograma.listarCronograma();

        Pessoa usuarioAutenticado = ControladorLogin.getUsuarioAutenticado();
        if(usuarioAutenticado.isUsuarioMarter()){
            return cronogramaArrayList;
        }

        ControladorDeDadosCronogramaPessoas cronogramaPessoas = new ControladorDeDadosCronogramaPessoas();
        ArrayList<CronogramaPessoa> cronogramaPessoaArrayList = cronogramaPessoas.listarCronogramaPessoaDoUsuario(usuarioAutenticado.getIdPessoa());

        ArrayList<Cronograma> cronogramasDoUsaurio = new ArrayList<>();
        for(Cronograma cronograma : cronogramaArrayList){
            boolean cronogramaDoUsuario = false;

            for(CronogramaPessoa cronogramaPessoa : cronogramaPessoaArrayList){
                if(cronograma.getIdCronograma() == cronogramaPessoa.getIdCronograma()){
                    cronogramaDoUsuario = true;
                    break;
                }
            }

            if(cronogramaDoUsuario){
                cronogramasDoUsaurio.add(cronograma);
            }
        }

        return cronogramasDoUsaurio;
    }

    @FXML
    protected void voltarTelaCronogramaOrganizador() throws IOException {
        System.out.println("Voltar Tela organizador");
        ControladorDeCena.trocarCena("tela-principal-admin.fxml");
    }

    @FXML
    protected void cadastrarCronograma() throws IOException {
        System.out.println("Cadastrar Cronograma");

        ControladorTelaCadastroCronograma.cronograma = cronogramaSelecionado;
        ControladorDeCena.trocarCena("tela-cadastro-cronograma.fxml");
    }

    @FXML
    protected void excluirCronograma() throws IOException {
        ControladorDeDadosCronogramaPessoas controladorDeDadosCronogramaPessoas = new ControladorDeDadosCronogramaPessoas();
        if(!controladorDeDadosCronogramaPessoas.excluirCronogramaUsuario(cronogramaSelecionado)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao excluir cronograma");
            alerta.setContentText("verifique os seus dados");
            alerta.show();
            return;
        }

        if(!controladorDeDadosCronograma.excluirCronograma(cronogramaSelecionado)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao excluir cronograma");
            alerta.setContentText("verifique os seus dados");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("A lista do cronograma foi atualizada.");
        alerta.setContentText("A atividade foi excluída! ");
        alerta.show();
        initialize();
    }

    @FXML
    protected void iniciarTarefa(){
        cronogramaSelecionado.setStatus(Cronograma.STATUS_CRONOGRAMA.STATUS_EM_ANDAMENTO);
        if(!controladorDeDadosCronograma.atualizarCronograma(cronogramaSelecionado)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao atualizar cronograma");
            alerta.setContentText("Não foi possível atualizar o status do cronograma. Por gentileza, tente novamente");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("A lista do cronograma foi atualizada.");
        alerta.setContentText("A atividade foi atualizada! ");
        alerta.show();
        initialize();
    }

    @FXML
    protected void finalizarTarefa(){
        cronogramaSelecionado.setStatus(Cronograma.STATUS_CRONOGRAMA.STATUS_FINALIZADO);
        if(!controladorDeDadosCronograma.atualizarCronograma(cronogramaSelecionado)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao atualizar cronograma");
            alerta.setContentText("Não foi possível atualizar o status do cronograma. Por gentileza, tente novamente");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("A lista do cronograma foi atualizada.");
        alerta.setContentText("A atividade foi atualizada! ");
        alerta.show();
        initialize();
    }

}
