package control.membre;

import modele.OperationMembre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreneauxLibreServlet")
public class CreneauxLibreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperationMembre operationMembre=new OperationMembre();
        try {
            request.setAttribute("activiteLibre",operationMembre.creneauxLibre());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/activiteLibre.jsp");
        dispatcher.forward(request,response);

    }
}
