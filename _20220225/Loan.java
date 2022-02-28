import java.io.Serializable;

public class Loan implements Serializable {
    double money;
    String name;

    public Loan(double money, String name) {
        this.money = money;
        this.name = name;
    }
}
