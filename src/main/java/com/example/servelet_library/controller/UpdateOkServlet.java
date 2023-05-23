package com.example.servelet_library.controller;

import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.service.book.BookReadService;
import com.example.servelet_library.service.book.BookWriteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateOkServlet extends HttpServlet {

    private BookWriteService bookWriteService = BookWriteService.getInstance();
    private BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("book_id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String isbn = req.getParameter("ISBN");
        String str = req.getParameter("year_of_publication");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String[] s = str.split(" ");
        LocalDate dateTime = LocalDate.parse(s[0], formatter);
        Book book = bookReadService.findById(id);
        book.updateBookName(name);
        book.updateAuthor(author);
        book.updatePublisher(publisher);
        book.updateISBN_NO(isbn);
        book.updateBookName(name);
        book.updateYearOfPublication(dateTime);
        bookWriteService.updateBook(book);

        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("<script>alert('수정이 완료 되었습니다.'); location.href=\"/library_servlet\";</script>");
        out.close();


    }
}