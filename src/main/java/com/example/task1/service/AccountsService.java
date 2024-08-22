package com.example.task1.service;

import com.example.task1.models.Account;
import com.example.task1.models.ProcessingResp;
import com.example.task1.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsService {


    private final AccountRepo accountRepo;


    // create account

    public ProcessingResp createAccount(Account account) {


        account.setAccountNo(((int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000)) + "");
        account.setDebit(0);
        account.setCredit(0);
        account.setBalance(account.getBalance());
        account.setName(account.getName());
        account.setIdNumber(account.getIdNumber());


        Account acc = accountRepo.save(account);

        return ProcessingResp.builder()
                .status("SUCCESS")
                .responseMessage("successful")
                .responseCode("000")
                .account(acc)
                .build();

    }

    // get account info of AccountHolder
    public Account getAccountUsingIdNumber(String idNumber) {
        return accountRepo.findByIdNumber(idNumber);
    }


}
