/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableHeader;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Tournoi;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Image;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import entities.Tournoi;

import java.io.IOException;


/**
 *
 * @author oumayma
 */
public class ServicePdf {
   
    private void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add image
          //  Image logo = Image.getInstance(HeaderFooterPageEvent.class.getResource("/barca.png"));
           // header.addCell(logo);

            // add text
                        PdfPTableHeader te = new PdfPTableHeader();

            PdfPHeaderCell text = new PdfPHeaderCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.addElement(new Phrase("iText PDF Header Footer Example", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addHeader(text);
           // text.addElement(new Phrase("https://memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);

            // write content
           // header.writeSelectedRows(0, -1, 0, 0, writer.getDirectContent());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }   
    public void liste_admins(Tournoi a)   {
         try {
             ServiceTournoi sa = new ServiceTournoi();
             String message = "M/MME :" + a.getNomt();
             //String messageEmail = "EMAIL :" + a.();
             
             String file_name = "PDF/liste_Tournoi.pdf";
             Document document = new Document();
             PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
             document.open();
            // Paragraph para = new Paragraph(message);
             // Paragraph paraEmail = new Paragraph(messageEmail);
             Paragraph para = new Paragraph();
para.add("Entete du fichier PDF");
 addHeader(writer);
             // document.add(para);
             // document.add(paraEmail);
             List<Tournoi> liste_admins = sa.afficher();
             PdfPTable table = new PdfPTable(6);
            
//document.add(img);

PdfPCell cl = new PdfPCell(new Phrase("Nom"));
table.addCell(cl);
PdfPCell cl1 = new PdfPCell(new Phrase("date debut"));
table.addCell(cl1);
PdfPCell cl11 = new PdfPCell(new Phrase("date fin "));
table.addCell(cl11);
PdfPCell c = new PdfPCell(new Phrase("Type"));
table.addCell(c);
PdfPCell cm = new PdfPCell(new Phrase("nombre d'equipe "));
table.addCell(cm);
PdfPCell ct = new PdfPCell(new Phrase("Logo "));
 // ct.addElement(img);
table.addCell(ct);

table.setHeaderRows(1);
document.add(table);

int i = 0;
for (i = 0; i < liste_admins.size(); i++) {
    table.addCell(liste_admins.get(i).getNomt());
    table.addCell(liste_admins.get(i).getDated());
    table.addCell(liste_admins.get(i).getDatef());
    table.addCell(liste_admins.get(i).getTypet());
    table.addCell(String.valueOf(liste_admins.get(i).getNbrc()));
     String imageFile = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\" +liste_admins.get(i).getLogo();
//ImageData data = ImageDataFactory.create(imageFile);
Image img = Image.getInstance(imageFile);
    table.addCell(img);
}
document.add(table);

document.close();
          } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }

}
