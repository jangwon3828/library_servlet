package com.example.servelet_library.domain.user;

import com.example.servelet_library.common.Role;
import com.example.servelet_library.domain.dto.LoginRequestDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.servelet_library.domain.book.BookRepository.st;

public class UserRepository {
    private static UserRepository userRepository = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return userRepository;
    }

    public void insertUser(User user) {
        try {
            st.execute("insert into users(user_id, user_name, user_email, user_password, user_phone, book_id,borrow_date,role) values (null,'" + user.getUser_name() + "','" + user.getUser_email() + "','" + user.getUser_password() + "','" + user.getUser_phone() + "','" + user.getBook_id() + "','" + user.getBorrow_date() + "','" + user.getRole() + "');");
        } catch (Exception e) {
            System.out.println("에러발생 insertBook");
        }
    }

    public User login(LoginRequestDto loginRequestDto) throws SQLException {
        String sql = "select * from users where email ='" + loginRequestDto.getEmail() + "', password = '" + loginRequestDto.getPassword() + "'";
        return getUser(sql);
    }



    private User getUser(String query) {
        User user = new User();
        try {
            ResultSet rs = st.executeQuery(query);
            String str = rs.getString("borrow_date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String[] s = str.split(" ");
            LocalDate dateTime = LocalDate.parse(s[0], formatter);
            user = new User(Long.parseLong(rs.getString("user_id")),
                    rs.getString("user_name"),
                    rs.getString("user_email"),
                    rs.getString("user_password"),
                    rs.getString("user_phone"),
                    Long.parseLong(rs.getString("book_id")),
                    dateTime,
                    Role.findByString(rs.getString("role")));
            return user;

        } catch (Exception e) {
            System.out.println("에러발생 findByAuthor");
        }
        return user;

    }


}
