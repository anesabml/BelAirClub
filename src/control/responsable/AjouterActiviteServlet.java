package control.responsable;

import modele.Activite;
import modele.Creneaux;
import modele.OperationResponsable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "AjouterActiviteServlet", urlPatterns = "/AjouterActiviteServlet")
public class AjouterActiviteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/AjouterActivite.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // Get the parameters
            String designation = req.getParameter("designation");
            double prix = Double.parseDouble(req.getParameter("prix"));

            // Create activity with the given parameters
            Activite activite = new Activite();
            activite.setDesignation(designation);
            activite.setPrix(prix);

            // Add the activity to db and get the it's id
            OperationResponsable operationResponsable = new OperationResponsable();
            int activityId = operationResponsable.ajouterActivite(activite);

            // Time to add the crenaux
            ArrayList<Creneaux> creneauxArrayList = new ArrayList<Creneaux>();
            String[] jours = req.getParameterValues("jour[]");
            String[] heursDeDebut = req.getParameterValues("debut[]");
            String[] heursDeFin = req.getParameterValues("fin[]");
            String[] places = req.getParameterValues("places[]");

            // Create an array list of crenaux
            for (int i = 0; i < jours.length; i++) {
                Creneaux creneaux = new Creneaux(jours[i], heursDeDebut[i], heursDeFin[i], Integer.parseInt(places[i]), activityId);
                creneauxArrayList.add(creneaux);
            }
            // Add the list to db
            operationResponsable.ajouterCreneaux(creneauxArrayList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/ListActivite.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
