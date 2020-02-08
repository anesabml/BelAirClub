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

@WebServlet(name = "LoginMembreServlet", urlPatterns = "/LoginMembreServlet")
public class LoginMembreServlet extends HttpServlet {
    final String pathFoder = "D:\\uploaded_files";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);
        String email = request.getParameter("email");
        String passWord = request.getParameter("password");
        if (session != null) {
            try {
                Membre membre = (Membre) session.getAttribute("membre");
                dispatcher = request.getRequestDispatcher("/WEB-INF/EspaceMembre.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                if (email == null) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/LoginMembre.html");
                    dispatcher.forward(request, response);
                } else {
                    try {
                        login(request, response, email, passWord);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } else {
            if (email == null) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/LoginMembre.html");
                dispatcher.forward(request, response);
            } else {
                try {
                    login(request, response, email, passWord);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response, String email, String passWord) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher;
        OperationMembre operationMembre = new OperationMembre();
        HttpSession session = request.getSession();
        Membre membre = operationMembre.loginMembre(email, passWord);
        if (membre == null) {
            dispatcher = request.getRequestDispatcher("/WEB-INF/LoginMembre.html");
            dispatcher.forward(request, response);
        } else {
            int id = membre.getId();
            String[] fileValeus = {membre.getPhoto(), membre.getPhotoidenti(), membre.getDocRes()};
            String[] fileName = {"photo", "photoidenti", "docRes"};
            for (int i = 0; i < fileName.length; i++) {
                if (fileValeus[i] == null || fileValeus[i].isEmpty()) {
                    fileValeus[i] = "default_person.png";
                    session.setAttribute(fileName[i], ("uploadFile\\" + fileValeus[i]));
                } else {
                    session.setAttribute(fileName[i], ("uploadFile\\" + id + fileValeus[i]));
                }

            }
            session.setAttribute("membre", membre);
            dispatcher = request.getRequestDispatcher("/WEB-INF/EspaceMembre.jsp");
            dispatcher.forward(request, response);
        }
    }
}

