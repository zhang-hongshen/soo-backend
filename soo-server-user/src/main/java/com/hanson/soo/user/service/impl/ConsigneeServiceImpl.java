package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanson.soo.common.dao.ConsigneeDao;
import com.hanson.soo.common.pojo.entity.ConsigneeDO;
import com.hanson.soo.user.pojo.dto.ConsigneeDTO;
import com.hanson.soo.user.service.ConsigneeService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsigneeServiceImpl implements ConsigneeService {
    @Autowired
    private ConsigneeDao consigneeDao;

    @Override
    @Transactional(readOnly = true)
    public List<ConsigneeDTO> listConsigneesByUserId(String userId){
        List<ConsigneeDO> consigneeDOs = consigneeDao.selectList(new LambdaQueryWrapper<ConsigneeDO>()
                .eq(ConsigneeDO::getUserId, userId));
        List<ConsigneeDTO> consigneeDTOs = new ArrayList<>();
        consigneeDOs.forEach((consigneeDO -> consigneeDTOs.add(ConverterUtils.consigneeDO2DTO(consigneeDO))));
        return consigneeDTOs;
    }

    @Override
    @Transactional
    public int saveConsignee(String userId, ConsigneeDTO consigneeDTO) {
        ConsigneeDO consigneeDO =  consigneeDao.selectById(consigneeDTO.getId());
        if(consigneeDO == null){
            return consigneeDao.insert(ConverterUtils.consigneeDTO2DO(consigneeDTO));
        }
        BeanUtils.copyProperties(consigneeDTO, consigneeDO);
        return  consigneeDao.updateById(consigneeDO);
    }

    @Override
    @Transactional
    public int deleteConsigneeById(Long id){
        return consigneeDao.deleteById(id);
    }
}
