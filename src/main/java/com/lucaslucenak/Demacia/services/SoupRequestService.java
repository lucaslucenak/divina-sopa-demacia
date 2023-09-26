package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.SoupRequestModel;
import com.lucaslucenak.Demacia.repositories.SoupRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SoupRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private SoupRequestRepository soupRequestRepository;

    @Value("${aws.sqs.queues.soup.queue-url}")
    private String queueUrl;

    public SendMessageResult requestSoup(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-soup")
                .withMessageBody(body.toString());

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        SoupRequestModel soupRequestModel = new SoupRequestModel();
        soupRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        soupRequestModel.setMessageId(sendMessageResult.getMessageId());
        soupRequestModel.setBody(body.toString());
        soupRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        soupRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        soupRequestRepository.save(soupRequestModel);

        return sendMessageResult;
    }
}
