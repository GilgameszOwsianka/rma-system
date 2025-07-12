package pl.rma.model;

public class Produkt {
    private int id;
    private String nazwa;

    public Produkt() {
        // pusty konstruktor potrzebny dla Jacksona
    }

    public Produkt(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
