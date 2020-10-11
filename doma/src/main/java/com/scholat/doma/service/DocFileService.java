package com.scholat.doma.service;

import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface DocFileService {

    //文件下载
    public ResponseEntity<Object> downloadFile(String fileName)
            throws UnsupportedEncodingException, FileNotFoundException;
}
