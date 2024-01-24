package exdev.com.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import exdev.com.common.ExdevConstants;
import exdev.com.common.service.ExdevBaseService;

@Service("EmailService")
public class EmailService  extends ExdevBaseService{
	
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    private String emilPath = ExdevConstants.EMAIL_TEMPLATE_PATH;
    
    @SuppressWarnings("unused")
	public Map<String,Object> sendHtmlEmail(Map<String,Object> mailObj) {
    	
        MimeMessage mimeMessage 	= 	javaMailSender.createMimeMessage();
        MimeMessageHelper helper 	= 	new MimeMessageHelper(mimeMessage, "utf-8");
        
	    String recipient	= (String) mailObj.get("recipient");
	    String subject 		= (String) mailObj.get("subject");
	    String templateName	= (String) mailObj.get("templateName");
	    
	    Context context		= new Context();
        for (Map.Entry<String, Object> entry : mailObj.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("cxt_")) {
                Object value = entry.getValue();
                context.setVariable(key,value);
            }
        }
 
        try {
        	
            String htmlBody = templateEngine.process(emilPath+templateName, context);
            System.out.println(htmlBody);
            
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            // javaMailSender.send(mimeMessage);

            System.out.println("HTML 이메일 전송 성공!");
            resultInfo = makeResult(ExdevBaseService.REQUEST_SUCCESS, "", mailObj);

        } catch (MessagingException e) {
        	mailObj.put("RESULT",e.getMessage());
            resultInfo = makeResult(ExdevBaseService.REQUEST_FAIL, "", mailObj);
        }
		
		return resultInfo; 
    }
}