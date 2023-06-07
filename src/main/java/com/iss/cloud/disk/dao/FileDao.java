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

    MyFile getFile(int id);

    Map<String, Integer> getCountByType(int userId);

    int insertFile(MyFile file);

    int exists(String path);

    // 文件重命名

    int rename(MyFile file);

    void renameByFilePath(String oldPath, String newPath);
}
