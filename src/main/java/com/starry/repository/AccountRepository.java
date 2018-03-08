package com.starry.repository;

import com.starry.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>,PagingAndSortingRepository<Account,Long> {
    Account findOneById(Long id);
}
