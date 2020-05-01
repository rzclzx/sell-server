package com.imooc.VO;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

@Data
public class PageList implements Serializable {

    private static final long serialVersionUID = 6508784663340195498L;
    
    private Object data;

    private PageResult pageResult;
}
