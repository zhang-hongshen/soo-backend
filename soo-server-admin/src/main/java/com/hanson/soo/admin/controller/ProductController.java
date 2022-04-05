package com.hanson.soo.admin.controller;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.vo.ProductInfoVO;
import com.hanson.soo.admin.pojo.vo.ProductVO;
import com.hanson.soo.admin.service.ProductDetailService;
import com.hanson.soo.admin.service.ProductInfoService;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.common.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductDetailService productDetailService;

    private static final BiMap<Boolean, String> statusMap = HashBiMap.create();

    static {
        statusMap.put(null, "全部");
        statusMap.put(Boolean.TRUE, "售卖中");
        statusMap.put(Boolean.FALSE, "已下架");
    }

    @PostMapping("/info")
    public PageListDTO<List<ProductInfoVO>> query(@RequestParam("current") int current,
                                                   @RequestParam("pageSize")int pageSize,
                                                   @RequestBody ProductInfoVO queryVO) {
        ProductInfoDTO queryDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(queryVO, queryDTO);
        queryDTO.setStatus(statusMap.inverse().get(queryVO.getStatus()));
        PageListDTO<List<ProductInfoDTO>> pageListDTO = productInfoService.listProductInfo(current,pageSize, queryDTO);
        List<ProductInfoVO> productInfoVOs = new ArrayList<>();
        for (ProductInfoDTO productInfoDTO : pageListDTO.getList()) {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfoDTO, productInfoVO);
            productInfoVO.setStatus(statusMap.get(productInfoDTO.getStatus()));
            productInfoVOs.add(productInfoVO);
        }
        return new PageListDTO<>(productInfoVOs, pageListDTO.getTotal());
    }

    @GetMapping("/detail")
    public ProductVO query(@RequestParam("productId") String productId) {
        ProductDetailDTO productDetailDTO = productDetailService.getProductDetailByProductId(productId);
        ProductInfoDTO productInfoDTO = productInfoService.getByProductId(productId);
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productDetailDTO, productVO);
        BeanUtils.copyProperties(productInfoDTO, productVO);
        productVO.setStatus(statusMap.get(productInfoDTO.getStatus()));
        return productVO;
    }

    @PutMapping("/update")
    public boolean update(@RequestBody ProductVO productVO) {
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productVO, productInfoDTO);
        productInfoDTO.setStatus(statusMap.inverse().get(productVO.getStatus()));
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        BeanUtils.copyProperties(productVO, productDetailDTO);
        productInfoService.updateByProductId(productInfoDTO);
        productDetailService.updateByProductId(productDetailDTO);
        return true;
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody List<String> productIds) {
        productInfoService.deleteByProductId(productIds);
        productDetailService.deleteByProductId(productIds);
        return true;
    }

    @PostMapping("/add")
    public boolean insert(@RequestBody ProductVO productVO) {
        String productId = UUIDUtils.getId();
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        BeanUtils.copyProperties(productVO, productInfoDTO);
        BeanUtils.copyProperties(productVO, productDetailDTO);
        productInfoDTO.setProductId(productId);
        productInfoDTO.setStatus(statusMap.inverse().get(productVO.getStatus()));
        productDetailDTO.setProductId(productId);
        productInfoService.insert(productInfoDTO);
        productDetailService.insert(productDetailDTO);
        return true;
    }
}
