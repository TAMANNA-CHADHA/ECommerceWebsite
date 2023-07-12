
package controller;
//import javax.servlet.httpServlet;
import java.io.*;
//import static java.lang.Character.UnicodeBlock.forName;
import java.sql.*;
//import javax.servlet.*;

public class SignUpBackend extends HttpServlet {
    
    
    
    ServletContext jdbc = null;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        jdbc=getServletContext();
        String driver = jdbc.getInitParameter("driver");
         String url = jdbc.getInitParameter("url");
          String username = jdbc.getInitParameter("user");
           String userpassword = jdbc.getInitParameter("pass");
           
         //Employee Form data  
           
           String name= request.getParameter("username");
            String useremail= request.getParameter("useremail");
                  String password= request.getParameter("userpassword");
               
            
                
                  
           Connection  con = null;
           try{
               Class.forName(driver);
               con= DriverManager.getConnection(url,username,password);
               String insert="insert into Customerdata(username,useremail,password)values(?,?,?)";
           PreparedStatement ps=con.prepareStatement(insert);
           ps.setString(1,name);

                ps.setString(2,useremail);
                 
               ps.setString(3,userpassword);
               
               
               
               
               int status =ps.executeUpdate();
               
               if(status>0){
                   
                   
                   out.print("Signed Up successfully");
                   
                   
               }
               else{
                    out.print("Already Have an account try to login");
                   
               }

             
           
                   
                   
                   }
           catch(Exception e){
               out.print(e);
           }
           finally{
               try{
                   con.close();
                   
               }
               catch(Exception e){
                   out.print(e);
               }
           }
        
        
        
    }
    private ServletContext getServletContext() {
        return null;
    }

 
}
