package com.fanhehe.message.service.common;

import java.util.HashMap;
import com.fanhehe.message.dto.bind.Bind;
import com.fanhehe.message.util.IResult;
import com.fanhehe.message.util.HttpUtil;
import com.fanhehe.message.constant.BindEnum;
import com.fanhehe.message.service.BindService;
import com.fanhehe.message.constant.ServiceEnum;
import org.springframework.stereotype.Service;

@Service("Impl.Common.BindService")
public class BindServiceImpl extends HttpUtil<Bind> implements BindService {

    public ServiceEnum cmlb() {
        return ServiceEnum.User;
    }

    public IResult<Bind> getBindByUid(int uid, BindEnum bindEnum) {
        HashMap<String, String> params = new HashMap<>();

        params.put("uid", String.valueOf(uid));
        params.put("bindType", String.valueOf(bindEnum.getValue()));

        return get("/api/user/bind/uid-bind-type", params);
    }

    public IResult<Bind> getBind(String target, BindEnum bindEnum) {

        HashMap<String, String> params = new HashMap<>();

        params.put("target", target);
        params.put("bindType", String.valueOf(bindEnum.getValue()));

        return get("/api/user/bind", params);
    }
}
