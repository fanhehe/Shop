package com.fanhehe.user.controller;

import com.fanhehe.user.model.Bind;
import com.fanhehe.util.result.IResult;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.util.result.InvokeResult;
import com.fanhehe.user.service.BindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BindController {

    private BindService bindService;

    @Autowired
    @Qualifier("Impl.BindService")
    void setBindService(BindService bindService) {
        this.bindService = bindService;
    }

    @RequestMapping(value ="/api/user/bind/uid-bind-type", method = RequestMethod.GET)
    public IResult<Bind> login(
            @RequestParam(defaultValue = "0") int uid,
            @RequestParam(defaultValue = "1") int bindType) {
        return InvokeResult.success(bindService.findBindByUidAndType(uid, BindEnum.getBindEnumByValue(bindType)));
    }
}
