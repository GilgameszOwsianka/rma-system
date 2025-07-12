package pl.rma.service;

import pl.rma.model.Reklamacja;
import pl.rma.model.Status;
import pl.rma.repo.Repozytorium;
import pl.rma.util.JsonFileUtils;


import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.IOException;

public class ReklamacjaService {

    private final Repozytorium repozytorium;

    public ReklamacjaService(Repozytorium repozytorium) {
        this.repozytorium = repozytorium;
    }

    public Reklamacja dodajReklamacje(Reklamacja reklamacja) {
        reklamacja.setStatus(Status.NOWA);
        return repozytorium.dodaj(reklamacja);
    }

    public Optional<Reklamacja> znajdzPoId(int id) {
        return repozytorium.znajdzPoId(id);
    }

    public List<Reklamacja> znajdzWszystkie() {
        return repozytorium.znajdzWszystkie();
    }

    public Reklamacja aktualizujReklamacje(Reklamacja reklamacja) {
        return repozytorium.aktualizuj(reklamacja);
    }

    public boolean usunReklamacje(int id) {
        return repozytorium.usun(id);
    }

    public void zapiszDoPlikuJson(String sciezkaPliku) throws IOException {
        List<Reklamacja> lista = repozytorium.znajdzWszystkie();
        JsonFileUtils.zapiszReklamacjeDoPliku(lista, sciezkaPliku);
    }

    public void wczytajZPlikuJson(String sciezkaPliku) throws IOException {
        List<Reklamacja> lista = JsonFileUtils.wczytajReklamacjeZPliku(sciezkaPliku);
        // Czyszczenie repozytorium i dodanie wczytanych (załóżmy, że repozytorium ma metodę czyszczącą)
        repozytorium.wyczysc();
        lista.forEach(repozytorium::dodaj);
    }
}

