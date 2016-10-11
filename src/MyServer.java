import java.net.*;
import java.io.*;

public class MyServer {

    ServerSocket ss;
    Socket s;
    InputStream is;
    int port = 2002;
    ObjectInputStream ois;

    public static void main(String args[]) {

        try {
            MyServer server = new MyServer();

            server.createConnection();

            SerializablePoint point1 = server.receivePoint();
            SerializablePoint point2 = server.receivePoint();
            System.out.print(server.distance(point1, point2));

            server.closeConnection();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void createConnection() throws IOException {
        this.ss = new ServerSocket(this.port);
        this.s = this.ss.accept();
        this.is = this.s.getInputStream();
        this.ois = new ObjectInputStream(this.is);
    }


    void closeConnection() throws IOException {
        this.is.close();
        this.s.close();
        this.ss.close();
    }

    SerializablePoint receivePoint() throws IOException, ClassNotFoundException {
        return (SerializablePoint) ois.readObject();
    }

    double distance(SerializablePoint point1, SerializablePoint point2) {
        double result = Math.pow(point1.x - point2.x, 2);
        result += Math.pow(point1.y - point2.y, 2);
        result += Math.pow(point1.z - point2.z, 2);
        return Math.sqrt(result);
    }

}
