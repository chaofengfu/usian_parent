package com.usian.controller;

import com.usian.pojo.TbItemParam;
import com.usian.service.ItemParamService;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;
    //查询商品规格模板
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        return this.itemParamService.selectItemParamByItemCatId(itemCatId);
    }
    /**
     * 查询所有商品规格模板
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(Integer page, Integer rows){
        return this.itemParamService.selectItemParamAll(page,rows);
    }

    //添加商品规格
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(Long itemCatId, String paramData){
      return itemParamService.insertItemParam(itemCatId,paramData);
    }

    //删除商品规格
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(Long id){
        return itemParamService.deleteItemParamById(id);
    }
}
