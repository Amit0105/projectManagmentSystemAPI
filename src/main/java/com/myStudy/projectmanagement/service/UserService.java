package com.myStudy.projectmanagement.service;

import com.myStudy.projectmanagement.modal.User;

public interface UserService {

    User findUserProfileByJwt(String jwt)throws Exception;

    User findUserByEmail(String email)throws Exception;

    User findUserById(Long userId)throws Exception;

    User updateUserProjectSize(User user,int number);

}
