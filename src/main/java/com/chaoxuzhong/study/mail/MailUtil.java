package com.chaoxuzhong.study.mail;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.json.JSONUtil;
import com.dtflys.forest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class MailUtil {
    private static MailAccount account;
    private static Properties props;

    static {
        if (account == null) {
            account = new MailAccount();
            account.setHost("smtp.126.com");
            account.setPort(Integer.valueOf("465"));
            account.setAuth(true);
            account.setFrom("rnrxt123@163.com");
            account.setUser("rnrxt123@163.com");         //account.setPass(MailConstant.password)
            account.setPass("DQTXQDWQLJGAEUFK");
            account.setSslEnable(true);
            account.setTimeout(10000);
        }
        if (props == null) {
            props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.163.com");
            props.put("mail.user", "rnrxt123@163.com");
            props.put("mail.password", "DQTXQDWQLJGAEUFK");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.port", "465");
        }
    }

    public static void sendSimpleMail(String to, String subject, String content, boolean isHtml) {
        String send = cn.hutool.extra.mail.MailUtil.send(account, to, subject, content, isHtml);

    }

    public static void main(String[] args) {
        sendEmail("chaoxuzhong.me@gmail.com", "", "测试03","测试需要回复的内容", "");
    }

    public static void sendEmail(String toEmail, String ccUser, String subject, String content, String attachmentJson) {
        sendEmail(toEmail, ccUser, subject, content, attachmentJson, "cs");
    }

    public static void sendEmail(String toEmail, String ccUser, String subject, String content, String attachmentJson, String personal) {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        Session mailSession = Session.getInstance(props, authenticator);
        final String messageIDValue = "<" + IdUtil.simpleUUID() + ">";
        MimeMessage message = new MimeMessage(mailSession) {
            @Override
            protected void updateMessageID() throws MessagingException {
                setHeader("Message-ID", messageIDValue);
            }
        };
        List<File> files = Lists.newArrayList();
        try {
            InternetAddress from = new InternetAddress(props.getProperty("mail.user"), Objects.isNull(personal) ? "cs" : personal);
            message.setFrom(from);
            Address[] a = new Address[1];
            a[0] = new InternetAddress("cs@adenfin.com.hk");
            message.setReplyTo(a);
            String[] toEmails = toEmail.split(",");
            InternetAddress[] adds = new InternetAddress[toEmails.length];
            for (int i = 0; i < toEmails.length; i++) {
                adds[i] = new InternetAddress(toEmails[i]);
            }
            message.setRecipients(Message.RecipientType.TO, adds);
            if (StringUtils.isNotEmpty(ccUser)) {
                @SuppressWarnings("static-access") InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            if ("追收融资保证金通知".equals(subject)) {
                String bccUser = "donotreply@adenfin.com.hk";
                if (null != bccUser && !bccUser.isEmpty()) {
                    @SuppressWarnings("static-access") InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
                    message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
                }
            }
            message.setSubject(subject);
            if (StrUtil.isNotBlank(attachmentJson)) {
                String host = NetUtil.getLocalhost().getHostAddress();
                String tempPath = System.getProperty("java.io.tmpdir") + "/attachment/" + host + "/";
                TypeReference<Map<String, String>> reference = new TypeReference<Map<String, String>>() {
                };
                Map<String, String> encodeMap = JSONUtil.toBean(attachmentJson, reference, false);
                Multipart multipart = new MimeMultipart();             // 文本消息
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(content, "text/html;charset=UTF-8");
                multipart.addBodyPart(messageBodyPart);
                for (Map.Entry<String, String> entry : encodeMap.entrySet()) {
                    String dest = tempPath + Thread.currentThread().getId() + "/" + entry.getKey();
                    File file = Base64.decodeToFile(entry.getValue(), FileUtil.newFile(dest));
                    files.add(file);
                    BodyPart bodyPart = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(file);
                    bodyPart.setDataHandler(new DataHandler(fds));
                    bodyPart.setFileName(MimeUtility.encodeText(entry.getKey(), "UTF-8", "B"));
                    bodyPart.addHeader("Content-Transfer-Encoding", "base64");
                    multipart.addBodyPart(bodyPart);
                }
                message.setContent(multipart);
            } else {
                message.setContent(content, "text/html;charset=UTF-8");
            }
            Transport.send(message);

        } catch (Exception e) {
            log.error("error=",e);
        } finally {
            files.forEach(FileUtil::del);
        }
        return;
    }

}