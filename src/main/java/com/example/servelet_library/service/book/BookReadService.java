package com.example.servelet_library.service.book;


import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.domain.book.BookRepository;
import com.example.servelet_library.domain.dto.BooksPage;

import java.time.LocalDate;
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

    public BooksPage findByAuthor(String book, Integer currentPage){
        return bookRepository.findByAuthor(book,currentPage);
    }


    public  BooksPage findByBookName(String book, Integer currentPage){
        return bookRepository.findByBookName(book,currentPage);
    }

    public BooksPage findByThreeWay(String searchData, Integer currentPage) {
        return bookRepository.findByThreeWay(searchData,currentPage);
    }

    public  BooksPage findByPublisher(String book, Integer currentPage){
        return bookRepository.findByPublisher(book,currentPage);
    }
    public  Book findByISBM(String book){
        return bookRepository.findByISBM(book);
    }
    public  Book findById(Long id){
        return bookRepository.findById(id);
    }

    public  BooksPage findByYear(){
        LocalDate localDate = LocalDate.now();
        return bookRepository.findByYear(localDate);
    }


    public  List<Book> findByTop10(){
        List<Book> books = bookRepository.findByAll();
        Collections.sort(books);
        return books.subList(0, 10);
    }

    public void getDeleteBookInfo(Long book_id){
            bookRepository.deleteBook(book_id);
    }

    public BooksPage findAll() {
        return bookRepository.findAll();
    }

    public BooksPage findByAllPage(Integer currentPage) {
        return bookRepository.findByAllPage(currentPage);
    }
}
