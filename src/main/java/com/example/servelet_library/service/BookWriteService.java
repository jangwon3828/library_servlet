package com.example.servelet_library.service;

import com.example.servelet_library.domain.Book;
import com.example.servelet_library.domain.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookWriteService {

    private BookRepository bookRepository;
    private BookReadService bookReadService;

    public BookWriteService(BookRepository bookRepository, BookReadService bookReadService){
        this.bookRepository=bookRepository;
        this.bookReadService=bookReadService;
    }

    public void getNewBookInfo() {
        String bookName = null;

        String author = null;

        String publisher =null;

        Long borrowCount = null;

        String ISBN_NO = null;
        LocalDate year_of_publication = null;
        Long count = null;

        Book book = new Book(null, bookName, author, publisher, borrowCount, ISBN_NO, year_of_publication, count);

        bookRepository.insertBook(book);
    }


    public void checkOutBook() {


        List<Book> books = new ArrayList<>();
        Long index;
        while (books.size()==0) {
            index = null;
            books = selectFind(index, books);
        }
        Long bookId = null;
        bookRepository.checkoutBook(bookId);

    }

    public void returnBook() {
        List<Book> books = null;
        Long index;
        while (books == null) {
            index = null;
            books = selectFind(index, books);
        }
        Long bookId = null;
      bookRepository.returnBook(bookId);
    }


    private List<Book> selectFind(Long index, List<Book> books) {
        int idx= Math.toIntExact(index);
        switch (idx) {
            case 1: {
                books = bookReadService.findByAuthor();
            }
            break;
            case 2: {
                books = bookReadService.findByBookName();
            }
            break;
            case 3: {
                books = bookReadService.findByPublisher();
            }
            break;
        }
        return books;

    }
}
