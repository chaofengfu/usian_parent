package com.usian.controller;

import com.usian.feign.ItemServiceFeignClient;
import com.usian.utuils.CatNode;
import com.usian.utuils.CatResult;
import com.usian.utuils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCatgoryController {

    @Autowired
    private ItemServiceFeignClient itemServiceFeignClient;

    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll(){
        CatResult catResult = itemServiceFeignClient.selectItemCategoryAll();
       if(catResult.getData().size()>0){
           return Result.ok(catResult);
       }
       return Result.error("查无结果");
    }


}
