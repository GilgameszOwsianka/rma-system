package pl.rma.service;

import org.junit.Before;
import org.junit.Test;
import pl.rma.model.Produkt;
import pl.rma.model.Reklamacja;
import pl.rma.model.Status;
import pl.rma.repo.MemoryRepozytorium;

import static org.junit.Assert.*;

public class ReklamacjaServiceTest {

    private ReklamacjaService service;

    @Before
    public void setUp() {
        service = new ReklamacjaService(new MemoryRepozytorium());
    }

    @Test
    public void testDodajReklamacjePowinienDodacNowaReklamacje() {
        Produkt produkt = new Produkt(1, "Laptop");
        Reklamacja reklamacja = new Reklamacja(1, produkt, Status.NOWA, "Nie działa ekran");

        Reklamacja dodana = service.dodajReklamacje(reklamacja);

        assertNotNull(dodana);
        assertEquals("Laptop", dodana.getProdukt().getNazwa());
        assertEquals(Status.NOWA, dodana.getStatus());
    }

    @Test(expected = NullPointerException.class)
    public void testDodajReklamacjeNullPowinnoRzucicWyjatek() {
        service.dodajReklamacje(null);
    }

    @Test
    public void testAktualizujStatus() {
        Produkt produkt = new Produkt(1, "Telefon");
        Reklamacja reklamacja = new Reklamacja(1, produkt, Status.NOWA, "Bateria się szybko rozładowuje");

        service.dodajReklamacje(reklamacja);

        // zmiana statusu
        reklamacja.setStatus(Status.W_TRAKCIE);
        service.aktualizujReklamacje(reklamacja);

        Reklamacja aktualizowana = service.znajdzPoId(1).orElse(null);
        assertNotNull(aktualizowana);
        assertEquals(Status.W_TRAKCIE, aktualizowana.getStatus());
    }

    @Test
    public void testUsunNieistniejacaReklamacjeZwracaFalse() {
        boolean wynik = service.usunReklamacje(999);
        assertFalse(wynik);
    }
}
