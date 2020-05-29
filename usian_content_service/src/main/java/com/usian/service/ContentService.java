package com.usian.service;

import com.usian.pojo.TbContent;
import com.usian.utuils.AdNode;
import com.usian.utuils.PageResult;
import com.usian.utuils.Result;

import java.util.List;

public interface ContentService {
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Integer insertTbContent(TbContent tbContent);

    Integer deleteContentByIds(Long ids);

    List<AdNode> selectFrontendContentByAD();
}
