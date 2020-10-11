package com.scholat.doma.controller;


import com.alibaba.fastjson.JSONObject;
import com.scholat.doma.entity.TeamMember;
import com.scholat.doma.entity.User;
import com.scholat.doma.service.DocInfoService;
import com.scholat.doma.service.TeamMemberService;
import com.scholat.doma.service.TeamService;
import com.scholat.doma.service.UserService;
//import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;
    @Autowired
    DocInfoService docInfoService;
    @Autowired
    TeamMemberService teamMemberService;

    @RequestMapping("login")
    public String Login(@RequestParam("account") String account, @RequestParam("password")String password, HttpServletRequest request) {

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        User user = userService.SelectUsersByAccount(account);

        // 缺少密码字段，已新增
        if (user != null && user.getPassword().equals(password)){
            session.setAttribute("user",user);
            jsonObject.put("user",user);
            jsonObject.put("msg","login success");
        }else {
            jsonObject.put("msg","login failed");
        }

        return jsonObject.toJSONString();
    }

    @RequestMapping("logout")
    public void Logout(HttpServletRequest request){

        request.getSession().removeAttribute("user");

    }



    @RequestMapping("Registry")
    public String toAddUser(@RequestParam(value = "userName",required = true)String userName,
                            @RequestParam(value = "account",required = true)String account,
                            @RequestParam(value = "password",required = true)String password){

        JSONObject jsonObject = new JSONObject();

        if(userName == "" || account== "" || password== "") {
            jsonObject.put("msg","fail");
            return jsonObject.toJSONString();
        }

        if(userService.SelectUsersByAccount(account) != null){
            jsonObject.put("msg","repeat account");
            return jsonObject.toJSONString();
        }

        if (userService.InsertUser(userName,account,password) > 0){
            jsonObject.put("msg","success");
        }else {
            jsonObject.put("msg","fail");
        }

        return jsonObject.toJSONString();

    }


    @RequestMapping("joinTeam")
    public String JoinTeam(TeamMember teamMember){

        JSONObject jsonObject = new JSONObject();

        if(teamMemberService.Add(teamMember) > 0){
            jsonObject.put("msg","success");
        }else {
            jsonObject.put("msg","failed");
        }

        return jsonObject.toJSONString();
    }


    @RequestMapping("quitTeam")
    public String quitTeam(String userId,String teamId){

        JSONObject jsonObject = new JSONObject();

        if(teamMemberService.quitUniqueTeam(userId,teamId) > 0){
            jsonObject.put("msg","success");
        }else {
            jsonObject.put("msg","failed");
        }

        return jsonObject.toJSONString();
    }

}
