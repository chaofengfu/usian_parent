package com.usian.service;

import com.usian.mapper.TbContentCategoryMapper;
import com.usian.pojo.TbContentCategory;
import com.usian.pojo.TbContentCategoryExample;
import com.usian.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService{

    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    //根据父节点id查询内容类目列表
    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long id) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        return tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
    }

    //添加内容目录节点
    @Override
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        //1.添加内容分类
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        Integer contentCategoryNum = tbContentCategoryMapper.insert(tbContentCategory);

        //2、如果他爹不是爹，要把他爹改成爹
        TbContentCategory contentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        System.out.println(contentCategory.getIsParent());
        System.out.println(contentCategory.getName());
        if(!contentCategory.getIsParent()){
            contentCategory.setIsParent(true);
            contentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        return contentCategoryNum;
    }

    @Override
    public Integer deleteContentCategoryById(Long categoryId) {
        //如果有子节点，则不能删除，直接返回一个0
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        if(tbContentCategory.getIsParent()==true){
            System.out.println("aaa");
            return 0;
        }

        //没有子节点则可以删除该节点
        int categoryNum = tbContentCategoryMapper.deleteByPrimaryKey(categoryId);

        //如果他爹不是爹，要把他爹给成不是爹
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(tbContentCategory.getParentId());
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        if(tbContentCategoryList.size()==0){
            TbContentCategory parenttbContentCategory = new TbContentCategory();
            parenttbContentCategory.setId(tbContentCategory.getParentId());
            parenttbContentCategory.setIsParent(false);
            parenttbContentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKeySelective(parenttbContentCategory);
        }
        System.out.println("bbb2");
        return 200;
    }

    //修改
    @Override
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }
}
