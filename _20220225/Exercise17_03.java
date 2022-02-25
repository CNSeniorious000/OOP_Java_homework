import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Exercise17_03 {
    public static void main(String[] args) throws IOException {
        DataInputStream f = new DataInputStream(new FileInputStream("Exercise17_02.dat"));
        long sum = 0;
        // int的和可能超过int的范围
        try {
            while (true) sum += f.readInt();
        } catch (IOException ignored) {
            System.out.println(sum);
        }
    }
}