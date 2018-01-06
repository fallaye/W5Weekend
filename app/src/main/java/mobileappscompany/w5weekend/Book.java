package mobileappscompany.w5weekend;

/**
 * Created by fallaye on 1/5/18.
 */

public class Book {

    String title, author, imageURL;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                "image='" + imageURL + '\'' +
                '}';
    }

    public Book(String title, String author, String image) {
        this.title = title;
        this.author = author;
        this.imageURL = image;
    }
}
