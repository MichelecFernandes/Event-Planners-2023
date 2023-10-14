package br.com.eventplanners.controladorTela;

import br.com.eventplanners.cadastros.Cronograma;
import br.com.eventplanners.cadastros.CronogramaPessoa;
import br.com.eventplanners.cadastros.Pessoa;
import br.com.eventplanners.cadastros.Tarefa;
import br.com.eventplanners.controlador.*;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoPessoa;
import br.com.eventplanners.manipulacaoArquivo.ControladorArquivoTarefa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorTelaCadastroCronograma {

    public static Cronograma cronograma;

    @FXML
    private TextField cadastrarDataCronograma;

    @FXML
    ComboBox<Pessoa> comboBoxPessoa;

    @FXML
    private TextField cadastrarHorarioEntrada;

    @FXML
    private TextField cadastrarHorarioSaida;

    @FXML
    ComboBox<Tarefa> comboBoxTarefa;

    @FXML
    private Button salvarNovoCronograna;

    private ArrayList<Pessoa> pessoas = ControladorArquivoPessoa.lerArquivoPessoas();
    private ArrayList<Tarefa> tarefas = ControladorArquivoTarefa.lerArquivoTarefas();

    private int idPessoaTarefa = -1;


    @FXML
    public void initialize(){
        pessoas = ControladorArquivoPessoa.lerArquivoPessoas();
        tarefas = ControladorArquivoTarefa.lerArquivoTarefas();

        comboBoxPessoa.getItems().addAll(pessoas);
        comboBoxPessoa.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Pessoa> call(ListView<Pessoa> clienteListView) {
                return new ListCell<>() {
                    protected void updateItem(Pessoa item, boolean vazio) {
                        super.updateItem(item, vazio);
                        if (item != null && !vazio) {
                            setText(item.getNomePessoa());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        comboBoxPessoa.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pessoa item, boolean vazio) {
                super.updateItem(item, vazio);
                if (item != null && !vazio) {
                    setText(item.getNomePessoa());
                } else {
                    setText(null);
                }
            }
        });
        comboBoxPessoa.getSelectionModel().select(0);

        comboBoxTarefa.getItems().addAll(tarefas);
        comboBoxTarefa.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Tarefa> call(ListView<Tarefa> clienteListView) {
                return new ListCell<>() {
                    protected void updateItem(Tarefa item, boolean vazio) {
                        super.updateItem(item, vazio);
                        if (item != null && !vazio) {
                            setText(item.getNomeTarefa());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        comboBoxTarefa.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Tarefa item, boolean vazio) {
                super.updateItem(item, vazio);
                if (item != null && !vazio) {
                    setText(item.getNomeTarefa());
                } else {
                    setText(null);
                }
            }
        });
        comboBoxTarefa.getSelectionModel().select(0);

        if(cronograma == null){
            return;
        }

        cadastrarDataCronograma.setText(cronograma.getDataCronograma());
        cadastrarHorarioEntrada.setText(String.valueOf(cronograma.getInicioCronograma()));
        cadastrarHorarioSaida.setText(String.valueOf(cronograma.getTerminoCronograma()));

        for(Tarefa tarefa : tarefas){
            if(tarefa.getNomeTarefa().equals(cronograma.getTarefaCronograma())){
                comboBoxTarefa.getSelectionModel().select(tarefa);
            }
        }

        for(Pessoa pessoa : pessoas){
            if(pessoa.getNomePessoa().equals(cronograma.getPessoaCronograma())){
                idPessoaTarefa = pessoa.getIdPessoa();
                comboBoxPessoa.getSelectionModel().select(pessoa);
            }
        }

        salvarNovoCronograna.setOnAction(actionEvent -> {
            try {
                editarCronograma(cronograma);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void salvarNovaListaCronograma() throws IOException {
        Pessoa pessoaSelecionadaParaTarefa = comboBoxPessoa.getValue();
        Cronograma cronograma = new Cronograma(cadastrarDataCronograma.getText(),
                pessoaSelecionadaParaTarefa,
                comboBoxTarefa.getValue(),
                comboBoxTarefa.getValue().getValorTarefa(),
                cadastrarHorarioEntrada.getText(),
                cadastrarHorarioSaida.getText());
        ControladorDeDadosCronograma controladorDeDadosCronograma = new ControladorDeDadosCronograma();
        controladorDeDadosCronograma.criarNovaCronograma(cronograma);

        CronogramaPessoa cronogramaPessoa = new CronogramaPessoa(cronograma.getIdCronograma(),
                pessoaSelecionadaParaTarefa.getIdPessoa());
        ControladorDeDadosCronogramaPessoas cronogramaPessoas = new ControladorDeDadosCronogramaPessoas();
        cronogramaPessoas.criarCronogramaPessoas(cronogramaPessoa);
        ControladorDeCena.trocarCena("tela-lista-cronograma.fxml");
    }

    @FXML
    protected void voltarTelaListaCronograma() throws IOException {
        System.out.println("Voltar Tela lista Cronograma");
        ControladorDeCena.trocarCena("tela-lista-cronograma.fxml");
    }

    private void editarCronograma(Cronograma cronograma) throws IOException{

        Pessoa novaPessoaSelecionada = comboBoxPessoa.getValue();

        cronograma.setDataCronograma(cadastrarDataCronograma.getText());
        cronograma.setPessoaCronograma(novaPessoaSelecionada.getNomePessoa());
        cronograma.setInicioCronograma(cadastrarHorarioEntrada.getText());
        cronograma.setTerminoCronograma(cadastrarHorarioSaida.getText());

        Tarefa tarefa = comboBoxTarefa.getValue();
        cronograma.setTarefaCronograma(tarefa.getNomeTarefa());
        cronograma.setValorCronograma(tarefa.getValorTarefa());

        ControladorDeDadosCronogramaPessoas controladorDeDadosCronogramaPessoas = new ControladorDeDadosCronogramaPessoas();
        if(!controladorDeDadosCronogramaPessoas.atualizarCronogramaUsuario(cronograma, idPessoaTarefa, novaPessoaSelecionada.getIdPessoa())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao editar cronograma");
            alerta.setContentText("verifique os seus dados");
            alerta.show();
            return;
        }

        ControladorDeDadosCronograma controladorDeDadosCronograma = new ControladorDeDadosCronograma();
        if(!controladorDeDadosCronograma.atualizarCronograma(cronograma)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Erro ao editar cronograma");
            alerta.setContentText("verifique os seus dados");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("Essa merda salvou");
        alerta.setContentText("o pai é brabo");
        alerta.show();

        ControladorDeCena.trocarCena("tela-lista-cronograma.fxml");
    }

}
