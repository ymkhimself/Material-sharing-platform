package com.example.sharing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharing.entity.User;
import com.example.sharing.mapper.UserMapper;
import com.example.sharing.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceimpl extends ServiceImpl<UserMapper,User> implements UserService{
}
