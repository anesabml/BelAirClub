package control.responsable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EspaceResponsableServlet", urlPatterns = "/EspaceResponsableServlet")
public class EspaceResponsableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        RequestDispatcher dispatcher;

        if (session == null || session.getAttribute("user") == null) {

            dispatcher = request.getRequestDispatcher("/AtentifResposable.html");
            dispatcher.forward(request, response);

        } else {

            dispatcher = request.getRequestDispatcher("/WEB-INF/MenuResponsable.html");
            dispatcher.forward(request, response);

        }
    }
}
