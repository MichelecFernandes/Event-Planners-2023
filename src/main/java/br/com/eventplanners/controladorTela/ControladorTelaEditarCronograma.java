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

public class ControladorTelaEditarCronograma {

    public static Cronograma cronograma;

    @FXML
    private TextField editarDataTarefa;

    @FXML
    ComboBox<Pessoa> editarComboBoxPessoa;

    @FXML
    private TextField editarHorarioEntrada;

    @FXML
    private TextField editarHorarioSaida;

    @FXML
    ComboBox<Tarefa> editarComboBoxTarefa;

    @FXML
    private Button salvarNovoCronograna;

    private ArrayList<Pessoa> pessoas = ControladorArquivoPessoa.lerArquivoPessoas();
    private ArrayList<Tarefa> tarefas = ControladorArquivoTarefa.lerArquivoTarefas();

    private int idPessoaTarefa = -1;

    @FXML
    public void initialize(){
        pessoas = ControladorArquivoPessoa.lerArquivoPessoas();
        tarefas = ControladorArquivoTarefa.lerArquivoTarefas();

        editarComboBoxPessoa.getItems().addAll(pessoas);
        editarComboBoxPessoa.setCellFactory(new Callback<>() {
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
        editarComboBoxPessoa.setButtonCell(new ListCell<>() {
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
        editarComboBoxPessoa.getSelectionModel().select(0);
        editarComboBoxTarefa.getItems().addAll(tarefas);
        editarComboBoxTarefa.setCellFactory(new Callback<>() {
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
        editarComboBoxTarefa.setButtonCell(new ListCell<>() {
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
        editarComboBoxTarefa.getSelectionModel().select(0);

        if(cronograma == null){
            return;
        }

        editarDataTarefa.setText(cronograma.getDataCronograma());
        editarHorarioEntrada.setText(String.valueOf(cronograma.getInicioCronograma()));
        editarHorarioSaida.setText(String.valueOf(cronograma.getTerminoCronograma()));

        for(Tarefa tarefa : tarefas){
            if(tarefa.getNomeTarefa().equals(cronograma.getTarefaCronograma())){
                editarComboBoxTarefa.getSelectionModel().select(tarefa);
            }
        }

        for(Pessoa pessoa : pessoas){
            if(pessoa.getNomePessoa().equals(cronograma.getPessoaCronograma())){
                idPessoaTarefa = pessoa.getIdPessoa();
                editarComboBoxPessoa.getSelectionModel().select(pessoa);
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
    public void salvarEdicaoListaCronograma() throws IOException {
        Pessoa pessoaSelecionadaParaTarefa = editarComboBoxPessoa.getValue();
        Cronograma cronograma = new Cronograma(editarDataTarefa.getText(),
                pessoaSelecionadaParaTarefa,
                editarComboBoxTarefa.getValue(),
                editarComboBoxTarefa.getValue().getValorTarefa(),
                editarHorarioEntrada.getText(),
                editarHorarioSaida.getText());
        ControladorDeDadosCronograma controladorDeDadosCronograma = new ControladorDeDadosCronograma();
        controladorDeDadosCronograma.criarNovaCronograma(cronograma);

        CronogramaPessoa cronogramaPessoa = new CronogramaPessoa(cronograma.getIdCronograma(), pessoaSelecionadaParaTarefa.getIdPessoa());
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

        Pessoa novaPessoaSelecionada = editarComboBoxPessoa.getValue();

        cronograma.setDataCronograma(editarDataTarefa.getText());
        cronograma.setPessoaCronograma(novaPessoaSelecionada.getNomePessoa());
        cronograma.setInicioCronograma(editarHorarioEntrada.getText());
        cronograma.setTerminoCronograma(editarHorarioSaida.getText());

        Tarefa tarefa = editarComboBoxTarefa.getValue();
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
        alerta.setHeaderText("Atualização do cronograma feita com sucesso!");
        alerta.setContentText("A atualização foi concluída!");
        alerta.show();

        ControladorDeCena.trocarCena("tela-lista-cronograma.fxml");
    }

}
