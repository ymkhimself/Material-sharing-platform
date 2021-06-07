package com.example.sharing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class User {
    @TableId(value = "user_id",type= IdType.AUTO)
    private Integer user_id;
    private String userName;
    private String userGroup;
    private Integer isAdmin;

}
