package com.example.servelet_library.domain.book;

import com.example.servelet_library.domain.dto.BooksPage;
import com.example.servelet_library.domain.user.UserRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {
    private static BookRepository bookRepository = new BookRepository();
    private static Connection con;
    public static Statement st;

    private UserRepository userRepository = UserRepository.getInstance();

    private BookRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezon=UTC", "root", "1234");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static BookRepository getInstance() {
        return bookRepository;
    }

    public BooksPage findByAuthor(String author, Integer currentPage) {
        String query = "select * from books where author like '%" + author + "%'";
        return getBooks(query, currentPage);

    }

    public BooksPage findByThreeWay(String data, Integer currentPage) {
        String query = "select * from books where author like '%" + data + "%' or bookname like '%" + data + "%' or publisher like '%" + data + "%'";
        ;
        return getBooks(query, currentPage);

    }

    public Book findById(Long id) {
        String query = "select * from books where book_id = '" + id + "'";
        return getBook(query);
    }

    public BooksPage findByBookName(String book_name, Integer currentPage) {
        String query = "select * from books where bookname like '%" + book_name + "%'";
        return getBooks(query, currentPage);
    }

    public BooksPage findByPublisher(String publisher, Integer currentPage) {
        String query = "select * from books where publisher  like '%" + publisher + "%'";
        return getBooks(query,currentPage);
    }

    public Book findByISBM(String isbm) {
        String query = "select * from books where isbn_no = '" + isbm + "'";
        return getBook(query);
    }

    public BooksPage findByYear(LocalDate localDate) {
        String query = "select * from books where year_of_publication > '%" + localDate + "%'";
        return getBooks(query, null);

    }

    public List<Book> findByAll() {
        String query = "select * from books";
        return getBookToList(query);

    }

    public BooksPage findAll() {
        String query = "select * from books";
        return getBooks(query, null);

    }

    public BooksPage findByAllPage() {
        String query = "select * from books";
        return getBooks(query, null);

    }

    private BooksPage getBooks(String query, Integer currentPage) {
        List<Book> books = new ArrayList<>();
        int pageSize = 10;
        currentPage= currentPage==null?1:currentPage;
        int totalPages = 0;
        int totalCount = 0;
        try {

            ResultSet rs = st.executeQuery(query);
          while (rs.next()){
              totalCount++;
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int startRow = (currentPage - 1) * pageSize;
        ResultSet resultSet=null;
        try {

            query += " LIMIT " + startRow + " , " + pageSize + " ;";
            resultSet = st.executeQuery(query);

            totalPages = (int) Math.ceil((double) totalCount / pageSize);

        } catch (Exception e) {
            System.out.println("에러발생 findByAuthor");
        }
        try {

            while (resultSet.next()) {
                String str = resultSet.getString("year_of_publication");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String[] s = str.split(" ");
                LocalDate dateTime = LocalDate.parse(s[0], formatter);
                Book book = new Book(Long.parseLong(resultSet.getString("book_id")),
                        resultSet.getString("bookname"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        Long.parseLong(resultSet.getString("borrowcount")),
                        resultSet.getString("ISBN_NO"),
                        dateTime,
                        Long.parseLong(resultSet.getString("count"))
                );
                books.add(book);
            }
        } catch (Exception e) {

        }

        return new BooksPage(books, currentPage, pageSize, totalPages);
    }

    private List<Book> getBookToList(String query) {
        List<Book> books = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String str = rs.getString("year_of_publication");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String[] s = str.split(" ");
                LocalDate dateTime = LocalDate.parse(s[0], formatter);
                Book book = new Book(Long.parseLong(rs.getString("book_id")),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        Long.parseLong(rs.getString("borrowcount")),
                        rs.getString("ISBN_NO"),
                        dateTime,
                        Long.parseLong(rs.getString("count"))
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("에러발생 findByAuthor");
        }

        return books;
    }

    private Book getBook(String query) {
        Book book = new Book();
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String str = rs.getString("year_of_publication");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String[] s = str.split(" ");
                LocalDate dateTime = LocalDate.parse(s[0], formatter);
                book = new Book(Long.parseLong(rs.getString("book_id")),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        Long.parseLong(rs.getString("borrowcount")),
                        rs.getString("ISBN_NO"),
                        dateTime,
                        Long.parseLong(rs.getString("count"))
                );
            }
            return book;

        } catch (Exception e) {
            System.out.println("error findByAuthor");
        }
        return book;

    }


    public void insertBook(Book book) {
        try {
            st.execute("insert into books(book_id, bookname, author, publisher, borrowcount, ISBN_NO, year_of_publication, count) values (null,'" + book.getBook_name() + "','" + book.getAuthor() + "','" + book.getPublisher() + "','" + book.getBorrow_count() + "','" + book.getISBN_NO() + "','" + book.getYear_of_publication() + "','" + book.getCount() + "');");
        } catch (Exception e) {
            System.out.println("에러발생 insertBook");
        }
    }

    public void deleteBook(Long book_id) {
        try {
            st.execute("DELETE FROM books WHERE book_id='" + book_id + "'");
        } catch (Exception e) {
            System.out.println("에러발생 deleteBook");
        }
    }

    public boolean checkoutBook(Long book_id) {
        int bookId = Math.toIntExact(book_id);
        Book byId = findById(book_id);
        if(byId.getCount()==0){
            return false;
        }
        String query = "update books set borrowcount = borrowcount +1 , count = count-1 where book_id= " + bookId;

        try {
            st.execute(query);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public void returnBook(Long book_id) {
        String query = "update books set count = count+1 where book_id= " + book_id;
        try {
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void plusBook(Book book) {
        String query = "update books set count = count+1 where book_id= " + book.getBook_id();
        try {
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) {
        String query = "update books set bookname ='"
                + book.getBook_name() + "' , author = '"
                +book.getAuthor()+"', publisher = '"
                +book.getPublisher()+"', Year_of_publication = '"
                +book.getYear_of_publication()
                +"' where book_id= " + book.getBook_id();
        try {
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
