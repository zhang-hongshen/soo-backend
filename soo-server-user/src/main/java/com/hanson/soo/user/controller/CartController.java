package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.common.pojo.vo.PageVO;
import com.hanson.soo.user.pojo.dto.CartDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.vo.CartVO;
import com.hanson.soo.user.service.CartService;
import com.hanson.soo.user.service.ProductInfoService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/query")
    public PageVO<List<CartVO>> query(@RequestParam("current") int current,
                              @RequestParam("pageSize") int pageSize,
                              @RequestParam("userId") String userId){
        if (current <= 0) {
            throw new IllegalArgumentException();
        }
        PageListDTO<List<CartDTO>> pageListDTO = cartService.listCarts(current, pageSize, userId);
        List<CartVO> cartVOS = new ArrayList<>(pageListDTO.getList().size());
        pageListDTO.getList().forEach(cartDTO -> {
            CartVO cartVO = ConverterUtils.cartDTO2VO(cartDTO);
            ProductInfoDTO productInfoDTO = productInfoService.getProductInfoByProductId(cartVO.getProductId());
            BeanUtils.copyProperties(productInfoDTO, cartVO);
            cartVOS.add(cartVO);
        });
        return new PageVO<>(cartVOS, pageListDTO.getTotal());
    }

    @PostMapping("/add/{userId}")
    public boolean add(@PathVariable("userId") String userId,
                       @RequestBody CartVO cartVO){
        if (cartVO == null) {
            return true;
        }
        CartDTO cartDTO = ConverterUtils.cartVO2DTO(cartVO);
        cartDTO.setUserId(userId);
        return cartService.insertCart(cartDTO);
    }

    @DeleteMapping("/delete/{userId}")
    public boolean delete(@PathVariable("userId") String userId,
                          @RequestBody  List<String> productIds){
        if (productIds == null || productIds.size() == 0) {
            return true;
        }
        return cartService.deleteCartsByUserIdAndProductId(userId, productIds);
    }

    @PutMapping("/update/{userId}")
    public boolean update(@PathVariable("userId") String userId,
                          @RequestBody  List<CartVO> cartVOS){
        if (cartVOS == null || cartVOS.size() == 0) {
            return true;
        }
        List<CartDTO> cartDTOS = new ArrayList<>(cartVOS.size());
        cartVOS.forEach(cartVO -> {
            CartDTO cartDTO = ConverterUtils.cartVO2DTO(cartVO);
            cartDTO.setUserId(userId);
            cartDTOS.add(cartDTO);
        });
        return cartService.updateCartByUserId(userId, cartDTOS);
    }
}
