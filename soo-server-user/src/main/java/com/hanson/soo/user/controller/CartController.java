package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/query")
    public PageVO<List<CartVO>> query(@RequestParam("current") int current,
                              @RequestParam("pageSize") int pageSize,
                              @RequestAttribute("userId") String userId){
        PageDTO<List<CartDTO>> pageDTO = cartService.listCart(current, pageSize, userId);
        List<CartVO> cartVOS = pageDTO.getList().stream()
                .map(cartDTO -> {
                    CartVO cartVO = ConverterUtils.cartDTO2VO(cartDTO);
                    ProductInfoDTO productInfoDTO = productInfoService.getProductInfoByProductId(cartDTO.getProductId());
                    BeanUtils.copyProperties(productInfoDTO, cartVO);
                    return cartVO;
                }).collect(Collectors.toList());
        return new PageVO<>(cartVOS, pageDTO.getTotal());
    }

    @PostMapping("/add")
    public boolean add(@RequestAttribute("userId") String userId,
                       @RequestBody  CartVO cartVO){
        CartDTO cartDTO = ConverterUtils.cartVO2DTO(cartVO);
        cartDTO.setUserId(userId);
        return cartService.insertCart(cartDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestAttribute("userId") String userId,
                          @RequestBody  List<String> productIds){
        return cartService.deleteCartsByUserIdAndProductId(userId, productIds);
    }

    @PutMapping("/update")
    public boolean update(@RequestAttribute("userId") String userId,
                          @RequestBody  List<CartVO> cartVOS){
        List<CartDTO> cartDTOS = new ArrayList<>(cartVOS.size());
        cartVOS.forEach(cartVO -> {
            CartDTO cartDTO = ConverterUtils.cartVO2DTO(cartVO);
            cartDTO.setUserId(userId);
            cartDTOS.add(cartDTO);
        });
        return cartService.updateCartByUserId(userId, cartDTOS);
    }
}
