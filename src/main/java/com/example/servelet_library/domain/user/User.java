package com.example.servelet_library.domain.user;

import com.example.servelet_library.common.Role;

import java.time.LocalDate;

public class User {
    private Long user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_phone;
    private Long book_id;
    private LocalDate borrow_date;
    private Role role;

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public User() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public Role getRole() {
        return role;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public Long getBook_id() {
        return book_id;
    }

    public User(Long user_id, String user_name, String user_email, String user_password, String user_phone, Long book_id, LocalDate borrow_date, Role role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_phone = user_phone;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
        this.role = role;
    }
}
