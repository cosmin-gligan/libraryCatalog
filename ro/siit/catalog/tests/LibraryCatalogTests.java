package ro.siit.catalog.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ro.siit.catalog.model.Book;
import ro.siit.catalog.model.Novel;
import ro.siit.catalog.model.NovelGenreEnum;
import ro.siit.catalog.service.LibraryService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.InvalidKeyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test metode din library catalog")
public class LibraryCatalogTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    //populam catalogul pt. material de lucru
    @BeforeEach
    public void setUp() {
        //capturam systemout-ul in outContent
        System.setOut(new PrintStream(outContent));
        LibraryService.populateLibraryCatalog();
    }
    //test stergere cu un nume carte valid
    @Test
    @DisplayName("Stergere carte valida")
    public void testDeleteBook() {
        try {
            Book deletedBook = LibraryService.removeBookFromLibraryCatalog("Dune");
            assertEquals(deletedBook.getTitle(), "Dune");
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        }
    }
    //test stergere carte cu nume invalid
    @Test
    @DisplayName("Stergere carte invalida")
    public void testDeleteInvalidBook() {
        assertThrows(InvalidKeyException.class, () -> LibraryService.removeBookFromLibraryCatalog("Dune 2021"));
    }
    //test cautare carte dupa nume valid
    @Test
    @DisplayName("Cauta carte valida")
    public void testGetBookByName() {
        try {
            String bookName = "Hyperion";
            Book searchedBook = LibraryService.getBookByNameFromCatalog(bookName);
            assertEquals(searchedBook.getTitle(), "Hyperion");
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        }
    }
    //test cautare carte dupa nume invalid
    @Test
    @DisplayName("Cauta carte invalida")
    public void testGetInvalidBookByName() {
        assertThrows(InvalidKeyException.class, () -> LibraryService.getBookByNameFromCatalog("Dune 2021"));
    }
    //Test adaugare carte in catalog
    @ParameterizedTest
    @DisplayName("Adaugare nuvele in lista")
    @CsvSource({"Do Androids Dream of Electric Sheep, 9780345404473, Del Rey, Philip, K. Dick, 13.69, 240, SF", "1984, 9780451524935, Signet Classic, Orwell, George, 8.75, 328, SF"})
    public void testAddNovel2LibraryCatalog(String title, String isbn, String publisher, String authorFirstName, String authorLastName, double price, int numberOfPages, NovelGenreEnum novelGenre){
        Book testBook = new Novel(title, isbn, publisher, authorFirstName, authorLastName, price, numberOfPages, novelGenre);
        LibraryService.addBook2Catalog(testBook);
    }
    //test actualizare carti valide
    @ParameterizedTest
    @DisplayName("Actualizare pret pentru carti")
    @CsvSource(value = {"Pasts, Futures, and Aftermaths; 25", "Dune; 10"}, delimiter = ';')
    public void testUpdatePrice4Book(String bookName, int discountPercent){
        try {
            LibraryService.updatePrice4Book(bookName, discountPercent);
        }catch(InvalidKeyException e){
            System.out.println(e.getMessage());
        }
    }
    @ParameterizedTest
    @DisplayName("Actualizare pret pentru carti invalide")
    @CsvSource(value = {"Crime and punishment;15", "Beyond good and evil;10"}, delimiter = ';')
    public void testUpdatePrice4InvalidBook(String bookName, int discountPercent){
        assertThrows(InvalidKeyException.class, () -> LibraryService.updatePrice4Book(bookName, discountPercent));
    }
    @Test
    @DisplayName("Listare catalog la consola")
    public void testPrintareCatalog(){
        LibraryService.printLibraryCatalog();
        String outC = outContent.toString();
        Assert.assertTrue(outC.indexOf("Dune") > 0);
    }
    @Test
    @DisplayName("Listare invalida catalog la consola")
    public void testPrintareInvalidaCatalog(){
        LibraryService.printLibraryCatalog();
        String outC = outContent.toString();
        Assert.assertTrue(outC.indexOf("Karenina") == -1);
    }
    //restauram out-ul default
    @After
    public void restoreStreams(){
        System.setOut(originalOut);
    }
}
