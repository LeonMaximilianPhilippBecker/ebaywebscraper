package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.ProductListing;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class WhatsAppMessenger implements Messenger {

  private final String twilioAccountSid;

  private final String twilioAuthToken;

  private final String senderPhoneNumber;

  private final String receiverPhoneNumber;

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
