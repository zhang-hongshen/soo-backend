package com.hanson.soo.user.controller;

import com.hanson.soo.user.pojo.dto.ProductDetailDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.service.ProductDetailService;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.service.ProductInfoService;
import com.hanson.soo.user.service.RecommendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private RecommendService recommendService;
    @GetMapping("/query")
    public PageListDTO<List<ProductInfoDTO>> query(@RequestParam("current") int current,
                                                    @RequestParam("pageSize") int pageSize,
                                                    @ModelAttribute ProductInfoDTO productInfoDTO){
        System.out.println(productInfoDTO);
        PageListDTO<List<ProductInfoDTO>> pageListDTO = productInfoService.listInfo(current, pageSize, productInfoDTO);
        return pageListDTO;
    }

    @GetMapping("/predict")
    public List<ProductInfoDTO> predict(@RequestParam("userId")String userId){
        List<String> productIds = recommendService.predict(userId);
        List<ProductInfoDTO> productInfoDTOs = productInfoService.listInfoByProductId(productIds);
        return  productInfoDTOs;
    }

    @GetMapping("/detail")
    public ProductDetailDTO query(@RequestParam("productId") String productId){
        System.out.println(productId);
        ProductDetailDTO productDetailDTO = productDetailService.getProductDetailByProductId(productId);
        return productDetailDTO;
    }
}
