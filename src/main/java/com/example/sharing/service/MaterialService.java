package com.example.sharing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sharing.entity.Material;
import com.example.sharing.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface MaterialService extends IService<Material> {
    public String downloadMaterial(Long id, HttpServletResponse response) throws IOException;

    public String deleteMaterial(Long id);

    public String uploadMaterial(MultipartFile file , User uploader);

    public List findAll();
}
