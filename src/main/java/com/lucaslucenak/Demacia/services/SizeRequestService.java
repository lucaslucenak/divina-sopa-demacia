package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.SizeRequestModel;
import com.lucaslucenak.Demacia.repositories.SizeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SizeRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private SizeRequestRepository sizeRequestRepository;

    @Value("${aws.sqs.queues.size.queue-url}")
    private String queueUrl;

    public SendMessageResult requestSize(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-size")
                .withMessageBody(body.toString());

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        SizeRequestModel sizeRequestModel = new SizeRequestModel();
        sizeRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        sizeRequestModel.setMessageId(sendMessageResult.getMessageId());
        sizeRequestModel.setBody(body.toString());
        sizeRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        sizeRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        sizeRequestRepository.save(sizeRequestModel);

        return sendMessageResult;
    }
}
