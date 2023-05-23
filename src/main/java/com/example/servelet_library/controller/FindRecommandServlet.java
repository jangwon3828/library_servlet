package com.example.servelet_library.controller;

import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.service.book.BookReadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//컨트롤러 만들때 항상
public class FindRecommandServlet extends HttpServlet {

    private final BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        List<Book> byTop10 = bookReadService.findByTop10();
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>우리 도서관 TOP10</title>");
        pw.println("<style>" +
                "        @import url('https://fonts.googleapis.com/css2?family=Hahmlet&display=swap');\n" +
                "html,body{\n" +
                "      background-color: #05507D;\n" +
                "      heigth:100%;\n" +
                "      width:100%;\n" +
                "      padding:0;\n" +
                "      margin:0;\n" +
                "      font-family: 'Hahmlet', sans-serif;\n" +
                "    }\n" +
                "    .container {\n" +
                "      background-color: white;\n" +
                "      display: flex;\n" +
                "      justify-content: space-around;\n" +
                "      align-items: center;\n" +
                "      flex-direction: column;\n" +
                "      height: 90vh;\n" +
                "      margin-top: 5vh\n" +
                "    }" +
                "table {\n" +
                "  border-collapse: collapse;\n" +
                "  width: 85vh;\n" +
                "}\n" +
                "\n" +
                "th, td {\n" +
                "  padding: 8px;\n" +
                "  text-align: left;\n" +
                "  border-bottom: 1px solid #ddd;\n" +
                "}\n" +
                "\n" +
                "th {\n" +
                "  background-color: #05507D;\n" +
                "  font-weight: bold;\n" +
                "  color: white" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #f9f9f9;\n" +
                "}" +
                ".back{\n" +
                "color:white;\n" +
                "text-decoration:none;" +
                "}" +
                ".footer{\n" +
                "text-align:right;\n" +
                "margin-top: 10px;\n" +
                "margin-right: 10px;\n" +
                "}" +
                "</style>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<div class=\"container\">");
        pw.println("<h1>우리 도서관 TOP10</h1>");
        pw.println("<table>");
        pw.println("<tr>");
        pw.println("<th>책 제목</th>");
        pw.println("<th>글쓴이</th>");
        pw.println("<th>출판사</th>");
        pw.println("<th>출판일자</th>");
        pw.println("<th>잔여 권수</th>");
        pw.println("<th>누적 대여 횟수</th>"); //대출가능 여부로 변경해야함
        pw.println("<th>꽂혀있는 위치</th>");
        pw.println("</tr>");
        for (Book book : byTop10) {
            pw.println("<tr>");
            pw.println("<td>" + book.getBook_name() + "</td>");
            pw.println("<td>" + book.getAuthor() + "</td>");
            pw.println("<td>" + book.getPublisher() + "</td>");
            pw.println("<td>" + book.getYear_of_publication() + "</td>");
            pw.println("<td>" + book.getCount() + "</td>");
            pw.println("<td>" + book.getBorrow_count() + "</td>");
            pw.println("<td>" + book.getISBN_NO() + "</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");


        pw.println("</div>");
        pw.println("<div class='footer'>");
        pw.println("<a class=\"back\" href='/library_servlet'>메인페이지로 이동</a>");
        pw.println("</div>");

        pw.println("</body>");
        pw.println("</html>");


    }
}
