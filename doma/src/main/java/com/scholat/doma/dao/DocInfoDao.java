package com.scholat.doma.dao;

import com.scholat.doma.entity.DocInfo;
import org.apache.coyote.OutputBuffer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocInfoDao {
    //创建文本
    public Integer createDocInfo(DocInfo docInfo);
    //通过文档ID获取文本信息
    public String getContent(String docId);
    //获得分类
    public String getStyle(String docId);
    //判断是否进行论文推荐
    public Integer getRecommend(String docId);
    //通过docId获取文本对象
    public DocInfo getDocInfo(String docId);
    //通过docId获取文档下载地址
    public String getAddress(String docId);
    //通过docId删除记录
    public Integer deleteDocInfo(String docId);
    //通过docId更新注解内容
    public Integer updataContent(String docId,String content);
    //通过docId更新分类
    public Integer updateStyle(String docId,String style);
    //通过docId更新是否推荐
    public Integer updateRecommend(String docId,String recommend);
    //通过docId更新是否注解
    public Integer updateExistAnn(String docId,String existAnn);

}
