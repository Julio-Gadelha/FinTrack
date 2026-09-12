package dio.fintrack.controller;

import dio.fintrack.dao.TransacaoDAO;
import dio.fintrack.model.Transacao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class TelaPrincipalController {

    @FXML
    private TableView<Transacao> tabelaTransacoes;

    @FXML
    private TableColumn<Transacao, String> colunaDescricao;

    @FXML
    private TableColumn<Transacao, String> colunaValor;

    @FXML
    private TableColumn<Transacao, String> colunaTipo;

    @FXML
    private TableColumn<Transacao, String> colunaData;

    @FXML
    private Label labelSaldo;

    private final TransacaoDAO dao = new TransacaoDAO();

    @FXML
    public void initialize() {
        colunaDescricao.setCellValueFactory(dadosCelula ->
                new SimpleStringProperty(dadosCelula.getValue().getDescricao()));

        colunaValor.setCellValueFactory(dadosCelula ->
                new SimpleStringProperty(dadosCelula.getValue().getValor().toString()));

        colunaTipo.setCellValueFactory(dadosCelula ->
                new SimpleStringProperty(dadosCelula.getValue().getTipo().name()));

        colunaData.setCellValueFactory(dadosCelula ->
                new SimpleStringProperty(dadosCelula.getValue().getData().toString()));

        atualizarTabela();
    }

    @FXML
    protected void atualizarTabela() {
        try {
            ObservableList<Transacao> lista = FXCollections.observableArrayList(dao.findAll());
            tabelaTransacoes.setItems(lista);
            labelSaldo.setText("Saldo atual: R$ " + dao.getSaldo());
        } catch (SQLException e) {
            mostrarErro("Erro ao carregar transações: " + e.getMessage());
        }
    }
    @FXML
    protected void abrirNovaTransacao() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/dio/fintrack/nova-transacao.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            scene.getStylesheets().add(getClass().getResource("/dio/fintrack/style.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Nova Transação");
            stage.setScene(scene);
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();

            atualizarTabela();
        } catch (Exception e) {
            mostrarErro("Erro ao abrir tela: " + e.getMessage());
        }
    }
    @FXML
    protected void abrirRelatorio() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/dio/fintrack/relatorio.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            scene.getStylesheets().add(getClass().getResource("/dio/fintrack/style.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Relatório");
            stage.setScene(scene);
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            mostrarErro("Erro ao abrir relatório: " + e.getMessage());
        }
    }

    @FXML
    protected void removerTransacao() {
        Transacao selecionada = tabelaTransacoes.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarInfo("Selecione uma transação para remover.");
            return;
        }
        try {
            dao.delete(selecionada.getId());
            atualizarTabela();
        } catch (SQLException e) {
            mostrarErro("Erro ao remover: " + e.getMessage());
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensagem);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem);
        alert.showAndWait();
    }
}