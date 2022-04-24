package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.CartDO;
import com.hanson.soo.user.dao.CartDao;
import com.hanson.soo.user.pojo.dto.CartDTO;
import com.hanson.soo.user.service.CartService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Override
    @Transactional
    public boolean insertCart(CartDTO cartDTO) {
        CartDO cartDO = cartDao.selectOne(new LambdaQueryWrapper<CartDO>()
                .eq(CartDO::getProductId, cartDTO.getProductId())
                .eq(CartDO::getDeparture, cartDTO.getDeparture())
                .eq(CartDO::getUserId, cartDTO.getUserId()));
        if(cartDO != null){
            cartDO.setNum(cartDO.getNum() + cartDTO.getNum());
            return cartDao.updateById(cartDO) > 0;
        }
        return cartDao.insert(ConverterUtils.cartDTO2DO(cartDTO)) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO<List<CartDTO>> listCart(int current, int pageSize, String userId){
        IPage<CartDO> page = cartDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<CartDO>()
                .eq(CartDO::getUserId, userId)
                .orderByDesc(CartDO::getCreateTime));
        List<CartDTO> cartDTOs = page.getRecords().stream()
                .map(ConverterUtils::cartDO2DTO)
                .collect(Collectors.toList());
        return new PageDTO<>(cartDTOs, (int) page.getTotal());
    }

    @Override
    @Transactional
    public boolean deleteCartsByUserIdAndProductId(String userId, List<String> productIds){
        AtomicInteger count = new AtomicInteger(0);
        productIds.forEach(productId -> {
            count.addAndGet(cartDao.delete(new LambdaQueryWrapper<CartDO>()
                    .eq(CartDO::getUserId, userId)
                    .eq(CartDO::getProductId, productId)));
        });
        return count.get() == productIds.size();
    }

    @Override
    @Transactional
    public boolean updateCartByUserId(String userId, List<CartDTO> cartDTOS){
        cartDTOS.forEach(cartDTO ->
                cartDao.update(ConverterUtils.cartDTO2DO(cartDTO),new LambdaUpdateWrapper<CartDO>()
                        .eq(CartDO::getUserId, userId)
                        .eq(CartDO::getProductId, cartDTO.getProductId())));
        return true;
    }
}
