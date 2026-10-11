package dio.fintrack.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        repositorio.adicionarTodos(List.of("A", "B", "C"));
        assertEquals(3, repositorio.total());
    }

    @Test
    void listaDeveComecarVazia() {
        assertEquals(0, repositorio.total());
        assertTrue(repositorio.listarTodos().isEmpty());
    }


    @Test
    void deveCopiarItensParaListaDeSuperTipo() {
        repositorio.adicionar("A");
        repositorio.adicionar("B");

        List<Object> destino = new ArrayList<>();
        repositorio.copiarPara(destino);

        assertEquals(2, destino.size());
        assertTrue(destino.contains("A"));
    }


    @Test
    void deveContarElementosDeListaDeQualquerTipo() {
        assertEquals(3, RepositorioGenerico.contar(List.of(1, 2, 3)));
        assertEquals(2, RepositorioGenerico.contar(List.of("x", "y")));
    }
}

