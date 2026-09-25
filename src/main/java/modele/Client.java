package modele;

public class Client {

    private final String nom;
    private final String numeroPermis;

    public Client(String nom, String numeroPermis) {
        this.nom = nom;
        this.numeroPermis = numeroPermis;
    }

    public String getNom() {
        return nom;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    @Override
    public String toString() {
        return "Client : " + nom + " | Permis : " + numeroPermis;
    }
}