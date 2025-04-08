package main.Java.Model;

public class Credit extends BaseModel {
    String libelle ; 
    int montant_credit;

    public Credit(int id, String libelle, int montant_credit) {
        super(id);
        this.libelle = libelle;
        this.montant_credit = montant_credit;
    }

    @Override
    public String toString() {
        return "Medicament{" +
            "id=" + id +
            ", name='" + libelle + '\'' +
            ", Dept_id=" + montant_credit +
            '}';
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMontant_credit() {
        return montant_credit;
    }

    public void setMontant_credit(int montant_credit) {
        this.montant_credit = montant_credit;
    }
}
