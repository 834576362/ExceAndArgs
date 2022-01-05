package org.zfjava.app.controller.old;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zhangfen
 * @date 2022/1/4 16:16
 * @Des 封装返回数据（接口返回数据一般要包含状态码、信息、数据等）
 */
//这种操作，每个接口都这样处理，非常麻烦，需要一种更优雅的实现方式。
@RestController
@RequestMapping(value = "/test",method = RequestMethod.GET)
public class TestController {

    @RequestMapping("/json")
    public JSONObject test(){
        JSONObject result = new JSONObject();

        try{
            //TODO ?
            result.put("code",0);
            result.put("msg","操作成功");
            result.put("data","响应内容（测试数据）");

        }catch(Exception e){
            result.put("code",500);
            result.put("msg","错误信息（系统异常）");
        }
        return result;
    }

}
