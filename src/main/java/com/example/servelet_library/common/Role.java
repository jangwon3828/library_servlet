package com.example.servelet_library.common;

public enum Role {
    사용자,관리자;
    public static Role findByString(String s){
        if(s.equals("사용자")){
            return Role.사용자;
        }else return Role.관리자;
    }
}
