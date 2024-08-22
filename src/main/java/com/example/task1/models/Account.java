package com.example.task1.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_accounts")
public class Account {

    @Id
    @Column(name = "account_no", nullable = false, length = 50)
    private String accountNo;

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "debit", nullable = false, length = 50)
    private double debit;

    @Basic
    @Column(name = "credit", nullable = false, length = 50)
    private double credit;

    @Basic
    @Column(name = "balance", nullable = false, length = 50)
    private double balance;

    @Basic
    @Column(name = "id_number", nullable = false, length = 50)
    private String idNumber;


}
