package com.example.servelet_library.controller;

import com.example.servelet_library.service.book.BookWriteService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheckoutServlet extends HttpServlet {
    private BookWriteService bookWriteService = BookWriteService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ServletContext servletContext = getServletContext();

        String msg = req.getParameter("msg");
        String[] value = msg.split("_");

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("book_ID")) {
                if (value[2].equals("same")) {
                    cookie.setValue("-1");
                } else {
                    bookWriteService.checkout(Long.parseLong(value[1]));
                    cookie.setValue(value[1]);
                }
                bookWriteService.returnBook(Long.parseLong(value[0]));
            }
        }

        this.getServletContext().getRequestDispatcher("/paging?currentPage=" + value[3] + "&search=" + value[4] + "&searchData=" + value[5]).forward(req, resp);

//        resp.sendRedirect("/library_servlet/paging?currentPage=" + value[2] + "&search=" + value[3] + "&searchData=" + value[4]);
    }
}
