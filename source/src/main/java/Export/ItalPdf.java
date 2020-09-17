package Export;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.RaktarkezeloProgram.domain.Ital;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItalPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
            Document document, PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        List<Ital> italok = (List<Ital>) model.get("italok");

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(95);
        table.setWidths(new int[] {1, 3, 8,3,3,3,3});
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        Font cimsorFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,30.0f,BaseColor.BLACK);
        Paragraph p = new Paragraph("Az összes ital a raktáron",cimsorFont);
        p.setSpacingAfter(30);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        
        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Id", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Cikkszám", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Név", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("Ár", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("Darabszám", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("Kiszerelés(liter)", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("Alkoholtartalom(%)", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        //

        
        


        for (Ital ital : italok) {

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(ital.getId().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(ital.getCikkSzam()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(ital.getNev())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(ital.getTermekAra())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(ital.getDarabSzam())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(ital.getKiszerelesLiter())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(ital.getAlkoholTartalom())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);
        }

        document.add(table);
        Font datumFont = FontFactory.getFont(FontFactory.TIMES_ITALIC,14.0f);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd  HH mm ss");
        String text = date.format(formatter);
        LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
        
        Paragraph datum = new Paragraph("Exportálva a raktárkezelő program által :\n"+parsedDate.toString(),datumFont);
        datum.setAlignment(Element.ALIGN_RIGHT);
        datum.setSpacingAfter(15);
        document.add(datum);
    }
}

