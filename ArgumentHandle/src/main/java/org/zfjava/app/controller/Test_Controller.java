package org.zfjava.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zfjava.app.msg.ResponseInfo;

/**
 * @auther zhangfen
 * @date 2022/1/4 16:53
 */

@RestController
@RequestMapping(value="/test1",method = RequestMethod.GET)
public class Test_Controller {

    @GetMapping(value="/json")
    public ResponseInfo test(){
        try{
            // 模拟异常业务代码
            int num = 1 / 0;
            return new ResponseInfo("测试数据");
        }catch(Exception e){
            return new ResponseInfo(500, "系统异常，请联系管理员！");
        }
    }
}
