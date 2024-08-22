package com.example.task1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessingResp {
    private String status;
    private String responseMessage;
    private String responseCode;
    private Object transaction;
    private Object balance;
    private Object account;

}
