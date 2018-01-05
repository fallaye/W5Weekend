package mobileappscompany.w5weekend;

/**
 * Created by fallaye on 1/5/18.
 */

public class Book {

    String image, title, author;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Book(String image, String title, String author) {
        this.image = image;
        this.title = title;
        this.author = author;
    }
}
