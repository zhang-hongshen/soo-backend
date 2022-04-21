package com.hanson.soo.admin.controller;

import com.hanson.soo.admin.pojo.ProductStatusEnum;
import com.hanson.soo.admin.pojo.dto.ProductDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.qo.ProductQO;
import com.hanson.soo.admin.pojo.vo.ProductInfoVO;
import com.hanson.soo.admin.pojo.vo.ProductVO;
import com.hanson.soo.admin.service.ProductService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/info")
    public PageVO<List<ProductInfoVO>> query(@RequestParam("current") int current,
                                              @RequestParam("pageSize")int pageSize,
                                              @RequestBody ProductQO productQO) {
        PageDTO<List<ProductInfoDTO>> pageDTO = productService.listProductInfo(current,pageSize, productQO);
        List<ProductInfoVO> productInfoVOs = new ArrayList<>();
        for (ProductInfoDTO productInfoDTO : pageDTO.getList()) {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfoDTO, productInfoVO);
            productInfoVO.setStatus(ProductStatusEnum.getValueByStatus(productInfoDTO.getStatus()));
            productInfoVOs.add(productInfoVO);
        }
        return new PageVO<>(productInfoVOs, pageDTO.getTotal());
    }

    @GetMapping("/detail/{productId}")
    public ProductVO query(@PathVariable("productId")String productId) {
        ProductDTO productDTO = productService.getProductByProductId(productId);
        ProductVO productVO = ConverterUtils.productDTO2VO(productDTO);
        productVO.setStatus(ProductStatusEnum.getValueByStatus(productDTO.getStatus()));
        return productVO;
    }

    @PutMapping("/update")
    public boolean update(@RequestBody ProductVO productVO) {
        ProductDTO productDTO = ConverterUtils.productVO2DTO(productVO);
        productDTO.setStatus(ProductStatusEnum.getStatusByValue(productVO.getStatus()));
        return productService.updateProductByProductId(productDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody List<String> productIds) {
        return productService.deleteByProductIds(productIds);
    }

    @PostMapping("/add")
    public boolean insert(@RequestBody ProductVO productVO) {
        ProductDTO productDTO = ConverterUtils.productVO2DTO(productVO);
        productDTO.setStatus(ProductStatusEnum.getStatusByValue(productVO.getStatus()));
        return productService.insert(productDTO);
    }
}
