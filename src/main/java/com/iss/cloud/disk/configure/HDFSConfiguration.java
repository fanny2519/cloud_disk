package com.iss.cloud.disk.configure;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.net.URI;

@Configuration
public class HDFSConfiguration {

    @Value("${hadoop.hdfs.uri}")
    private String hdfs_uri;

    /**
     * 获取 HDFS 文件系统对象
     */
    @Bean
    public FileSystem getFileSystem() {
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfs_uri), new org.apache.hadoop.conf.Configuration());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fs;
    }
}