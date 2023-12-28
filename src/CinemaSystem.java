import java.util.ArrayList;

public class CinemaSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Ticket> soldTickets = new ArrayList<>();
    private int ticketIdCounter = 1;

    // Getter methods
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void addMovie(String title, String genre, int ageRestriction, int duration, int releaseYear, String director) {
        Movie movie = new Movie(title, genre, ageRestriction, duration, releaseYear, director);
        movies.add(movie);
        System.out.println("Movie added: " + title);
        // Display the title in the confirmation message
    }


    public void showAllMovies() {
        System.out.println("Available Movies:");
        for (Movie movie : movies) {
            String movieInfo = String.format("'%s' - %s, dir. %s, %d minutes, %d, %d+",
                    movie.getTitle(), movie.getGenre(), movie.getDirector(),
                    movie.getDuration(), movie.getReleaseYear(), movie.getAgeRestriction());

            System.out.println(movieInfo);
        }
    }


    public void addUser(String name, int age, double balance, boolean isStudent) {
        User user = new User(name, age, balance, isStudent);
        users.add(user);
        System.out.println("User added. Hello, " + name + "!");
    }

    private double calculateTicketPrice(User user, Movie movie) {
        int basePrice = 15;  // Regular ticket price
        int childDiscountAge = 13;
        int studentDiscountPrice = 12;

        // Check user's age and student status to determine ticket price
        if (user.getAge() < childDiscountAge) {
            return 9.0;  // Child discount
        } else if (user.isStudent()) {
            return studentDiscountPrice;  // Student discount
        } else {
            return basePrice;  // Regular price
        }
    }

    public void buyTicket(User user, Movie movie, String date, String time) {
        double ticketPrice = calculateTicketPrice(user, movie);
        if (user.getBalance() >= ticketPrice) {
            Ticket ticket = new Ticket(ticketIdCounter++, movie.getTitle(), date, time, ticketPrice);
            soldTickets.add(ticket);
            user.addToOrderHistory(ticket);
            user.setBalance(user.getBalance() - ticketPrice);
            System.out.println("Ticket purchased successfully!");

            // Display the new balance of the user
            System.out.println("New Balance: $" + user.getBalance());
        } else {
            System.out.println("Insufficient funds. Unable to purchase a ticket.");
        }
    }

    public void showUserOrderHistory() {
        if (currentUser != null) {
            System.out.println("Order History for " + currentUser.getName() + ":");
            for (Ticket ticket : currentUser.getOrderHistory()) {
                System.out.println("Ticket ID: " + ticket.getId() +
                        ", Movie: " + ticket.getMovieName() +
                        ", Date: " + ticket.getDate() +
                        ", Time: " + ticket.getTime() +
                        ", Price: $" + ticket.getPrice());
            }
        } else {
            System.out.println("No user selected. Please select a user first.");
        }
    }

    private User currentUser;  // Add this field to store the current user

    public User getCurrentUser() {
        return currentUser;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null; // User not found
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
