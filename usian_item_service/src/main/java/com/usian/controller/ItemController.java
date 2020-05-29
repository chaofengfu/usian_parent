package com.usian.controller;

import com.usian.pojo.TbItem;
import com.usian.service.Itemservice;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private Itemservice itemservice;

    //查询所有商品
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(Long itemId){
        return itemservice.selectItemInfo(itemId);
    }

    //分页查询所有商品
    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(Integer page,Long rows) {
        return itemservice.selectTbItemAllByPage(page, rows);
    }

    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem, String desc, String itemParams){
        return itemservice.insertTbItem(tbItem,desc,itemParams);
    }

    //删除
    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(Long itemId){
        return itemservice.deleteItemById(itemId);
    }

    @RequestMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(Long itemId){
        return itemservice.preUpdateItem(itemId);
    }

    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem, String desc, String itemParams){
        return itemservice.updateTbItem(tbItem,desc,itemParams);
    }
}

