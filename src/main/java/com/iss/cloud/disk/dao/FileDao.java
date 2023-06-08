package com.iss.cloud.disk.dao;

import com.iss.cloud.disk.dto.ShareInfoDto;
import com.iss.cloud.disk.model.MyFile;
import com.iss.cloud.disk.model.Node;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface FileDao {

    int getCount(@Param("flag") int flag, @Param("userId") int userId, @Param("pid") int pid);

    List<MyFile> getFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("userId") int userId);

    List<MyFile> getFilesByPid(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("pid") int pid);

    List<Node> getFolders(@Param("pid") int pid, @Param("userId") int userId);

    List<MyFile> getFilesByIds(int[] ids);

    Map<String, Integer> getCountByType(int userId);

    int insertFile(MyFile file);
    
    String getFilesPath(int userId);
    void deletefiles(int userId);

    int exists(String path);

    // 文件重命名

    int rename(MyFile file);

    void renameByFilePath(String oldPath, String newPath);

    // 文件回收、分享、消息
    int getRecoveryCount(@Param("flag") int flag, @Param("userId") int userId, @Param("pid") int pid);

    List<MyFile> getRecoveryFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("userId") int userId);

    MyFile getFile(int id);

    int getShareFilesCount(@Param("userId") int userId);

    List<ShareInfoDto> getShareFiles(@Param("start") Integer start,
                                     @Param("pageSize") Integer pageSize, @Param("userId") int userId);

    int deleteFile(int id);

    int temDelete(int ids);

    int reduction(int id);

    //获取收藏列表
    int getCollectCount(@Param("flag") int flag, @Param("userId") int userId, @Param("pid") int pid);

    List<MyFile>getCollectFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("userId") int userId);

    ///收藏
    int collectFile(int id);

    //    //取消收藏
    int notcollection(int id);

}
