package com.boot.jpa.service;

import com.boot.jpa.model.UserInfo;
import com.boot.jpa.repository.UserInfoRepository;
import com.boot.jpa.repository.UserRepository;
import com.boot.jpa.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public ResponseVO login(String email) {
        UserInfo userInfo = userInfoRepository.findByEmail(email);
        if (userInfo == null) {
            throw new NullPointerException("user is not exist");
        }
        ResponseVO responseVO = new ResponseVO(0, "userExist");
        return responseVO;
    }
}
