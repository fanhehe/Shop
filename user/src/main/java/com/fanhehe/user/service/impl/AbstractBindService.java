package com.fanhehe.user.service.impl;

import com.fanhehe.user.util.Time;
import com.fanhehe.user.model.Bind;
import com.fanhehe.user.dao.BindDao;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.dao.BindHistoryDao;
import com.fanhehe.user.service.BindService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("Abstract.BindService")
public abstract class AbstractBindService implements BindService {

    private BindDao bindDao;
    private BindHistoryDao bindHistoryDao;

    @Autowired
    public void setBindDao(BindDao bindDao) {
        this.bindDao = bindDao;
    }

    @Autowired
    public void setBindHistoryDao(BindHistoryDao bindHistoryDao) {
        this.bindHistoryDao = bindHistoryDao;
    }

    public Bind findBindByUid(int uid) {
        return bindDao.findBindByUid(uid);
    }

    public Bind findBindByUidAndType(int uid, BindEnum type) {
        return bindDao.findBindByUidAndType(uid, type.getValue());
    }

    public boolean existBindByOpenidAndType(String openid, int type) {
        Integer result = bindDao.existBindByOpenidAndType(openid, type);
        return result != null;
    }

    public void handleCreateBind(int uid, BindEnum type, String openid, String credential) {
        int instant = Time.makeUnixTimestamp();
        createBind(uid, type, openid, credential, instant);
        createBindHistory(uid, type, openid, credential, instant);
    }

    public void createBind(int uid, BindEnum type, String openid, String credential, int instant) {
        bindDao.createBind(uid, type.getValue(), openid, credential, instant, instant);
    }

    public void createBindHistory(int uid, BindEnum type, String openid, String credential, int instant) {
        bindHistoryDao.createBind(uid, type.getValue(), openid, credential, instant, instant);
    }
}
