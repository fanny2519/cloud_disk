package com.iss.cloud.disk.dao;

import com.iss.cloud.disk.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    User login(@Param("username") String username, @Param("password") String password);

    int getCount();

    User getUser(int id);

    List<User> getUsers(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    List<User> chooseUser(int id);

    int insertUser(User user);

    int register1(User user);//用来查询表里还有没有一样的用户名
    int register2(User user);// 用来创建用户

    int updatePath(User user);

    int updateUser(User user);

    int delete(List<Integer> ids);


//    修改头像
    int updateUserPhoto(User user);

    // 修改权限
    int grantAuthorization(int[] ids,int[] roleId);


}
