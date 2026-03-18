import java.util.*;

/**
 * Library Catalog System with binary search capabilities for fast lookups.
 * Manages books using OOP principles and provides sub-1s lookup times.
 */
public class LibraryCatalog {
    private List<Book> booksByIsbn;
    private List<Book> booksByTitle;
    private Map<String, List<Book>> booksByCategory;
    private int totalBooks;
    
    public LibraryCatalog() {
        this.booksByIsbn = new ArrayList<>();
        this.booksByTitle = new ArrayList<>();
        this.booksByCategory = new HashMap<>();
        this.totalBooks = 0;
    }
    
    /**
     * Adds a book to the catalog and maintains sorted order for binary search.
     */
    public void addBook(Book book) {
        // Add to ISBN-sorted list
        insertSortedByIsbn(book);
        
        // Add to title-sorted list
        insertSortedByTitle(book);
        
        // Add to category map
        booksByCategory.computeIfAbsent(book.getCategory(), k -> new ArrayList<>()).add(book);
        
        totalBooks++;
    }
    
    /**
     * Inserts book in ISBN-sorted order for binary search.
     */
    private void insertSortedByIsbn(Book book) {
        int index = Collections.binarySearch(booksByIsbn, book);
        if (index < 0) {
            index = -(index + 1);
        }
        booksByIsbn.add(index, book);
    }
    
    /**
     * Inserts book in title-sorted order for binary search.
     */
    private void insertSortedByTitle(Book book) {
        int index = Collections.binarySearch(booksByTitle, book, Book::compareByTitle);
        if (index < 0) {
            index = -(index + 1);
        }
        booksByTitle.add(index, book);
    }
    
    /**
     * Binary search for book by ISBN - O(log n) complexity.
     */
    public Book searchByIsbn(String isbn) {
        Book searchBook = new Book(isbn, "", "", 0) {
            @Override
            public String getBookType() { return ""; }
        };
        
        int index = Collections.binarySearch(booksByIsbn, searchBook);
        return index >= 0 ? booksByIsbn.get(index) : null;
    }
    
    /**
     * Binary search for book by title - O(log n) complexity.
     */
    public Book searchByTitle(String title) {
        Book searchBook = new Book("", title, "", 0) {
            @Override
            public String getBookType() { return ""; }
        };
        
        int index = Collections.binarySearch(booksByTitle, searchBook, Book::compareByTitle);
        return index >= 0 ? booksByTitle.get(index) : null;
    }
    
    /**
     * Search for books by category.
     */
    public List<Book> searchByCategory(String category) {
        return booksByCategory.getOrDefault(category, new ArrayList<>());
    }
    
    /**
     * Search for books by author.
     */
    public List<Book> searchByAuthor(String author) {
        return booksByIsbn.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    /**
     * Get all available books.
     */
    public List<Book> getAvailableBooks() {
        return booksByIsbn.stream()
                .filter(Book::isAvailable)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    /**
     * Check out a book by ISBN.
     */
    public boolean checkOutBook(String isbn) {
        Book book = searchByIsbn(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }
    
    /**
     * Return a book by ISBN.
     */
    public boolean returnBook(String isbn) {
        Book book = searchByIsbn(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }
    
    /**
     * Get total number of books in catalog.
     */
    public int getTotalBooks() {
        return totalBooks;
    }
    
    /**
     * Get books by type.
     */
    public List<Book> getBooksByType(String bookType) {
        return booksByIsbn.stream()
                .filter(book -> book.getBookType().equals(bookType))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    /**
     * Display all books in the catalog.
     */
    public void displayAllBooks() {
        System.out.println("\n=== LIBRARY CATALOG ===");
        System.out.println("Total Books: " + totalBooks);
        System.out.println("========================");
        
        for (Book book : booksByIsbn) {
            System.out.println(book);
        }
    }
    
    /**
     * Display books by category.
     */
    public void displayBooksByCategory(String category) {
        List<Book> books = searchByCategory(category);
        System.out.println("\n=== " + category.toUpperCase() + " BOOKS ===");
        System.out.println("Count: " + books.size());
        System.out.println("========================");
        
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    /**
     * Get catalog statistics.
     */
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBooks", totalBooks);
        stats.put("availableBooks", getAvailableBooks().size());
        stats.put("checkedOutBooks", totalBooks - getAvailableBooks().size());
        
        Map<String, Integer> categoryCounts = new HashMap<>();
        for (String category : booksByCategory.keySet()) {
            categoryCounts.put(category, booksByCategory.get(category).size());
        }
        stats.put("categoryCounts", categoryCounts);
        
        return stats;
    }
}
