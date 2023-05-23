//package com.example.servelet_library.service.book;
//
//import com.example.servelet_library.domain.book.Book;
//import com.example.servelet_library.domain.book.BookRepository;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookWriteService {
//
//    private static BookWriteService bookWriteService=new BookWriteService();
//    private BookWriteService(){
//    }
//
//    public static BookWriteService getInstance(){
//        return bookWriteService;
//    }
//
//    private BookRepository bookRepository=BookRepository.getInstance();
//    private BookReadService bookReadService=BookReadService.getInstance();
//
//    public void getNewBookInfo() {
//        String bookName = null;
//
//        String author = null;
//
//        String publisher =null;
//
//        Long borrowCount = null;
//
//        String ISBN_NO = null;
//        LocalDate year_of_publication = null;
//        Long count = null;
//
//        Book book = new Book(null, bookName, author, publisher, borrowCount, ISBN_NO, year_of_publication, count);
//
//        bookRepository.insertBook(book);
//    }
//
//
//    public void checkOutBook(Long bookId,Long userId) {
//        Book book = bookRepository.findById(bookId);
//        bookRepository.checkoutBook(book.getBook_id(),userId);
//    }
//
//    public void returnBook() {
//        List<Book> books = null;
//        Long index;
//        while (books == null) {
//            index = null;
//            books = selectFind(index, books);
//        }
//        Long bookId = null;
//      bookRepository.returnBook(bookId);
//    }
//
//
//}
