package org.zfjava.app.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zfjava.app.dto.User;
import org.zfjava.app.msg.ResponseInfo;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @auther zhangfen
 * @date 2022/1/4 17:36
 */
@RestController
@RequestMapping(value = "/test2", method = RequestMethod.POST)
public class ExceptionController {
    @RequestMapping("/json")
    public ResponseInfo test(@Validated @RequestBody User user, BindingResult bindingResult) {
        try {
            if(bindingResult.getErrorCount()>0){
                System.out.println("Error："+bindingResult.getFieldError());
                return new ResponseInfo<User>(500,bindingResult.getFieldError().getDefaultMessage().toString()
                        , user);
            }else {
                String data = "登录用户：" + user.getName() + "，密码：" + user.getPassword();
                return new ResponseInfo<String>(0, "操作成功！", data);
            }
        } catch (Exception e) {
            return new ResponseInfo(500, "系统异常，请联系管理员！");
        }
    }
}
