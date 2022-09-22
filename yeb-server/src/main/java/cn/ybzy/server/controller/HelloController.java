package cn.ybzy.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangHao
 * @Description 测试
 * @createTime 2022-09-06 11:17
 **/

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2() {
        return "/employee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello3() {
        return "/employee/advanced/hello";
    }
}
