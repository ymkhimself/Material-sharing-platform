package com.example.sharing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data

public class Material {
    @TableId(value="pk_fileid",type= IdType.AUTO)
    private Integer pkFileid;
    private String fileName;
    private String filePath;
    private String whichGroup;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer userId;

    public Material(Integer pkFileid, String fileName, String filePath, String whichGroup, Timestamp createTime, Timestamp updateTime, Integer userId) {
        this.pkFileid = pkFileid;
        this.fileName = fileName;
        this.filePath = filePath;
        this.whichGroup = whichGroup;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }
}