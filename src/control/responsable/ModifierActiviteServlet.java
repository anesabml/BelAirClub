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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "ModifierActiviteServlet", urlPatterns = "/ModifierActiviteServlet")
public class ModifierActiviteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OperationResponsable operationResponsable = new OperationResponsable();
            int id = Integer.parseInt(req.getParameter("id"));
            Activite activite = operationResponsable.getActivite(id);
            HttpSession session = req.getSession();
            session.setAttribute("activite", activite);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/ModifierActivite.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // Get the parameters
            int activityId = Integer.parseInt(req.getParameter("id"));
            String designation = req.getParameter("designation");
            double prix = Double.parseDouble(req.getParameter("prix"));

            // Create activity with the given parameters
            Activite activite = new Activite();
            activite.setCode(activityId);
            activite.setDesignation(designation);
            activite.setPrix(prix);

            // Update the activity in the db
            OperationResponsable operationResponsable = new OperationResponsable();
            operationResponsable.modiferActivite(activite);

            // Time to update the crenaux
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
            // Update the list in db
            operationResponsable.modifierCreneaux(creneauxArrayList, activityId);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/ListActivite.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
