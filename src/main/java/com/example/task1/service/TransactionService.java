package com.example.task1.service;


import com.example.task1.models.Account;
import com.example.task1.models.ProcessingResp;
import com.example.task1.models.TransactionType;
import com.example.task1.models.Transactions;
import com.example.task1.repository.AccountRepo;
import com.example.task1.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {


    private final TransactionRepo transactionRepo;

    private final AccountRepo accountRepo;

    private final AccountsService accountsService;

    public List<Transactions> getTransactions(int transactionLimit, String accountId) {
        return transactionRepo.findTopTransactionById(accountId, transactionLimit);
    }

    public Transactions getSingleTransaction(String id) {
        return transactionRepo.findTransactionById(id).orElse(null);
    }

    public ProcessingResp ProcessTransaction(Transactions transactions, TransactionType transactionType) {


        return switch (transactionType) {
            case TRANSFER -> transferFunds(transactions);
        };
    }

    // deposit money to receiver account -- credit
    private synchronized void depositAccount(Account acct, double amount) {
        acct.setBalance((amount + acct.getBalance()) - acct.getDebit());
        acct.setCredit(acct.getCredit() + amount);
        accountRepo.save(acct);
    }

    //initiate withdrawal from the sender account -- debit
    private synchronized void withdrawAccount(double amount, Account acct) {

        acct.setBalance(acct.getBalance() - amount);
        acct.setDebit(acct.getDebit() + amount);
        accountRepo.save(acct);
    }

    // core function for transfer of funds
    public ProcessingResp transferFunds(Transactions transactions) {
        log.info("transaction amount {}", transactions.getAmount());
        ProcessingResp resp = null;

        Optional<Account> senderAccount = accountRepo.findById(transactions.getSenderAccountNo());
        Optional<Account> receiverAccount = accountRepo.findById(transactions.getReceiverAccountNo());

        if (senderAccount.isPresent() && receiverAccount.isPresent()) {

            double amount = Double.parseDouble(transactions.getAmount());

            //check if amount being transferred is greater than 0
            if (amount > 0) {

                // check if balance is greater than the amount being transferred
                if (senderAccount.get().getBalance() > amount) {

                    withdrawAccount(amount, senderAccount.get());

                    String id = LocalDateTime.now(ZoneOffset.UTC).getNano() + "";
                    log.info("Transaction ID : {}", id);

                    transactions.setTransactionID(id);
                    transactions.setTransactionType(TransactionType.TRANSFER.name());
                    transactionRepo.save(transactions);

                    depositAccount(receiverAccount.get(), amount);
                    resp = ProcessingResp.builder()
                            .status("SUCCESS")
                            .responseCode("000")
                            .responseMessage("SUCCESSFUL")
                            .transaction(transactions)
                            .build();

                } else {
                    resp = ProcessingResp.builder()
                            .status("FAILED")
                            .responseCode("004")
                            .responseMessage("Insufficient Funds")
                            .build();
                }
            } else {
                resp = ProcessingResp.builder()
                        .status("FAILED")
                        .responseCode("002")
                        .responseMessage("Invalid Amount")
                        .build();

            }
        } else {
            resp = ProcessingResp.builder()
                    .status("FAILED")
                    .responseCode("006")
                    .responseMessage("Account Not Found")
                    .build();
        }
        return resp;
    }

}
