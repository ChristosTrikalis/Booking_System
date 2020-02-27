import presentation.UserInterface;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class Launcher extends JFrame
{
    private JButton openServer;

    private Launcher()
    {
        super("Server");
        setLayout(new FlowLayout());
        Icon account = new ImageIcon(getClass().getResource("download.png"));
        openServer = new JButton("Open Server",account);
        add(openServer);
        openServer.setBackground(Color.white);
        ButtonHandler handler = new ButtonHandler();
        openServer.addActionListener(handler);

    }

    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

                if (event.getSource() == openServer)
                {

                    Thread t = new UserInterface();
                    t.start();
                    try
                    {
                        t.join();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }



        }

    }

    public static void main(String[] args)
    {
        Launcher launch = new Launcher();
        launch.getContentPane().setBackground(Color.darkGray);
        launch.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        launch.setSize(500,500);
        launch.setVisible(true);
    }

}
