package ro.siit.catalog.service;

import ro.siit.catalog.model.*;

import java.security.InvalidKeyException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static ro.siit.catalog.common.Constant.Discounts.MAX_DISCOUNT_PERCENT;
import static ro.siit.catalog.common.Constant.Discounts.MIN_DISCOUNT_PERCENT;

public class LibraryService {
    public static void startApp() {
        //cream map-ul si-l populam
        System.out.println("Catalogul de carti in starea initiala este:");
        Map<String, Book> libraryCatalog = new TreeMap<>(populateLibraryCatalog());

        //afisam map-ul in starea initiala
        printLibraryCatalog(libraryCatalog);

        //cautam o carte in catalog dupa nume, ii aplicam un %discount => pret vanzare diferit
        String searchedBookName = "Hyperion";
        try {
            //cautam cartea dupa nume
            Book searchedBook = getBookByNameFromCatalog(libraryCatalog, searchedBookName);
            //cartea cautata primeste un procent de discount aleatoriu intre 1 si 20
            int randomDiscountPercent = getRandomDiscountPercent();
            searchedBook.setDiscountedPrice(randomDiscountPercent);
            System.out.println("\nCartea " + searchedBook.getTitle() + " de " + searchedBook.getAuthorFullName() + " este la reducere ( % discount " + randomDiscountPercent + "), noul pret este: " + searchedBook.getPrice());
        } catch (InvalidKeyException invalidKeyException) {
            System.out.println("\nNu am reusit sa gasesc cartea cu numele " + searchedBookName);
        }

        //stergem o carte din catalog
        String bookToDelete = "Sh*t My Dad Says";
        try {
            Book deletedBook = removeBookFromLibraryCatalog(libraryCatalog, bookToDelete);
            System.out.println("\nA fost epuizat stocul pentru cartea " + deletedBook.getTitle() + " !");
        } catch (InvalidKeyException invalidKeyException) {
            System.out.println("\nNu am reusit sa sterg cartea cu numele " + bookToDelete);
        }

        //adaugam o noua carte in catalog
        Book foundation = new Novel("Foundation", "9780553293357", "Bantam Spectra Books", "Isaac", "Asimov", 20.65, 296, NovelGenreEnum.SF);
        System.out.println("\nLibraria a primit cartea " + foundation.getTitle() + " de " + foundation.getAuthorFullName() + " in stoc.");
        libraryCatalog.put(foundation.getTitle(), foundation);

        //afisam catalogul actualizat
        System.out.println("\n\nCatalogul de carti actualizat este:");
        printLibraryCatalog(libraryCatalog);
    }

    //populeaza catalog-ul
    private static Map<String, Book> populateLibraryCatalog() {
        Book hyperion = new Novel("Hyperion", "9780399178610", "Ace", "Dan", "Simmons", 16.27, 300, NovelGenreEnum.SF);
        Book dune = new Novel("Dune", "9780593099322", "Penguin Publishing Group", "Frank", "Herbert", 9.37, 500, NovelGenreEnum.SF);
        Book shtMyDadSays = new Novel("Sh*t My Dad Says", "9780061992704", "It Books", "Justin", "Halpern", 16, 250, NovelGenreEnum.COMEDY);
        ArtAlbum pastFutures = new ArtAlbum("Pasts, Futures, and Aftermaths", "9781734681710", "Koenig Books", "Adam", "Pendleton", 61, 100, PaperQualityEnum.FINE);
        ArtAlbum interiorGarden = new ArtAlbum("Interior Garden", "9783775750905", "Hatje Cantz", "Hannah", "Höch", 32, 125, PaperQualityEnum.POOR);

        Map<String, Book> libraryCatalog = new TreeMap<>() {{
            put(hyperion.getTitle(), hyperion);
            put(dune.getTitle(), dune);
            put(shtMyDadSays.getTitle(), shtMyDadSays);
            put(pastFutures.getTitle(), pastFutures);
            put(interiorGarden.getTitle(), interiorGarden);
        }};

        return libraryCatalog;
    }

    //printeaza catalog-ul
    private static void printLibraryCatalog(Map<String, Book> libraryCatalog) {
        for (Map.Entry<String, Book> bookInfo : libraryCatalog.entrySet()) {
            System.out.println(bookInfo.getValue());
        }
    }

    //sterge o carte din catalog
    private static Book removeBookFromLibraryCatalog(Map<String, Book> libraryCatalog, String bookName) throws InvalidKeyException {
        Book deletedBook = libraryCatalog.remove(bookName);
        if (deletedBook == null) {
            throw new InvalidKeyException();
        }
        return deletedBook;
    }

    //intoarce o carte pe baza denumirii
    private static Book getBookByNameFromCatalog(Map<String, Book> libraryCatalog, String bookName) throws InvalidKeyException {
        Book searchedBook = libraryCatalog.get(bookName.trim());
        if (searchedBook == null) {
            throw new InvalidKeyException();
        }
        return searchedBook;
    }

    //generam un procent discount random ( "constantele" sunt definite in common.Constant )
    private static int getRandomDiscountPercent() {
        Random random =  new Random();
        int randomDiscountPercent = random.nextInt(MAX_DISCOUNT_PERCENT - MIN_DISCOUNT_PERCENT + 1) + MIN_DISCOUNT_PERCENT;
        return randomDiscountPercent;
    }
}
