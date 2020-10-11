package com.scholat.doma.controller;


import com.alibaba.fastjson.JSONObject;
import com.scholat.doma.entity.Team;
import com.scholat.doma.entity.User;
import com.scholat.doma.service.DocInfoService;
import com.scholat.doma.service.TeamService;
import com.scholat.doma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeamController {

    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;
    @Autowired
    DocInfoService docInfoService;

    @RequestMapping("creatTeam")
    public String creatTeam(){
        return "creatTeam";
    }

    @RequestMapping("toCreatTeam")
    public String ToCreatTeam(Team team){

        JSONObject jsonObject = new JSONObject();

        if (teamService.insertTeam(team) > 0){
            jsonObject.put("msg","create team success");
        }else {
            jsonObject.put("msg","create team failed");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("deleteTeam")
    public String DeleteTeam(@RequestParam("teamId")String teamId){

        JSONObject jsonObject = new JSONObject();

        if (teamService.DeleteTeam(teamId) > 0){
            jsonObject.put("msg","delete team success");
        }else {
            jsonObject.put("msg","delete team failed");
        }
        return jsonObject.toJSONString();

    }

//    restful风格
//    @RequestMapping("deleteTeam/{team}")
//    public String DeleteTeam(@PathVariable("teamId")String teamId){
//        teamService.DeleteTeam(teamId);
//        return "index";
//    }


    @RequestMapping("upateTeam")
    public String udateTeam(){
        return "upateTeam";
    }

    @RequestMapping("toUpdateTeam")
    public String ToUpdateTeam(Team team){

        JSONObject jsonObject = new JSONObject();

        if (teamService.updateTeam(team) > 0){
            jsonObject.put("msg","update team success");
        }else {
            jsonObject.put("msg","update team failed");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("selectTeam")
    public String SelectTeam(@RequestParam(value = "teamId")String teamId){

        Team team = teamService.SelectById(teamId);
        JSONObject jsonObject = new JSONObject();

        if (team != null){
            jsonObject.put("msg","select team success");
            jsonObject.put("team",team);
        }else {
            jsonObject.put("msg","select team failed");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("selectAllUserFromTeam")
    public String SelectAllUserFromTeam(@RequestParam("teamId")String teamId ){

        List<User> userList = teamService.SelectAllUserFromTeam(teamId);

        JSONObject jsonObject = new JSONObject();

        if (userList != null){
            jsonObject.put("msg","selectAllUserFromTeam success");
            jsonObject.put("userList",userList);
        }else {
            jsonObject.put("msg","selectAllUserFromTeam failed");
        }
        return jsonObject.toJSONString();

    }


}
