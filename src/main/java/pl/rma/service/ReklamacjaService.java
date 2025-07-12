package pl.rma.service;

import pl.rma.model.Produkt;
import pl.rma.model.Reklamacja;
import pl.rma.repo.Repozytorium;
import pl.rma.util.JsonFileUtils;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ReklamacjaService {

    private final Repozytorium repozytorium;

    public ReklamacjaService(Repozytorium repozytorium) {
        this.repozytorium = repozytorium;
    }

    public Reklamacja dodajReklamacje(Reklamacja reklamacja) {
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

    public void wyczyscRepozytorium() {
        if (repozytorium instanceof pl.rma.repo.MemoryRepozytorium) {
            repozytorium.wyczysc();
        } else {
            throw new UnsupportedOperationException("Metoda wyczyscRepozytorium jest obsługiwana tylko dla MemoryRepozytorium");
        }
    }

    public void zapiszDoPlikuJson(String sciezkaPliku) throws IOException {
        List<Reklamacja> lista = repozytorium.znajdzWszystkie();
        JsonFileUtils.zapiszReklamacjeDoPliku(lista, sciezkaPliku);
        System.out.println("Zapisano reklamacje do pliku: " + lista.size());
    }

    public void wczytajZPlikuJson(String sciezkaPliku) throws IOException {
        List<Reklamacja> lista = JsonFileUtils.wczytajReklamacjeZPliku(sciezkaPliku);
        wyczyscRepozytorium();
        lista.forEach(repozytorium::dodaj);
        System.out.println("Wczytano reklamacje z pliku: " + lista.size());
    }

    public Reklamacja aktualizujOpis(int id, String nowyOpis) {
        Reklamacja r = repozytorium.znajdzPoId(id)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono reklamacji o ID: " + id));
        r.setOpis(nowyOpis);
        return repozytorium.aktualizuj(r);
    }

    public Reklamacja aktualizujProdukt(int id, Produkt nowyProdukt) {
        Reklamacja r = repozytorium.znajdzPoId(id)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono reklamacji o ID: " + id));
        r.setProdukt(nowyProdukt);
        return repozytorium.aktualizuj(r);
    }
}
