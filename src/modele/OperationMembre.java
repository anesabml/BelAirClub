package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationMembre {
    public Membre loginMembre(String email, String password) throws SQLException {

        String sql = "select  * from  membre where email='" + email + "' and password='" + password + "'";
        BdConnection bdConnection = new BdConnection();
        ResultSet resultSet = bdConnection.getStatement().executeQuery(sql);
        Membre membre = new Membre();
        if (resultSet.next()) {
            membre.setId(resultSet.getInt("id"));
            membre.setNom(resultSet.getString("nom"));
            membre.setPrenom(resultSet.getString("prenom"));
            membre.setEmail(email);
            membre.setPassWord(password);
            membre.setAdress(resultSet.getString("adres"));
            membre.setDateN(resultSet.getDate("date_nais").toString());
            membre.setCompteloque(resultSet.getBoolean("comtebloque"));
            membre.setPhoto(resultSet.getString("photo"));
            membre.setDocRes(resultSet.getString("docRes"));
            membre.setPhotoidenti(resultSet.getString("photoidenti"));
            bdConnection.getStatement().close();
            bdConnection.getConnection().close();
            return membre;
        } else {
            bdConnection.getStatement().close();
            bdConnection.getConnection().close();
            return null;
        }


    }

    public int reservir(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservation(code,date_res,confirme,annuler,enattend,id_membre,id_creneau)select '" +
                reservation.getCode() +
                "','" + reservation.getDate() +
                "'," + reservation.isComfirme() +
                "," + reservation.isAnnule() +
                "," + reservation.isAttend() +
                "," + reservation.getIdMembre() +
                "," + reservation.getIdCreneau() + " from dual where not exists (select * from reservation where id_membre="
                + reservation.getIdMembre() + " and id_creneau=" + reservation.getIdCreneau() + " and annuler=false )";
        BdConnection bdConnection = new BdConnection();
        int resultSet = bdConnection.getStatement().executeUpdate(sql);
        bdConnection.getConnection().close();
        bdConnection.getStatement().close();
        return resultSet;
    }

    public ArrayList<CollectionARC> listReservatioMembre(Membre membre) throws SQLException {
        BdConnection bdConnection = new BdConnection();
        String sql = "select * from reservation,activite,creneaux where id_membre=" +
                membre.getId() + " and annuler=false and reservation.id_creneau=creneaux.id_c and creneaux.id_activite=activite.id ";
        ResultSet resultSet = bdConnection.getStatement().executeQuery(sql);
        ArrayList<CollectionARC> reservations = new ArrayList<CollectionARC>();
        while (resultSet.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getInt("id_res"));
            reservation.setCode(resultSet.getString("code"));
            reservation.setDate(resultSet.getString("date_res"));
            reservation.setComfirme(resultSet.getBoolean("confirme"));
            reservation.setAttend(resultSet.getBoolean("enattend"));
            reservation.setIdMembre(membre.getId());
            reservation.setIdCreneau(resultSet.getInt("id_c"));
            Activite activite=new Activite();
            activite.setDesignation(resultSet.getString("Designation"));
            activite.setPrix(resultSet.getFloat("prix"));
            Creneaux creneaux=new Creneaux();
            creneaux.setJour(resultSet.getString("jour"));
            creneaux.setHeure(resultSet.getString("heure"));
            CollectionARC collectionARC=new CollectionARC();
            collectionARC.setActivite(activite);
            collectionARC.setReservation(reservation);
            collectionARC.setCreneaux(creneaux);
            reservations.add(collectionARC);
        }
        return reservations;
    }
     public  ArrayList<Activite> creneauxLibre() throws SQLException {
         ArrayList<Activite> activites = new ArrayList<Activite>();
         BdConnection bdConnection = new BdConnection();
         String sql = "  select *,(select count(id_creneau) from reservation where reservation.id_creneau=creneaux.id_c and annuler=false) as A from creneaux,activite where creneaux.id_activite=activite.id and (nbplace>(select count(id_creneau) from reservation where reservation.id_creneau=creneaux.id_c and annuler=false) or (select count(id_creneau) from reservation where id_res=creneaux.id_c and annuler=false and enattend=true)<4)" ;;
         ResultSet rsultate = bdConnection.getStatement().executeQuery(sql);
         getActivite(activites, rsultate);
         bdConnection.getStatement().close();
         bdConnection.getConnection().close();
         return activites;
     }

    public  void getActivite(ArrayList<Activite> activites, ResultSet rsultate) throws SQLException {
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
                creneaux.setNbPlace(rsultate.getInt("nbplace")-rsultate.getInt("A"));
                activite.getCreneaux().add(creneaux);


            } else {
                activite.setCode(id);
                activite.setDesignation(rsultate.getString("Designation"));
                activite.setPrix(rsultate.getFloat("prix"));
                creneaux.setId(rsultate.getInt("id_c"));
                creneaux.setJour(rsultate.getString("jour"));
                creneaux.setHeure(rsultate.getString("heure"));
                creneaux.setNbPlace(rsultate.getInt("nbplace")-rsultate.getInt("A"));
                creneaux.setId_activite(rsultate.getInt("id_activite"));
                activite.getCreneaux().add(creneaux);
                activites.add(activite);
            }


        }
    }

    public  void comfirmeReservasion(Reservation reservation) throws SQLException {
        BdConnection bdConnection =new BdConnection();
        String sql="update reservation set confirme=true where id_res="+reservation.getId();
        bdConnection.getStatement().executeUpdate(sql);
    }
    public  void annulerReservasion(Reservation reservation) throws SQLException {
        BdConnection bdConnection =new BdConnection();
        String sql="update reservation set annuler=true where id_res="+reservation.getId();

        bdConnection.getStatement().executeUpdate(sql);
    }
    public Creneaux getCreneaux (int id) {
        BdConnection bdConnection = new BdConnection();
        String sql="select * ,(select count(id_creneau) from reservation where reservation.id_creneau=creneaux.id_c and annuler=false) as A  from creneaux where id_c="+id;
       ResultSet resultSet ;
        try {
            resultSet= bdConnection.getStatement().executeQuery(sql);
            Creneaux creneaux=new Creneaux();
            while (resultSet.next())
            {
                creneaux.setNbPlace(resultSet.getInt("nbplace")-resultSet.getInt("A"));
            }

            return creneaux;
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }
}