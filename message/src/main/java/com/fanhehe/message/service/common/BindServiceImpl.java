package com.fanhehe.message.service.common;

import java.util.HashMap;
import com.fanhehe.util.http.HttpUtil;
import com.fanhehe.util.result.IResult;
import com.fanhehe.message.dto.bind.Bind;
import com.fanhehe.message.constant.BindEnum;
import org.springframework.stereotype.Service;
import com.fanhehe.message.service.BindService;
import org.springframework.beans.factory.annotation.Value;

@Service("Impl.Common.BindService")
public class BindServiceImpl extends HttpUtil<Bind> implements BindService {

    @Value("com.fanhehe.module.user")
    private String USER_SERVICE;

    public String getEndpoint() {
        return "127.0.0.1:10014";
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
