package com.fanhehe.user.service;

import java.util.HashMap;

import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.model.User;

public interface UserService {

   int findMaxUid();

   User findUserByUid(int uid);

   User createUserByEmail(String email, String password, HashMap<String, String> options);

   User createUserByPhone(String phone, String password, HashMap<String, String> options);

   User commonCreateUser(String target, String password, BindEnum bindType, HashMap<String, String> options);

   default User createUserByEmail(String email, String password) {
      return this.createUserByEmail(email, password, new HashMap<>());
   }

   default User createUserByPhone(String phone, String password) {
      return this.createUserByPhone(phone, password, new HashMap<>());
   }
}