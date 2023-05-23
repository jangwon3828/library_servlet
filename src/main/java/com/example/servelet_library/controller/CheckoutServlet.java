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

public class CheckoutServlet extends HttpServlet {

    private BookWriteService bookWriteService = BookWriteService.getInstance();
    private BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long book_id = Long.valueOf(req.getParameter("book_id"));

        boolean checkout = bookWriteService.checkout(book_id);

        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
       if(checkout){

           out.println("<script>alert('성공적으로 빌렸습니다.'); location.href=\"/library_servlet\";</script>");
       }else {
           out.println("<script>alert('현재 도서관에 책이 없어 대여할 수 없습니다.'); location.href=\"/library_servlet\";</script>");
       }
        out.close();


    }
}