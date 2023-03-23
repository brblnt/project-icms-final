package brblnt.icms.service.modules.worksheet.exporter;

import java.awt.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Customer PDF creator.
 */
@Component
@Setter
public class CreateWorksheetPDF {

  private CompleteCompany company;
  private CompleteWorksheet worksheet;
  private CompleteColleague colleague;
  private String year;


  /**
   * Export worksheet to PDF
   * Create header and body for table.
   */
  public void export(HttpServletResponse response) throws DocumentException, IOException {

    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());
    document.open();

    /*
     * TITLE + WORKSHEET ID
     * ----------------------------------------------------------------
     */
    Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontTitle.setSize(18);
    fontTitle.setColor(Color.GRAY);

    Font fontWorksheetId = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    fontWorksheetId.setSize(9);
    fontWorksheetId.setStyle("italic");
    fontWorksheetId.setColor(Color.BLACK);


    Paragraph title = new Paragraph("MUNKALAP", fontTitle);
    Paragraph worksheetId = new Paragraph(year + "ML-" + worksheet.getId(), fontWorksheetId);

    title.setAlignment(Paragraph.ALIGN_CENTER);
    worksheetId.setAlignment(Paragraph.ALIGN_RIGHT);

    document.add(worksheetId);
    document.add(title);

    /*
     * ----------------------------------------------------------------
     */


    Font customerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    customerFont.setSize(12);
    customerFont.setColor(Color.BLACK);

    Font fontInformation = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    fontInformation.setSize(10);
    fontInformation.setColor(Color.BLACK);


    Table table = new Table(4);

    table.setBorder(Rectangle.NO_BORDER);
    table.setPadding(5);
    table.setSpacing(0);
    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table.setWidth((float) (PageSize.A4.getWidth() * (0.18)));

    Cell cell = new Cell(new Phrase("Szervíz:\n" +
            company.getCompanyName() + "\n" +
            company.getAddress1().getPostalCode() + " " + company.getAddress1().getCityName() + ", " +
            company.getAddress1().getAddress() + "\n" +
            company.getEmailAddress() + "\n" + company.getPhoneNumber() + "\n" +
            company.getFinance().getNumberVAT(), customerFont));

    cell.setColspan(2);
    table.addCell(cell);
    cell = new Cell(new Phrase("Ügyfél:\n" +
            worksheet.getCustomer().getCustomerName() + "\n" +
            worksheet.getCustomer().getAddress1().getPostalCode() + " " + worksheet.getCustomer().getAddress1().getCityName() + ", " +
            worksheet.getCustomer().getAddress1().getAddress() + "\n" +
            worksheet.getCustomer().getEmailAddress() + "\n" +
            worksheet.getCustomer().getPhoneNumber(), customerFont));
    cell.setColspan(2);
    table.addCell(cell);


    cell = new Cell(new Phrase("Felvétel dátuma:\n" + worksheet.getCreateDate(), fontInformation));
    table.addCell(cell);
    cell = new Cell(new Phrase("Befejezés dátuma:\n" + worksheet.getFinishDate(), fontInformation));
    table.addCell(cell);
    cell = new Cell("");
    table.addCell(cell);
    cell = new Cell(new Phrase("Technikus:\n" +
            colleague.getCustomCode() + " - " + colleague.getName(), fontInformation));
    table.addCell(cell);
    String faults = "";
    for (CompleteFault fault : worksheet.getFaults()) {
      String separator = " ";
      if (faults.length() <= 0) {
        separator = " ";
      } else {
        separator = ", ";
      }
      faults = faults + separator + fault.getFaultName();
    }

    cell = new Cell(new Phrase("Észlelt hiba jelenség(ek): " + faults, fontInformation));
    cell.setColspan(4);
    table.addCell(cell);

    document.add(table);

    /*
     * ----------------------------------------------------------------
     */

    Paragraph placeHolder = new Paragraph("\n", fontInformation);
    document.add(placeHolder);

    /*
     * ----------------------------------------------------------------
     */

    Font fontTableHeader = FontFactory.getFont(FontFactory.HELVETICA);
    fontTableHeader.setSize(10);
    fontTableHeader.setColor(Color.BLACK);

    Font fontTableSmallTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    fontTableSmallTitle.setSize(8);
    fontTableSmallTitle.setColor(Color.BLACK);

    Table table2 = new Table(7);
    table2.setBorder(Rectangle.NO_BORDER);
    table2.setPadding(5);
    table2.setSpacing(0);
    table2.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table2.setWidth((float) (PageSize.A4.getWidth() * (0.18)));
    cell = new Cell(new Phrase("Felhasznált anyagok", fontTableHeader));
    cell.setColspan(7);
    cell.setBackgroundColor(Color.LIGHT_GRAY);
    cell.setBorderColor(Color.LIGHT_GRAY);
    table2.addCell(cell);

    /*
     * ----------------------------------------------------------------
     */

    cell = new Cell(new Phrase("Cikkszám", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table2.addCell(cell);
    cell = new Cell(new Phrase("Termék megnevezése", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    cell.setColspan(2);
    table2.addCell(cell);
    cell = new Cell(new Phrase("Nettó ár", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table2.addCell(cell);
    cell = new Cell(new Phrase("Áfa", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table2.addCell(cell);
    cell = new Cell(new Phrase("Áfa %", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table2.addCell(cell);
    cell = new Cell(new Phrase("Bruttó", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table2.addCell(cell);
    document.add(table2);
    LineSeparator lineSeparator = new LineSeparator();
    document.add(lineSeparator);
    /*
     * ----------------------------------------------------------------
     */

    int price = 0;

    for (CompleteProduct product : worksheet.getProducts()) {

      price += product.getPriceSellN();

      Table table21 = new Table(7);
      table21.setBorder(Rectangle.NO_BORDER);
      table21.setPadding(5);
      table21.setSpacing(0);
      table21.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table21.setWidth((float) (PageSize.A4.getWidth() * (0.18)));


      cell = new Cell(new Phrase(product.getCustomCode(), fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table21.addCell(cell);
      cell = new Cell(new Phrase(product.getProductName(), fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      cell.setColspan(2);
      table21.addCell(cell);
      cell = new Cell(new Phrase(product.getPriceSellN() + "", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table21.addCell(cell);
      cell = new Cell(new Phrase((product.getPriceSellB() - product.getPriceSellN()) + "", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table21.addCell(cell);
      cell = new Cell(new Phrase(product.getVatSell() + " %", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table21.addCell(cell);
      cell = new Cell(new Phrase(product.getPriceSellB() + "", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table21.addCell(cell);

      document.add(table21);
      document.add(lineSeparator);
    }
    /*
     * ----------------------------------------------------------------
     */
    document.add(placeHolder);
    Table table3 = new Table(7);
    table3.setBorder(Rectangle.NO_BORDER);
    table3.setPadding(5);
    table3.setSpacing(0);
    table3.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table3.setWidth((float) (PageSize.A4.getWidth() * (0.18)));
    cell = new Cell(new Phrase("Szolgáltatások", fontTableHeader));
    cell.setColspan(7);
    cell.setBackgroundColor(Color.LIGHT_GRAY);
    cell.setBorderColor(Color.LIGHT_GRAY);
    table3.addCell(cell);

    /*
     * ----------------------------------------------------------------
     */

    cell = new Cell(new Phrase("Azonosító", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table3.addCell(cell);
    cell = new Cell(new Phrase("Szolgáltatás megnevezése", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    cell.setColspan(2);
    table3.addCell(cell);
    cell = new Cell(new Phrase("Nettó ár", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table3.addCell(cell);
    cell = new Cell(new Phrase("Áfa", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table3.addCell(cell);
    cell = new Cell(new Phrase("Áfa %", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table3.addCell(cell);
    cell = new Cell(new Phrase("Bruttó", fontTableSmallTitle));
    cell.setBorderColor(Color.WHITE);
    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table3.addCell(cell);
    document.add(table3);
    document.add(lineSeparator);
    /*
     * ----------------------------------------------------------------
     */

    for (CompleteService service : worksheet.getServices()) {
      price += service.getPriceN();


      Table table31 = new Table(7);
      table31.setBorder(Rectangle.NO_BORDER);
      table31.setPadding(5);
      table31.setSpacing(0);
      table31.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table31.setWidth((float) (PageSize.A4.getWidth() * (0.18)));


      cell = new Cell(new Phrase(service.getCustomCode(), fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table31.addCell(cell);
      cell = new Cell(new Phrase(service.getServiceName(), fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      cell.setColspan(2);
      table31.addCell(cell);
      cell = new Cell(new Phrase(service.getPriceN() + "", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table31.addCell(cell);
      cell = new Cell(new Phrase((service.getPriceB() - service.getPriceN()) + "", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table31.addCell(cell);
      cell = new Cell(new Phrase(service.getVat() + " %", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table31.addCell(cell);
      cell = new Cell(new Phrase(service.getPriceB() + "", fontTableSmallTitle));
      cell.setBorderColor(Color.WHITE);
      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table31.addCell(cell);


      document.add(table31);
      document.add(lineSeparator);
    }

    /*
     * ----------------------------------------------------------------
     */
    document.add(placeHolder);
    Table table5 = new Table(7);
    table5.setBorder(Rectangle.NO_BORDER);
    table5.setPadding(5);
    table5.setSpacing(0);
    table5.setHorizontalAlignment(HorizontalAlignment.CENTER);
    table5.setWidth((float) (PageSize.A4.getWidth() * (0.18)));
    cell = new Cell(new Phrase("Végösszeg:", fontTableHeader));
    cell.setColspan(5);
    cell.setBackgroundColor(Color.LIGHT_GRAY);
    cell.setBorderColor(Color.LIGHT_GRAY);
    table5.addCell(cell);
    cell = new Cell(new Phrase(price + " + ÁFA", fontTableHeader));
    cell.setBackgroundColor(Color.LIGHT_GRAY);
    cell.setBorderColor(Color.LIGHT_GRAY);
    cell.setColspan(2);
    cell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
    table5.addCell(cell);
    document.add(table5);
    /*
     * ----------------------------------------------------------------
     */
    document.add(placeHolder);
    Font fontOther = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    fontOther.setSize(10);
    fontOther.setColor(Color.BLACK);
    Paragraph other = new Paragraph("Egyéb:\n" + worksheet.getOther(), fontOther);
    other.setAlignment(Element.ALIGN_LEFT);
    document.add(other);

    document.close();

  }
}
