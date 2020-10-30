package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootTaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {

        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7点开会");


        message.setTo("xiongtian_email@163.com");
        message.setFrom("764578422@qq.com");
        mailSender.send(message);
    }


    @Test
    public void test02() throws Exception{
        // 1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今晚 7:30 开会</b>",true);


        helper.setTo("xiongtian_email@163.com");
        helper.setFrom("764578422@qq.com");


        // 上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator\\Pictures\\1.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\Administrator\\Pictures\\2.jpg"));
        mailSender.send(mimeMessage);

    }

}
