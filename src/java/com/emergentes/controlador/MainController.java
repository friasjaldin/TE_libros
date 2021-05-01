package com.emergentes.controlador;
import com.emergentes.ConexionDB;
import com.emergentes.modelo.Libro;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CristhianFriasJaldin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String op;
        op=(request.getParameter("op") != null )? request.getParameter("op"):"list";
        ArrayList<Libro> lista = new ArrayList<Libro>();
        ConexionDB canal =new ConexionDB();
        Connection conn =canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("list")){
        try {
        String sql = "select * from libros";
        ps = (PreparedStatement) conn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next())
        {
            Libro lib =new Libro();
            lib.setId(rs.getInt("id"));
            lib.setIsbn(rs.getString("isbn"));
            lib.setTitulo(rs.getString("titulo"));
            lib.setCategoria(rs.getString("categoria"));
            lista.add(lib);
            
        }
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (SQLException ex){
            System.out.println("Error en SQL:"+ex.getMessage());
        }finally {
        canal.desconectar();
        }
        }
    if (op.equals("nuevo")){
    Libro l= new Libro();
    request.setAttribute("libro", l);
    request.getRequestDispatcher("editar.jsp").forward(request,response);
    
    }    
    //para que se pueda editar
    if (op.equals("editar")){
    
        try {
        int id=Integer.parseInt(request.getParameter("id"));
        
        String sql ="select * from libros where id = ?";
    ps = (PreparedStatement) conn.prepareStatement(sql);
    ps.setInt(1, id);
    rs= ps.executeQuery();
    Libro aux =new Libro ();
    
    while(rs.next()){
    
        Libro lib =new Libro();
            lib.setId(rs.getInt("id"));
            lib.setIsbn(rs.getString("isbn"));
            lib.setTitulo(rs.getString("titulo"));
            lib.setCategoria(rs.getString("categoria"));

    }
    request.setAttribute("libro", aux);
    request.getRequestDispatcher("editar.jsp").forward(request,response);
    }catch (SQLException ex){
            System.out.println("Error en SQL:"+ex.getMessage());
        }
        }
    
        
    if (op.equals("eliminar")){
    int id = Integer.parseInt(request.getParameter("id"));
    
    try{
    String sql = "delete from libros where id=?";
    ps = (PreparedStatement) conn.prepareStatement(sql);
    ps.setInt(1, id);
    ps.executeUpdate();
    }catch (SQLException ex) {
        System.out.println("Error en sql:"+ ex.getMessage());
    }finally{
    canal.desconectar();
    
    }
    response.sendRedirect("MainController");
    }
    }     

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   int id =Integer.parseInt(request.getParameter("id"));
   String isbn =request.getParameter("isbn");
   String titulo =request.getParameter("titulo");
   String categoria =request.getParameter("categoria");
   Libro l = new Libro();
   
   l.setId(id);
   l.setIsbn(isbn);
   l.setTitulo(titulo);
   l.setCategoria(categoria);
   
   ConexionDB canal =new ConexionDB();
   Connection conn = canal.conectar();
   PreparedStatement ps;
   ResultSet rs;
   if (id == 0){
   try{
       String sql = "insert into libros (isbn,titulo,categoria) values (?,?,?)";
       ps =(PreparedStatement) conn.prepareStatement(sql);
       ps.setString(1, l.getIsbn());
       ps.setString(2, l.getTitulo());
       ps.setString(3, l.getCategoria());
       ps.executeUpdate();
   }catch (SQLException ex){
       System.out.println("Error en sql: " + ex.getMessage());
   }finally{
   canal.desconectar();
   }
   }
   response.sendRedirect("MainController");
    }
    }

 
