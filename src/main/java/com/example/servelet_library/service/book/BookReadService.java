package com.example.servelet_library.service.book;


import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.domain.book.BookRepository;
import com.example.servelet_library.domain.dto.BooksPage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookReadService {
    private static BookReadService bookReadService=new BookReadService();
    private BookRepository bookRepository=BookRepository.getInstance();

    private BookReadService(){
    }

    public static BookReadService getInstance(){
        return bookReadService;
    }

    public BooksPage findByAuthor(String book){
        return bookRepository.findByAuthor(book);
    }


    public  BooksPage findByBookName(String book){
        return bookRepository.findByBookName(book);
    }

    public BooksPage findByThreeWay(String searchData) {
        return bookRepository.findByThreeWay(searchData);
    }

    public  BooksPage findByPublisher(String book){
        return bookRepository.findByPublisher(book);
    }
    public  Book findByISBM(String book){
        return bookRepository.findByISBM(book);
    }

    public  BooksPage findByYear(){
        LocalDate localDate = LocalDate.now();
        return bookRepository.findByYear(localDate);
    }


    public  List<Book> findByTop10(){
        List<Book> books = bookRepository.findByAll();
        Collections.sort(books);
        return books;
    }

    public void getDeleteBookInfo(Long book_id){
            bookRepository.deleteBook(book_id);
    }

    public BooksPage findAll() {
        return bookRepository.findAll();
    }
}
