package ro.siit.catalog;

import ro.siit.catalog.service.LibraryService;

public class LibraryCatalog {

    /**
     * Course event
     * Create a small application that mimics a library catalog.
     * - LibraryCatalog class should contain an array of books where you need to implement some functionalities like:
     * 1. Add a book to library
     * 2. Update a book from library
     * 3. Delete a book from library
     * 4. Search for a book in library by name
     * 5. List/Print all books on console
     * You can also add your custom functionalities on the project, but the methods listed above are mandatory
     * Books are of two kinds: novels and art albums. They both have names and number of pages. Novels have type (like mystery, sf, etc.) while albums have paper quality
     * Model these entities (book, novel, album) with different classes and inheritance.
     **/
    static LibraryService libraryService = new LibraryService();

    public static void main(String[] args) {
        //am pus toata logica in LibraryService, pastram main-ul cat mai curat
        libraryService.startApp();
    }
}
