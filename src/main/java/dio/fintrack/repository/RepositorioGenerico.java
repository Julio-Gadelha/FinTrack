package dio.fintrack.repository;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGenerico<T> {

    private final List<T> items = new ArrayList<>();

    public void adicionar(T item) {
        items.add(item);
    }

    public void remover(T item) {
        items.remove(item);
    }

    public List<T> listarTodos() {
        return new ArrayList<>(items);
    }


    public void adicionarTodos(List<? extends T> newItems) {
        items.addAll(newItems);
    }


    public void copiarPara(List<? super T> destino) {
        destino.addAll(items);
    }


    public static int contar(List<?> lista) {
        return lista.size();
    }

    public int total() {
        return items.size();
    }
}