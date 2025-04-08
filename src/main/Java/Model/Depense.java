package main.Java.Model;

public class Depense extends BaseModel {
    int idCredit;
    int montantDepense;
    
    public Depense(int id, int idCredit, int montantDepense) {
        super(id);
        this.idCredit = idCredit;
        this.montantDepense = montantDepense;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public int getMontantDepense() {
        return montantDepense;
    }

    public void setMontantDepense(int montantDepense) {
        this.montantDepense = montantDepense;
    }
  
    @Override
    public String toString() {
        return "Medicament{" +
            "id=" + id +
            ", idCredit='" + idCredit + '\'' +
            ", montantCredit=" + montantDepense +
            '}';
    }
   
}
