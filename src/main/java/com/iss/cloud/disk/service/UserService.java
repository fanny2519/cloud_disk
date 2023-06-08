package com.iss.cloud.disk.service;

import com.iss.cloud.disk.model.ResultModel;
import com.iss.cloud.disk.model.Pagination;
import com.iss.cloud.disk.model.User;

import java.util.List;


public interface UserService {

    User login(String username, String password);

    Pagination<User> getUsers(Pagination page);

    List<User> chooseUser(int id);

    User getUser(int id);

//    管理员新增用户
    ResultModel insertUser(User user);

//    用户注册
    ResultModel registerUser(User user);

    ResultModel updateUser(User user);

    ResultModel delete(List<Integer> ids);

    ResultModel updatePhoto(User user);

    ResultModel grantAuthorization(int[] ids, int[] roleId);
}
