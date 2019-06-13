package util;

import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

import Launcher.App;

public class Mailjet {
	public static boolean SendMail(String Sender, String Receiver, String Content) throws MailjetException, MailjetSocketTimeoutException {
		
		MailjetClient client;
	      MailjetRequest request;
	      MailjetResponse response;
	      
	      client = new MailjetClient(App.getConfigStrArr()[0], App.getConfigStrArr()[1], new ClientOptions("v3.1"));
	      request = new MailjetRequest(Emailv31.resource)
	            .property(Emailv31.MESSAGES, new JSONArray()
	                .put(new JSONObject()
	                    .put(Emailv31.Message.FROM, new JSONObject()
	                        .put("Email", Sender)
	                        .put("Name", "GUO KUNPENG"))
	                    .put(Emailv31.Message.TO, new JSONArray()
	                        .put(new JSONObject()
	                            .put("Email", Receiver)
	                            ))
	                    .put(Emailv31.Message.SUBJECT, "RAPPORT - DIGITAL-LEAGUE")
	                    .put(Emailv31.Message.TEXTPART, "DIGITAL-LEAGUE VOUS PRESENTE.")
	                    //.put(Emailv31.Message.HTMLPART, "DIGITAL <b>LEAGUE</b> VOUS PRESENTE."+Content)
	                    .put(Emailv31.Message.INLINEDATTACHMENTS,new JSONArray().put(new JSONObject()
	                    		.put("ContentType", "text/html").put("Filename","rapport.html").put("Base64Content", getEncodedStr(Content))))));
	      response = client.post(request);
	      System.out.println(response.getStatus());
	      if(response.getStatus() == 200) {
	    	  System.out.println("Email Sent");
	    	  return true;
	      }else {
	    	  System.out.println(response.getData());
	    	  return false;
	      }
	}
	
	public static String getEncodedStr (String str) {
		return com.mailjet.client.Base64.encode(str.getBytes());
	}
	
	public static void main(String[] args) {
		/***************Send a email*******************/
		/**
		 *  10 email / hour (Limit by trial account)
		 *  SendMail(Sender,Receicer)
		 *  Sender can only be "gabin.guo@gmail.com"
		 *  Receiver can be everybody
		 */
		//Mailjet.SendMail("gabin.guo@gmail.com", "gabin_guo@163.com" , generator.getHtml().toBigHtmlString());
		
		
		try {
			SendMail(null, null,null);
		} catch (MailjetException e) {
			e.printStackTrace();
		} catch (MailjetSocketTimeoutException e) {
			e.printStackTrace();
		}
	}
		
	
}
