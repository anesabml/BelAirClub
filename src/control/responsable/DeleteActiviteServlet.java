package control.responsable;

import modele.OperationResponsable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "DeleteActiviteServlet", urlPatterns = "/DeleteActiviteServlet")
public class DeleteActiviteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            OperationResponsable operationResponsable = new OperationResponsable();
            operationResponsable.deleteActivity(id);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/ListActivite.jsp");
            // Todo
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
