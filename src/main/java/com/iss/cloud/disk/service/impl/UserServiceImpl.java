package com.iss.cloud.disk.service.impl;

import com.iss.cloud.disk.model.*;
import com.iss.cloud.disk.dao.UserDao;
import com.iss.cloud.disk.service.HDFSService;
import com.iss.cloud.disk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HDFSService hdfsService;

    @Override
    public User login(String username, String password) {
        User user = this.userDao.login(username, password);
        // 检查登录用户在 hdfs中是否存在个人目录
        if (user != null && !StringUtils.hasText(user.getPath())) {
            String path = "/" + username + "/";
            this.hdfsService.mkdirs(path);
            user.setPath(path);
            this.userDao.updatePath(user);
        }
        return user;
    }

    @Override
    public Pagination<User> getUsers(Pagination page) {
        int start = (page.getPageNum() - 1) * page.getPageSize();
        List<User> rows = this.userDao.getUsers(start, page.getPageSize());
        page.setRows(rows);

        int total = this.userDao.getCount();
        page.setTotal(total);

        return page;
    }

    @Override
    public List<User> chooseUser(int id) {
        return this.userDao.chooseUser(id);
    }

    @Override
    public User getUser(int id) {
        return this.userDao.getUser(id);
    }

    @Override
    public ResultModel insertUser(User user) {
//        user.getUserRole();
        user.setPath("/" + user.getUsername() + "/"); // 个人存储路径
        user.setSize(5 * 1024); // 5G 存储空间
        user.setUsedSize(0);
        user.setPhoto("avatar-1.jpg"); // 默认头像
        int result = this.userDao.insertUser(user);
        return result > 0 ? ResultModel.success("Your imaginary data has been inserted.") : ResultModel.dbError();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public ResultModel registerUser(User user) {
        user.setUserRole(new Role(2,"普通用户")); // 注册的用户默认是普通用户
        user.setSize(5 * 1024);
        user.setUsedSize(0);
        user.setPhoto("avatar-1.jpg");
        user.setPath("/" + user.getUsername() + "/");
        int userNum = this.userDao.register1(user);
        System.out.println(userNum);
        if(userNum > 0){
            return ResultModel.error("您注册的用户名重复，请重新注册！");
        }else{
            int result = this.userDao.register2(user);
            return result > 0 ? ResultModel.success("Your imaginary data has been register.") : ResultModel.dbError();
        }

    }

    @Override
    public ResultModel updateUser(User user) {
        int result = this.userDao.updateUser(user);
        return result > 0 ? ResultModel.success("Your imaginary data has been updated.") : ResultModel.dbError();
    }

    @Override
    public ResultModel delete(int[] ids) {
        int result = this.userDao.delete(ids);
        return result > 0 ? ResultModel.success("Your imaginary data has been deleted.") : ResultModel.dbError();

    }


    @Override
    public ResultModel updatePhoto(User user) {

        int result = this.userDao.updateUserPhoto(user);
        return result > 0 ? ResultModel.success("Your avatar data has been update.") : ResultModel.dbError();
    }


    @Override
    public ResultModel grantAuthorization(int[] ids,int[] roleId) {
        int result = this.userDao.grantAuthorization(ids,roleId);
        return result > 0 ? ResultModel.success("Your imaginary data has been updated.") : ResultModel.dbError();
    }

}
