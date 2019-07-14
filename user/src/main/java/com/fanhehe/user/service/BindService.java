package com.fanhehe.user.service;

import com.fanhehe.user.model.Bind;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.util.Time;

public interface BindService {

    Bind findBindByUid(int uid);

    Bind findBindByUidAndType(int uid, BindEnum type);

    void handleCreateBind(int uid, BindEnum type, String openid, String credential);

    boolean existBindByOpenidAndType(String openid, int type);

    void createBind(int uid, BindEnum type, String openid, String credential, int instant);

    void createBindHistory(int uid, BindEnum type, String openid, String credential, int instant);

    default void createBind(int uid, BindEnum type, String openid, String credential) {
        createBind(uid, type, openid, credential, Time.makeUnixTimestamp());
    }

    default void createBindHistory(int uid, BindEnum type, String openid, String credential) {
        createBindHistory(uid, type, openid, credential, Time.makeUnixTimestamp());
    }
}
