package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    PageListDTO<List<CommentDTO>> listByProductId(int current, int pageSize, String productId);
    int add(CommentDTO commentDTO);
}
