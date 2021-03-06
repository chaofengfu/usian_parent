package com.usian.feign;

import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utuils.AdNode;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("usian-content-service")
public interface ContentServiceFeign {

    @RequestMapping("/service/content/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id);

    @RequestMapping("/service/content/insertContentCategory")
    Integer insertContentCategory(TbContentCategory tbContentCategory);

    @RequestMapping("/service/content/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam Long categoryId);

    @RequestMapping("/service/content/updateContentCategory")
    Integer updateContentCategory(TbContentCategory tbContentCategory);

    @RequestMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam Integer page,@RequestParam Integer rows,@RequestParam Long categoryId);

    @RequestMapping("/service/content/insertTbContent")
    Integer insertTbContent(TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam Long ids);

    @RequestMapping("/service/content/selectFrontendContentByAD")
    List<AdNode> selectFrontendContentByAD();
}
