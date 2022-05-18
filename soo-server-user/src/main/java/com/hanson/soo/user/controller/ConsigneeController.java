package com.hanson.soo.user.controller;

import com.hanson.soo.user.pojo.dto.ConsigneeDTO;
import com.hanson.soo.user.pojo.vo.ConsigneeVO;
import com.hanson.soo.user.service.ConsigneeService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consignee")
public class ConsigneeController {
    @Autowired
    private ConsigneeService consigneeService;

    @GetMapping("/info")
    public List<ConsigneeVO> query(@RequestAttribute("userId")String userId){
        List<ConsigneeDTO> consigneeDTOs = consigneeService.listConsigneesByUserId(userId);
        return consigneeDTOs.stream()
                .map(ConverterUtils::consigneeDTO2VO)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public boolean save(@RequestAttribute("userId") String userId,
                                 @RequestBody ConsigneeVO consigneeVO){
        ConsigneeDTO consigneeDTO = ConverterUtils.consigneeVO2DTO(consigneeVO);
        consigneeDTO.setUserId(userId);
        return consigneeService.saveConsignee(userId, consigneeDTO);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody Long id){
        return consigneeService.deleteConsigneeById(id);
    }
}
