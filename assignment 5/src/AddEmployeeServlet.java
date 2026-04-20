import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        double salary = Double.parseDouble(req.getParameter("salary"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/company", "root", "Star@123");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO employee(name,email,salary) VALUES(?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDouble(3, salary);

            ps.executeUpdate();

            res.getWriter().println("Employee Added Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
