package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.OrderRequestModel;
import com.lucaslucenak.Demacia.repositories.OrderRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Value("${aws.sqs.order.url}")
    private String queueUrl;

    public SendMessageResult requestOrder(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-order")
                .withMessageBody(body.toString());
        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        OrderRequestModel orderRequestModel = new OrderRequestModel();
        orderRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        orderRequestModel.setMessageId(sendMessageResult.getMessageId());
        orderRequestModel.setBody(body.toString());
        orderRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        orderRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        orderRequestRepository.save(orderRequestModel);

        return sendMessageResult;
    }
}
