import java.util.ArrayList;
import java.util.Scanner;

class SeatNotAvailableException extends Exception {
    public SeatNotAvailableException(String message) {
        super(message);
    }
}

class Movie {
    private int id;
    private String name;
    private int totalSeats;
    private int bookedSeats;
    private double ticketPrice;
    private double totalCollection;

    public Movie(int id, String name, int totalSeats, double ticketPrice) {
        this.id = id;
        this.name = name;
        this.totalSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.bookedSeats = 0;
        this.totalCollection = 0;
    }

    public int getId() {
        return id;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    public void bookTickets(int seats) throws SeatNotAvailableException {
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be greater than zero.");
        }

        if (seats > getAvailableSeats()) {
            throw new SeatNotAvailableException("Not enough seats available.");
        }

        bookedSeats += seats;
        totalCollection += seats * ticketPrice;

        System.out.println("Tickets booked successfully.");
        System.out.println("Amount Paid: ₹" + (seats * ticketPrice));
    }

    public void cancelTickets(int seats) {
        if (seats <= 0) {
            System.out.println("Invalid number of seats.");
        } else if (seats > bookedSeats) {
            System.out.println("Cannot cancel more seats than booked.");
        } else {
            bookedSeats -= seats;
            totalCollection -= seats * ticketPrice;
            System.out.println("Tickets cancelled successfully.");
            System.out.println("Refund Amount: ₹" + (seats * ticketPrice));
        }
    }

    public void display() {
        System.out.println("Movie ID: " + id);
        System.out.println("Movie Name: " + name);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Booked Seats: " + bookedSeats);
        System.out.println("Available Seats: " + getAvailableSeats());
        System.out.println("Ticket Price: ₹" + ticketPrice);
        System.out.println("Total Collection: ₹" + totalCollection);
        System.out.println("----------------------");
    }
}

class MovieBookingSystem {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Movie added successfully.");
    }

    public Movie searchMovie(int id) {
        for (Movie m : movies) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void bookTicket(int id, int seats) {
        Movie movie = searchMovie(id);

        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        try {
            movie.bookTickets(seats);
        } catch (SeatNotAvailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public void cancelTicket(int id, int seats) {
        Movie movie = searchMovie(id);

        if (movie != null) {
            movie.cancelTickets(seats);
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void displayMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        for (Movie m : movies) {
            m.display();
        }
    }
}

public class moviemng {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieBookingSystem system = new MovieBookingSystem();

        system.addMovie(new Movie(1, "Interstellar", 50, 250));
        system.addMovie(new Movie(2, "Inception", 40, 220));
        system.addMovie(new Movie(3, "Avengers", 60, 300));

        while (true) {
            System.out.println("\n--- Movie Ticket Booking System ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Display Movies");
            System.out.println("3. Search Movie");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Movie ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Movie Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Total Seats: ");
                    int seats = sc.nextInt();

                    System.out.print("Enter Ticket Price: ");
                    double price = sc.nextDouble();

                    system.addMovie(new Movie(id, name, seats, price));
                    break;

                case 2:
                    system.displayMovies();
                    break;

                case 3:
                    System.out.print("Enter Movie ID to search: ");
                    int searchId = sc.nextInt();

                    Movie movie = system.searchMovie(searchId);

                    if (movie != null) {
                        movie.display();
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Movie ID: ");
                    int movieId = sc.nextInt();

                    System.out.print("Enter number of seats to book: ");
                    int bookSeats = sc.nextInt();

                    system.bookTicket(movieId, bookSeats);
                    break;

                case 5:
                    System.out.print("Enter Movie ID: ");
                    int cancelMovieId = sc.nextInt();

                    System.out.print("Enter number of seats to cancel: ");
                    int cancelSeats = sc.nextInt();

                    system.cancelTicket(cancelMovieId, cancelSeats);
                    break;

                case 6:
                    System.out.println("Exiting Movie Booking System.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
