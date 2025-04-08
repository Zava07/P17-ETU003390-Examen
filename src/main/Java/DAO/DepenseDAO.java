package main.Java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Java.Model.BaseModel;
import main.Java.Model.Depense;
import main.Java.Model.DAO;
import main.Java.utils.DB;

public class DepenseDAO  implements DAO{
    public float getSommeDepense(Connection connection, BaseModel m) throws Exception{
        Depense dp = (Depense)m;
        float somme = 0;
            String request = "SELECT SUM(montantDepense) AS somme FROM depense WHERE idCredit = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(request)){
                preparedStatement.setInt(1, dp.getIdCredit());
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    somme = rs.getFloat("somme");
                }
            }catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        return somme;
    }
    public float calculSommeDepense(BaseModel m) throws Exception {
        Connection connection = null;
        float sommeDep = 0;

        try {
            connection = DB.connect();
            connection.setAutoCommit(false);
            sommeDep = getSommeDepense(connection, m);
            connection.commit();

        } catch (SQLException e) {
            assert connection != null;
            connection.rollback();
            throw new Exception("erreur sql" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return sommeDep;
    }
    public float getReste(Connection connexion, BaseModel m) throws Exception{
        Depense dp = (Depense)m;
        float sommeReste = 0;

            String request = "SELECT (SUM(c2.montantCredit) - SUM(depense.montantDepense)) AS reste\n" +
                              "FROM depense JOIN credit c2 ON c2.idCredit = depense.idCredit WHERE c2.idCredit = ?";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(request)){
                preparedStatement.setInt(1, dp.getIdCredit());
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    sommeReste = rs.getFloat("reste");
                }
            }catch(SQLException e){
            throw new Exception("Erreur sql "+e.getMessage());
            }
        return sommeReste;
    }
    public float calculReste(BaseModel m) throws Exception {
        Connection connection = null;
        float sommeReste = 0;
        try {
            connection = DB.connect();
            connection.setAutoCommit(false);
            sommeReste = getReste(connection, m);
            connection.commit();

        } catch (SQLException e) {
            assert connection != null;
            connection.rollback();
            throw new Exception("erreur sql" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return sommeReste;
    }
    @Override
   public void save(Connection connection, BaseModel m) throws Exception{
        Depense dp = (Depense) m;
        float sommeDepense = calculSommeDepense(m);
        float reste = calculReste(m);

        if (reste < sommeDepense){
            throw new Exception("Impossible d'inserer une depense");
        }
        try(Connection connexion = DB.connect()){
            String request = "INSERT INTO depense (idCredit , montantDepense) VALUES (? , ?) ";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(request)){
                preparedStatement.setInt(1,dp.getIdCredit());
                preparedStatement.setInt(2, dp.getMontantDepense());
                preparedStatement.executeUpdate();
            }
        }
    }
    public void saveDepense(BaseModel m) throws Exception {
        Connection connection = null;

        try{
            connection = DB.connect();
            connection.setAutoCommit(false);
            save(connection, m);
            connection.commit();
        }catch(SQLException e){
            assert connection != null;
            connection.rollback();
            throw new Exception("erreur sql"+e.getMessage());
        }finally {
            if (connection !=null){
                connection.close();
            }
        }
    }
    @Override
    public void delete(int id) throws Exception{
        String request = "DELETE FROM depense WHERE idDepense = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                prstm.executeUpdate();
                System.out.println("Depense deleted successfully");
            }
        }
    }
    @Override 
    public void update(BaseModel m) throws Exception{
        Depense dp = (Depense) m;
        String request = "UPDATE depense SET idCredit = ? , montantDepense = ? WHERE id = ? ";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, dp.getIdCredit());
                prstm.setInt(2, dp.getMontantDepense());
                prstm.setInt(3, dp.getId());

                prstm.executeUpdate();
                System.out.println("Depense update successfully");
            }
        }
    }

    @Override
    public List<BaseModel> findAll() throws Exception{
        List<BaseModel> cr = new ArrayList<>();
        try(Connection connexion = DB.connect()){
            String request = "SELECT * FROM depense";
            try(Statement stm = connexion.createStatement()){
                try(ResultSet rs = stm.executeQuery(request)){
                    while(rs.next()){
                        cr.add(new Depense(
                            rs.getInt("idDepense"),
                            rs.getInt("idCredit"), 
                            rs.getInt("montantDepense")));
                    }
                }
            }
        }
        return cr;
    }

    @Override
    public Depense findById(int id) throws Exception{
        String request = "SELECT * FROM depense WHERE idDepense = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                try(ResultSet rs = prstm.executeQuery()){
                    if(rs.next()){
                        return new Depense(rs.getInt("id"), 
                            rs.getInt("idCredit"),
                            rs.getInt("montantDepense"));
                    }
                }catch (SQLException e) {
                    throw new Exception("erreur sql"+e.getMessage());
                }
            }catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        }catch(Exception e){
            throw new Exception("Exception"+e.getMessage());
        }
        System.err.println("Depense non trouve pour l'id"+id);
        return null;
    }

}
