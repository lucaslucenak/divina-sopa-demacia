package com.lucaslucenak.Demacia.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.models.ClientAccountRequestModel;
import com.lucaslucenak.Demacia.repositories.ClientAccountRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientAccountRequestService {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private ClientAccountRequestRepository clientAccountRequestRepository;

    @Value("${aws.sqs.queues.client-account.queue-url}")
    private String queueUrl;

    public SendMessageResult requestClientAccount(Object body) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("save-client-account")
                .withMessageBody(body.toString());

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);

        ClientAccountRequestModel clientAccountRequestModel = new ClientAccountRequestModel();
        clientAccountRequestModel.setRequestId(sendMessageResult.getSdkResponseMetadata().getRequestId());
        clientAccountRequestModel.setMessageId(sendMessageResult.getMessageId());
        clientAccountRequestModel.setBody(body.toString());
        clientAccountRequestModel.setHttpStatus(sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        clientAccountRequestModel.setMd5OfMessageBody(sendMessageResult.getMD5OfMessageBody());

        clientAccountRequestRepository.save(clientAccountRequestModel);

        return sendMessageResult;
    }
}
