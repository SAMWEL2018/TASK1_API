package com.example.task1.repository;


import com.example.task1.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepo extends JpaRepository<Account, String> {

    Account findByIdNumber(String idNumber);


}
