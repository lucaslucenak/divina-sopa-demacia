package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.AddressRequestModel;
import com.lucaslucenak.Demacia.repositories.AddressRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddressRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private AddressRequestRepository addressRequestRepository;

    @Value("${aws.sqs.queues.address.queue-url}")
    private String queueUrl;

    public SendMessageResult requestAddress(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-address")
                .withMessageBody(body.toString());

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        AddressRequestModel addressRequestModel = new AddressRequestModel();
        addressRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        addressRequestModel.setMessageId(sendMessageResult.getMessageId());
        addressRequestModel.setBody(body.toString());
        addressRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        addressRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        addressRequestRepository.save(addressRequestModel);

        return sendMessageResult;
    }
}
