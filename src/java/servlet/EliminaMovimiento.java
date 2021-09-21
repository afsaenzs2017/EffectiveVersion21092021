/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.PRODUCTOS_DAO;
import controlador.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PRODUCTOS;

/**
 *
 * @author Luis Cubero
 */
@WebServlet(name = "EliminaMovimiento", urlPatterns = {"/EliminaMovimiento"})
public class EliminaMovimiento extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EliminaMovimiento</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
            int codigo = Integer.parseInt(request.getParameter("codmovimi"));
            out.println("<script>");
                //out.println("alert('"+codigo+"')");
            out.println("</script>");
            conexion con = new conexion();
            Connection cn = con.getConn();
            String query,querya;
            //Actualizar estado de venta
            query="Update movimiento set estado_movimiento = ? where id_movimiento = ? ";
            try
            {
                PreparedStatement pst=cn.prepareStatement(query);
                pst.setInt(1,2);
                pst.setInt(2,codigo);
                int n=pst.executeUpdate();
                if (n>0)
                {
                    
                }
                else
                {
                    out.println("<script>");
                        out.println("alert('no se actualizo movimiento');");
                    out.println("</script>");
                }
                int matriz[][] = new int[1000][4];
                int i=0,j=0;
                int codmat;
                //Recuperar codigo producto en la venta, cantidad de venta e inventario de producto
                query="select D.PRODUCTOS_id_producto as C01,D.cantidad as C02,P.cantidad_producto as C03,D.id_detalle_movimiento as C04 from detalle_movimiento D, productos P where MOVIMIENTO_id_movimiento = '"+codigo+"' and D.PRODUCTOS_id_producto = P.id_producto";
                PreparedStatement stmtr = cn.prepareStatement(query);
                ResultSet rsr = stmtr.executeQuery();
                while(rsr.next())
                  {
                      matriz[i][0]=rsr.getInt("C01");
                      matriz[i][1]=rsr.getInt("C02");
                      matriz[i][2]=rsr.getInt("C03");
                      matriz[i][3]=rsr.getInt("C04");
                  i++;
                  }
                int cantaux=0;
                //Actualizar inventario producto
                for(j=0;j<i;j++)
                {
                   cantaux=matriz[j][1]+matriz[j][2];
                   query="update productos set cantidad_producto = ? where id_producto = ? ";
                   pst=cn.prepareStatement(query);
                   pst.setInt(1,cantaux);
                   pst.setInt(2,matriz[j][0]);
                   n=pst.executeUpdate();
                   if (n>0)
                   {
                       
                   }
                   else
                   {
                    out.println("<script>");
                        out.println("alert('no se actualizo producto');");
                    out.println("</script>");
                   }
                   //Actualizar Kardex
                   query="Insert into inventario(entradas,salidas,saldo,PRODUCTOS_id_producto,DETALLE_MOVIMIENTO_id_detalle_movimiento) values (?,?,?,?,?) ";
                   pst=cn.prepareStatement(query);
                   pst.setInt(1,matriz[j][1]);
                   pst.setInt(2,0);
                   pst.setInt(3,cantaux);
                   pst.setInt(4,matriz[j][0]);
                   pst.setInt(5,matriz[j][3]);
                   n=pst.executeUpdate();
                   if (n>0)
                   {
                     out.println("<script>");
                         out.println("alert('Factura Anulada');");
                         out.println("window.location.href = '../../Effective/vista/gestionarMovimientos.jsp';");
                     out.println("</script>");
                   }
                   else
                   {
                    out.println("<script>");
                        out.println("alert('no se actualizo');");
                    out.println("</script>");
                   }
                   cantaux=0;
                }
            }
            catch (SQLException ex) 
                 {
                   out.println("Error al insertar " + ex.getMessage());
                 } 
            
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
