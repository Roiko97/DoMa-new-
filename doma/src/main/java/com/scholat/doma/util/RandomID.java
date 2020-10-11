package com.scholat.doma.util;

import java.util.Random;

public class RandomID {


    final static Integer length = 6;

    /**
     * 随机生成ID（个人ID和团队ID）
     * @param style 类型，分别可选user类型和team类型
     * @return ID字符串，分别可能以user/team/erro开头
     */
    public String GetRandomId(String style) {
        String originStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        if(style.equals("user")){
            stringBuffer.append("user");
        }else if(style.equals("team")){
            stringBuffer.append("team");
        }else if(style.equals("doc")){
            stringBuffer.append("doc");
        }
        else{
            stringBuffer.append("erro"); //错误前缀
        }
        for (int i = 0; i < length;++i) {
            int index = random.nextInt(originStr.length());
            char charAt = originStr.charAt(index);
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }



    public String GetRandomPassword() {
        String originStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < length;++i) {
            int index = random.nextInt(originStr.length());
            char charAt = originStr.charAt(index);
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }



}
