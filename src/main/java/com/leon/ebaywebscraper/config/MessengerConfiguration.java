package com.leon.ebaywebscraper.config;

import com.leon.ebaywebscraper.service.Messenger;
import com.leon.ebaywebscraper.service.WhatsAppMessenger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerConfiguration {

  @Value("${twilio.authtoken}")
  private String twilioAuthToken;

  @Value("${twilio.sender.phonenumber}")
  private String senderPhoneNumber;

  @Value("${twilio.receiver.phonenumber}")
  private String receiverPhoneNumber;

  @Value("${twilio.account.sid}")
  private String twilioAccountSid;

  @Bean
  public Messenger messenger() {
    return new WhatsAppMessenger(twilioAuthToken, senderPhoneNumber, receiverPhoneNumber,
        twilioAccountSid);
  }
}
