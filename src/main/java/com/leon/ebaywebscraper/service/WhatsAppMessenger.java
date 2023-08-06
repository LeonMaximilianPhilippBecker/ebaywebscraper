package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.ProductListing;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WhatsAppMessenger implements Messenger {

  @Value("${twilio.account.sid}")
  private String twilioAccountSid;

  @Value("${twilio.authtoken}")
  private String twilioAuthToken;

  @Value("${twilio.sender.phonenumber}")
  private String senderPhoneNumber;

  @Value("${twilio.receiver.phonenumber}")
  private String receiverPhoneNumber;

  @Override
  public void sendProductListingAlert(final ProductListing productListing) {
    Twilio.init(twilioAccountSid, twilioAuthToken);
    Message message = Message.creator(
        new PhoneNumber(receiverPhoneNumber),
        new PhoneNumber(senderPhoneNumber),
        productListing.toString()
    ).create();
  }
}
