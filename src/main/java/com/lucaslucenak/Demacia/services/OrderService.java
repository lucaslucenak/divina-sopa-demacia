package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private AmazonSQS amazonSQS;

    @Value("${aws.sqs.order.queue-url}")
    private String queueUrl;

    public SendMessageResult requestOrder(Object order) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-order")
                .withMessageBody(order.toString());

        return amazonSQS.sendMessage(request);
    }
}
