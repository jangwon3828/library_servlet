package com.example.servelet_library.service.user;

import com.example.servelet_library.domain.book.BookRepository;
import com.example.servelet_library.domain.dto.LoginRequestDto;
import com.example.servelet_library.domain.user.User;
import com.example.servelet_library.domain.user.UserRepository;
import com.example.servelet_library.service.book.BookReadService;

import java.sql.SQLException;

public class UserWriteService {

    private static UserWriteService bookReadService=new UserWriteService();
    private UserRepository userRepository= UserRepository.getInstance();

    private UserWriteService(){
    }

    public User Login(LoginRequestDto loginRequestDto){
        User loginUser;
        try {
             loginUser = userRepository.login(loginRequestDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loginUser;
    }



}
