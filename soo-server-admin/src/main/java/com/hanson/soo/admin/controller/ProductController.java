package com.hanson.soo.admin.controller;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.vo.ProductVO;
import com.hanson.soo.admin.service.ProductDetailService;
import com.hanson.soo.admin.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductDetailService productDetailService;


    @PostMapping("/detail")
    public ProductDetailDTO query(@RequestBody String productId){
        System.out.println(productId);
        ProductDetailDTO productDetailDTO = productDetailService.getProductDetailByProductId(productId);
        return productDetailDTO;
    }


    @GetMapping("/info")
    public PageListDTO<List<ProductInfoDTO>> query(@RequestParam("current") int current,
                                                   @RequestParam("pageSize")int pageSize,
                                                   @ModelAttribute ProductInfoDTO productInfoDTO){
        System.out.println(current + " " + pageSize);
        System.out.println(productInfoDTO);
        PageListDTO<List<ProductInfoDTO>> res = productInfoService.listProductInfos(current,pageSize, productInfoDTO);
        return res;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody ProductVO productVO){
        System.out.println(productVO);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productVO, productInfoDTO);
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        BeanUtils.copyProperties(productVO, productDetailDTO);
        System.out.println(productInfoDTO);
        productInfoService.updateByProductId(productInfoDTO);
        System.out.println(productDetailDTO);
        productDetailService.updateByProductId(productDetailDTO);
        return true;
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody List<String> productIds){
        System.out.println(productIds);
        productInfoService.deleteByProductId(productIds);
        productDetailService.deleteByProductId(productIds);
        return true;
    }

    @PostMapping("/add")
    public boolean insert(@RequestBody ProductVO productVO){
        System.out.println(productVO);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productVO, productInfoDTO);
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        BeanUtils.copyProperties(productVO, productDetailDTO);
        productInfoService.insert(productInfoDTO);
        productDetailService.insert(productDetailDTO);
        return true;
    }
}
