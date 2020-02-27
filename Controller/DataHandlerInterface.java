package data;

public interface DataHandlerInterface
{
    void showResults(int id);

    void insert(int id, String firstName, String lastName, String email, String phone, int quantity);

    int getId();

    void closeConn();
}
