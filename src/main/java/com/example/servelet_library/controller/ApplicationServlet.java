package com.example.servelet_library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.service.book.BookReadService;
import com.example.servelet_library.service.book.BookWriteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApplicationServlet extends HttpServlet {

    private BookWriteService bookWriteService = BookWriteService.getInstance();
    private BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String isbn = req.getParameter("ISBN");
        Book byISBM = bookReadService.findByISBM(isbn);
        String str = req.getParameter("year_of_publication");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] s = str.split(" ");
        LocalDate dateTime = LocalDate.parse(s[0], formatter);
        // TODO user 생성 시 해당 유저 서재에 신청한 도서 목록이 뜨게끔 만들기
        Book book = new Book(null,
                name,
                author,
                publisher,
                0L,
                isbn,
                dateTime, 1L);
//        bookWriteService.
        if (book.equals(byISBM)) {
            byISBM.updateCount();
            bookWriteService.plusBook(byISBM);
        } else {
            bookWriteService.saveBook(book);
        }
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("<script>alert('신청이 완료되었습니다.'); location.href=\"/library_servlet\";</script>");
        out.close();


    }
}