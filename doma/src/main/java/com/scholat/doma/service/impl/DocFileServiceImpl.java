package com.scholat.doma.service.impl;

import com.scholat.doma.service.DocFileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class DocFileServiceImpl implements DocFileService {
    @Override
    public ResponseEntity<Object> downloadFile(String filePath) throws UnsupportedEncodingException, FileNotFoundException {
        if(filePath==null){
            ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                    .body("文档id不存在！");
            return responseEntity;
        }
        System.out.println("---------用户正在下载文件...---------");
        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream((file)));
        //设置文件名编码，否则下载后无法显示中文
        String finalFileName = URLEncoder.encode(file.getName(), "UTF-8");
        HttpHeaders headers = new HttpHeaders();
        //附件下载
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", finalFileName));
        //headers.add("Content-Disposition",String.format("attachment;abc;filename=",file.getName()));
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Content-type", "charset=utf-8");
        headers.add("Expires", "0");
        ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/text;charset=utf-8"))
                .body(resource);
        return responseEntity;
    }
}
