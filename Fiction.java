/**
 * Fiction class extending Book with genre-specific information.
 */
public class Fiction extends Book {
    private String genre;
    private String series;
    private int numberOfPages;
    
    public Fiction(String isbn, String title, String author, int year, 
                  String genre, String series, int numberOfPages) {
        super(isbn, title, author, year);
        this.genre = genre;
        this.series = series;
        this.numberOfPages = numberOfPages;
        this.category = "Fiction";
    }
    
    // Getters
    public String getGenre() { return genre; }
    public String getSeries() { return series; }
    public int getNumberOfPages() { return numberOfPages; }
    
    @Override
    public String getBookType() {
        return "Fiction";
    }
    
    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Year: %d | Type: %s | Genre: %s | Series: %s | Pages: %d | Available: %s",
                isbn, title, author, year, getBookType(), genre, series, numberOfPages, isAvailable ? "Yes" : "No");
    }
}

