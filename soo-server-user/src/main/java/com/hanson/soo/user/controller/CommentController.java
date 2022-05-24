package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.vo.PageVO;
import com.hanson.soo.user.pojo.dto.CommentDTO;
import com.hanson.soo.user.pojo.vo.CommentVO;
import com.hanson.soo.user.service.CommentService;
import com.hanson.soo.user.service.UserService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public PageVO<List<CommentVO>> query(@RequestParam("current") int current,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam("productId") String productId){
        PageDTO<List<CommentDTO>> pageDTO = commentService.listCommentsByProductId(current, pageSize, productId);
        List<CommentVO> commentVOs = pageDTO.getList().stream()
                .map(commentDTO -> {
                    CommentVO commentVO = ConverterUtils.commentDTO2VO(commentDTO);
                    commentVO.setUsername(userService.getUsernameByUserId(commentDTO.getUserId()));
                    return commentVO;
                }).collect(Collectors.toList());
        return new PageVO<>(commentVOs, pageDTO.getTotal());
    }

    @PostMapping("/add")
    public boolean add(@RequestAttribute("userId")String userId, @RequestBody CommentDTO commentDTO){
        commentDTO.setUserId(userId);
        return commentService.insertComment(commentDTO);
    }
}
