package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.user.pojo.dto.CommentDTO;
import com.hanson.soo.user.pojo.vo.CommentVO;
import com.hanson.soo.user.service.CommentService;
import com.hanson.soo.user.service.UserService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public PageDTO<List<CommentVO>> query(@RequestParam("current") int current,
                                          @RequestParam("pageSize") int pageSize,
                                          @RequestParam("productId") String productId){
        if (current <= 0) {
            throw new IllegalArgumentException();
        }
        PageDTO<List<CommentDTO>> pageDTO = commentService.listCommentsByProductId(current, pageSize, productId);
        List<CommentVO> commentVOs = new ArrayList<>(pageDTO.getList().size());
        for(CommentDTO commentDTO : pageDTO.getList()){
            String username = userService.getUserInfoByUserId(commentDTO.getUserId()).username;
            CommentVO commentVO = ConverterUtils.commentDTO2VO(commentDTO);
            commentVO.setUsername(username);
            commentVOs.add(commentVO);
        }
        return new PageDTO<>(commentVOs, pageDTO.getTotal());
    }

    @PostMapping("/add")
    public boolean add(@RequestBody CommentDTO commentDTO){
        return commentService.insertComment(commentDTO);
    }
}
