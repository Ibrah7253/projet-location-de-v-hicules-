package modele;

public class Moto extends Vehicule {

    private final int cylindree;

    public Moto(String immatriculation, int cylindree) {
        super(immatriculation);
        this.cylindree = cylindree;
    }

    public int getCylindree() {
        return cylindree;
    }

    @Override
    public String toString() {
        return "Moto {" +
                "immatriculation='" + getImmatriculation() + '\'' +
                ", cylindree=" + cylindree +
                ", disponible=" + estDisponible() +
                '}';
    }
}