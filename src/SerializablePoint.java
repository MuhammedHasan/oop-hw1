import java.io.*;

class SerializablePoint implements Serializable {

    double x;
    double y;
    double z;

    public SerializablePoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}