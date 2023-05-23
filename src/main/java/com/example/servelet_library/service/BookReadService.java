package com.example.servelet_library.service;


import com.example.servelet_library.domain.Book;
import com.example.servelet_library.domain.BookRepository;

import java.sql.DriverManager;
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

    public  List<Book> findByAuthor(String book){
        return bookRepository.findByAuthor(book);
    }


    public  List<Book> findByBookName(String book){
        return bookRepository.findByBookName(book);
    }

    public List<Book> findByThreeWay(String searchData) {
        return bookRepository.findByThreeWay(searchData);
    }

    public  List<Book> findByPublisher(String book){
        return bookRepository.findByPublisher(book);
    }

    public  List<Book> findByYear(){
        LocalDate localDate = LocalDate.now();
        List<Book> books = bookRepository.findByYear(localDate);
        return books;
    }

    public  List<Book> findByAll(){
        List<Book> books = bookRepository.findByAll();
        return books;
    }

    public  List<Book> findByTop10(){
        List<Book> books = bookRepository.findByAll();
        Collections.sort(books);
        List<Book> top10=new ArrayList<>();
        if(books.size()<10){
            return books;
        }else {
            for (int i = 0; i < 10; i++) {
                top10.add(books.get(i));
            }
        }
        return top10;

    }

    public void getDeleteBookInfo(){
        String book_name = "삭제할 책제목";
        List<Book> books = bookRepository.findByBookName(book_name);
        if(books.size()>0){
            Long book_id  = 1L;//삭제할 책 고유번호
            bookRepository.deleteBook(book_id);
        }
        else{
            System.out.println("책의 정보가 존재하지 않습니다.");
        }
    }

}
