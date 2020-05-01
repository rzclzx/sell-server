package com.imooc.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageResult implements Serializable {

    private static final long serialVersionUID = -4227807192090077367L;

    private Integer pageNumber;

    private Integer size;

    private Long total;
}
