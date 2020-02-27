package presentation;
import application.Payment;
import data.DataHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;


public class UserInterface extends Thread
{
    private String phone,firstName,lastName,email;
    private int groupCount;
    private static DataInputStream in=null;
    private static DataOutputStream out = null;
    private static ServerSocket socket;
    private static Socket connection ;
    private Values values = new Values();

    public void run()
    {
        OpenConnection();
            try
            {
                firstName = in.readUTF();
                lastName = in.readUTF();
                email = in.readUTF();
                phone = in.readUTF();
                groupCount = in.readInt();
                if ((groupCount % 5) != 0) {
                    groupCount = 5;
                }
                Payment payment = new Payment(groupCount,false);
                payment.insertValues(firstName,lastName,email,phone);
                out.writeBoolean(true);
            }
            catch (IOException e1)
            {
                System.out.println("Oops! something went wrong!");
            }

            CloseConnection();

    }
    private void OpenConnection()
    {
        try
        {

            socket = new ServerSocket(values.getPort(), values.getClientSupport());
            System.out.println("Server is up!");
            connection = socket.accept();
            System.out.println("Connected with client");
            out = new DataOutputStream(connection.getOutputStream());
            in = new DataInputStream(connection.getInputStream());
        }
        catch (IOException e){e.printStackTrace();}
    }
    private static void CloseConnection()
    {
        if(out!=null && in !=null && socket !=null)
        {
            try
            {
                out.close();
                in.close();
                socket.close();
            }
            catch (IOException e){System.out.println("Something went wrong");e.printStackTrace();}
        }
    }


}
