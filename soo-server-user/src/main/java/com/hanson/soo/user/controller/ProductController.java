package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageListDTO;
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

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/query")
    public PageListDTO<List<ProductInfoVO>> query(@RequestParam("current") int current,
                                                    @RequestParam("pageSize") int pageSize,
                                                    @ModelAttribute ProductQO query) {
        PageListDTO<List<ProductInfoDTO>> pageListDTO = productService.listProductInfos(current, pageSize, query);
        List<ProductInfoVO> productInfoVOs = new ArrayList<>(pageListDTO.getList().size());
        for (ProductInfoDTO productInfoDTO : pageListDTO.getList()) {
            productInfoVOs.add(ConverterUtils.productInfoDTO2VO(productInfoDTO));
        }
        return new PageListDTO<>(productInfoVOs, pageListDTO.getTotal());
    }

    @GetMapping("/predict")
    public List<ProductInfoVO> predict(@RequestParam("userId")String userId) {
        List<ProductInfoDTO> productInfoDTOs = productService.predict(userId);
        List<ProductInfoVO> productInfoVOs = new ArrayList<>(productInfoDTOs.size());
        for (ProductInfoDTO productInfoDTO : productInfoDTOs) {
            productInfoVOs.add(ConverterUtils.productInfoDTO2VO(productInfoDTO));
        }
        return  productInfoVOs;
    }

    @GetMapping("/detail")
    public ProductVO query(@RequestParam("productId") String productId) {
        ProductDTO productDTO = productService.getProductByProductId(productId);
        return ConverterUtils.productDTO2VO(productDTO);
    }
}
