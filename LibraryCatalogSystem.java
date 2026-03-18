import java.util.*;

/**
 * Main application class for the Library Catalog System.
 * Provides a user interface for interacting with the catalog.
 */
public class LibraryCatalogSystem {
    private LibraryCatalog catalog;
    private Scanner scanner;
    
    public LibraryCatalogSystem() {
        this.catalog = new LibraryCatalog();
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\\n");
    }
    
    public static void main(String[] args) {
        LibraryCatalogSystem system = new LibraryCatalogSystem();
        system.initializeSystem();
        system.run();
    }
    
    /**
     * Initialize the system with sample data.
     */
    private void initializeSystem() {
        System.out.println("=== LIBRARY CATALOG SYSTEM ===");
        System.out.println("Initializing system with sample data...");
        
        BookDataGenerator generator = new BookDataGenerator();
        generator.populateCatalog(catalog);
        
        System.out.println("System initialized successfully!");
        System.out.println("Total books in catalog: " + catalog.getTotalBooks());
        System.out.println("================================\n");
    }
    
    /**
     * Main application loop.
     */
    private void run() {
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    searchByIsbn();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByAuthor();
                    break;
                case 4:
                    searchByCategory();
                    break;
                case 5:
                    displayAllBooks();
                    break;
                case 6:
                    displayBooksByCategory();
                    break;
                case 7:
                    checkOutBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 9:
                    displayStatistics();
                    break;
                case 10:
                    performanceTest();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        System.out.println("Thank you for using the Library Catalog System!");
        scanner.close();
    }
    
    /**
     * Display the main menu.
     */
    private void displayMenu() {
        System.out.println("\n=== LIBRARY CATALOG SYSTEM MENU ===");
        System.out.println("1. Search by ISBN");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.println("4. Search by Category");
        System.out.println("5. Display All Books");
        System.out.println("6. Display Books by Category");
        System.out.println("7. Check Out Book");
        System.out.println("8. Return Book");
        System.out.println("9. Display Statistics");
        System.out.println("10. Performance Test");
        System.out.println("0. Exit");
        System.out.println("==================================");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Get user menu choice.
     */
    private int getMenuChoice() {
        try {
            
            System.out.flush();
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("No input received. Exiting...");
                return 0;
            }
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        } catch (NoSuchElementException e) {
            System.out.println("No input available. Exiting...");
            return 0;
        }
    }
    
    /**
     * Search for a book by ISBN.
     */
    private void searchByIsbn() {
        System.out.print("Enter ISBN to search: ");
        String isbn = scanner.nextLine();
        
        long startTime = System.nanoTime();
        Book book = catalog.searchByIsbn(isbn);
        long endTime = System.nanoTime();
        
        double searchTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        if (book != null) {
            System.out.println("\nBook found!");
            System.out.println(book);
            System.out.printf("Search time: %.3f ms%n", searchTime);
        } else {
            System.out.println("Book not found.");
            System.out.printf("Search time: %.3f ms%n", searchTime);
        }
    }
    
    /**
     * Search for a book by title.
     */
    private void searchByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        
        long startTime = System.nanoTime();
        Book book = catalog.searchByTitle(title);
        long endTime = System.nanoTime();
        
        double searchTime = (endTime - startTime) / 1_000_000.0;
        
        if (book != null) {
            System.out.println("\nBook found!");
            System.out.println(book);
            System.out.printf("Search time: %.3f ms%n", searchTime);
        } else {
            System.out.println("Book not found.");
            System.out.printf("Search time: %.3f ms%n", searchTime);
        }
    }
    
    /**
     * Search for books by author.
     */
    private void searchByAuthor() {
        System.out.print("Enter author name to search: ");
        String author = scanner.nextLine();
        
        long startTime = System.nanoTime();
        List<Book> books = catalog.searchByAuthor(author);
        long endTime = System.nanoTime();
        
        double searchTime = (endTime - startTime) / 1_000_000.0;
        
        System.out.println("\nBooks by " + author + ":");
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
        System.out.printf("Search time: %.3f ms%n", searchTime);
    }
    
    /**
     * Search for books by category.
     */
    private void searchByCategory() {
        System.out.println("Available categories: Fiction, Textbook, General");
        System.out.print("Enter category to search: ");
        String category = scanner.nextLine();
        
        long startTime = System.nanoTime();
        List<Book> books = catalog.searchByCategory(category);
        long endTime = System.nanoTime();
        
        double searchTime = (endTime - startTime) / 1_000_000.0;
        
        System.out.println("\nBooks in " + category + " category:");
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
        System.out.printf("Search time: %.3f ms%n", searchTime);
    }
    
    /**
     * Display all books in the catalog.
     */
    private void displayAllBooks() {
        catalog.displayAllBooks();
    }
    
    /**
     * Display books by category.
     */
    private void displayBooksByCategory() {
        System.out.println("Available categories: Fiction, Textbook, General");
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        catalog.displayBooksByCategory(category);
    }
    
    /**
     * Check out a book.
     */
    private void checkOutBook() {
        System.out.print("Enter ISBN of book to check out: ");
        String isbn = scanner.nextLine();
        
        if (catalog.checkOutBook(isbn)) {
            System.out.println("Book checked out successfully!");
        } else {
            System.out.println("Book not found or already checked out.");
        }
    }
    
    /**
     * Return a book.
     */
    private void returnBook() {
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine();
        
        if (catalog.returnBook(isbn)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found or already available.");
        }
    }
    
    /**
     * Display catalog statistics.
     */
    private void displayStatistics() {
        Map<String, Object> stats = catalog.getStatistics();
        
        System.out.println("\n=== CATALOG STATISTICS ===");
        System.out.println("Total Books: " + stats.get("totalBooks"));
        System.out.println("Available Books: " + stats.get("availableBooks"));
        System.out.println("Checked Out Books: " + stats.get("checkedOutBooks"));
        
        @SuppressWarnings("unchecked")
        Map<String, Integer> categoryCounts = (Map<String, Integer>) stats.get("categoryCounts");
        System.out.println("\nBooks by Category:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    /**
     * Performance test for binary search.
     */
    private void performanceTest() {
        System.out.println("\n=== PERFORMANCE TEST ===");
        
        // Test ISBN search performance
        System.out.println("Testing ISBN search performance...");
        long totalTime = 0;
        int testCount = 1000;
        
        for (int i = 0; i < testCount; i++) {
            // Generate random ISBN for testing
            String randomIsbn = String.format("978%010d", (long) (Math.random() * 10000000000L));
            
            long startTime = System.nanoTime();
            catalog.searchByIsbn(randomIsbn);
            long endTime = System.nanoTime();
            
            totalTime += (endTime - startTime);
        }
        
        double averageTime = (totalTime / testCount) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("Average ISBN search time over %d searches: %.3f ms%n", testCount, averageTime);
        
        // Test title search performance
        System.out.println("Testing title search performance...");
        totalTime = 0;
        
        for (int i = 0; i < testCount; i++) {
            String randomTitle = "Test Title " + i;
            
            long startTime = System.nanoTime();
            catalog.searchByTitle(randomTitle);
            long endTime = System.nanoTime();
            
            totalTime += (endTime - startTime);
        }
        
        averageTime = (totalTime / testCount) / 1_000_000.0;
        System.out.printf("Average title search time over %d searches: %.3f ms%n", testCount, averageTime);
        
        System.out.println("Performance test completed!");
    }
}

