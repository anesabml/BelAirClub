package control.membre;

import modele.Membre;
import modele.OperationMembre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ReservasionmembreServlet")
public class ReservasionmembreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        Membre membre= (Membre) session.getAttribute("membre");
        OperationMembre operationMembre=new OperationMembre();
        try {
            session.setAttribute("reservations",operationMembre.listReservatioMembre(membre));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/ListReservation.jsp");
        dispatcher.forward(request,response);
    }
}
