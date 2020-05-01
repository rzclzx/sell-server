package com.imooc.util;

import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

public class ListMap {

    public static void listMapToString(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
