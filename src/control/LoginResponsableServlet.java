package control;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginResponsableServlet", urlPatterns = "/LoginResponsableServlet")
public class LoginResponsableServlet extends HttpServlet {
    private String USERNAME = "Responsable";
    private String PASSWORD = "1234";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        RequestDispatcher dispatcher;
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {

            session.setAttribute("user", username);
            session.setAttribute("pass", password);

            dispatcher = request.getRequestDispatcher("/WEB-INF/MenuResponsable.html");
            dispatcher.forward(request, response);

        } else {

            try {
                int n = (Integer) session.getAttribute("NumbreLogin");

                if (n < 3) {
                    n++;
                    session.setAttribute("NumbreLogin", n);
                    dispatcher = request.getRequestDispatcher("AtentifResposable.html");
                    dispatcher.forward(request, response);
                } else {

                    PrintWriter out = response.getWriter();
                    out.println("error");

                }
            } catch (Exception e) {
                session.setAttribute("NumbreLogin", 1);
                dispatcher = request.getRequestDispatcher("AtentifResposable.html");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
