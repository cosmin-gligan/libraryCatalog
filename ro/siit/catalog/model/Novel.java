package ro.siit.catalog.model;

public class Novel extends Book {

    private NovelGenreEnum novelGenre; // novel genre: sf, drama, romance

    public Novel(String title, String isbn, String publisher, String authorFirstName, String authorLastName, double price, int numberOfPages, NovelGenreEnum novelGenre) {
        //am folosit constructorul din clasa abstracta "Book" sa asignez valori field-urilor comune
        super(title, isbn, publisher, authorFirstName, authorLastName, price, numberOfPages);
        //asignam valoare field-ului specific clasei
        this.novelGenre = novelGenre;
    }

    public NovelGenreEnum getNovelGenre() {
        return novelGenre;
    }

    public void setNovelGenre(NovelGenreEnum novelGenre) {
        this.novelGenre = novelGenre;
    }

    @Override
    public String toString() {
        return "Novel: " +
                "title='" + getTitle() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", isbn='" + getIsbn() + '\'' +
                ", author='" + getAuthorFullName() + '\'' +
                ", price=" + getPrice() +
                ", noOfPages=" + getNumberOfPages() +
                ", genre=" + getNovelGenre();
    }
}
