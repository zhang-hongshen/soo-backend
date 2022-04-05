package com.hanson.soo.admin.controller;

import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.admin.pojo.vo.OrderInfoVO;
import com.hanson.soo.admin.service.OrderInfoService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/info")
    public PageListDTO<List<OrderInfoVO>> query(@RequestParam("current")int current, @RequestParam("pageSize")int pageSize,
                                                @RequestBody OrderInfoVO orderInfoVO) {
        PageListDTO<List<OrderInfoDTO>> pageListDTO = orderInfoService.listInfo(current, pageSize,
                ConverterUtils.orderInfoVO2DTO(orderInfoVO));
        List<OrderInfoVO> orderInfoVOs = new ArrayList<>();
        for (OrderInfoDTO orderInfoDTO : pageListDTO.getList()) {
            orderInfoVOs.add(ConverterUtils.orderInfoDTO2VO(orderInfoDTO));
        }
        return new PageListDTO<>(orderInfoVOs, pageListDTO.getTotal());
    }
}
