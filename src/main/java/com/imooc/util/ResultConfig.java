package com.imooc.util;

import com.imooc.VO.PageList;
import com.imooc.VO.PageResult;
import com.imooc.VO.Result;
import com.imooc.enums.ResultEnum;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

public class ResultConfig {

    public static Result success(Object obj) {

        Result result = new Result();

        result.setCode(0);

        result.setMsg("success");

        result.setData(obj);

        return result;
    }

    public static Result error(Integer code, String msg) {

        Result result = new Result();

        result.setCode(code);

        result.setMsg(msg);

        return result;
    }

    public static PageList getPageList(Page page) {

        PageList pageList = new PageList();

        pageList.setData(page.getContent());

        PageResult pageResult = new PageResult();

        pageResult.setPageNumber(page.getPageable().getPageNumber());

        pageResult.setSize(page.getPageable().getPageSize());

        pageResult.setTotal(page.getTotalElements());

        pageList.setPageResult(pageResult);

        return pageList;
    }

    public static Result getRequestBodyError(BindingResult bindingResult) {

        return ResultConfig.error(ResultEnum.FORM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
    }
}
