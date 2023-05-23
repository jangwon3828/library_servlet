package com.example.servelet_library.domain.dto;

import com.example.servelet_library.domain.book.Book;

import java.util.List;

public class BooksPage {
    private List<Book> books;   // 페이지별 도서 목록
    private int currentPage;    // 현재 페이지 번호
    private int pageSize;       // 페이지당 도서 수
    private int totalPages;     // 총 페이지 수

    public BooksPage(List<Book> books, int currentPage, int pageSize, int totalPages) {
        this.books = books;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public boolean hasNextPage() {
        return currentPage < totalPages;
    }

    public int getPreviousPage() {
        return currentPage - 1;
    }

    public int getNextPage() {
        return currentPage + 1;
    }
}
