package pl.rma;

import pl.rma.model.Produkt;
import pl.rma.model.Reklamacja;
import pl.rma.model.Status;
import pl.rma.repo.MemoryRepozytorium;
import pl.rma.service.ReklamacjaService;

import java.text.MessageFormat;
import java.util.*;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ReklamacjaService service = new ReklamacjaService(new MemoryRepozytorium());
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.forLanguageTag("pl"));

    public static void main(String[] args) {
        try {
            service.wczytajZPlikuJson("reklamacje.json");
            System.out.println("Wczytano dane z pliku reklamacje.json");
        } catch (Exception e) {
            System.out.println("Nie udało się wczytać pliku. Zaczynam od pustej listy.");
        }

        // Ustalenie nextId na podstawie danych wczytanych z pliku
        int nextId = service.znajdzWszystkie().stream()
                .mapToInt(Reklamacja::getId)
                .max()
                .orElse(0) + 1;

        System.out.println();
        System.out.println(messages.getString("menu.title"));

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println(messages.getString("menu.options"));
            System.out.print(messages.getString("menu.choice"));
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // Dodawanie reklamacji
                    System.out.print(messages.getString("prompt.product"));
                    String nazwa = scanner.nextLine();

                    System.out.print(messages.getString("prompt.description"));
                    String opis = scanner.nextLine();

                    Produkt produkt = new Produkt(nextId, nazwa);
                    Reklamacja reklamacja = new Reklamacja(nextId, produkt, Status.NOWA, opis);
                    service.dodajReklamacje(reklamacja);
                    System.out.println(MessageFormat.format(messages.getString("info.added"), nextId));
                    nextId++;

                    zapisz();
                    break;

                case "2": // Wyświetlanie wszystkich
                    List<Reklamacja> wszystkie = service.znajdzWszystkie();
                    if (wszystkie.isEmpty()) {
                        System.out.println(messages.getString("info.noComplaints"));
                    } else {
                        wszystkie.forEach(System.out::println);
                    }
                    break;

                case "3": // Aktualizacja statusu
                    System.out.print(messages.getString("prompt.idUpdate"));
                    int idAkt = wczytajId();
                    if (idAkt == -1) break;

                    service.znajdzPoId(idAkt).ifPresentOrElse(r -> {
                        System.out.println("Aktualny status: " + r.getStatus());
                        System.out.print(messages.getString("prompt.newStatus"));
                        String nowyStatus = scanner.nextLine();
                        try {
                            Status status = Status.valueOf(nowyStatus.toUpperCase());
                            r.setStatus(status);
                            service.aktualizujReklamacje(r);
                            System.out.println(messages.getString("info.updated"));
                            zapisz();
                        } catch (IllegalArgumentException e) {
                            System.out.println(messages.getString("info.invalidStatus"));
                        }
                    }, () -> System.out.println(MessageFormat.format(messages.getString("info.notFound"), idAkt)));
                    break;

                case "4": // Usuwanie
                    System.out.print(messages.getString("prompt.idDelete"));
                    int idUsun = wczytajId();
                    if (idUsun == -1) break;

                    boolean usunieto = service.usunReklamacje(idUsun);
                    System.out.println(usunieto ? messages.getString("info.deleted") :
                            MessageFormat.format(messages.getString("info.notFound"), idUsun));
                    zapisz();
                    break;

                case "5": // Wyjście
                    System.out.println(messages.getString("info.goodbye"));
                    running = false;
                    break;

                case "6": // Aktualizacja opisu
                    System.out.print(messages.getString("prompt.idUpdate"));
                    int idOpis = wczytajId();
                    if (idOpis == -1) break;

                    System.out.print(messages.getString("prompt.newDescription"));
                    String nowyOpis = scanner.nextLine();
                    try {
                        service.aktualizujOpis(idOpis, nowyOpis);
                        System.out.println(messages.getString("info.updated"));
                        zapisz();
                    } catch (NoSuchElementException e) {
                        System.out.println(MessageFormat.format(messages.getString("info.notFound"), idOpis));
                    }
                    break;

                case "7": // Aktualizacja produktu
                    System.out.print(messages.getString("prompt.idUpdate"));
                    int idProdukt = wczytajId();
                    if (idProdukt == -1) break;

                    System.out.print(messages.getString("prompt.newProductName"));
                    String nazwaProduktu = scanner.nextLine();
                    Produkt nowyProdukt = new Produkt(idProdukt, nazwaProduktu);
                    try {
                        service.aktualizujProdukt(idProdukt, nowyProdukt);
                        System.out.println(messages.getString("info.updated"));
                        zapisz();
                    } catch (NoSuchElementException e) {
                        System.out.println(MessageFormat.format(messages.getString("info.notFound"), idProdukt));
                    }
                    break;

                default:
                    System.out.println(messages.getString("info.invalidChoice"));
            }
        }
    }

    private static int wczytajId() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(messages.getString("info.invalidChoice"));
            return -1;
        }
    }

    private static void zapisz() {
        try {
            service.zapiszDoPlikuJson("reklamacje.json");
        } catch (Exception e) {
            System.out.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }
}
