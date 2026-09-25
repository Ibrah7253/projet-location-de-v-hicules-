package modele;

public abstract class Vehicule {

    private final String immatriculation;
    private boolean disponible;

    protected Vehicule(String immatriculation) {
        this.immatriculation = immatriculation;
        this.disponible = true;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public boolean estDisponible() {
        return disponible;
    }

    public void louer() {
        disponible = false;
    }

    public void rendreDisponible() {
        disponible = true;
    }
}