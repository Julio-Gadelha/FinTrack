package dio.fintrack.repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryGeneric<T> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public List<T> listAll() {
        return new ArrayList<>(items);
    }

    public void addAll(List<? extends T> newItems) {
        items.addAll(newItems);
    }

    public int total() {
        return items.size();
    }
}
