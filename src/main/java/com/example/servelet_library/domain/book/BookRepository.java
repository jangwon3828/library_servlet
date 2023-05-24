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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezon=UTC", "root", "1234");
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
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
        return getBooks(query, currentPage);
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


    public BooksPage findByAllPage(Integer integer) {
        String query = "select * from books";
        return getBooks(query, integer);

    }

    private BooksPage getBooks(String query, Integer currentPage) {
        connect();
        List<Book> books = new ArrayList<>();
        int pageSize = 10;
        currentPage = currentPage == null ? 1 : currentPage;
        int totalPages = 0;
        int totalCount = 0;
        try {

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                totalCount++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int startRow = (currentPage - 1) * pageSize;
        ResultSet resultSet = null;
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

        }finally {
            disconnect(resultSet);
        }

        return new BooksPage(books, currentPage, pageSize, totalPages);
    }

    private List<Book> getBookToList(String query) {
        connect();
        List<Book> books = new ArrayList<>();
        ResultSet rs=null;
        try {
            rs = st.executeQuery(query);

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
        finally {
            disconnect(rs);
        }
        return books;
    }

    private Book getBook(String query) {
        connect();
        Book book = new Book();
        ResultSet rs =null;
        try {
            rs= st.executeQuery(query);
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
        }finally {
            disconnect(rs);
        }
        return book;

    }


    public void insertBook(Book book) {
        connect();
        try {
            st.execute("insert into books(book_id, bookname, author, publisher, borrowcount, ISBN_NO, year_of_publication, count) values (null,'" + book.getBook_name() + "','" + book.getAuthor() + "','" + book.getPublisher() + "','" + book.getBorrow_count() + "','" + book.getISBN_NO() + "','" + book.getYear_of_publication() + "','" + book.getCount() + "');");
        } catch (Exception e) {
            System.out.println("에러발생 insertBook");
        }finally {
            disconnect(null);
        }
    }

    public void deleteBook(Long book_id) {
        connect();
        try {
            int bookId = Math.toIntExact(book_id);
            String sql = "DELETE FROM books WHERE book_id= " + bookId + ";";
            st.execute(sql);
        } catch (Exception e) {
            System.out.println("에러발생 deleteBook");
        }finally {
            disconnect(null);
        }
    }

    public boolean checkoutBook(Long book_id) {
        int bookId = Math.toIntExact(book_id);
        Book byId = findById(book_id);
        connect();
        if (byId.getCount() == 0) {
            return false;
        }
        String query = "update books set borrowcount = borrowcount +1 , count = count-1 where book_id= " + bookId;

        try {
            st.execute(query);
            return true;
        } catch (SQLException e) {
            return false;
        }finally {
            disconnect(null);
        }

    }

    public boolean returnBook(Long book_id) {
        connect();
        String query = "update books set count = count+1 where book_id= " + book_id;
        try {
            st.execute(query);
        } catch (SQLException e) {
            return false;
        }finally {
            disconnect(null);
        }
        return true;
    }

    public void plusBook(Book book) {
        connect();
        String query = "update books set count = count+1 where book_id= " + book.getBook_id();
        try {
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            disconnect(null);
        }
    }

    public void updateBook(Book book) {
        connect();
        String query = "update books set bookname ='"
                + book.getBook_name() + "' , author = '"
                + book.getAuthor() + "', publisher = '"
                + book.getPublisher() + "', Year_of_publication = '"
                + book.getYear_of_publication() + "', ISBN_NO = '"
                + book.getISBN_NO()
                + "' where book_id= " + book.getBook_id();
        try {
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            disconnect(null);
        }
    }
}