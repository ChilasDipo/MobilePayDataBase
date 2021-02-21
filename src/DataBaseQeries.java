import java.sql.*;
public class DataBaseQeries {
    Connection conn = null;
      Connection connect() {
        try {
            // db parameters
            String url = "JDBC:sqlite:mobilepaydatabase.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
     String[] selectallnames() {
    int i = 0;
        String[] nameList = new String[20];
        String sql = "SELECT name FROM Brugere";
       Statement stmt  = null;
       try {
           Connection conn = this.connect();
           stmt = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql);
           while (rs.next()){
                       nameList[i]=  rs.getString("name");
                       i++;
           }
           conn.close();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
        return nameList;
    }
     void adduser(String name, int phonenumber,String adresse, int cardnumber ){
      //  String sql = "Insert into Brugere (name , phone_number , adresse,card_number) VALUES (name , phonenumber , adresse, cardnumber" );
        Statement stmt  = null;
        try {
            Connection conn = this.connect();
            stmt = conn.createStatement();
           // stmt.executeUpdate("INSERT INTO `Brugere`(name,phone_number,adresse,card_number) VALUE ('"+name+"','"+phonenumber+"','"+adresse+"',"+cardnumber+",'"+"')");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `Brugere`(name,phone_number,adresse,card_number) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, phonenumber);
            pstmt.setString(3, adresse);
            pstmt.setInt(4, cardnumber);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    void movemoney(String til , String fra , int belob){
        Statement stmt  = null;
        try {
            Connection conn = this.connect();
            stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `Transactions`(til,fra,amount) VALUES (?, ?, ?)");
            pstmt.setString(1, til);
            pstmt.setString(2, fra);
            pstmt.setInt(3, belob);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    int[] getAmountTranaction(){
        int[] tempintlist = new int[20];
        Statement stmt  = null;
        int i = 0;
        try {
            Connection conn = this.connect();
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT amount FROM Transactions");
            while (rs.next()){
                tempintlist[i]=  rs.getInt("amount");
                i++;
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tempintlist;
    }
    String[] getTranaction(int j){
        String[] templist = new String[20];
        int[] tempintlist = new int[20];
        int i = 0;
        String sql = null ;
        Statement stmt  = null;
        String columnlabel = null;
        switch (j){
            case 1:
                 sql = "SELECT til FROM Transactions";
                 columnlabel = "til" ;
                break;
            case 2:
                 sql = "SELECT fra FROM Transactions";
                columnlabel = "fra" ;
                break;
        }
        try {
            Connection conn = this.connect();
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()){
                templist[i]=  rs.getString(columnlabel);
                i++;
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return templist;
    }
}