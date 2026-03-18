import java.util.Random;

/**
 * Generates sample data for the Library Catalog System.
 * Creates 80+ books across different categories for testing.
 */
public class BookDataGenerator {
    private static final String[] FICTION_GENRES = {
        "Fantasy", "Science Fiction", "Mystery", "Romance", "Thriller", 
        "Horror", "Adventure", "Historical Fiction", "Literary Fiction", "Young Adult"
    };
    
    private static final String[] TEXTBOOK_SUBJECTS = {
        "Mathematics", "Physics", "Chemistry", "Biology", "Computer Science",
        "History", "English Literature", "Psychology", "Economics", "Engineering"
    };
    
    private static final String[] AUTHORS = {
        "J.K. Rowling", "George Orwell", "Harper Lee", "F. Scott Fitzgerald", "Jane Austen",
        "Charles Dickens", "Mark Twain", "Ernest Hemingway", "Virginia Woolf", "Toni Morrison",
        "Isaac Asimov", "Arthur C. Clarke", "Ray Bradbury", "Ursula K. Le Guin", "Octavia Butler",
        "Agatha Christie", "Stephen King", "Dan Brown", "John Grisham", "Michael Crichton",
        "Dr. Smith", "Prof. Johnson", "Dr. Williams", "Prof. Brown", "Dr. Davis",
        "Prof. Miller", "Dr. Wilson", "Prof. Moore", "Dr. Taylor", "Prof. Anderson"
    };
    
    private static final String[] TITLES = {
        // Fiction titles
        "The Great Adventure", "Mystery of the Lost City", "Love in Paris", "The Last Dragon",
        "Space Odyssey", "The Haunted Manor", "Time Traveler's Journal", "The Secret Garden",
        "War and Peace", "The Chronicles of Narnia", "Lord of the Rings", "Harry Potter",
        "The Hunger Games", "Divergent", "The Maze Runner", "Twilight", "The Fault in Our Stars",
        "To Kill a Mockingbird", "1984", "Animal Farm", "Pride and Prejudice", "Jane Eyre",
        "Wuthering Heights", "The Great Gatsby", "The Catcher in the Rye", "The Hobbit",
        "The Chronicles of Narnia", "The Lion, the Witch and the Wardrobe", "The Magician's Nephew",
        "The Voyage of the Dawn Treader", "The Silver Chair", "The Last Battle",
        
        // Textbook titles
        "Advanced Calculus", "Introduction to Physics", "Organic Chemistry", "Cell Biology",
        "Data Structures and Algorithms", "World History", "Shakespeare's Works", "Cognitive Psychology",
        "Microeconomics", "Mechanical Engineering", "Linear Algebra", "Quantum Mechanics",
        "Biochemistry", "Computer Networks", "Ancient Civilizations", "Modern Literature",
        "Social Psychology", "Macroeconomics", "Electrical Engineering", "Statistics",
        "Thermodynamics", "Genetics", "Database Systems", "Medieval History", "Creative Writing",
        "Abnormal Psychology", "International Economics", "Civil Engineering", "Probability Theory",
        "Electromagnetism", "Molecular Biology", "Software Engineering", "Renaissance Art",
        "Technical Writing", "Developmental Psychology", "Public Finance", "Aerospace Engineering"
    };
    
    private static final String[] SERIES = {
        "The Chronicles", "The Saga", "The Trilogy", "The Series", "The Collection",
        "The Adventures", "The Mysteries", "The Legends", "The Tales", "The Stories"
    };
    
    private static final String[] EDITIONS = {
        "1st Edition", "2nd Edition", "3rd Edition", "4th Edition", "5th Edition",
        "Revised Edition", "Updated Edition", "Special Edition", "International Edition"
    };
    
    private Random random = new Random(42); // Fixed seed for reproducible data
    
    /**
     * Populates the catalog with sample books.
     */
    public void populateCatalog(LibraryCatalog catalog) {
        System.out.println("Generating sample data...");
        
        // Generate Fiction books (30 books)
        for (int i = 0; i < 30; i++) {
            String isbn = generateISBN();
            String title = TITLES[i % TITLES.length] + " " + (i + 1);
            String author = AUTHORS[random.nextInt(AUTHORS.length)];
            int year = 1950 + random.nextInt(74); // 1950-2024
            String genre = FICTION_GENRES[random.nextInt(FICTION_GENRES.length)];
            String series = SERIES[random.nextInt(SERIES.length)];
            int pages = 200 + random.nextInt(600); // 200-800 pages
            
            Fiction book = new Fiction(isbn, title, author, year, genre, series, pages);
            catalog.addBook(book);
        }
        
        // Generate Textbook books (30 books)
        for (int i = 0; i < 30; i++) {
            String isbn = generateISBN();
            String title = TITLES[(i + 30) % TITLES.length] + " " + (i + 1);
            String author = AUTHORS[random.nextInt(AUTHORS.length)];
            int year = 1990 + random.nextInt(34); // 1990-2024
            String subject = TEXTBOOK_SUBJECTS[random.nextInt(TEXTBOOK_SUBJECTS.length)];
            String edition = EDITIONS[random.nextInt(EDITIONS.length)];
            int gradeLevel = 9 + random.nextInt(10); // 9-18 (high school to graduate)
            
            Textbook book = new Textbook(isbn, title, author, year, subject, edition, gradeLevel);
            catalog.addBook(book);
        }
        
        // Generate regular Book objects (25 books)
        for (int i = 0; i < 25; i++) {
            String isbn = generateISBN();
            String title = TITLES[(i + 60) % TITLES.length] + " " + (i + 1);
            String author = AUTHORS[random.nextInt(AUTHORS.length)];
            int year = 1960 + random.nextInt(64); // 1960-2024
            
            Book book = new Book(isbn, title, author, year) {
                @Override
                public String getBookType() {
                    return "General";
                }
            };
            book.category = "General";
            catalog.addBook(book);
        }
        
        // Randomly set some books as unavailable
        for (int i = 0; i < 15; i++) {
            Book randomBook = catalog.searchByIsbn(generateISBN());
            if (randomBook != null) {
                catalog.checkOutBook(randomBook.getIsbn());
            }
        }
        
        System.out.println("Sample data generation complete!");
        System.out.println("Total books added: " + catalog.getTotalBooks());
    }
    
    /**
     * Generates a random ISBN-13 number.
     */
    private String generateISBN() {
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }
    
    /**
     * Creates a specific set of books for performance testing.
     */
    public void addPerformanceTestBooks(LibraryCatalog catalog) {
        // Add books with known ISBNs for testing
        String[] testIsbns = {
            "9780000000001", "9780000000002", "9780000000003", "9780000000004", "9780000000005"
        };
        
        for (int i = 0; i < testIsbns.length; i++) {
            String isbn = testIsbns[i];
            String title = "Test Book " + (i + 1);
            String author = "Test Author " + (i + 1);
            int year = 2020 + i;
            
            Book book = new Book(isbn, title, author, year) {
                @Override
                public String getBookType() {
                    return "Test";
                }
            };
            book.category = "Test";
            catalog.addBook(book);
        }
    }
}
