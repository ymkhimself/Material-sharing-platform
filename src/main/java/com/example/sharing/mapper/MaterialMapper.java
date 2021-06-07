package com.example.sharing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharing.dto.FindAllDTO;
import com.example.sharing.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import sun.print.BackgroundServiceLookup;

import java.util.List;


@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
    public List<FindAllDTO> findAll();
}
