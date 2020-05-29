package com.usian.controller;

import com.usian.feign.ContentServiceFeign;
import com.usian.pojo.TbContent;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/content")
public class ContentController {

    @Autowired
    private ContentServiceFeign contentServiceFeign;

    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "30") Integer rows, Long categoryId){
        PageResult pageResult = contentServiceFeign.selectTbContentAllByCategoryId(page,rows,categoryId);

        if(pageResult!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
            return Result.error("查询失败");
    }

    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
       Integer tbContentNum = contentServiceFeign.insertTbContent(tbContent);
        if (tbContentNum==1) {
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
    Integer num =   contentServiceFeign.deleteContentByIds(ids);
        if (num==1) {
            return Result.ok();
        }
        return Result.error("删除失败");
    }


}
