package pl.rma.repo;

import pl.rma.model.Reklamacja;

import java.util.*;

public class MemoryRepozytorium implements Repozytorium {

    private final Map<Integer, Reklamacja> storage = new HashMap<>();

    @Override
    public Reklamacja dodaj(Reklamacja reklamacja) {
        storage.put(reklamacja.getId(), reklamacja);
        return reklamacja;
    }

    @Override
    public Optional<Reklamacja> znajdzPoId(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Reklamacja> znajdzWszystkie() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Reklamacja aktualizuj(Reklamacja reklamacja) {
        if (!storage.containsKey(reklamacja.getId())) {
            throw new NoSuchElementException("Reklamacja o ID " + reklamacja.getId() + " nie istnieje");
        }
        storage.put(reklamacja.getId(), reklamacja);
        return reklamacja;
    }

    @Override
    public boolean usun(int id) {
        return storage.remove(id) != null;
    }
}

