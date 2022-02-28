import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Exercise17_04 {
    public static void main(String[] args) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Exercise17_06.dat"))) {
            for (int i = 0; i < 5; i++) out.writeObject(new Loan(Math.random(), String.valueOf(Math.random())));
        } catch (IOException exception) {
            System.out.println(exception.getCause().toString());
        }
    }
}

