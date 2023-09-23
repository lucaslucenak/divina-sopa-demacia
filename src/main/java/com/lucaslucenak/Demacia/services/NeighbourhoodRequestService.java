package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.NeighbourhoodRequestModel;
import com.lucaslucenak.Demacia.repositories.NeighbourhoodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NeighbourhoodRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private NeighbourhoodRequestRepository neighbourhoodRequestRepository;

    @Value("${aws.sqs.queues.neighbourhood.queue-url}")
    private String queueUrl;

    public SendMessageResult requestNeighbourhood(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-neighbourhood")
                .withMessageBody(body.toString());

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        NeighbourhoodRequestModel neighbourhoodRequestModel = new NeighbourhoodRequestModel();
        neighbourhoodRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        neighbourhoodRequestModel.setMessageId(sendMessageResult.getMessageId());
        neighbourhoodRequestModel.setBody(body.toString());
        neighbourhoodRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        neighbourhoodRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        neighbourhoodRequestRepository.save(neighbourhoodRequestModel);

        return sendMessageResult;
    }
}
