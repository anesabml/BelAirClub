package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationVisiteur {
    public boolean adherer(Membre m) throws SQLException {
        BdConnection con = new BdConnection();
        String sql = "INSERT INTO membre (nom, prenom,date_nais,adres,dateInsecription,email,password,photo,docRes,photoidenti) select '"
                + m.getNom()
                + "','" + m.getPrenom()
                + "','" + m.getDateN()
                + "','" + m.getAdress() +
                "','" + m.getDateInsecription() +
                "','" + m.getEmail() +
                "','" + m.getPassWord() +
                "','" + m.getPhoto() +
                "','" + m.getDocRes() +
                "','" + m.getPhotoidenti() +
                "' from  dual  where not exists (select * from membre where email='" + m.getEmail()+"')";


            int nbRow = con.getStatement().executeUpdate(sql);
        con.getStatement().close();
        con.getConnection().close();
            if (nbRow == 0)
                return false;
            else
                return true;

    }
    public int getIdMembre (Membre m) throws SQLException{
        BdConnection con=new BdConnection();
        String sql ="select id from membre where email='"+m.getEmail()+"'";
        ResultSet resultSet=con.getStatement().executeQuery(sql);
        resultSet.next();
        int id =resultSet.getInt("id");
        con.getStatement().close();
        con.getConnection().close();
        return id;
    }
    public ArrayList<Activite> consulter() throws SQLException {
        ArrayList<Activite> activites = new ArrayList<Activite>();
        BdConnection con = new BdConnection();
        String sql = "select * from activite a ,creneaux c where a.id=c.id_activite ";
        ResultSet rsultate = con.getStatement().executeQuery(sql);
        OperationMembre operationMembre = new OperationMembre();

        while (rsultate.next()) {
            Activite activite = new Activite();
            Creneaux creneaux = new Creneaux();
            int id = rsultate.getInt("id");

            if ((!activites.isEmpty()) && (activites.get(activites.size() - 1).getCode() == id)) {
                activite = (activites.get(activites.size() - 1));
                creneaux.setId(rsultate.getInt("id_c"));
                creneaux.setId_activite(rsultate.getInt("id_activite"));
                creneaux.setJour(rsultate.getString("jour"));
                creneaux.setHeure(rsultate.getString("heure"));
                activite.getCreneaux().add(creneaux);


            } else {
                activite.setCode(id);
                activite.setDesignation(rsultate.getString("Designation"));
                activite.setPrix(rsultate.getFloat("prix"));
                creneaux.setId(rsultate.getInt("id_c"));
                creneaux.setJour(rsultate.getString("jour"));
                creneaux.setHeure(rsultate.getString("heure"));
                creneaux.setId_activite(rsultate.getInt("id_activite"));
                activite.getCreneaux().add(creneaux);
                activites.add(activite);
            }}


            return activites;

    }
}
