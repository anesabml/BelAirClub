package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class OperationResponsable {

    BdConnection connection = new BdConnection();

    public ArrayList<Membre> exportListMemberWith3Cancelation() throws SQLException {

        // Get all the members who canceled more then 3 times
        String sql = "SELECT * FROM membre WHERE membre.id IN (SELECT id_membre FROM reservation WHERE (SELECT SUM(annuler = true) FROM reservation WHERE reservation.id_membre = membre.id) >=3);";
        ResultSet resultSet = connection.getStatement().executeQuery(sql);

        ArrayList<Membre> membres = new ArrayList<Membre>();
        while (resultSet.next()) {
            Membre membre = new Membre();

            membre.setId(resultSet.getInt("id"));
            membre.setNom(resultSet.getString("nom"));
            membre.setPrenom(resultSet.getString("prenom"));
            membre.setEmail(resultSet.getString("email"));
            membre.setDateN(resultSet.getString("date_nais"));
            membre.setAdress(resultSet.getString("adres"));

            membres.add(membre);
        }

        return membres;
    }


    public ArrayList<Activite> getActivites() throws SQLException {
        String sql = "SELECT * FROM activite a;";
        ResultSet resultSet = connection.getStatement().executeQuery(sql);


        ArrayList<Activite> activites = new ArrayList<Activite>();
        while (resultSet.next()) {
            Activite activite = new Activite();
            activite.setCode(
                    resultSet.getInt("id")
            );
            activite.setDesignation(
                    resultSet.getString("Designation")
            );
            activite.setPrix(
                    resultSet.getFloat("prix")
            );
            activites.add(activite);
        }
        return activites;
    }

    public void deleteActivity(int id) throws SQLException {
        String sql = String.format("DELETE FROM activite WHERE id = %d", id);
        connection.getStatement().executeUpdate(sql);
    }

    public void modiferActivite(Activite activite) throws SQLException {
        String sql =
                String.format("UPDATE activite SET Designation = \"%s\", prix = %.2f WHERE id = %d;",
                        activite.getDesignation(),
                        activite.getPrix(),
                        activite.getCode());

        connection.getStatement().executeUpdate(sql);
    }

    public int ajouterActivite(Activite activite) throws SQLException {
        String sql =
                String.format("INSERT INTO activite(Designation, prix) VALUES (\"%s\", %f);",
                        activite.getDesignation(),
                        activite.getPrix());

        connection.getStatement().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        int activityId = -1;
        ResultSet generatedKeys = connection.getStatement().getGeneratedKeys();
        if (generatedKeys.next()) {
            activityId = generatedKeys.getInt(1);
        }
        return activityId;
    }

    public Activite getActivite(int id) throws SQLException {
        String sql =
                String.format("SELECT * FROM activite a ,creneaux c WHERE a.id = c.id_activite AND a.id = %d;", id);

        ResultSet resultSet = connection.getStatement().executeQuery(sql);
        resultSet.next();

        Activite activite = new Activite();
        activite.setCode(
                resultSet.getInt("id")
        );
        activite.setDesignation(
                resultSet.getString("Designation")
        );
        activite.setPrix(
                resultSet.getFloat("prix")
        );

        ArrayList<Creneaux> creneauxArrayList = new ArrayList<Creneaux>();

        Creneaux creneaux = new Creneaux();
        creneaux.setJour(resultSet.getString("jour"));
        creneaux.setHeureDebut(resultSet.getString("heureDebut"));
        creneaux.setHeureFin(resultSet.getString("heureFin"));
        creneaux.setNbPlace(resultSet.getInt("nbplace"));
        creneauxArrayList.add(creneaux);

        activite.setCreneaux(creneauxArrayList);

        return activite;
    }

    public void ajouterCreneaux(ArrayList<Creneaux> creneauxArrayList) throws SQLException {
        for (Creneaux creneaux : creneauxArrayList) {
            String sql =
                    String.format("INSERT INTO creneaux(id_activite, jour, heureDebut, heureFin, nbplace) VALUES (%d, \"%s\", \"%s\", \"%s\", %d);",
                            creneaux.getIdActivite(),
                            creneaux.getJour(),
                            creneaux.getHeureDebut(),
                            creneaux.getHeureFin(),
                            creneaux.getNbPlace());
            connection.getStatement().executeUpdate(sql);
        }
    }

    public void modifierCreneaux(ArrayList<Creneaux> creneauxArrayList, int activityId) throws SQLException {
        for (Creneaux creneaux : creneauxArrayList) {
            String sql =
                    String.format("UPDATE creneaux SET jour = \"%s\", heureDebut = \"%s\", heureFin = \"%s\", nbplace = %d WHERE id_activite = %d;",
                            creneaux.getJour(),
                            creneaux.getHeureDebut(),
                            creneaux.getHeureFin(),
                            creneaux.getNbPlace(),
                            activityId);
            connection.getStatement().executeUpdate(sql);
        }
    }

    public int[] getAllReservationsByMonth() throws SQLException {
        String sql = "SELECT DATE_FORMAT(date_res, '%m'), count(*) FROM reservation GROUP BY DATE_FORMAT(date_res, '%m');";
        ResultSet resultSet = connection.getStatement().executeQuery(sql);

        int[] reservationsCount = new int[12];

        Arrays.fill(reservationsCount, 0);

        while (resultSet.next()) {
            int month = resultSet.getInt(1);
            int count = resultSet.getInt(2);
            reservationsCount[month] = count;
        }

        return reservationsCount;
    }

    public int[] getAllLoginsByMonth() throws SQLException {
        String sql = "SELECT DATE_FORMAT(dateInsecription, '%m'), count(*) FROM membre GROUP BY DATE_FORMAT(dateInsecription, '%m');";
        ResultSet resultSet = connection.getStatement().executeQuery(sql);

        int[] loginsCount = new int[12];

        Arrays.fill(loginsCount, 0);

        while (resultSet.next()) {
            int month = resultSet.getInt(1);
            int count = resultSet.getInt(2);
            loginsCount[month - 1] = count;
        }

        return loginsCount;
    }
}
