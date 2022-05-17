package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.user.pojo.dto.ProductDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;
import com.hanson.soo.user.pojo.vo.ProductInfoVO;
import com.hanson.soo.user.pojo.vo.ProductVO;
import com.hanson.soo.user.service.ProductService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/query")
    public PageDTO<List<ProductInfoVO>> query(@RequestParam("current") int current,
                                              @RequestParam("pageSize") int pageSize,
                                              @ModelAttribute ProductQO query) {
        if (current <= 0) {
            throw new IllegalArgumentException();
        }
        PageDTO<List<ProductInfoDTO>> pageDTO = productService.listProductInfo(current, pageSize, query);
        List<ProductInfoVO> productInfoVOs = pageDTO.getList().stream()
                .map(ConverterUtils::productInfoDTO2VO)
                .collect(Collectors.toList());
        return new PageDTO<>(productInfoVOs, pageDTO.getTotal());
    }

    @GetMapping("/predict/{userId}")
    public List<ProductInfoVO> predict(@PathVariable("userId") String userId) {
        return productService.predict(userId)
                .stream()
                .map(ConverterUtils::productInfoDTO2VO)
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/{productId}")
    public ProductVO query(@PathVariable("productId")String productId) {
        ProductDTO productDTO = productService.getProductByProductId(productId);
        return ConverterUtils.productDTO2VO(productDTO);
    }
}
