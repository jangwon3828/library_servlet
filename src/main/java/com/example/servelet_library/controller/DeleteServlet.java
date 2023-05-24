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

public class DeleteServlet extends HttpServlet {

    private BookWriteService bookWriteService = BookWriteService.getInstance();
    private BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long book_id = Long.valueOf(req.getParameter("book_id"));
        bookWriteService.deleteBook(book_id);

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<script>alert('삭제가 완료 되었습니다.'); location.href=\"/library_servlet/manage\";</script>");
        out.close();


    }
}