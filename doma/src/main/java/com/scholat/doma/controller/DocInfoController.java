package com.scholat.doma.controller;

import com.alibaba.fastjson.JSONObject;
import com.scholat.doma.entity.DocInfo;
import com.scholat.doma.entity.User;
import com.scholat.doma.service.DocInfoService;
import com.scholat.doma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/docinfo")
public class DocInfoController {

    @Autowired
    UserService userService;

    @Autowired
    DocInfoService docInfoService;

//    @RequestMapping("/creatDoc")
//    public String createDocInfo(@RequestParam("userid") String userId,@RequestParam("docid") String docId,
//                                @RequestParam("docname") String docName, @RequestParam("recommend") Integer recommend,
//                                @RequestParam("style") String style,@RequestParam("address") String address,
//                                @RequestParam("existadd") Integer exist_add,@RequestParam("content") String content){
//
//        Integer res = docInfoService.createDocInfo(userId,docId,docName,recommend,style,address,exist_add,content);
//        JSONObject jsonObject = new JSONObject();
//        if(res >0){ //代表插入成功
//            jsonObject.put("msg","SUCCESS");
//        }else{
//            jsonObject.put("msg","创建失败,请重新在试");
//        }
//        return jsonObject.toString();
//    }
    @RequestMapping("/gContent")
    public String getContent(String docId){
        JSONObject jsonObject = new JSONObject();
        if(docId.equals("")){
            jsonObject.put("msg","文档不存在，请重新在试");
        }else{
            String content = docInfoService.getContent(docId);
            if(content.equals("")|| content == null){
                jsonObject.put("msg","内容为空,无法获取");
            }else{
                jsonObject.put("msg",content);
            }
        }
        return jsonObject.toString();
    }
    @RequestMapping("/gStyle")
    public String getStyle(String docId){
        JSONObject jsonObject = new JSONObject();
        String res = docInfoService.getStyle(docId);
        if(res.equals("") || res == null){
            jsonObject.put("msg","无分类,请添加");
        }else{
            jsonObject.put("msg",res);
        }
        return jsonObject.toString();
    }
    @RequestMapping("/gRecommend")
    public String getRecommend(String docId){
        JSONObject jsonObject = new JSONObject();
        Integer res = docInfoService.getRecommend(docId);
        if(res ==1 ){
            jsonObject.put("msg",1);
        }else if(res ==0 ){
            jsonObject.put("msg",0);
        }else{
            jsonObject.put("msg",-1);
        }
        return  jsonObject.toString();
    }
    @RequestMapping("/gDocInfo")
    public String  getDocInfo(String docId){
        JSONObject jsonObject = new JSONObject();
        DocInfo docInfo = docInfoService.getDocInfo(docId);
        if(docInfo.equals("") || docInfo == null){
            jsonObject.put("msg","error");
        }else{
            jsonObject.put("msg",docInfo);
        }
        return jsonObject.toString();
    }
    @RequestMapping("/dDocInfo")
    public String deleteDocInfo(String docId){
        JSONObject jsonObject = new JSONObject();
        Integer res = docInfoService.deleteDocInfo(docId);
        if(res !=0){
            jsonObject.put("msg",1); //删除成功
        }else{
            jsonObject.put("msg","删除失败");
        }
        return jsonObject.toString();
    }
    @RequestMapping("/uContent")
    public String updataContent(String docId,String content){
        JSONObject jsonObject = new JSONObject();
        Integer res = docInfoService.updataContent(docId,content);
        if(res !=0){
            jsonObject.put("msg",1);
        }else{
            jsonObject.put("msg",0);
        }
        return jsonObject.toString();
    }
    @RequestMapping("/uStyle")
    public String  updateStyle(String docId,String style){
        JSONObject jsonObject = new JSONObject();
        Integer res = docInfoService.updateStyle(docId,style);
        if(res !=0){
            jsonObject.put("msg",1);
        }else{
            jsonObject.put("msg",0);
        }
        return jsonObject.toString();
    }
    @RequestMapping("/uRecommend")
    public String updateRecommend(String docId,String recommend){
        JSONObject jsonObject = new JSONObject();
        Integer res = docInfoService.updateRecommend(docId,recommend);
        if(res !=0){
            jsonObject.put("msg",1);
        }else{
            jsonObject.put("msg",0);
        }
        return jsonObject.toString();
    }
    @RequestMapping("/uExAnn")
    public String updateExistAnn(String docId,String existAnn){
        JSONObject jsonObject = new JSONObject();
        Integer res = docInfoService.updateExistAnn(docId,existAnn);
        if(res !=0){
            jsonObject.put("msg",1);
        }else{
            jsonObject.put("msg",0);
        }
        return jsonObject.toString();
    }





}
