package com.wei.utils;
import com.wei.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    // 发送成功时返回1，发送失败返回0
    public static int sendmail(String email, String info){
        //参数设置
        Properties properties = new Properties();
        // socketfactory来取代默认的socketfactory
        properties.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.host","smtp.qq.com");//发送邮箱服务器
        properties.setProperty("mail.smtp.port","465");//发送端口
        properties.setProperty("mail.smtp.auth","true");//是否开启权限控制
        properties.setProperty("mail.debug","true");//true 打印信息到控制台
        properties.setProperty("mail.transport.protocol","smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable","true");
        //建立连接
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1678455784@qq.com","rrdeoevwgqfbdedi");
            }
        });
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress("1678455784@qq.com"));
            //设置收件人
            System.out.println(email);
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));//收件人
            //设置主题
            message.setSubject("韦韦商城");
            //设置邮件正文  第二个参数是邮件发送的类型
            message.setContent(info,"text/html;charset=UTF-8");
            //发送一封邮件
            Transport transport = session.getTransport();
            transport.connect("1678455784@qq.com","rrdeoevwgqfbdedi");
            Transport.send(message);
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
