package modele;

public class Voiture extends Vehicule {

    private final int nombrePlaces;

    public Voiture(String immatriculation, int nombrePlaces) {
        super(immatriculation);
        this.nombrePlaces = nombrePlaces;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    @Override
    public String toString() {
        return "Voiture {" +
                "immatriculation='" + getImmatriculation() + '\'' +
                ", nombrePlaces=" + nombrePlaces +
                ", disponible=" + estDisponible() +
                '}';
    }
}