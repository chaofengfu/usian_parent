package com.usian.service;

import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemCat;
import com.usian.utuils.CatNode;
import com.usian.utuils.CatResult;
import com.usian.utuils.PageResult;

import java.util.List;

public interface ItemCatService {
    //根据id查询商品类目
    List<TbItemCat> selectItemCategoryByParentId(Long id);

    CatResult selectItemCategoryAll();
}
