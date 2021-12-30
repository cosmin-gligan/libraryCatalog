package ro.siit.catalog.model;

public class ArtAlbum extends Book {
    private PaperQualityEnum paperQuality;

    public ArtAlbum(String title, String isbn, String publisher, String authorFirstName, String authorLastName, double price, int numberOfPages, PaperQualityEnum paperQuality) {
        //am folosit constructorul din clasa abstracta "Book" sa asignez valori field-urilor comune
        super(title, isbn, publisher, authorFirstName, authorLastName, price, numberOfPages);
        //asignam valoare field-ului specific clasei
        this.paperQuality = paperQuality;
    }

    public PaperQualityEnum getPaperQuality() {
        return paperQuality;
    }

    public void setPaperQuality(PaperQualityEnum paperQuality) {
        this.paperQuality = paperQuality;
    }

    @Override
    public String toString() {
        return "ArtAlbum: " +
                "title='" + getTitle() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", isbn='" + getIsbn() + '\'' +
                ", author='" + getAuthorFullName() + '\'' +
                ", price=" + getPrice() +
                ", noOfPages=" + getNumberOfPages() +
                ", paperQuality=" + getPaperQuality();
    }

}
