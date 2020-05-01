package com.imooc.dao;

import com.imooc.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDAO extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> queryOrderMasterByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
