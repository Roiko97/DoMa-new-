package com.scholat.doma.util;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * GUID构造器
 * 32位：14位当前系统时间(yyyyMMddHHmmss) + 当前电脑的IP地址的最后两位 + 当前线程的hashCode的前9位 + 7位的随机数
 * 19位：12位当前系统时间(yyMMddHHmmss) + 当前线程的hashCode的前3位 + 4位的随机数
 * @author Administrator
 * @create
 */
public class UniqId {
    private static UniqId me = new UniqId();
    private String hostAddr;
    private final Random random = new SecureRandom();
    private final UniqTimer timer = new UniqTimer();

    private boolean isOutputInfo = false;

    private UniqId() {
        try {
            final InetAddress addr = InetAddress.getLocalHost();
            hostAddr = addr.getHostAddress();
        }catch (final IOException e) {
            System.err.println("[UniqID] Get HostAddr Error"+e);
            hostAddr = String.valueOf(System.currentTimeMillis());
        }
        if (null == hostAddr || hostAddr.trim().length() == 0 || "127.0.0.1".equals(hostAddr)) {
            hostAddr = String.valueOf(System.currentTimeMillis());
        }
        hostAddr = hostAddr.substring(hostAddr.length()-2).replace(".", "0");
        if(isOutputInfo){
            System.out.println("[UniqID]hostAddr is:" + hostAddr + "----length:"+hostAddr.length());
        }
    }


    /**
     * 获取UniqID实例
     * @return UniqId
     */
    public static UniqId getInstance() {
        me.isOutputInfo = false;
        return me;
    }

    /**
     * 获取UniqID实例
     * @return UniqId
     */
    public static UniqId getInstanceWithLog() {
        me.isOutputInfo = true;
        return me;
    }


    /**
     * 获得不会重复的毫秒数
     *
     * @return 不会重复的时间
     */
    public String getUniqTime() {
        String time = timer.getCurrentTime();
        if(isOutputInfo){
            System.out.println("[UniqID.getUniqTime]" + time +"----length:"+ time.length());
        }
        return time;
    }

    public String getUniqTime2() {
        String time = timer.getCurrentTime2();
        if(isOutputInfo){
            System.out.println("[UniqID.getUniqTime]" + time +"----length:"+ time.length());
        }
        return time;
    }

    /**
     * 获得UniqId
     *
     * @return uniqTime-randomNum-hostAddr-threadId
     */
    public String get32UniqID() {
        final StringBuffer sb = new StringBuffer();
        final String t = getUniqTime();
        int randomNumber = random.nextInt(8999999) + 1000000;
        sb.append(t);
        sb.append(hostAddr);
        sb.append(getUniqThreadCode());
        sb.append(randomNumber);
        if (isOutputInfo) {
            System.out.println("[UniqID.randomNumber]" + randomNumber+"----length:"+String.valueOf(randomNumber).length());
            System.out.println("[UniqID.getUniqID]" + sb.toString()+"----length:"+String.valueOf(sb).length());
        }
        return sb.toString();
    }

    /**
     * 获取19位GUID 不考虑分布式
     * @return
     */
    public Long get19UniqID() {
        final StringBuffer sb = new StringBuffer();
        final String t = getUniqTime2();
        int randomNumber = random.nextInt(8999) + 1000;
        sb.append(t);
        sb.append(getUniqThreadCode2());
        sb.append(randomNumber);
        return Long.parseLong(sb.toString());
    }

    public String getUniqThreadCode(){
        String threadCode = StringUtils.left(String.valueOf(Thread.currentThread().hashCode()),9);
        if (isOutputInfo) {
            System.out.println("[UniqID.getUniqThreadCode]" +threadCode+"----length:"+threadCode.length());
        }
        return StringUtils.leftPad(threadCode, 9, "0");
    }

    public String getUniqThreadCode2(){
        String threadCode = StringUtils.left(String.valueOf(Thread.currentThread().hashCode()),9);
        if (isOutputInfo) {
            System.out.println("[UniqID.getUniqThreadCode]" +threadCode+"----length:"+threadCode.length());
        }
        return StringUtils.leftPad(threadCode, 9, "0").substring(6);
    }

    /**
     * 实现不重复的时间
     */
    private class UniqTimer {
        private final AtomicLong lastTime = new AtomicLong(System.currentTimeMillis());
        public String getCurrentTime() {
            if(!timestamp2Date(this.lastTime.incrementAndGet()).equals(timestamp2Date(System.currentTimeMillis()))){
                lastTime.set(System.currentTimeMillis()+random.nextInt(10000));
            }
            return timestamp2Datetimes(this.lastTime.incrementAndGet());
        }

        public String getCurrentTime2() {
            if(!timestamp2Date(this.lastTime.incrementAndGet()).equals(timestamp2Date(System.currentTimeMillis()))){
                lastTime.set(System.currentTimeMillis()+random.nextInt(10000));
            }
            return timestamp2Datetimes2(this.lastTime.incrementAndGet());
        }
    }

    /**
     * 规范化日期，规范成yyyy-MM-dd
     * @param timestamp
     * @return
     */
    public static String timestamp2Date(long timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date(timestamp * 1000));
    }

    private static String timestamp2Datetimes(long timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date(timestamp));
    }

    private static String timestamp2Datetimes2(long timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        return dateFormat.format(new Date(timestamp));
    }

}