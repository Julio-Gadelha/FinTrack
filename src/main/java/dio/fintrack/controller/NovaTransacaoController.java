package dio.fintrack.controller;

import dio.fintrack.dao.TransacaoDAO;
import dio.fintrack.model.Tipo;
import dio.fintrack.model.Transacao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class NovaTransacaoController {

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoValor;

    @FXML
    private ComboBox<Tipo> campoTipo;

    @FXML
    private DatePicker campoData;

    private final TransacaoDAO dao = new TransacaoDAO();

    @FXML
    public void initialize() {
        campoTipo.setItems(FXCollections.observableArrayList(Tipo.values()));
        campoData.setValue(LocalDate.now());
    }

    @FXML
    protected void salvar() {
        String descricao = campoDescricao.getText();
        String valorTexto = campoValor.getText();
        Tipo tipo = campoTipo.getValue();
        LocalDate data = campoData.getValue();

        if (descricao == null || descricao.isBlank() ||
                valorTexto == null || valorTexto.isBlank() ||
                tipo == null || data == null) {
            mostrarErro("Preencha todos os campos.");
            return;
        }

        BigDecimal valor;
        try {
            valor = new BigDecimal(valorTexto.replace(",", "."));
        } catch (NumberFormatException e) {
            mostrarErro("Valor inválido. Use apenas números (ex: 150.00).");
            return;
        }

        try {
            dao.insert(new Transacao(descricao, valor, tipo, data));
            fecharJanela();
        } catch (SQLException e) {
            mostrarErro("Erro ao salvar: " + e.getMessage());
        }
    }

    @FXML
    protected void cancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) campoDescricao.getScene().getWindow();
        stage.close();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensagem);
        alert.showAndWait();
    }
}