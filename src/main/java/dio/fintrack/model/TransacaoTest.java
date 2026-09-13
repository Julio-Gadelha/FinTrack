package dio.fintrack.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransacaoTest {

    @Test
    void deveCriarTransacaoComConstrutorCompleto() {
        LocalDate hoje = LocalDate.now();
        Transacao t = new Transacao("Salário", new BigDecimal("3000.00"), Tipo.RECEITA, hoje);

        assertEquals("Salário", t.getDescricao());
        assertEquals(0, new BigDecimal("3000.00").compareTo(t.getValor()));
        assertEquals(Tipo.RECEITA, t.getTipo());
        assertEquals(hoje, t.getData());
    }

    @Test
    void deveAlterarValoresComSetters() {
        Transacao t = new Transacao();
        t.setId(1);
        t.setDescricao("Mercado");
        t.setValor(new BigDecimal("150.00"));
        t.setTipo(Tipo.DESPESA);
        t.setData(LocalDate.of(2026, 1, 10));

        assertEquals(Integer.valueOf(1), t.getId());
        assertEquals("Mercado", t.getDescricao());
        assertEquals(0, new BigDecimal("150.00").compareTo(t.getValor()));
        assertEquals(Tipo.DESPESA, t.getTipo());
        assertEquals(LocalDate.of(2026, 1, 10), t.getData());
    }
}