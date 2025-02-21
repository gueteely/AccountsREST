package com.spring.boot.application.accounts.repository;

import com.spring.boot.application.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
