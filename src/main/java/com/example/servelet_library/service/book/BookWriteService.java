package com.example.servelet_library.service.book;

import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.domain.book.BookRepository;

import java.time.LocalDate;

public class BookWriteService {

    private static BookWriteService bookWriteService=new BookWriteService();
    private BookWriteService(){
    }

    public static BookWriteService getInstance(){
        return bookWriteService;
    }

    private BookRepository bookRepository=BookRepository.getInstance();
    private BookReadService bookReadService=BookReadService.getInstance();

    public void saveBook(Book book){
        bookRepository.insertBook(book);
    }
    public void plusBook(Book book){
        bookRepository.plusBook(book);
    }

    public void updateBook(Book book){
        bookRepository.updateBook(book);
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


    public boolean checkout(Long book_id) {
        return bookRepository.checkoutBook(book_id);
    }
}