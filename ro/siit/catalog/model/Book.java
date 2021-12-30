package ro.siit.catalog.model;

import java.util.Objects;

//clasa abstracta care contine field-urile comune ambelor tipuri de copii: artalbum si novel
//clasa contine si o metoda abstracta care-i forteaza pe "copii" sa-si implementeze propria lor versiune

public abstract class Book implements Comparable<Book> {
    protected String title;
    protected String publisher;
    protected String isbn; // International Standard Book Number
    protected String authorFirstName;
    protected String authorLastName;
    protected double price;
    protected int numberOfPages;

    //am facut constructorul "protected" sa poata fi folosit de clasele care mostenesc clasa abstracta
    protected Book(String title, String isbn, String publisher, String authorFirstName, String authorLastName, double price, int numberOfPages) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.price = price;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorFullName() {
        return authorFirstName + " " + authorLastName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setDiscountedPrice ( int discountPercent) {

      this.setPrice(calculateDiscountPrice(discountPercent));

    }

    private double calculateDiscountPrice(int discountPercent){
        double discountPrice = Math.round((this.getPrice() * ( 1 - discountPercent * 0.01)) * 100.00 ) / 100.00;
        return discountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int compareTo(Book o) {

        return getTitle().compareTo(o.getTitle());

    }

    // am facut metoda toString abstracta, sa fortez fiecare clasa sa-si implementeze propria ei versiune
    public abstract String toString();
}
