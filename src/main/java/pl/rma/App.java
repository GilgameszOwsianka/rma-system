package pl.rma;

import pl.rma.model.Produkt;
import pl.rma.model.Reklamacja;
import pl.rma.model.Status;
import pl.rma.repo.MemoryRepozytorium;
import pl.rma.service.ReklamacjaService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ReklamacjaService service = new ReklamacjaService(new MemoryRepozytorium());
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale("pl"));

    public static void main(String[] args) {
        System.out.println(messages.getString("menu.title"));
        boolean running = true;
        int nextId = 1;

        while (running) {
            System.out.println();
            System.out.println(messages.getString("menu.options"));
            System.out.print(messages.getString("menu.choice"));

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print(messages.getString("prompt.product"));
                    String nazwa = scanner.nextLine();

                    System.out.print(messages.getString("prompt.description"));
                    String opis = scanner.nextLine();

                    Produkt produkt = new Produkt(nextId, nazwa);
                    Reklamacja reklamacja = new Reklamacja(nextId, produkt, Status.NOWA, opis);
                    service.dodajReklamacje(reklamacja);
                    System.out.println(MessageFormat.format(messages.getString("info.added"), nextId));
                    nextId++;
                    break;

                case "2":
                    List<Reklamacja> wszystkie = service.znajdzWszystkie();
                    if (wszystkie.isEmpty()) {
                        System.out.println(messages.getString("info.noComplaints"));
                    } else {
                        wszystkie.forEach(System.out::println);
                    }
                    break;

                case "3":
                    System.out.print(messages.getString("prompt.idUpdate"));
                    int idAkt;
                    try {
                        idAkt = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(messages.getString("info.invalidChoice"));
                        break;
                    }

                    service.znajdzPoId(idAkt).ifPresentOrElse(r -> {
                        System.out.println("Aktualny status: " + r.getStatus());
                        System.out.print(messages.getString("prompt.newStatus"));
                        String nowyStatus = scanner.nextLine();
                        try {
                            Status status = Status.valueOf(nowyStatus.toUpperCase());
                            r.setStatus(status);
                            service.aktualizujReklamacje(r);
                            System.out.println(messages.getString("info.updated"));
                        } catch (IllegalArgumentException e) {
                            System.out.println(messages.getString("info.invalidStatus"));
                        }
                    }, () -> System.out.println(MessageFormat.format(messages.getString("info.notFound"), idAkt)));
                    break;

                case "4":
                    System.out.print(messages.getString("prompt.idDelete"));
                    int idUsun;
                    try {
                        idUsun = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(messages.getString("info.invalidChoice"));
                        break;
                    }

                    boolean usunieto = service.usunReklamacje(idUsun);
                    System.out.println(usunieto ? messages.getString("info.deleted") :
                            MessageFormat.format(messages.getString("info.notFound"), idUsun));
                    break;

                case "5":
                    running = false;
                    System.out.println(messages.getString("info.goodbye"));
                    break;

                default:
                    System.out.println(messages.getString("info.invalidChoice"));
            }
        }
    }
}


