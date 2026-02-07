package entities;

public class BookFactory {

    public static Book createBook(BookType type, String title, String author, String isbn) {
        return switch (type) {
            case PRINTED -> new Book(title, author, isbn, true);
            case EBOOK -> new Book(title + " (E-Book)", author, isbn, true);
            case REFERENCE -> new Book(title + " (Reference)", author, isbn, false);
        };
    }
}
