package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);
//       String user=(String) session.getAttribute("user");
        if ((session == null) || (session.getAttribute("user") == null)) {
            dispatcher = request.getRequestDispatcher("AtentifResposable.html");
            dispatcher.forward(request, response);
        } else {

            request.setAttribute("name", session.getAttribute("user"));

            session.invalidate();
            dispatcher = request.getRequestDispatcher("WEB-INF/Logout.jsp");
            dispatcher.forward(request, response);

        }
    }
}
