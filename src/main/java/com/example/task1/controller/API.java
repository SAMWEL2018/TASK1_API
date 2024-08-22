package com.example.task1.controller;


import com.example.task1.models.*;
import com.example.task1.service.AccountsService;
import com.example.task1.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class API {


    private final TransactionService transactionService;

    private final AccountsService accountsService;

    // Request Body To create account
    ///   {
    //    "name":"samwel wafula",
    //    "balance":1000.0,
    //    "idNumber":"38717160"
    //}

    @RequestMapping(value = "/api/createAccount", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {

        return ResponseEntity.ok(accountsService.createAccount(account));
    }

    // get account details
    // http://localhost:8082/api/getAccount/12345

    @RequestMapping(value = "/api/getAccount/{idNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserAccount(@PathVariable("idNumber") String idNumber) {
        try {
            Account acc = accountsService.getAccountUsingIdNumber(idNumber);
            if (acc != null) {
                return ResponseEntity.status(200).body(CustomResponse.builder().responseCode(200).responseDesc("Account found").account(acc).build());
            } else {
                return ResponseEntity.status(200).body(CustomResponse.builder().responseCode(200).responseDesc("Account not found").account(null).build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(CustomResponse.builder().responseCode(500).responseDesc("Internal Server Error " + e.getMessage()).account(null).build());
        }
    }

    //transfer amount from one account to another  :

    ///{
    //    "sender_account_no":"133469",
    //    "receiver_account_no":"242698",
    //    "amount":"100"
    //}

    @PostMapping(value = "/api/transfer")
    public ResponseEntity<ProcessingResp> transferFunds(@RequestBody Optional<Transactions> tsn) {
        if (tsn.isPresent()) {

            try {
                return ResponseEntity.ok(transactionService.ProcessTransaction(tsn.get(), TransactionType.TRANSFER));
            } catch (Exception e) {
                log.error("Error In Transfer: {}", e.getMessage());
                return ResponseEntity.status(500).body(
                        ProcessingResp.builder()
                                .status("FAILED")
                                .responseMessage("Internal server error " + e.getMessage())
                                .responseCode("003")
                                .build()
                );
            }

        } else {
            return ResponseEntity.badRequest().body(ProcessingResp.builder()
                    .status("FAILED")
                    .responseMessage("Internal server error")
                    .responseCode("003")
                    .build()
            );
        }
    }

}
