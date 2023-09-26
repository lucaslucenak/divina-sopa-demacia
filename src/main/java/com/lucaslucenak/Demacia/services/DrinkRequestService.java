package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.DrinkRequestModel;
import com.lucaslucenak.Demacia.repositories.DrinkRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DrinkRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private DrinkRequestRepository drinkRequestRepository;

    @Value("${aws.sqs.queues.drink.queue-url}")
    private String queueUrl;

    public SendMessageResult requestDrink(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-drink")
                .withMessageBody(body.toString());

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        DrinkRequestModel drinkRequestModel = new DrinkRequestModel();
        drinkRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        drinkRequestModel.setMessageId(sendMessageResult.getMessageId());
        drinkRequestModel.setBody(body.toString());
        drinkRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        drinkRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        drinkRequestRepository.save(drinkRequestModel);

        return sendMessageResult;
    }
}
