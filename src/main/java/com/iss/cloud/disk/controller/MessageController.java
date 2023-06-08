package com.iss.cloud.disk.controller;

import com.iss.cloud.disk.model.Message;
import com.iss.cloud.disk.model.Pagination;
import com.iss.cloud.disk.model.ResultModel;
import com.iss.cloud.disk.model.User;
import com.iss.cloud.disk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/getMessages")
    @ResponseBody
    public Pagination<Message> getMessages(Pagination page, @SessionAttribute("user") User user) {
        page.setCurrentUser(user.getId());
        return this.messageService.getMessages(page);
    }

    @GetMapping("/update")
    @ResponseBody
    public ResultModel update(int id) {
        return this.messageService.update(id);
    }

    // 文件回收、分享、消息
    @PostMapping("/delete")
    @ResponseBody
    public ResultModel delete(HttpServletRequest request) {
        return this.messageService.delete(Integer.parseInt(request.getParameter("id")));
    }

    @PostMapping("/neglect")
    @ResponseBody
    public ResultModel neglect(HttpServletRequest request) {
        return this.messageService.neglect(Integer.parseInt(request.getParameter("id")));
    }

}
