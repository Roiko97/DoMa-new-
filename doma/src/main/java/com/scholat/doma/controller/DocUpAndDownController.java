package com.scholat.doma.controller;

import com.alibaba.fastjson.JSONObject;
import com.scholat.doma.entity.DocInfo;
import com.scholat.doma.util.RandomID;
import com.scholat.doma.service.DocFileService;
import com.scholat.doma.service.DocInfoService;
import com.scholat.doma.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * 实现文件的上传与下载
 */
@Controller
public class DocUpAndDownController {

    @Autowired
    DocFileService docFileService;

    @Autowired
    DocInfoService docInfoService;

    @Autowired
    UserService userService;

    //文件基础路径
    String BASEPATH = "E:\\\\DoMaFilesSpace\\";

//    /**
//     * 用户更换文档对应文件
//     * 由用户id+文档名查出文档记录，保存文件到本地后记录本地的文档地址，
//     * 后期也可改成由用户id+文档id查找，看前端需求
//     * @param multipartFile
//     * @param userId 用户id
//     * @param docName 文档名
//     * @param httpServletRequest
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping("/upload")
//    private String uploadFile(@RequestParam("file") MultipartFile multipartFile,
//                              @RequestParam("userId") String userId,
//                              @RequestParam(value = "docName") String docName,
//                              HttpServletRequest httpServletRequest) throws IOException {
//        HttpSession session = httpServletRequest.getSession();
//        System.out.println("用户id："+userId);
//        System.out.println("文档名："+docName);
//        System.out.println("请求体："+httpServletRequest);
//
//        DocInfo docInfo = docInfoService.SelectByUserIdAndDocName(userId,docName);
//        //查不到则报错
//        if(docInfo==null) {
//            System.out.println("不存在该用户或者文档");
//            return "error/400";
//        }
//
//        //获取原始文件名
//        String originalFilename = multipartFile.getOriginalFilename();
//        //若无文档名则直接使用文件名
//        if(docName.isEmpty()) docName = originalFilename;
//
//        String docId = docInfo.getDocId();
//
//        //生成保存在文件名
//        String fileName = docId+"-"+ UUID.randomUUID()+"-"+originalFilename;
//
//        //生成完整路径
//        String filePath = BASEPATH + fileName;
//
//        //文件上传
//        System.out.println(multipartFile.getBytes());
//        if (!multipartFile.isEmpty()) {
//            //保存文件到本地
//            try {
//                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(filePath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("文件上传成功！保存到了："+filePath);
//            //生成服务器本地地址
//            String address = filePath;
//
//            //更新文件地址到数据库中
//            docInfo.setAddress(filePath);
//            Integer res = docInfoService.UpdateDocInfoByDocId(docInfo);
//            //插入失败则跳转到500
//            if(res<0) return "error/500";
//
//            //上传好了之后返回成功
//            return "uploadSuccess";
//        }
//
//        else {
//            System.out.println("没有上传文件！");
//            return "error/400";
//        }
//
//    }

    /**
     * 用户初次上传文档，则在数据库上更新记录
     * 要打上@ResponseBody的注解，否则json字符串会自动解析为模板引擎而报错
     * @param multipartFile 文件
     * @param userId
     * @param docName
     * @param recommend
     * @param style
     * @param existAnn
     * @param content
     * @param httpServletRequest
     * @return
     * @throws IOException
     */
    @RequestMapping("/createdoc")
    @ResponseBody
    private String createDoc(@RequestParam("file") MultipartFile multipartFile,
                             @RequestParam(value = "userId" ) String userId,
                             @RequestParam(value = "docName") String docName,
                             @RequestParam(value = "recommend",defaultValue = "0") Integer recommend,
                             @RequestParam(value = "style",defaultValue = "null") String style,
                             @RequestParam(value = "existann",defaultValue = "0") Integer existAnn,
                             @RequestParam(value = "content",defaultValue = "null") String content,
                             HttpServletRequest httpServletRequest) throws IOException {

        JSONObject jsonObject = new JSONObject();   //建立回传前台的json
        //TODO 验证是否有该用户
        if(userService.SelectUserById(userId)==null)
        {
            jsonObject.put("msg","用户不存在");
            return jsonObject.toString();
        }

        //TODO 文档名若是不存在则设为上传的文件名
        //获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //若无文档名则直接使用文件名(去掉后缀名)
        if(docName.isEmpty()) docName = originalFilename.split(".")[0];

        //TODO 生成文档id
        RandomID randomID = new RandomID();
        String docId = randomID.GetRandomId("doc");

        //TODO 生成存于服务器的文件名
        //生成保存在文件名
        String fileName = docId+"-"+ UUID.randomUUID()+"-"+originalFilename;

        //TODO 生成完整路径
        String filePath = BASEPATH + fileName;

        //TODO 文件上传
        //System.out.println(multipartFile.getBytes());
        if (!multipartFile.isEmpty()) {
            //保存文件到本地
            try {
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件上传成功！保存到了："+filePath);
        }
        else {
            System.out.println("没有上传文件！");
            jsonObject.put("msg","文件为空，上传失败");
            return jsonObject.toString();
        }
        //TODO 生成服务器本地地址
        String address = filePath;


        //TODO 生成文档记录
        Integer res = docInfoService.createDocInfo(userId,docId,docName,recommend,
                style,address,existAnn,content);

        //插入失败回传
        if(res<0) {
            jsonObject.put("msg","文件上传失败，服务器有问题");
            return jsonObject.toString();
        }

        //上传好了之后返回成功
        jsonObject.put("msg","上传成功！太棒了JOJO！");
        return jsonObject.toString();

    }

    /**
     * 传入文档id下载文档
     * @param docId
     * @return
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    @RequestMapping("/download")
    private ResponseEntity<Object> downloadFile(@RequestParam("docId") String docId)
            throws UnsupportedEncodingException, FileNotFoundException {
        //根据文档ID从数据库中得到路径
        String filePath = docInfoService.getAddress(docId);
        System.out.println(docId+" : "+filePath);
        return docFileService.downloadFile(filePath);


    }
}
