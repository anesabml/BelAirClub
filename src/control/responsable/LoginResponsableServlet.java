package control.responsable;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginResponsableServlet", urlPatterns = "/LoginResponsableServlet")
public class LoginResponsableServlet extends HttpServlet {
    private String USERNAME = "Responsable";
    private String PASSWORD = "1234";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        RequestDispatcher dispatcher;

        // If the inputs are correct,
        // navigate to the menu page,
        // and create a session
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {

            session.setAttribute("user", username);
            session.setAttribute("pass", password);

            dispatcher = request.getRequestDispatcher("/WEB-INF/MenuResponsable.html");
            dispatcher.forward(request, response);

        } else {
            // If the inputs are not correct,
            // return back to the login page
            dispatcher = request.getRequestDispatcher("AtentifResposable.html");
            dispatcher.forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
