import java.util.*;
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

class Library {
    private Map<Integer, Book> books = new HashMap<>();

    public void addBook(Book book) {
        if (books.containsKey(book.id)) {
            System.out.println("Book ID already exists. Please use a unique ID.");
        } else {
            books.put(book.id, book);
            System.out.println("Book added successfully!");
        }
    }

    public void removeBook(int id) {
        if (books.containsKey(id)) {
            books.remove(id);
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void issueBook(int id) {
        Book book = books.get(id);
        if (book != null && !book.isIssued) {
            book.isIssued = true;
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not available or already issued.");
        }
    }

    public void returnBook(int id) {
        Book book = books.get(id);
        if (book != null && book.isIssued) {
            book.isIssued = false;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid ID or book was not issued.");
        }
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("\n--- Book List ---");
            for (Book b : books.values()) {
                System.out.println(b);
            }
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase()) ||
                    b.author.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found for keyword: " + keyword);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. Search Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(id, title, author));
                }
                case 2 -> {
                    System.out.print("Enter Book ID to remove: ");
                    int id = sc.nextInt();
                    lib.removeBook(id);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int id = sc.nextInt();
                    lib.issueBook(id);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = sc.nextInt();
                    lib.returnBook(id);
                }
                case 5 -> lib.listBooks();
                case 6 -> {
                    System.out.print("Enter title or author keyword: ");
                    String keyword = sc.nextLine();
                    lib.searchBook(keyword);
                }
                case 0 -> System.out.println("Exiting Library Management System. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
