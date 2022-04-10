package com.hanson.soo.user.service;


import com.hanson.soo.user.pojo.dto.ConsigneeDTO;

import java.util.List;

public interface ConsigneeService {
    List<ConsigneeDTO> listConsigneesByUserId(String userId);
    boolean saveConsignee(String userId, ConsigneeDTO consigneeDTO);
    boolean deleteConsigneeById(Long id);
}
