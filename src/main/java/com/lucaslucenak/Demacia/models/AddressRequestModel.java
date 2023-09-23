package com.lucaslucenak.Demacia.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "address_request")
@EntityListeners(AuditingEntityListener.class)
public class AddressRequestModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String requestId;

        private String messageId;

        private String body;

        private Integer httpStatus;

        private String md5OfMessageBody;

        public AddressRequestModel() {
        }

        public AddressRequestModel(Long id, String requestId, String messageId, String body, Integer httpStatus, String md5OfMessageBody) {
                this.id = id;
                this.requestId = requestId;
                this.messageId = messageId;
                this.body = body;
                this.httpStatus = httpStatus;
                this.md5OfMessageBody = md5OfMessageBody;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getRequestId() {
                return requestId;
        }

        public void setRequestId(String requestId) {
                this.requestId = requestId;
        }

        public String getMessageId() {
                return messageId;
        }

        public void setMessageId(String messageId) {
                this.messageId = messageId;
        }

        public String getBody() {
                return body;
        }

        public void setBody(String body) {
                this.body = body;
        }

        public Integer getHttpStatus() {
                return httpStatus;
        }

        public void setHttpStatus(Integer httpStatus) {
                this.httpStatus = httpStatus;
        }

        public String getMd5OfMessageBody() {
                return md5OfMessageBody;
        }

        public void setMd5OfMessageBody(String md5OfMessageBody) {
                this.md5OfMessageBody = md5OfMessageBody;
        }
}


