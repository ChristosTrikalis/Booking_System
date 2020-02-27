package presentation;

public final class Values implements ValuesInterface
{
    private static final String ipServer = "192.168.1.6";
    private static final int portServer = 8000;
    private static final int clientSupport = 2;

    public String getIp()
    {

        return ipServer;
    }

    public int getPort()
    {

        return portServer;
    }

    public int getClientSupport()
    {

        return clientSupport;
    }


}
