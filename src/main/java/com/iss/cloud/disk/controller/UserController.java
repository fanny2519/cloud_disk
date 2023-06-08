package com.iss.cloud.disk.controller;

import com.alibaba.fastjson.JSON;
import com.iss.cloud.disk.model.Pagination;
import com.iss.cloud.disk.model.ResultModel;
import com.iss.cloud.disk.model.Role;
import com.iss.cloud.disk.model.User;
import com.iss.cloud.disk.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = Logger.getLogger(FileController.class);

    @Autowired
    private UserService userService;

    // 获得用户数据列表
    @GetMapping("/getUsers")
    @ResponseBody
    public Pagination<User> getUsers(Pagination page) {
        return this.userService.getUsers(page);
    }

    // 获得用户数据列表
    @GetMapping("/chooseUser")
    @ResponseBody
    public List<User> chooseUser(@SessionAttribute("user") User user) {
        return this.userService.chooseUser(user.getId());
    }

    // 获取指定ID用户信息
    @GetMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(HttpServletRequest request) {
        return this.userService.getUser(Integer.parseInt(request.getParameter("id")));
    }

    // 新增用户信息
    @PostMapping("/insertUser")
    @ResponseBody
    public ResultModel insertUser(User user) {
        logger.info("...insertUser  ...request params is { " + JSON.toJSONString(user) + " } ");
        return this.userService.insertUser(user);
    }

    // 用户信息注册
    @PostMapping("/registerUser")
    @ResponseBody
    public ResultModel registerUser(User user) {
        logger.info("...registerUser  ...request params is { " + JSON.toJSONString(user) + " } ");
        return this.userService.registerUser(user);
    }

    // 修改用户信息
    @PostMapping("/updateUser")
    @ResponseBody
    public ResultModel updateUser(User user) {
        return this.userService.updateUser(user);
    }

    // 修改用户头像
    @PostMapping("/updatePhoto")
    @ResponseBody
    public ResultModel updatePhoto(User user) {
        logger.info("...updatePhoto  ...request params is { " + JSON.toJSONString(user) + " } ");
        return this.userService.updatePhoto(user);
    }

    // 删除用户数据
    @GetMapping("/delete")
    @ResponseBody
    public ResultModel delete(@RequestParam("ids") List<Integer> ids) {
        return this.userService.delete(ids);
    }

    // 修改用户权限
    @PostMapping("/grantAuthorization")
    @ResponseBody
    public ResultModel grantAuthorization(int[] ids,int[] roleId) {

        return this.userService.grantAuthorization(ids, roleId);
    }

}
