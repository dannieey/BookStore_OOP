package org.example;

import models.*;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class BookStoreSystem {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Are you an admin or a user? (Enter 'admin' or 'user'): ");
            String role = scanner.nextLine().toLowerCase();

            if (role.equals("admin")) {
                // Админский функционал
                Connection connection = DataBaseConnection.getConnection();
                BookDAO bookDAO = new BookDAO(connection);

                while (true) {
                    System.out.println("\n===== ADMIN BOOKSTORE SYSTEM=====");
                    System.out.println("1. Add Book");
                    System.out.println("2. View Books");
                    System.out.println("3. Update Book");
                    System.out.println("4. Delete Book");
                    System.out.println("5. Exit");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter genre: ");
                            String genre = scanner.nextLine();
                            System.out.print("Enter price: ");
                            double price = scanner.nextDouble();
                            bookDAO.addBook(title, author, genre, price);
                            break;
                        case 2:
                            bookDAO.viewBooks();
                            break;
                        case 3:
                            System.out.print("Enter book ID to update: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new title: ");
                            String newTitle = scanner.nextLine();
                            System.out.print("Enter new author: ");
                            String newAuthor = scanner.nextLine();
                            System.out.print("Enter new price: ");
                            String newGenre = scanner.nextLine();
                            System.out.print("Enter new genre: ");
                            double newPrice = scanner.nextDouble();
                            bookDAO.updateBook(id, newTitle, newAuthor, newGenre, newPrice);
                            break;
                        case 4:
                            System.out.print("Enter book ID to delete: ");
                            int bookId = scanner.nextInt();
                            bookDAO.deleteBook(bookId);
                            break;
                        case 5:
                            System.out.println("Exiting the system...");
                            scanner.close();
                            connection.close();
                            System.exit(0);
                        default:
                            System.out.println("Invalid option, try again.");
                    }
                }
            } else if (role.equals("user")) {
                // Юзерский функционал
                while (true) {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();

                    Users user = new Users(name, surname);

                    List<String> genres = BookStoreService.getGenres();
                    System.out.println("\nAvailable genres: :");
                    for (int i = 0; i < genres.size(); i++) {
                        System.out.println((i + 1) + ". " + genres.get(i));
                    }

                    System.out.print("\nChoose an option: ");
                    int genreChoice = scanner.nextInt();
                    scanner.nextLine();
                    String selectedGenre = genres.get(genreChoice - 1);

                    List<Book> books = BookStoreService.getBooksByGenre(selectedGenre);
                    System.out.println("\nAvailable books:");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.println((i + 1) + ". " + books.get(i).getTitle() + " (Author: " + books.get(i).getAuthor() + ")");
                    }

                    System.out.print("\nChoose an option: ");
                    int bookChoice = scanner.nextInt();
                    scanner.nextLine();
                    Book selectedBook = books.get(bookChoice - 1);

                    System.out.println("\nYou have chosen: " + selectedBook.getTitle() + " (Author: " + selectedBook.getAuthor() + ")");
                    System.out.println("Price: " + selectedBook.getPrice() + " tenge.");

                    System.out.print("Do you want to purchase? (yes/no/menu): ");
                    String decision = scanner.nextLine().toLowerCase();

                    if (decision.equals("yes")) {
                        System.out.println("\nThank you for your purchase, " + user.getFirstName() + "!");
                        break;
                    } else if (decision.equals("menu")) {
                        System.out.println("\nReturning to the main menu...\n");
                    } else {
                        System.out.println("\nThank you for visiting!\n");
                        break;
                    }
                }
                scanner.close();
            } else {
                System.out.println("Invalid role. Exiting the system...");
                scanner.close();
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}