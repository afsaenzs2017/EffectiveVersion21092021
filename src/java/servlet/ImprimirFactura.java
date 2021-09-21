/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.conexion;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.text.DecimalFormat;

/**
 *
 * @author Luis Cubero
 */
@WebServlet(name = "ImprimirFactura", urlPatterns = {"/ImprimirFactura"})
public class ImprimirFactura extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int paracodmovi=Integer.parseInt(request.getParameter("codmovimiento"));
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        conexion con = new conexion();
        Connection cn = con.getConn();
        String numdoc="",fecha="",cliente="";
        String producto="";
        DecimalFormat df = new DecimalFormat("#.00");
        double precio=1,subtotal=1,iva=1,total=1,ssub=0,siva=0,stot=0;
        int cantidad=1;
        int codmov=1;
        try
        {
           try
           {
               Document documento = new Document();
               PdfWriter.getInstance(documento, out);
               
               documento.open();
               
               Paragraph par1 = new Paragraph();
               Font fontitulo = new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.BLUE);
               Font fontcabecera = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD,BaseColor.BLACK);
               Font fonttotales = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD,BaseColor.RED);
               Font fontcuerpo = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD,BaseColor.BLACK);
               
               par1.add(new Phrase("Reporte Factura",fontitulo));
               par1.setAlignment(Element.ALIGN_CENTER);
               par1.add(new Phrase(Chunk.NEWLINE));
               par1.add(new Phrase(Chunk.NEWLINE));
               documento.add(par1);
               
               PreparedStatement stmtr = cn.prepareStatement("select M.numero_de_documento,M.fecha,C.nombreCliente,C.apellidoCliente,M.id_movimiento,M.subtotal,M.total_iva,M.total_factura from movimiento M, clientes C WHERE M.CLIENTES_idCliente = C.idCliente and M.id_movimiento = '"+paracodmovi+"' ");
               ResultSet rsr = stmtr.executeQuery();
               if(rsr.next())
                 {
                 numdoc = rsr.getString(1);
                 fecha = rsr.getString(2);
                 cliente = rsr.getString(3)+" "+rsr.getString(4);
                 codmov = rsr.getInt(5);
                 ssub= rsr.getFloat(6);
                 siva= rsr.getFloat(7);
                 stot= rsr.getFloat(8);
                 }
               
               //Cabecera Movimiento
               Paragraph par2 = new Paragraph();
               par2.add(new Phrase("Numero Documento: "+numdoc,fontcabecera));
               par2.setAlignment(Element.ALIGN_LEFT);
               par2.add(new Phrase(Chunk.NEWLINE));
               documento.add(par2);
               
               Paragraph par3 = new Paragraph();
               par3.add(new Phrase("Fecha: "+fecha,fontcabecera));
               par3.setAlignment(Element.ALIGN_LEFT);
               par3.add(new Phrase(Chunk.NEWLINE));
               documento.add(par3);
               
               Paragraph par4 = new Paragraph();
               par4.add(new Phrase("Cliente: "+cliente,fontcabecera));
               par4.setAlignment(Element.ALIGN_LEFT);
               par4.add(new Phrase(Chunk.NEWLINE));
               documento.add(par4);
               
               Paragraph par5 = new Paragraph();
               par5.add(new Phrase(Chunk.NEWLINE));
               par5.add(new Phrase(Chunk.NEWLINE));
               par5.add(new Phrase(Chunk.NEWLINE));
               documento.add(par5);
               
               PdfPTable tablac = new PdfPTable(6);
               PdfPCell celdaac = new PdfPCell(new Paragraph("Producto",fontcuerpo));
               PdfPCell celdabc = new PdfPCell(new Paragraph("Cantidad",fontcuerpo));
               PdfPCell celdacc = new PdfPCell(new Paragraph("Precio",fontcuerpo));
               PdfPCell celdadc = new PdfPCell(new Paragraph("Subtotal",fontcuerpo));
               PdfPCell celdaec = new PdfPCell(new Paragraph("Iva",fontcuerpo));
               PdfPCell celdafc = new PdfPCell(new Paragraph("Total",fontcuerpo));
               tablac.addCell(celdaac);
               tablac.addCell(celdabc);
               tablac.addCell(celdacc);
               tablac.addCell(celdadc);
               tablac.addCell(celdaec);
               tablac.addCell(celdafc);
                              
               documento.add(tablac);
               
               //Detalle Movimiento
               stmtr = cn.prepareStatement("select P.descripcion_producto,D.cantidad,D.precio_productos,D.subtotal_productos,D.iva_productos,D.total_productos from detalle_movimiento D, productos P where D.MOVIMIENTO_id_movimiento = '"+codmov+"' and D.PRODUCTOS_id_producto = P.id_producto");
               rsr = stmtr.executeQuery();
               while(rsr.next())
                 {
                 producto = rsr.getString(1);
                 cantidad = rsr.getInt(2);
                 precio = rsr.getDouble(3); 
                 subtotal = rsr.getDouble(4);
                 iva = rsr.getDouble(5);
                 total = rsr.getDouble(6);
                 PdfPTable tabla = new PdfPTable(6);
                 PdfPCell celdaa = new PdfPCell(new Paragraph(producto,fontcuerpo));
                 
                 //Math.round(d.getSubtotal_productos()*100.0)/100.0
                 
                 PdfPCell celdab = new PdfPCell(new Paragraph(String.valueOf(cantidad),fontcuerpo));
                 celdab.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                 PdfPCell celdac = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.0f",precio)),fontcuerpo));
                 celdac.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                 PdfPCell celdad = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.2f",subtotal)),fontcuerpo));
                 celdad.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                 PdfPCell celdae = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.2f",iva)),fontcuerpo));
                 celdae.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                 PdfPCell celdaf = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.2f",total)),fontcuerpo));
                 celdaf.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                 
                 tabla.addCell(celdaa);
                 tabla.addCell(celdab);
                 tabla.addCell(celdac);
                 tabla.addCell(celdad);
                 tabla.addCell(celdae);
                 tabla.addCell(celdaf);
                 
                 documento.add(tabla);
                 
                 }
               
                PdfPTable tablafinal = new PdfPTable(6);
                PdfPCell uno = new PdfPCell(new Paragraph("",fonttotales));
                PdfPCell dos = new PdfPCell(new Paragraph("",fonttotales));
                PdfPCell tres = new PdfPCell(new Paragraph("",fonttotales));
                PdfPCell celdafinala = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.2f",ssub)),fonttotales));
                celdafinala.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                PdfPCell celdafinalb = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.2f",siva)),fonttotales));
                celdafinalb.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                PdfPCell celdafinalc = new PdfPCell(new Paragraph(String.valueOf(String.format("$ %.2f",stot)),fonttotales));
                celdafinalc.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                tablafinal.addCell(uno);
                tablafinal.addCell(dos);
                tablafinal.addCell(tres);
                tablafinal.addCell(celdafinala);
                tablafinal.addCell(celdafinalb);
                tablafinal.addCell(celdafinalc);

                documento.add(tablafinal);
               
               documento.close();
           }
           catch(Exception ex)
           {
               ex.getMessage();
           }
        }
        finally
        {
            out.close();
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


