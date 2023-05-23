package com.example.servelet_library.domain.book;


import java.time.LocalDate;

public class Book implements Comparable<Book>{
    private Long book_id;
    private String book_name;
    private String author;
    private String publisher;
    private Long borrow_count;
    private String ISBN_NO;
    private LocalDate year_of_publication;
    private Long count;

    public Book() {
    }

    public Book(Long  book_id, String book_name, String author, String publisher, Long borrow_count, String ISBN_NO, LocalDate year_of_publication, Long count) {
        this.book_id=book_id;
        this.book_name = book_name;
        this.author = author;
        this.publisher = publisher;
        this.borrow_count = borrow_count;
        this.ISBN_NO = ISBN_NO;
        this.year_of_publication = year_of_publication;
        this.count = count;
    }

    public Long getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Long getBorrow_count() {
        return borrow_count;
    }

    public String getISBN_NO() {
        return ISBN_NO;
    }

    public LocalDate getYear_of_publication() {
        return year_of_publication;
    }

    public Long getCount() {
        return count;
    }

    public void updateBookName(String bookName){
        this.book_name = bookName;
    }

    public void updateAuthor(String author){
        this.author = author;
    }

    public void updatePublisher(String publisher){
        this.publisher = publisher;
    }

    public void updateBorrowCount(Long borrowCount){
        this.borrow_count = borrowCount;
    }


    public void updateISBN_NO(String ISBN_NO){
        this.ISBN_NO = ISBN_NO;
    }

    public void updateYearOfPublication(LocalDate year_of_publication){
        this.year_of_publication = year_of_publication;
    }

    public void updateCount(Long count) {
        this.count = count;
    }

    @Override
    public int compareTo(Book b){
        return (int) (b.getBorrow_count()-getBorrow_count());
    }
}
