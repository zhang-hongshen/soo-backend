package com.hanson.soo.user.service.impl;

import com.hanson.soo.common.dao.CityDao;
import com.hanson.soo.common.pojo.entity.CityDO;
import com.hanson.soo.user.pojo.dto.CityDTO;
import com.hanson.soo.user.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<CityDTO> query() {
        List<CityDO> cityDOs = cityDao.selectList(null);
        return null;
    }
}
