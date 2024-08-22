package com.example.task1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tbl_transactions")
public class Transactions {

    @Id
    @Column(name = "transactionid", nullable = false, length = 50, unique = true)
    private String transactionID;


    @Basic
    @Column(name = "transactiontype", nullable = false, length = 50)
    private String transactionType;

    @Basic
    @Column(name = "amount", nullable = false, length = 50)
    private String amount;

    @Basic
    @JsonProperty("sender_account_no")
    @Column(name = "sender_account_no", nullable = false, length = 20)
    private String senderAccountNo;

    @Basic
    @JsonProperty("receiver_account_no")
    @Column(name = "receiver_account_no", nullable = true, length = 20)
    private String receiverAccountNo;

    @Basic
    @Column(name = "transaction_time", nullable = true, length = 20)
    @CreationTimestamp
    private Timestamp timeCreated;


}
