/**
 * Base class for all books in the library catalog system.
 * Implements Comparable for binary search functionality.
 */
public abstract class Book implements Comparable<Book> {
    protected String isbn;
    protected String title;
    protected String author;
    protected int year;
    protected String category;
    protected boolean isAvailable;
    
    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }
    
    // Getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }
    
    // Setters
    public void setAvailable(boolean available) { this.isAvailable = available; }
    
    // Abstract method to be implemented by subclasses
    public abstract String getBookType();
    
    // Compare books by ISBN for binary search
    @Override
    public int compareTo(Book other) {
        return this.isbn.compareTo(other.isbn);
    }
    
    // Compare by title for title-based binary search
    public int compareByTitle(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }
    
    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Year: %d | Type: %s | Available: %s",
                isbn, title, author, year, getBookType(), isAvailable ? "Yes" : "No");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn.equals(book.isbn);
    }
    
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
