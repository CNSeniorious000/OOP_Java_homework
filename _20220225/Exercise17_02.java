import java.io.*;

public class Exercise17_02 {
    public static void main(String[] args) throws IOException {
        DataOutputStream f = new DataOutputStream(new FileOutputStream("Exercise17_02.dat", true));
        for (int i = 0; i < 100; i++) f.writeInt((int) (123456789 * Math.random()));
    }
}