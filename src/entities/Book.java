package entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book() {
    }

    public Book(String title, String author, String isbn, boolean available) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setAvailable(available);
    }

    public Book(int id, String title, String author, String isbn, boolean available) {
        this(title, author, isbn, available);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getIsbn() { return isbn; }

    public boolean isAvailable() { return available; }


    public void setId(int id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setAuthor(String author) { this.author = author; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public void setAvailable(boolean available) { this.available = available; }
}