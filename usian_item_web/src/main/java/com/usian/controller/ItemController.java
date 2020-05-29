package com.usian.controller;

import com.usian.feign.ItemServiceFeignClient;
import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemDesc;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/backend/item")
public class ItemController {

    @Autowired
    private ItemServiceFeignClient itemServiceFeignClient;

    /*
        查询商品详情
     */
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId){
        TbItem tbItem = itemServiceFeignClient.selectItemInfo(itemId);
        if(tbItem != null){
            return Result.ok(tbItem);
        }
        return Result.error("查无结果");
    };

    /*
     * 查询所有商品，并分页。
     */
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1")Integer page,
                                        @RequestParam(defaultValue = "2")Long rows){
       PageResult pageResult = itemServiceFeignClient.selectTbItemAllByPage(page,rows);
       if(pageResult.getResult() != null && pageResult.getResult().size() > 0){
           return Result.ok(pageResult);
       }
        return Result.error("查无结果");
    }

    /**
     * 添加商品
     */
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem, String desc,String itemParams){
       Integer result = itemServiceFeignClient.insertTbItem(tbItem,desc,itemParams);
        if(result==3){
            return Result.ok();
        }
        return Result.error("保存失败");
    }
    //删除
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId){
        Integer num = itemServiceFeignClient.deleteItemById(itemId);
        if(num==1){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    /**
     * 根据itemId回显商品信息
     * @param itemId
     * @return
     */
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId){
        Map<String,Object> map = itemServiceFeignClient.preUpdateItem(itemId);
        if(map.size()>0){
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }


    //修改
    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams){
       Integer num = itemServiceFeignClient.updateTbItem(tbItem,desc,itemParams);
        if(num==3){
            return Result.ok();
        }
        return Result.error("修改失败");
    }
}
