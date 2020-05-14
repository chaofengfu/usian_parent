package com.usian.controller;

import com.usian.pojo.TbItem;
import com.usian.service.Itemservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private Itemservice itemservice;

    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(Long itemId){
        return itemservice.selectItemInfo(itemId);
    }
}
