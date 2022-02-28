import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Exercise17_05 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        double ans = 0;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Exercise17_06.dat"))) {
            for (int i = 0; i < 5; i++) ans += ((Loan) in.readObject()).money;
        } catch (EOFException ignored) {
        } finally {
            System.out.println(ans);
        }
    }
}
