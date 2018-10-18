package com.starryfei.elastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starryfei.elastic.bean.Book;
import com.starryfei.elastic.dao.BookDao;

@RestController
public class SearchBookController {
    @Autowired
    private BookDao bookDao;
    
    @GetMapping(path="/search/{name}")
    @ResponseBody
    public List<Book> searchBookByName(@PathVariable("name")String name) {
        return bookDao.searchByName(name);
    }
}
