package pl.rma.model;

public class Produkt {
    private int id;
    private String nazwa;

    public Produkt(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    // Gettery i settery

    public int getId() {
        return id;
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
