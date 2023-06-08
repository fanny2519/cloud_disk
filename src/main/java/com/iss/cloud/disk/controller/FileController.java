package com.iss.cloud.disk.controller;

import com.alibaba.fastjson.JSON;
import com.iss.cloud.disk.configure.LoginHandlerInterceptor;
import com.iss.cloud.disk.dto.ShareInfoDto;
import com.iss.cloud.disk.model.*;
import com.iss.cloud.disk.service.FileService;
import com.iss.cloud.disk.service.HDFSService;
import com.iss.cloud.disk.service.MessageService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    private final static Logger logger = Logger.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    @Autowired
    private HDFSService hdfsService;

    @GetMapping("/getFiles")
    @ResponseBody
    public Pagination<MyFile> getFiles(Pagination page) {
        return this.fileService.getFiles(page);
    }

    @GetMapping("/getFilesByPid")
    @ResponseBody
    public Pagination<MyFile> getFilesByPid(Pagination page) {
        return this.fileService.getFilesByPid(page);
    }

    @GetMapping("/getFolders")
    @ResponseBody
    public List<Node> getFolders(@RequestParam int pid, @SessionAttribute("user") User user) {
        logger.info("...getCollectFiles  ...request params is {} " + JSON.toJSONString(user));
        return this.fileService.getFolders(pid, user.getId());
    }

    @PostMapping("/insertFile")
    @ResponseBody
    public ResultModel insertFile(@RequestParam("file") MultipartFile file, MyFile myFile) throws IOException {
        logger.info("...getCollectFiles  ...request params is {} " + JSON.toJSONString(myFile));
        myFile.setFileSize(file.getSize());
        return this.fileService.insertFile(file.getInputStream(), myFile);
    }


    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam String fileName, @RequestParam String filePath) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));
        InputStream input = this.hdfsService.download(filePath);
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(input), headers, HttpStatus.OK);
    }

    //
    @PostMapping("/mkdir")
    @ResponseBody
    public ResultModel mkdir(MyFile myFile) {
        return this.fileService.mkdir(myFile);
    }


    //    文件重命名
    @GetMapping(value = "/rename")
    @ResponseBody
    public ResultModel rename(MyFile myFile, @RequestParam String newPath) {
        return this.fileService.rename(myFile, newPath);
    }

    // 移动文件夹
    @PostMapping(value = "/move")
    @ResponseBody
    public ResultModel move(@RequestBody MoveVO vo) {
        return this.fileService.move(vo);
    }


    // 文件回收、分享、消息
    @PostMapping("/getReduction")
    @ResponseBody
    public ResultModel getReduction(HttpServletRequest request) {
        logger.info("...getCollectFiles  ...request params is {} " + JSON.toJSONString(Integer.parseInt(request.getParameter("id"))));
        return this.fileService.reduction(Integer.parseInt(request.getParameter("id")));

    }

    @PostMapping("/temDelete")
    @ResponseBody
    public ResultModel temDelete(int ids) {
        return this.fileService.temDelete(ids);
    }

    @PostMapping("/deleteInfo")
    @ResponseBody
    public ResultModel deleteFile(HttpServletRequest request) {
        logger.info("...getCollectFiles  ...request params is {} " + JSON.toJSONString(Integer.parseInt(request.getParameter("id"))));
        return this.fileService.deleteFile(Integer.parseInt(request.getParameter("id")));
    }

    @GetMapping("/getRecoveryFiles")
    @ResponseBody
    public Pagination<MyFile> getRecoveryFiles(Pagination page) {
        return this.fileService.getRecoveryFiles(page);
    }


    @GetMapping("/getShareFiles")
    @ResponseBody
    public Pagination<ShareInfoDto> getShareFiles(Pagination page) {
        return this.fileService.getShareFiles(page);
    }

    @GetMapping("/shareFile")
    @ResponseBody
    public ResultModel shareFile(@RequestParam int[] ids, @RequestParam int fileId, @SessionAttribute("user") User currentUser) {
        return this.fileService.share(ids, fileId, currentUser);
    }


    //收藏列表
    @GetMapping("/getCollectFiles")
    @ResponseBody
    public Pagination<MyFile> getCollectFiles(Pagination page) {
        return this.fileService.getCollectFiles(page);
    }

    //收藏页面
    @GetMapping(value = "/collectFile")
    @ResponseBody
    public ResultModel collectFile(@RequestParam int id) {
//        logger.info("...collectFile...request params is {} " + JSON.toJSONString(id));
        return this.fileService.collectFile(id);
    }

    @PostMapping("/notCollection")
    @ResponseBody
    public ResultModel notCollection(HttpServletRequest request) {
        logger.info("...getCollectFiles  ...request params is {} " + JSON.toJSONString(Integer.parseInt(request.getParameter("id"))));
        return this.fileService.notcollection(Integer.parseInt(request.getParameter("id")));

    }


}
