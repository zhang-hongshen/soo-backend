package com.hanson.soo.user.service;


import com.hanson.soo.user.pojo.dto.ConsigneeDTO;

import java.util.List;

public interface ConsigneeService {
    List<ConsigneeDTO> listConsigneeByUserId(String userId);
    int saveConsignee(String userId, ConsigneeDTO consigneeDTO);
    int deleteConsigneeById(Long id);
}
