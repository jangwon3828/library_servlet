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

    private UserRepository userRepository=UserRepository.getInstance();
    private BookRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezon=UTC", "root", "1234");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static BookRepository getInstance() {
        return bookRepository;
    }

    public BooksPage findByAuthor(String author) {
        String query = "select * from books where author like '%" + author + "%'";
        return getBooks(query);

    }
    public BooksPage findByThreeWay(String data) {
        String query = "select * from books where author like '%" + data + "%' or bookname like '%" + data + "%' or publisher like '%"+ data + "%'";;
        return getBooks(query);

    }
    public Book findById(Long id) {
        String query = "select * from books where book_id = '" + id + "'";
        return getBook(query);
    }
    public BooksPage findByBookName(String book_name) {
        String query = "select * from books where bookname like '%" + book_name + "%'";
        return getBooks(query);
    }

    public BooksPage findByPublisher(String publisher) {
        String query = "select * from books where publisher  like '%" + publisher + "%'";
        return getBooks(query);
    }

    public BooksPage findByYear(LocalDate localDate) {
        String query = "select * from books where year_of_publication > '%" + localDate + "%'";
        return getBooks(query);

    }

    public List<Book> findByAll() {
        String query = "select * from books";
        return getBookToList(query);

    }

    public BooksPage findByAllPage() {
        String query = "select * from books";
        return getBooks(query);

    }

    private BooksPage getBooks(String query) {
        List<Book> books = new ArrayList<>();
        int pageSize = 10;
        int currentPage = 1;
        String countQuery = "SELECT COUNT(*) FROM books";
        int totalPages=0;
        int totalCount=0;
        int startRow=0;
        try {
            ResultSet resultSet = st.executeQuery(countQuery);
            resultSet.next();
            totalCount = resultSet.getInt(1);
            startRow = (currentPage - 1) * pageSize;
            totalPages = (int) Math.ceil((double) totalCount / pageSize);

        } catch (Exception e) {
            System.out.println("에러발생 findByAuthor");
        }
        try {
            query+= " LIMIT "+startRow+" , "+pageSize+" ;";
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
        }catch (Exception e){

        }

        BooksPage booksPage = new BooksPage(books, currentPage,pageSize,totalPages);
        return booksPage;
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
        Book book=new Book();
        try {
            ResultSet rs = st.executeQuery(query);
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
            return book;

        } catch (Exception e) {
            System.out.println("에러발생 findByAuthor");
        }
        return book;

    }


    public void insertBook(Book book){
        try{
            st.execute("insert into books(book_id, bookname, author, publisher, borrowcount, ISBN_NO, year_of_publication, count) values (null,'"+book.getBook_name()+"','"+book.getAuthor()+"','"+book.getPublisher()+"','"+book.getBorrow_count()+"','"+book.getISBN_NO()+"','"+book.getYear_of_publication()+"','"+book.getCount()+"');");
        }catch(Exception e){
            System.out.println("에러발생 insertBook");
        }
    }

    public void deleteBook(Long book_id){
        try{
            st.execute("DELETE FROM books WHERE book_id='"+ book_id +"'");
        }catch(Exception e){
            System.out.println("에러발생 deleteBook");
        }
    }
  
     public void checkoutBook(Long book_id,Long user_id) {
        int bookId= Math.toIntExact(book_id);
        int userId= Math.toIntExact(user_id);
        String query = "update books set borrowcount = borrowcount +1 , count = count-1 where book_id= " + bookId;

        try {
            st.execute(query);
            query="update users set book_id= "+bookId+" where user_id = "+userId;
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void returnBook(Long book_id) {
        String query = "update books set count = count+1 where book_id= " + book_id ;
        try {
            st.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
