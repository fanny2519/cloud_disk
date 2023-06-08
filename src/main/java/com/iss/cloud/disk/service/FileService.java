package com.iss.cloud.disk.service;

import com.iss.cloud.disk.dto.ShareInfoDto;
import com.iss.cloud.disk.model.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


public interface FileService {

    /**
     * 获得文件列表
     */
    Pagination<MyFile> getFiles(Pagination page);


    /**
     * 获得文件列表
     */
    Pagination<MyFile> getFilesByPid(Pagination page);

    List<Node> getFolders(int pid, int userId);

    Map<String, Integer> getCountByType(int userId);

    ResultModel insertFile(InputStream inputStream, MyFile myFile);

    ResultModel mkdir(MyFile myFile);

    //    文件重命名
    ResultModel rename(MyFile myFile, String newPath);

    ResultModel move(MoveVO vo);

    /**
     * 删除文件
     */
    ResultModel deleteFile(int id);

    ResultModel temDelete(int ids);

    ResultModel reduction(int id);

    /**
     * 获得回收站文件列表
     */
    Pagination<MyFile> getRecoveryFiles(Pagination page);

    Pagination<ShareInfoDto> getShareFiles(Pagination page);

    ResultModel share(int[] ids, int fileId, User currentUser);


}
