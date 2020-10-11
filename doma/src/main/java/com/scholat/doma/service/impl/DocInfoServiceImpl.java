package com.scholat.doma.service.impl;

import com.scholat.doma.dao.DocInfoDao;
import com.scholat.doma.entity.DocInfo;
import com.scholat.doma.service.DocInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class DocInfoServiceImpl implements DocInfoService {

    @Autowired
    DocInfoDao docInfoDao;
    @Override
    public Integer createDocInfo(String userId, String docId, String docName, Integer recommend, String style, String address, Integer existAnn, String content) {
        DocInfo docInfo = new DocInfo();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());
        docInfo.setUserId(userId);
        docInfo.setDocId(docId);
        docInfo.setPubTime(date);
        docInfo.setDocName(docName);
        docInfo.setRecommend(recommend);
        docInfo.setStyle(style);
        docInfo.setAddress(address);
        docInfo.setExistAnn(existAnn);
        docInfo.setContent(content);
        return docInfoDao.createDocInfo(docInfo);
    }

    @Override
    public String getContent(String docId) {
        return docInfoDao.getContent(docId);
    }

    //style格式:aaa,bbb,ccc
    @Override
    public String getStyle(String docId) {
        return docInfoDao.getStyle(docId);
    }

    @Override
    public Integer getRecommend(String docId) {
        return docInfoDao.getRecommend(docId);
    }

    @Override
    public DocInfo getDocInfo(String docId) {
        return docInfoDao.getDocInfo(docId);
    }

    @Override
    public String getAddress(String docId) {
        return docInfoDao.getAddress(docId);
    }

    @Override
    public Integer deleteDocInfo(String docId) {
        return docInfoDao.deleteDocInfo(docId);
    }

    @Override
    public Integer updataContent(String docId, String content) {
        return docInfoDao.updataContent(docId,content);
    }

    @Override
    public Integer updateStyle(String docId, String style) {
        return docInfoDao.updateStyle(docId,style);
    }

    @Override
    public Integer updateRecommend(String docId, String recommend) {
        return docInfoDao.updateRecommend(docId,recommend);
    }

    @Override
    public Integer updateExistAnn(String docId, String existAnn) {
        return docInfoDao.updateExistAnn(docId,existAnn);
    }


}
