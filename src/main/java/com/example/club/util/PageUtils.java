package com.example.club.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


public class PageUtils {

    /**
     * 调用分页插件完成分页 并 封装
     *
     * @param pageRequest 分页请求对象
     * @return 分页返回对象
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageSelectFun fun) {

        //设置 页码/页距
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        //待插入的代码片段 即要查询的语句
        List<?> select = fun.select();

        //查询结果进行包装
        PageInfo<?> pageInfo = new PageInfo<>(select);

        //再将包装后的对象再封装到写好的返回对象 使返回内容更加明了
        PageResult pageResult = new PageResult();

        return getPageResult(pageInfo, pageResult);
    }

    private static PageResult getPageResult(PageInfo<?> pageInfo, PageResult pageResult) {
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());

        return pageResult;
    }

}