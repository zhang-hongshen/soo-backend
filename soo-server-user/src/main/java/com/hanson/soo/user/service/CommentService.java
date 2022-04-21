package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.user.pojo.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    PageDTO<List<CommentDTO>> listCommentsByProductId(int current, int pageSize, String productId);
    boolean insertComment(CommentDTO commentDTO);
}
