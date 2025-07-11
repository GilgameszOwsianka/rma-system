package pl.rma.repo;

import pl.rma.model.Reklamacja;

import java.util.List;
import java.util.Optional;

public interface Repozytorium {
    Reklamacja dodaj(Reklamacja reklamacja);
    Optional<Reklamacja> znajdzPoId(int id);
    List<Reklamacja> znajdzWszystkie();
    Reklamacja aktualizuj(Reklamacja reklamacja);
    boolean usun(int id);
}

