<%@page import="com.emergentes.modelo.Libro"%>
<%
    Libro libro = (Libro)request.getAttribute("libro");
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo libro</h1>
        <form action="MainController" method="post">
            <table>
                                   
                <td><input type="hidden" name="id" value="${libro.id}"></td>
                
                <tr>
                    <td>ISBN</td>
                    <td><input type="text" name="isbn" value="${libro.isbn}"></td>
                </tr> 
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${libro.titulo}"></td>
                </tr>
                <tr>
                    <td>Categotia</td>
                    <td><input type="text" name="categoria" value="${libro.categoria}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>            
        </form>
    </body>
</html>
