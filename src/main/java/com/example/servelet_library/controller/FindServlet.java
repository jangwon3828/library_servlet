package com.example.servelet_library.controller;

import com.example.servelet_library.domain.book.Book;
import com.example.servelet_library.domain.dto.BooksPage;
import com.example.servelet_library.service.book.BookReadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//컨트롤러 만들때 항상
public class FindServlet extends HttpServlet {

    private final BookReadService bookReadService = BookReadService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String search = req.getParameter("search");
        String searchData = req.getParameter("searchData");
        BooksPage books=null;
        switch (search) {
            case "전체":
                books  = bookReadService.findByThreeWay(searchData, null);
                break;
            case "제목":
                books  = bookReadService.findByBookName(searchData, null);
                break;
            case "저자":
                books  = bookReadService.findByAuthor(searchData, null);
                break;
            case "출판사":
                books  = bookReadService.findByPublisher(searchData, null);
                break;
        }
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>책목록</title>");
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
                "      justify-content: space-between;\n" +
                "      align-items: center;\n" +
                "      flex-direction: column;\n" +
                "      height: 90vh;\n" +
                "      margin-top: 5vh\n" +
                "    }" +
                ".paging {\n" +
                "  display: inline-block;\n" +
                "  padding: 5px 7px;\n" +
                "margin-bottom: 15px;\n" +
                "  font-size: 14px;\n" +
                "  font-weight: bold;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  background-color: #05507D;\n" +
                "  color: white;\n" +
                "  border-radius: 4px;\n" +
                "  border: none;\n" +
                "  cursor: pointer;\n" +
                "}" +
                ".paging:hover {\n" +
                "  background-color: #00A5E5;\n" +
                "}\n" +
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
        pw.println("<h1>책 목록</h1>");
        pw.println("<table>");
        pw.println("<tr>");
        pw.println("<th>책 제목</th>");
        pw.println("<th>글쓴이</th>");
        pw.println("<th>출판사</th>");
        pw.println("<th>출판일자</th>");
        pw.println("<th>잔여 권수</th>");
        pw.println("<th>누적 대여 횟수</th>");
        pw.println("<th>꽂혀있는 위치</th>");
        pw.println("<th>대출</th>");//대출예약버튼 만약 0이라면 대출 불가능
        pw.println("</tr>");

        for (Book book : books.getBooks()) {
            pw.println("<tr>");
            pw.println("<td>" + book.getBook_name() + "</td>");
            pw.println("<td>" + book.getAuthor() + "</td>");
            pw.println("<td>" + book.getPublisher() + "</td>");
            pw.println("<td>" + book.getYear_of_publication() + "</td>");
            pw.println("<td>" + book.getCount() + "</td>");
            pw.println("<td>" + book.getBorrow_count() + "</td>");
            pw.println("<td>" + book.getISBN_NO() + "</td>");
            pw.println("<form action=\"/library_servlet/checkout\" method=\"'get'\">");
            pw.println("<input type = \"hidden\" name=\"book_id\" value=\"" + book.getBook_id() + "\">");
            pw.println("<td><button type=\"submit\">" + "대여</button></td>");
            pw.println("</form>");
            pw.println("</td>");
            pw.println("</tr>");
        }

        pw.println("</table>");
// 페이징 링크 생성
        pw.println("<div>");

// 이전 페이지 링크
        if (books.hasPreviousPage()) {
            pw.println("<a class=\"paging\" href=\"/library_servlet/paging?currentPage=" + books.getPreviousPage() + "&search=" + search + "&searchData=" + searchData + "\">이전</a>");
        }

// 페이지 번호 링크
        for (int i = 1; i <= books.getTotalPages(); i++) {
            if (i == books.getCurrentPage()) {
                pw.println("<strong>" + i + "</strong>");
            } else {
                pw.println("<a class=\"paging\" href=\"/library_servlet/paging?currentPage=" + i + "&search=" + search + "&searchData=" + searchData + "\">" + i + "</a>");
            }
        }
// 다음 페이지 링크
        if (books.hasNextPage()) {
            pw.println("<a class=\"paging\" href=\"/library_servlet/paging?currentPage=" + books.getNextPage() + "&search=" + search + "&searchData=" + searchData + "\">다음</a>");
        }

        pw.println("</div>");
        pw.println("</div>");

        pw.println("<div class='footer'>");
        pw.println("<a class=\"back\" href='/library_servlet'>메인페이지로 이동</a>");
        pw.println("</div>");

        pw.println("</body>");
        pw.println("</html>");

        pw.close();
    }
}
