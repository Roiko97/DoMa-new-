package com.scholat.doma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 做页面跳转的
 */
@Controller

public class JumpController {
    //暂时不写业务逻辑

    @RequestMapping("ToLogin")
    public String ToLogin(){
        return "login";
    }

    @RequestMapping("ToLogout")
    public String ToLogout(){
        return "redirect:logout";
    }

    @RequestMapping("toRegistry")
    public String ToRegistry(){
        return "forward:Registry";
    }

    @RequestMapping("Communicate")
    public String ToCommunicate(){return  "Communicate";}

    @RequestMapping("Development")
    public String Development(){return  "Development";}

    @RequestMapping("Summarize")
    public String Summarize(){return "Summarize";}
}
