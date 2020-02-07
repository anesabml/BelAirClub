package control;

import modele.Membre;
import modele.OperationVisiteur;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ServletAdhesion", urlPatterns = "/ServletAdhesion")
public class ServletAdhesion extends HttpServlet {
    String parth;
    @Override
    public void init() throws ServletException {
        super.init();
        File file=new File("D:\\study\\GL S1\\daaaw\\mini project\\web\\uploadFile");
        if(!file.exists())
            file.mkdir();
        parth=file.getPath();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Membre m = new Membre();

        String file_name = null;
        OperationVisiteur op = new OperationVisiteur();

        PrintWriter out = response.getWriter();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fields = upload.parseRequest(request);
            Iterator<FileItem> it = fields.iterator();
            ArrayList<FileItem> file = new ArrayList<FileItem>();

            if (!it.hasNext()) {
                return;
            }
            while (it.hasNext()) {
                FileItem fileItem = it.next();
                String s = fileItem.getFieldName();
                System.out.println(s);
                if ("nom".equals(s)) {
                    m.setNom(fileItem.getString());

                } else if ("prenom".equals(s)) {
                    m.setPrenom(fileItem.getString());

                } else if ("dateN".equals(s)) {
                    m.setDateN(fileItem.getString());

                } else if ("adress".equals(s)) {
                    m.setAdress(fileItem.getString());

                } else if ("passw".equals(s)) {
                    m.setPassWord(fileItem.getString());

                } else if ("email".equals(s)) {
                    m.setEmail(fileItem.getString());

                } else if ("photo".equals(s)) {
                    System.out.println(fileItem.getName());
                    m.setPhoto(fileItem.getName());
                    file.add(fileItem);

                } else if ("photoidenti".equals(s)) {
                    m.setPhotoidenti(fileItem.getName());
                    file.add(fileItem);

                } else if ("docRes".equals(s)) {
                    m.setDocRes(fileItem.getName());
                    file.add(fileItem);

                }
            }
            m.setDateInsecription(getCurrentDate());
            boolean b = false;
            try {
                b = op.adherer(m);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (b) {
                int id = op.getIdMembre(m);
                for (FileItem fileItem : file) {
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                        if (file_name == null) {
                            if (fileItem.getFieldName().equals("file_name")) {
                                file_name = fileItem.getString();
                            }
                        }
                    } else {
                        if (fileItem.getSize() > 0) {
                            fileItem.write(new File(parth + id + fileItem.getName()));
                        }
                    }
                }
                out.println("iscription termine !");
            } else {
                out.println("tu es deja un compte");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }
    public String getCurrentDate()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date).toString();
    }
}
