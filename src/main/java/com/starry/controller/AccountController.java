package com.starry.controller;


import com.starry.entity.Account;
import com.starry.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @RequestMapping("account")
    @ResponseBody
    public Page<Account> getAccount(@RequestParam(value = "page",defaultValue = "1") int currentPage,@RequestParam( value = "size",defaultValue = "5") int pageSize){
        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(idOrder);
        PageRequest pageRequest  = new PageRequest(currentPage, pageSize, sort);
        Page<Account> account = accountRepository.findAll(pageRequest);
       return account;
    }
}
