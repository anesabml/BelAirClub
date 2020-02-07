package control.responsable;


import modele.Membre;
import modele.OperationResponsable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ListMemberNonActiveServlet", urlPatterns = "/ListMemberNonActiveServlet")
public class ListMemberNonActiveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        try {
            OperationResponsable operationResponsable = new OperationResponsable();

            ArrayList<Membre> membres = operationResponsable.exportListMemberWith3Cancelation();

            XSSFSheet sheet = workbook.createSheet("Members");

            writeHeaderLine(sheet);

            writeDataLines(membres, sheet);

            resp.setContentType("application/vnd.ms-excel");

            OutputStream outputStream = resp.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }

    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Nom");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Prenom");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Email");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Adress");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Date de naissance");
    }

    private void writeDataLines(ArrayList<Membre> result,
                                XSSFSheet sheet) {
        int rowCount = 1;

        for (Membre membre : result) {
            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(membre.getId());

            cell = row.createCell(columnCount++);
            cell.setCellValue(membre.getNom());

            cell = row.createCell(columnCount++);
            cell.setCellValue(membre.getPrenom());

            cell = row.createCell(columnCount++);
            cell.setCellValue(membre.getEmail());

            cell = row.createCell(columnCount++);
            cell.setCellValue(membre.getAdress());

            cell = row.createCell(columnCount++);
            cell.setCellValue(membre.getDateN());
        }
    }
}
