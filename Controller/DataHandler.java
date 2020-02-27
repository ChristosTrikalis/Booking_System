package data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler implements DataHandlerInterface
{
    private static ArrayList<String[]> ClientsInformation = new ArrayList<String[]>();
    private String requestName,requestCredentials;
    private int requestId;
    private static final String bold = "\033[0;1m";
    private static Connection conn;

    public DataHandler(String name, int id, String credentials)
    {
        requestName = name;
        requestId = id;
        requestCredentials = credentials;
        setConnection();
    }
    private void setConnection()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trikalischrschema?connectTimeout=0&socketTimeout=0&useSSL=false",
                    "trikalisChristos", "JamesonSucks96");
        }catch (java.sql.SQLException e){e.printStackTrace();}
         catch (java.lang.ClassNotFoundException e){e.printStackTrace();}
    }
    public void closeConn()
    {
        try {
            if(conn != null)
            {
                conn.close();
                System.out.println("\033[0;1m Database closed");
            }
        }catch (java.sql.SQLException e){e.printStackTrace();}
    }


    private void getValues(int id)
    {
        try
        {
            Statement stmt = conn.createStatement();
            String query = "select firstName,lastName from reservation where id ="+id;

            ResultSet rs = stmt.executeQuery(query);
            while ( rs.next() )
            {
                int numColumns = rs.getMetaData().getColumnCount();
                for ( int i = 1 ; i <= numColumns ; i+=3 )
                {
                    String[] info = new String[2];
                    info[0] = rs.getObject(i).toString();
                    info[1] = rs.getObject(i+1).toString();
                    ClientsInformation.add(info);
                }
            }

        }catch (java.sql.SQLException e){e.printStackTrace();}
    }
    private void insertValues(int id, String firstName, String lastName, String email, String phone,int quantity)
    {
        try
        {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(" insert into reservation values("+id+",'"+firstName+"','"+lastName+"','"+email+"',"+phone+","+quantity+")");
            System.out.println(bold+"Your data were succesfully stored!");
        }catch (java.sql.SQLException e){e.printStackTrace();}
    }
    private int getLastIDstored()
    {
        int last_id =0;
        try {
            Statement stmt = conn.createStatement();
            String query = "select id from reservation ORDER BY id DESC LIMIT 1";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                last_id = rs.getInt(1);

        }catch (java.sql.SQLException e){e.printStackTrace();}
        return last_id;
    }
    public int getId()
    {
        return getLastIDstored();
    }
    //public searchByName,searchByLastName,update, delete

    public void showResults(int id)
    {
        String[] result = getClientsInformation(id);
        String str="";
        for (String aResult : result)
        {
            str +=" "+aResult;
        }
        System.out.println(str);
    }
    public void insert(int id, String firstName, String lastName, String email, String phone,int quantity)
    {
        insertValues(id,firstName,lastName,email,phone,quantity);
    }
    private String[] getClientsInformation(int i)
    {
        getValues(i);
        return ClientsInformation.get(i);
    }


}
