package com.example.task1.models;

import lombok.Builder;
import lombok.Data;

/**
 * @author samwel.wafula
 * Created on 21/08/2024
 * Time 10:13
 * Project TASK1
 */
@Builder
@Data
public class CustomResponse {

    private int responseCode;
    private String responseDesc;
    private Account account;
}
