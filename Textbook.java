/**
 * Textbook class extending Book with subject-specific information.
 */
public class Textbook extends Book {
    private String subject;
    private String edition;
    private int gradeLevel;
    
    public Textbook(String isbn, String title, String author, int year, 
                   String subject, String edition, int gradeLevel) {
        super(isbn, title, author, year);
        this.subject = subject;
        this.edition = edition;
        this.gradeLevel = gradeLevel;
        this.category = "Textbook";
    }
    
    // Getters
    public String getSubject() { return subject; }
    public String getEdition() { return edition; }
    public int getGradeLevel() { return gradeLevel; }
    
    @Override
    public String getBookType() {
        return "Textbook";
    }
    
    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Year: %d | Type: %s | Subject: %s | Edition: %s | Grade: %d | Available: %s",
                isbn, title, author, year, getBookType(), subject, edition, gradeLevel, isAvailable ? "Yes" : "No");
    }
}

