package control.responsable;

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


@WebServlet(name = "LoginStatisticsServlet", urlPatterns = "/LoginStatisticsServlet")
public class LoginStatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            OperationResponsable operationResponsable = new OperationResponsable();

            HttpSession session = req.getSession();
            int[] loginsByMonth = operationResponsable.getAllLoginsByMonth();
            session.setAttribute("loginsByMonth", loginsByMonth);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/LoginStatistics.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
