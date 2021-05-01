<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Libro"%>
<%@page import="java.util.List"%>
<%
    List<Libro> lista = (List<Libro>) request.getAttribute("lista");
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Libros</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
           <table border="0" style="margin: 0 auto;" bgcolor="FF6406" >
        <td rowspan="20"> </tr>
        <tr><td><b>TUTORIAL 13 EMERGENTES 2  </b></td>  
        <tr><td><b>Nombre:  </b></td> <td>Cristhian Jaime Frias Jaldin</td> 
        <tr><td><b>Ci:  </b></td> <td >10072610 lp</td>
         <tr><td><b>email:  </b></td> <td>friasyoyo@gmail.com </td> 
        <tr><td><b>celular|whatsapp:  </b></td> <td>60122932 </td>
       </table>
        
        <table border="1">
            <tr>
               
                <th>ISBN</th>
                <th>Titulo</th>
                <th>Categoria</th>
                <th></th>
            </tr>
            <c:forEach var="item" items ="${lista}">
                <tr>
                   
                    <td>${item.isbn}</td>
                    <td>${item.titulo}</td>
                    <td>${item.categoria}</td>
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
