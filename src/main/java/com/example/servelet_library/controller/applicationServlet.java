package com.example.servelet_library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class applicationServlet extends HttpServlet{
    public applicationServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO user 생성 시 해당 유저 서재에 신청한 도서 목록이 뜨게끔 만들기
//		request.getParameter("name");
//		request.getParameter("author");
//		request.getParameter("publisher");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("<script>alert('신청이 완료되었습니다.'); location.href=\"/library_servlet/html/index.html\";</script>");
        out.close();


    }
}
