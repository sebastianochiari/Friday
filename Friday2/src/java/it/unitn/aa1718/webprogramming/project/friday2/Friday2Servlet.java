/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.friday2;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import java.sql.Connection;
 
/*
 * Servlet implementation class ServletConnectDB
 */
@WebServlet("/ServletConnectDB")
public class Friday2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    /*
     * @see HttpServlet#HttpServlet()
     */
    public Friday2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /*
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
             
         
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL = "jdbc:mysql://localhost:3306/FridayDB?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "appa";
        Connection connection = (Connection) DriverManager.getConnection(dbURL,username, password);
     
        Statement statement = connection.createStatement();
        String command = null;
        
        /*
        command = "insert into users (Email, Password, Name, Surname, Avatar, Administrator, List_Owner)"+ "VALUES('ababba@zuzzu.com', 'hindi4ever', 'Lucia', 'Cavallari', null, null, null)";
        statement.executeUpdate(command);
        */
         
        // query di base per testare la connessione 
        /*
        command = "select * from users";
        statement.execute(command);
        ResultSet risultato = statement.executeQuery(command);
        while(risultato.next()) {
            String risultatoUser = risultato.getString("Email");
            System.out.println("--------------------------------------------");
            System.out.println(risultatoUser);
        }
        */
        
        // calcolo PCID
        command = "select max(PCID) as maxPCID from product_categories";
        statement.execute(command);
        ResultSet risultato = statement.executeQuery(command);
        risultato.next();
        System.out.println(risultato.getString("maxPCID"));
        int PCID = Integer.parseInt(risultato.getString("maxPCID"))+1;
        statement.close();
        
        // query inserimento categoria prodotto
        statement = connection.createStatement();
        String name = request.getParameter("productCategoryName");
        String description = request.getParameter("description");
        String logo = request.getParameter("categoryLogo");
        String email = "britney.spears@gmail.com";
        command = null;
        
        System.out.println(logo);
        
        // controllo presenza logo
        if (logo != null & !logo.isEmpty()){
            command = "insert into product_categories (PCID, Name, Description, Logo, Email) values  ('"+PCID+"', '"+name+"', '"+description+"', '"+logo+"', '"+email+"')";
        } else {
            command = "insert into product_categories (PCID, Name, Description,Logo, Email) values  ('"+PCID+"', '"+name+"', '"+description+"', null, '"+email+"')";
        }
        
        statement.executeUpdate(command);
        
        // chiusura connessione e statement
        try {
            if (connection != null) {
                connection.close();
                System.out.println("connessione zao zao");
            }
            if (statement != null) {
                statement.close();
                System.out.println("statement zao zao (ciao anche a te iconcina)");
            }
        }catch (SQLException sqlee) {
            sqlee.printStackTrace();
        }
       } catch(SQLException e){
        for (Throwable t : e)
            t.printStackTrace();
             }
    catch(ClassNotFoundException e){
        e.printStackTrace();
    }
     
    }
 
    /*
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
 
}
