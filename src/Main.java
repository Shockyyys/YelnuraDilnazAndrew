import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CinemaSystem cinemaSystem = new CinemaSystem();

        int choice;

        do {
            System.out.println("Hello! You have the following available functions:");
            System.out.println("1) To add a new movie;");
            System.out.println("2) To show all available movies;");
            System.out.println("3) To add a new user;");
            System.out.println("4) To buy a ticket;");
            System.out.println("5) To show order history;");
            System.out.println("6) Exit");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    addNewMovie(cinemaSystem, scanner);
                    break;
                case 2:
                    cinemaSystem.showAllMovies();
                    break;
                case 3:
                    addNewUser(cinemaSystem, scanner);
                    break;
                case 4:
                    buyTicket(cinemaSystem, scanner);
                    break;
                case 5:
                    cinemaSystem.showUserOrderHistory();
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 6);

        scanner.close();
    }


    private static void addNewMovie(CinemaSystem cinemaSystem, Scanner scanner) {
        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();

        System.out.print("Enter movie genre: ");
        String movieGenre = scanner.nextLine();
        System.out.print("Enter age restriction: ");
        String ageRestrictionString = scanner.nextLine();

        int ageRestriction;
        try {
            ageRestriction = Integer.parseInt(ageRestrictionString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age restriction format. Setting age restriction to 0.");
            ageRestriction = 0;
        }

        System.out.print("Enter movie duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.print("Enter release year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.print("Enter director: ");
        String director = scanner.nextLine();

        cinemaSystem.addMovie(movieTitle, movieGenre, ageRestriction, duration, releaseYear, director);
    }

    private static void addNewUser(CinemaSystem cinemaSystem, Scanner scanner) {
        System.out.print("Enter user's name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter user's age: ");
        int userAge = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.print("Enter user's balance: ");
        double userBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character left by nextDouble()

        System.out.print("Is the user a student? (yes/no): ");
        String isStudentInput = scanner.nextLine();
        boolean isStudent = isStudentInput.equalsIgnoreCase("yes");

        cinemaSystem.addUser(userName, userAge, userBalance, isStudent);
    }

    private static void buyTicket(CinemaSystem cinemaSystem, Scanner scanner) {
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        // Look for the user in the CinemaSystem
        User currentUser = cinemaSystem.getUserByName(userName);

        if (currentUser != null) {
            // User found, proceed with ticket purchase
            cinemaSystem.setCurrentUser(currentUser);
            System.out.println("Welcome back, " + userName + "!");

            cinemaSystem.showAllMovies();  // Display available movies and ticket prices

            System.out.print("Enter movie title: ");
            String movieTitle = scanner.nextLine();

            System.out.print("Enter date: ");
            String date = scanner.nextLine();

            System.out.print("Enter time: ");
            String time = scanner.nextLine();

            // Find the selected movie in the list
            Movie selectedMovie = cinemaSystem.getMovies().stream()
                    .filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle))
                    .findFirst()
                    .orElse(null);

            if (selectedMovie != null) {
                // Call the buyTicket method in CinemaSystem
                cinemaSystem.buyTicket(cinemaSystem.getCurrentUser(), selectedMovie, date, time);

                // Display the new balance of the user
                System.out.println("New Balance: $" + cinemaSystem.getCurrentUser().getBalance());

            } else {
            System.out.println("Movie not found. Please check the title and try again.");
            }
        } else {
            // User not found
            System.out.println("User not found. Please add the user first.");
        }

    }

        }
