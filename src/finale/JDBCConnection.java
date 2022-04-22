package finale;

import java.sql.*;
import java.util.ArrayList;

public class JDBCConnection {
  
    public static Connection getJDBCConnection() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/chubedan";
        final String user = "root";
        final String password = "tnnt9b";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password); //getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String insertData(Finale in) {
        String url = "jdbc:mysql://localhost:3306/chubedan";
        String user = "root";
        String password = "tnnt9b";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement psmnt = connection.prepareStatement("insert into account values(?,?,?,?,?,?)");
            psmnt.setString(1, in.getFirstName());
            psmnt.setString(2, in.getLastName());
            psmnt.setString(3, (String) in.getUserName());
            psmnt.setString(4, in.getPassword());
            psmnt.setString(5, in.getPhone());
            psmnt.setString(6, in.getEmail());
            psmnt.executeUpdate();
            return "register successfull !";
        } catch (Exception e) {
            return "Phone number can't be more than 11 numbers !";
        }
    }
    public ArrayList<Finale> getAll() {
        Connection connection = getConnection();
        ArrayList<Finale> informations = new ArrayList<>();
        if (connection != null) {
            System.out.println("Connection database successfull");
        } else {
            System.out.println("Connection database fail");
        }
        try {
            Statement statement = connection.createStatement() ;
            String sql = "select*from account";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Finale i = new Finale();
                i.setFirstName(rs.getString("firstname"));
                i.setLastName(rs.getString("lastname"));
                i.setUserName(rs.getString("username"));
                i.setPassword(rs.getString("password"));
                i.setPhone(rs.getString("phonenumber"));
                i.setEmail(rs.getString("email"));
                informations.add(i);
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
        return informations;   
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}