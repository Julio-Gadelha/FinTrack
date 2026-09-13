package dio.fintrack.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryGenericTest {

    private RepositorioGenerico<String> repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new RepositorioGenerico<>();
    }

    @Test
    void deveAdicionarItem() {
        repositorio.adicionar("Item 1");
        assertEquals(1, repositorio.total());
    }

    @Test
    void deveRemoverItem() {
        repositorio.adicionar("Item 1");
        repositorio.remover("Item 1");
        assertEquals(0, repositorio.total());
    }

    @Test
    void deveListarTodosOsItens() {
        repositorio.adicionar("Item 1");
        repositorio.adicionar("Item 2");

        List<String> lista = repositorio.listarTodos();

        assertEquals(2, lista.size());
        assertTrue(lista.contains("Item 1"));
        assertTrue(lista.contains("Item 2"));
    }

    @Test
    void deveAdicionarVariosItensDeUmaVez() {
        repositorio.AdicionarTodos(List.of("A", "B", "C"));
        assertEquals(3, repositorio.total());
    }

    @Test
    void listaDevecomecarVazia() {
        assertEquals(0, repositorio.total());
        assertTrue(repositorio.listarTodos().isEmpty());
    }
}
