package dio.fintrack.dao;

import dio.fintrack.model.Tipo;
import dio.fintrack.model.Transacao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoDAOTest {


    private static final String URL_TESTE = "jdbc:sqlite:file::memory:?cache=shared";

    private Connection conexaoAncora;
    private TransacaoDAO dao;

    @BeforeEach
    void setUp() throws SQLException {
        conexaoAncora = DriverManager.getConnection(URL_TESTE);
        dao = new TransacaoDAO(URL_TESTE);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conexaoAncora.close();
    }

    @Test
    void deveInserirEBuscarTransacao() throws SQLException {
        dao.insert(new Transacao("Salário", new BigDecimal("3000.00"), Tipo.RECEITA, LocalDate.now()));

        List<Transacao> transacoes = dao.findAll();

        assertEquals(1, transacoes.size());
        assertEquals("Salário", transacoes.get(0).getDescricao());
        assertEquals(Tipo.RECEITA, transacoes.get(0).getTipo());
    }

    @Test
    void deveAtualizarTransacao() throws SQLException {
        dao.insert(new Transacao("Mercado", new BigDecimal("100.00"), Tipo.DESPESA, LocalDate.now()));
        Transacao salva = dao.findAll().get(0);

        salva.setDescricao("Mercado Atualizado");
        salva.setValor(new BigDecimal("200.00"));
        dao.update(salva);

        Transacao atualizada = dao.findAll().get(0);
        assertEquals("Mercado Atualizado", atualizada.getDescricao());
        assertEquals(0, new BigDecimal("200.00").compareTo(atualizada.getValor()));
    }

    @Test
    void deveExcluirTransacao() throws SQLException {
        dao.insert(new Transacao("Aluguel", new BigDecimal("800.00"), Tipo.DESPESA, LocalDate.now()));
        Transacao salva = dao.findAll().get(0);

        dao.delete(salva.getId());

        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void deveCalcularSaldoCorretamente() throws SQLException {
        dao.insert(new Transacao("Salário", new BigDecimal("3000.00"), Tipo.RECEITA, LocalDate.now()));
        dao.insert(new Transacao("Mercado", new BigDecimal("450.00"), Tipo.DESPESA, LocalDate.now()));

        assertEquals(0, new BigDecimal("3000.00").compareTo(dao.getTotalReceitas()));
        assertEquals(0, new BigDecimal("450.00").compareTo(dao.getTotalDespesas()));
        assertEquals(0, new BigDecimal("2550.00").compareTo(dao.getSaldo()));
    }

    @Test
    void deveLancarExcecaoAoBuscarComTipoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Tipo.valueOf("INEXISTENTE"));
    }
}
