import java.net.*;
import java.io.*;

public class MyClient {

    Socket s;
    OutputStream os;
    ObjectOutputStream oos;

    String host = "localhost";
    int port = 2002;

    public static void main(String args[]) {

        try {
            MyClient client = new MyClient();
            client.createConnection();

            client.sendPoint(1,1,1);
            client.sendPoint(0,0,0);

            client.closeConnection();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void createConnection() throws IOException {
        s = new Socket(host, port);
        os = s.getOutputStream();
        oos = new ObjectOutputStream(os);
    }

    void sendPoint(double x, double y, double z) throws IOException {
        oos.writeObject(new SerializablePoint(x, y, z));
    }

    void closeConnection() throws IOException {
        oos.close();
        os.close();
        s.close();
    }


}