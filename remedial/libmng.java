import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private int totalCopies;
    private int issuedCopies;

    public Book(int id, String title, String author, int totalCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.issuedCopies = 0;
    }

    public int getId() {
        return id;
    }

    public int getAvailableCopies() {
        return totalCopies - issuedCopies;
    }

    public void updateBook(String title, String author, int totalCopies) {
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
    }

    public boolean issueBook() {
        if (getAvailableCopies() > 0) {
            issuedCopies++;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (issuedCopies > 0) {
            issuedCopies--;
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("Book ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Total Copies: " + totalCopies);
        System.out.println("Issued Copies: " + issuedCopies);
        System.out.println("Available Copies: " + getAvailableCopies());
        System.out.println("----------------------");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public Book searchBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public void updateBook(int id, String title, String author, int totalCopies) {
        Book book = searchBook(id);

        if (book != null) {
            book.updateBook(title, author, totalCopies);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void deleteBook(int id) {
        Book book = searchBook(id);

        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void issueBook(int id) {
        Book book = searchBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.issueBook()) {
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not available right now.");
        }
    }

    public void reissueBook(int id) {
        Book book = searchBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Book reissued successfully.");
        }
    }

    public void returnBook(int id) {
        Book book = searchBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.returnBook()) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("No issued copy found for this book.");
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : books) {
            b.display();
        }
    }
}

public class libmng {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Reissue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Display All Books");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Total Copies: ");
                    int copies = sc.nextInt();

                    library.addBook(new Book(id, title, author, copies));
                    break;

                case 2:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = sc.nextInt();

                    Book book = library.searchBook(searchId);
                    if (book != null) {
                        book.display();
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter New Author: ");
                    String newAuthor = sc.nextLine();

                    System.out.print("Enter New Total Copies: ");
                    int newCopies = sc.nextInt();

                    library.updateBook(updateId, newTitle, newAuthor, newCopies);
                    break;

                case 4:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = sc.nextInt();

                    library.deleteBook(deleteId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();

                    library.issueBook(issueId);
                    break;

                case 6:
                    System.out.print("Enter Book ID to reissue: ");
                    int reissueId = sc.nextInt();

                    library.reissueBook(reissueId);
                    break;

                case 7:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();

                    library.returnBook(returnId);
                    break;

                case 8:
                    library.displayBooks();
                    break;

                case 9:
                    System.out.println("Exiting Library System.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
