package pl.rma.model;

public class Reklamacja {
    private int id;
    private Produkt produkt;
    private Status status;
    private String opis;

    public Reklamacja() {
        // pusty konstruktor potrzebny dla Jacksona
    }

    public Reklamacja(int id, Produkt produkt, Status status, String opis) {
        this.id = id;
        this.produkt = produkt;
        this.status = status;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Reklamacja{" +
                "id=" + id +
                ", produkt=" + produkt +
                ", status=" + status +
                ", opis='" + opis + '\'' +
                '}';
    }
}
