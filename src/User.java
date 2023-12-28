import java.util.ArrayList;

public class User {
    private String name;
    private int age;
    private double balance;
    private boolean isStudent;  // New field
    private ArrayList<Ticket> orderHistory = new ArrayList<>();

    public User(String name, int age, double balance, boolean isStudent) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.isStudent = isStudent;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Ticket> getOrderHistory() {
        return orderHistory;
    }

    public void addToOrderHistory(Ticket ticket) {
        orderHistory.add(ticket);
    }

    public boolean isStudent(){
        return isStudent;
    }
}
