package com.fanhehe.user.service.common;

import java.util.List;
import java.util.ArrayList;
import com.fanhehe.user.model.User;
import com.fanhehe.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("Common.CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String username) {

        int uid;

        if (username == null) {
            throw new UsernameNotFoundException("username is null");
        }

        try {
            uid = Integer.valueOf(username);
        } catch (NumberFormatException e) {
            uid = 0;
//            throw new UsernameNotFoundException("仅支持ID登录");
        }

        User user = userDao.findUserByUid(uid);

        if (user == null) {
            throw new UsernameNotFoundException("username is null");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList <>();

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
    }
}