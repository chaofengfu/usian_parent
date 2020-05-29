package com.usian.controller;

import com.usian.feign.ContentServiceFeign;
import com.usian.pojo.TbContentCategory;
import com.usian.utuils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backend/content")
public class ContentCategoryController {

    @Autowired
    private ContentServiceFeign contentServiceFeign;

    //根据id查询类别
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id) {

        List<TbContentCategory> tbContentCategoryList = contentServiceFeign.selectContentCategoryByParentId(id);
        if (tbContentCategoryList != null && tbContentCategoryList.size() > 0) {
            return Result.ok(tbContentCategoryList);
        }
        return Result.error("查无结果");
    }

    //添加分类内容
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(TbContentCategory tbContentCategory) {
        Integer contentCategoryNum = contentServiceFeign.insertContentCategory(tbContentCategory);
        if (contentCategoryNum == 1) {
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    //根据id删除内容分类节点
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(Long categoryId) {
        Integer status = contentServiceFeign.deleteContentCategoryById(categoryId);
        if (status == 200) {
            return Result.ok();
        }
        return Result.error("删除失败");
    }
    //根据id修改内容分类
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(TbContentCategory tbContentCategory){
        Integer contentCategoryNum = contentServiceFeign.updateContentCategory(tbContentCategory);
        if (contentCategoryNum == 1) {
            return Result.ok();
        }
        return Result.error("修改失败");
    }

}
