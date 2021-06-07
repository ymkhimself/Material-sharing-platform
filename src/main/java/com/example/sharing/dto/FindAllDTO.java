package com.example.sharing.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FindAllDTO {
    private Integer pkFileid;
    private String fileName;
    private String userName;
    private Timestamp createTime;;
}
