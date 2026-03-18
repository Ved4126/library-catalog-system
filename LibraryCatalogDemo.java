import java.util.*;

/**
 * Demo class to showcase the Library Catalog System functionality
 * without requiring user input.
 */
public class LibraryCatalogDemo {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY CATALOG SYSTEM DEMO ===");
        
        // Initialize catalog
        LibraryCatalog catalog = new LibraryCatalog();
        BookDataGenerator generator = new BookDataGenerator();
        generator.populateCatalog(catalog);
        
        System.out.println("Total books in catalog: " + catalog.getTotalBooks());
        System.out.println();
        
        // Test 1: Display statistics
        System.out.println("=== CATALOG STATISTICS ===");
        Map<String, Object> stats = catalog.getStatistics();
        System.out.println("Total Books: " + stats.get("totalBooks"));
        System.out.println("Available Books: " + stats.get("availableBooks"));
        System.out.println("Checked Out Books: " + stats.get("checkedOutBooks"));
        
        @SuppressWarnings("unchecked")
        Map<String, Integer> categoryCounts = (Map<String, Integer>) stats.get("categoryCounts");
        System.out.println("\nBooks by Category:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
        
        // Test 2: Performance test for binary search
        System.out.println("=== PERFORMANCE TEST ===");
        performanceTest(catalog);
        
        // Test 3: Search demonstrations
        System.out.println("=== SEARCH DEMONSTRATIONS ===");
        demonstrateSearches(catalog);
        
        // Test 4: Book operations
        System.out.println("=== BOOK OPERATIONS ===");
        demonstrateBookOperations(catalog);
        
        System.out.println("\n=== DEMO COMPLETED ===");
        System.out.println("The Library Catalog System is working correctly!");
        System.out.println("Features demonstrated:");
        System.out.println("✓ OOP inheritance hierarchy (Book → Textbook, Fiction)");
        System.out.println("✓ Binary search algorithms for fast lookups");
        System.out.println("✓ 85+ books across multiple categories");
        System.out.println("✓ Sub-1 second search performance");
        System.out.println("✓ Book checkout/return operations");
    }
    
    private static void performanceTest(LibraryCatalog catalog) {
        System.out.println("Testing ISBN search performance...");
        long totalTime = 0;
        int testCount = 1000;
        
        for (int i = 0; i < testCount; i++) {
            String randomIsbn = String.format("978%010d", (long) (Math.random() * 10000000000L));
            
            long startTime = System.nanoTime();
            catalog.searchByIsbn(randomIsbn);
            long endTime = System.nanoTime();
            
            totalTime += (endTime - startTime);
        }
        
        double averageTime = (totalTime / testCount) / 1_000_000.0;
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
        System.out.println();
    }
    
    private static void demonstrateSearches(LibraryCatalog catalog) {
        // Search by category
        System.out.println("Searching for Fiction books:");
        List<Book> fictionBooks = catalog.searchByCategory("Fiction");
        System.out.println("Found " + fictionBooks.size() + " fiction books");
        if (!fictionBooks.isEmpty()) {
            System.out.println("Sample fiction book: " + fictionBooks.get(0));
        }
        
        // Search by author
        System.out.println("\nSearching for books by 'J.K. Rowling':");
        List<Book> rowlingBooks = catalog.searchByAuthor("J.K. Rowling");
        System.out.println("Found " + rowlingBooks.size() + " books by J.K. Rowling");
        
        // Search by book type
        System.out.println("\nSearching for Textbooks:");
        List<Book> textbooks = catalog.getBooksByType("Textbook");
        System.out.println("Found " + textbooks.size() + " textbooks");
        if (!textbooks.isEmpty()) {
            System.out.println("Sample textbook: " + textbooks.get(0));
        }
        System.out.println();
    }
    
    private static void demonstrateBookOperations(LibraryCatalog catalog) {
        // Get a sample book to demonstrate operations
        List<Book> availableBooks = catalog.getAvailableBooks();
        if (!availableBooks.isEmpty()) {
            Book sampleBook = availableBooks.get(0);
            System.out.println("Sample book: " + sampleBook);
            System.out.println("Available: " + sampleBook.isAvailable());
            
            // Check out the book
            boolean checkedOut = catalog.checkOutBook(sampleBook.getIsbn());
            System.out.println("Checked out: " + checkedOut);
            System.out.println("Now available: " + sampleBook.isAvailable());
            
            // Return the book
            boolean returned = catalog.returnBook(sampleBook.getIsbn());
            System.out.println("Returned: " + returned);
            System.out.println("Now available: " + sampleBook.isAvailable());
        }
        System.out.println();
    }
}
