package com.example.sharing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharing.entity.Material;
import com.example.sharing.entity.User;
import com.example.sharing.mapper.MaterialMapper;
import com.example.sharing.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;


@Service
public class MaterialServiceimpl extends ServiceImpl<MaterialMapper,Material> implements MaterialService{

    @Autowired
    MaterialMapper materialMapper;



    @Override
    public String downloadMaterial(Long id, HttpServletResponse response) throws IOException{

        String returnString="下载成功";


        Material material=materialMapper.selectById(id);
        response.setContentType("application/octet-stream");
        String filename=new String(material.getFileName().getBytes(StandardCharsets.UTF_8),"ISO8859-1");
        response.setHeader("Content-Disposition","attachment; filename="+filename);

        byte [] buff=new byte[1024];


        BufferedInputStream bis=null;
        OutputStream outputStream= null;

        try{
            outputStream=response.getOutputStream();
            bis=new BufferedInputStream(new FileInputStream(material.getFilePath()));
            int count=bis.read(buff);
            while(count!=-1){
                outputStream.write(buff);
                count=bis.read(buff);
            }
        }catch (IOException ioException){
            returnString="下载失败";
            ioException.printStackTrace();
        }finally {
            if(bis!=null){
                try{
                    bis.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(outputStream!=null){
                try{
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return returnString;
    }

    /**
     *
     * @param id
     */
    @Override
    public String deleteMaterial(Long id) {
        materialMapper.deleteById(id);
        return "删除成功";
    }



    @Override
    public String uploadMaterial(MultipartFile file, User uploader){
        String returnString="上传成功";
        //获得组别
        String whichgroup = uploader.getUserGroup();
        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("filename:"+fileName);
        //下载总目录+组别构成文件路径
        String path = "D:/upload/" + whichgroup+"/";
        System.out.println("path:"+path);

        InputStream inputStream = null;

        OutputStream outputStream = null;

        File targetFile = new File(path + fileName);


        try {
            inputStream = file.getInputStream();

            if(!targetFile.getParentFile().exists()){
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }
            outputStream = new FileOutputStream(targetFile);

            FileCopyUtils.copy(inputStream, outputStream);

        } catch (IOException ioException) {
            returnString="上传失败";
            ioException.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (outputStream != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        materialMapper.insert(new Material(null,fileName,path+fileName,whichgroup,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),uploader.getUser_id()));
        return returnString;
    }

    @Override
    public List findAll(){
        return materialMapper.findAll();
    }


}
