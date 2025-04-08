package main.Java.DAO;
import main.Java.Model.BaseModel;
import main.Java.Model.Credit;
import main.Java.Model.DAO;
import main.Java.Model.Dashboard;
import main.Java.Model.Depense;
import main.Java.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO implements DAO {

    @Override
   public void save(Connection connection, BaseModel m) throws Exception{
        Credit cr = (Credit) m;
            String request = "INSERT INTO credit (libelle , montantCredit) VALUES (? , ?) ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(request)){
                preparedStatement.setString(1,cr.getLibelle());
                preparedStatement.setInt(2, cr.getMontant_credit());
                preparedStatement.executeUpdate();
            }
    }
    public void SaveCredit(BaseModel m) throws  Exception{
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
        String request = "DELETE FROM credit WHERE idCredit = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                prstm.executeUpdate();
                System.out.println("Credit deleted successfully");
            }
        }
    }
    
    @Override 
    public void update(BaseModel m) throws Exception{
        Credit cr = (Credit) m;
        String request = "UPDATE credit SET libelle = ? , montantCredit = ? WHERE id = ? ";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setString(1, cr.getLibelle());
                prstm.setInt(2, cr.getMontant_credit());
                prstm.setInt(3, cr.getId());

                prstm.executeUpdate();
                System.out.println("credit update successfully");
            }
        }
    }

    @Override
    public List<BaseModel> findAll() throws Exception{
        List<BaseModel> cr = new ArrayList<>();
        try(Connection connexion = DB.connect()){
            String request = "SELECT * FROM credit";
            try(Statement stm = connexion.createStatement()){
                try(ResultSet rs = stm.executeQuery(request)){
                    while(rs.next()){
                        cr.add(new Credit(
                            rs.getInt("idCredit"),
                            rs.getString("libelle"), 
                            rs.getInt("montantCredit")));
                    }
                }
            }
        }
        return cr;
    }

    @Override
    public Credit findById(int id) throws Exception{
        String request = "SELECT * FROM credit WHERE idCredit = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                try(ResultSet rs = prstm.executeQuery()){
                    if(rs.next()){
                        return new Credit(rs.getInt("id"), 
                            rs.getString("libelle"),
                            rs.getInt("montantCredit"));
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
        System.err.println("Credit non trouve pour l'id"+id);
        return null;
    }
     public List<Dashboard> getDashBord() throws Exception{
        List<Dashboard> list = new ArrayList<>();
        DepenseDAO dep = new DepenseDAO();
        try(Connection connexion = DB.connect()){
            String request = "SELECT * FROM credit";
            try(Statement stm = connexion.createStatement()){
                try(ResultSet rs = stm.executeQuery(request)){
                    while(rs.next()){
                        int idCredit = (int) rs.getInt("idCredit");
                        String libelle =(String) rs.getString("libelle");
                        float montantCredit = rs.getInt("montantCredit");
                        Depense d =  new Depense(0, idCredit, 0);
                        float montantDepense = dep.calculSommeDepense(d);
                        float reste = dep.calculReste(d);
                        Dashboard dash = new Dashboard(idCredit, libelle, montantCredit, montantDepense, reste);
                        list.add(dash);

                    }
                }catch (SQLException e) {
                    throw new Exception("erreur sql"+e.getMessage());
                }
            }
            catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        }
        catch(Exception e){
            throw new Exception("Exception"+e.getMessage());
        }
        return list;
    }


   
}
