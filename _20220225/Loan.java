import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Loan implements Serializable {
    double money;
    String name;

    public Loan(double money, String name) {
        this.money = money;
        this.name = name;
    }

    public static void main(String[] args) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Exercise17_06.dat"))) {
            for (int i = 0; i < 5; i++) {
                double rand = Math.random() * 10000;
                System.out.print(rand + "\t");
                Loan loan = new Loan(rand, "name");
                out.writeObject(loan);
            }
        } catch (IOException E) {
            E.printStackTrace();
        }
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
