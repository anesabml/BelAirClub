package control.membre;

import modele.Creneaux;
import modele.Membre;
import modele.OperationMembre;
import modele.Reservation;

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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ReserverServlet")
public class ReserverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher;
        if ((session == null) || (session.getAttribute("membre") == null)) {
            dispatcher = request.getRequestDispatcher("WEB-INF/LoginMembre.html");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("creneauxReserver")==null)
        {
            try {
                request.setAttribute("activiteLibre" ,(new OperationMembre()).creneauxLibre());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dispatcher=request.getRequestDispatcher("WEB-INF/reservationCreneaux.jsp");
            dispatcher.forward(request,response);
        }else {
            int idCreneaux=Integer.valueOf(request.getParameter("creneauxReserver"));
            Reservation reservation=new Reservation();
            reservation.setIdCreneau(idCreneaux);
            reservation.setIdMembre(((Membre)session.getAttribute("membre")).getId());
            reservation.setDate(getCurrentDate());
            OperationMembre operationMembre=new OperationMembre();
            Creneaux creneaux=operationMembre.getCreneaux(idCreneaux);
            if(creneaux.getNbPlace()<=0)
            reservation.setAttend(true);
            else
                reservation.setAttend(false);
            String codeReservation=getCode();
            reservation.setCode(codeReservation);

            int r=0;
            try {
                 r= operationMembre.reservir(reservation);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter out=response.getWriter();
            if (r==0)
                out.print("tu deja reserver");
            else
                out.print(codeReservation);
        }


    }
    public String getCurrentDate()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date).toString();
    }

    public String getCode(){
        String s="qwertyuiopasdfghjkzxcvbnm1234567890";
        String code="";
        for (int i=0 ;i<7;i++)
        {
            int r= (int) (Math.random()*35);
            code=code+s.charAt(r);
        }
        return  code;
    }
}
