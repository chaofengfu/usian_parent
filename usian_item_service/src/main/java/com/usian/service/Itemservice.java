package com.usian.service;

import com.usian.pojo.TbItem;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;

import java.util.Map;

public interface Itemservice {

    //查询所有商品
    TbItem selectItemInfo(Long itemId);

    //分页查询所有商品
    PageResult selectTbItemAllByPage(Integer page, Long rows);

    Integer insertTbItem(TbItem tbItem, String desc, String itemParams);

    Map<String, Object> preUpdateItem(Long itemId);

    Integer updateTbItem(TbItem tbItem, String desc, String itemParams);

    Integer deleteItemById(Long itemId);
}
