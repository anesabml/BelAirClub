package control.membre;

import modele.CollectionARC;
import modele.Mail;
import modele.OperationMembre;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "annulerReservationServlet")
public class annulerReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code =request.getParameter("code");
        HttpSession session=request.getSession(false);
        RequestDispatcher dispatcher;
        PrintWriter out=response.getWriter();
        int id;
        try {
             id =Integer.valueOf(request.getParameter("idR"));
            session.setAttribute("idR",id);
        }catch (Exception e){
             id =(Integer)session.getAttribute("idR");
        }

        ArrayList<CollectionARC>collectionARCS= (ArrayList<CollectionARC>) session.getAttribute("reservations");
        CollectionARC collection= collectionARCS.get(id);

        if(code==null)
        {
            request.setAttribute("reservation",collection);
            dispatcher=request.getRequestDispatcher("WEB-INF/AnnulerReservation.jsp");
            dispatcher.forward(request,response);
        }else if(collection.getReservation().getCode().equals(code))
        {
            OperationMembre operationMembre=new OperationMembre();
            try {
                operationMembre.annulerReservasion(collection.getReservation());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Mail.sendEmail("mohammedsaoudi98@gmail.com");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            dispatcher=request.getRequestDispatcher("WEB-INF/EspaceMembre.jsp");
            dispatcher.forward(request,response);

        }else{
            out.print("code incorect");
        }
        }

             }

