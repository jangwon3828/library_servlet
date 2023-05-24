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

public class UpdateServlet extends HttpServlet {

    private BookWriteService bookWriteService = BookWriteService.getInstance();
    private BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long book_id = Long.valueOf(req.getParameter("book_id"));
        Book book = bookReadService.findById(book_id);
        bookWriteService.updateBook(book);

        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>도서 수정</title>\n" +
                "  <style>\n" +
                "    @import url('https://fonts.googleapis.com/css2?family=Hahmlet&display=swap');\n" +
                "    html,body{\n" +
                "      background-color: #05507D;\n" +
                "      heigth:100%;\n" +
                "      width:100%;\n" +
                "      padding:0;\n" +
                "      margin:0;\n" +
                "      font-family: 'Hahmlet', sans-serif;\n" +
                "    }\n" +
                "    .container {\n" +
                "      background-color: #05507D;\n" +
                "      display: flex;\n" +
                "      justify-content: center;\n" +
                "      align-items: center;\n" +
                "      height: 100vh;\n" +
                "    }\n" +
                "    .label{\n" +
                "      font-size:1.5em;\n" +
                "      padding:2vh;\n" +
                "    }\n" +
                "    form{\n" +
                "      display: flex;\n" +
                "      flex-direction: column;\n" +
                "      align-items: center;\n" +
                "      background-color:white;\n" +
                "      padding-left:10vh;\n" +
                "      padding-right:10vh;\n" +
                "      padding-top:13vh;\n" +
                "      padding-bottom:13vh;\n" +
                "    }\n" +
                "    fieldset{\n" +
                "      border-color: #cccccc;\n" +
                "    }\n" +
                "    button:hover{\n" +
                "      background-color: #00A5E5;\n" +
                "    }\n" +
                "    button {\n" +
                "      padding: 8px 16px;\n" +
                "      font-size: 14px;\n" +
                "      background-color: #05507D;\n" +
                "      color: white;\n" +
                "      border: none;\n" +
                "      border-radius: 4px;\n" +
                "      cursor: pointer;\n" +
                "      margin:3px;\n" +
                "    }\n" +
                "    .buttons{\n" +
                "      display: flex;\n" +
                "      justify-content: flex-end;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "  <form action=\"/library_servlet/updateOkServlet\" method=\"get\">\n" +
                "    <div class=\"label\">책 수정</div>\n" +
                "    <fieldset>\n" +
                "       <input type = \"hidden\" name=\"book_id\" value=\"" + book.getBook_id() + "\">\n"+
                "      도서명 : <input type = \"text\" name=\"name\" value="+book.getBook_name()+" required/><br><br>\n" +
                "      저자명 : <input type = \"text\" name=\"author\"  value="+book.getAuthor()+" required/><br><br>\n" +
                "      출판사 : <input type = \"text\" name=\"publisher\" value="+book.getPublisher()+" required/><br><br>\n" +
                "      ISBN : <input type = \"text\" name=\"ISBN\" value="+book.getISBN_NO()+" required/><br><br>\n" +
                "      출판연도 : <input type = \"text\" name=\"year_of_publication\" value="+book.getYear_of_publication()+" required /><br><br>\n" +
                "      <div class=\"buttons\">\n" +
                "        <button>수정</button>\n" +
                "        <button type=\"button\" onclick=\"location.href='/library_servlet'\">취소</button>\n" +
                "\n" +
                "      </div>\n" +
                "    </fieldset>\n" +
                "  </form>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        out.close();


    }
}