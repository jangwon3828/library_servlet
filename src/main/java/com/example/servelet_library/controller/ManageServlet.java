//package com.example.servelet_library.controller;
//
//import com.example.servelet_library.domain.book.Book;
//import com.example.servelet_library.domain.dto.BooksPage;
//import com.example.servelet_library.service.book.BookReadService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
////컨트롤러 만들때 항상
//public class ManageServlet extends HttpServlet {
//
//    private final BookReadService bookReadService = BookReadService.getInstance();
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("euc-kr");
//        BooksPage books=bookReadService.findAll();
//        resp.setContentType("text/html;charset=euc-kr");
//
//        PrintWriter pw = resp.getWriter();
//        pw.println("<html>");
//        pw.println("<head>");
//        pw.println("<title>책목록</title>");
//        pw.println("<style>");
//        pw.println("table {");
//        pw.println("  border-collapse: collapse;");
//        pw.println("  width: 100%;");
//        pw.println("}");
//        pw.println("th, td {");
//        pw.println("  border: 1px solid black;");
//        pw.println("  padding: 8px;");
//        pw.println("  text-align: left;");
//        pw.println("}");
//        pw.println("tr:nth-child(even) {");
//        pw.println("  background-color: #f2f2f2;");
//        pw.println("}");
//        pw.println("</style>");
//        pw.println("</head>");
//        pw.println("<body>");
//
//        pw.println("<h1>책 목록</h1>");
//        pw.println("<table>");
//        pw.println("<tr>");
//        pw.println("<th>책 제목</th>");
//        pw.println("<th>글쓴이</th>");
//        pw.println("<th>출판사</th>");
//        pw.println("<th>출판일자</th>");
//        pw.println("<th>잔여 권수</th>");
//        pw.println("<th>여태까지 대여횟수</th>");
//        pw.println("<th>꽂혀있는 위치</th>");
//        pw.println("<th>책고유값</th>");//대출예약버튼 만약 0이라면 대출 불가능
//        pw.println("</tr>");
//
//        for (Book book : books.getBooks()) {
//            pw.println("<tr>");
//            pw.println("<td>" + book.getBook_name() + "</td>");
//            pw.println("<td>" + book.getAuthor() + "</td>");
//            pw.println("<td>" + book.getPublisher() + "</td>");
//            pw.println("<td>" + book.getYear_of_publication() + "</td>");
//            pw.println("<td>" + book.getCount() + "</td>");
//            pw.println("<td>" + book.getBorrow_count() + "</td>");
//            pw.println("<td>" + book.getISBN_NO() + "</td>");
//            pw.println("<td>" + book.getBook_id() + "</td>");
//            pw.println("</tr>");
//        }
//
//        pw.println("</table>");
//
//// 페이징 링크 생성
//        pw.println("<div>");
//
//// 이전 페이지 링크
//        if(books.hasPreviousPage()) {
//            pw.println("<a href=\"/paging?currentPage=" + books.getPreviousPage() + "&search=" + search + "&searchData=" + searchData + "\">이전</a>");
//        }
//
//// 페이지 번호 링크
//        for (int i = 1; i <= books.getTotalPages(); i++) {
//            if (i == books.getCurrentPage()) {
//                pw.println("<strong>" + i + "</strong>");
//            } else {
//                pw.println("<a href=\"/paging?currentPage=" + i + "&search=" + search + "&searchData=" + searchData + "\">" + i + "</a>");
//            }
//        }
//
//
//
//        pw.println("</div>");
//
//        pw.println("<br>");
//        pw.println("<a href='/library_servlet'>메인페이지로 이동</a>");
//
//        pw.println("</body>");
//        pw.println("</html>");
//
//        pw.close();
//    }
//}
