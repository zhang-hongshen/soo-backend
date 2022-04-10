package com.hanson.soo.user.controller;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.CommentDTO;
import com.hanson.soo.user.pojo.vo.CommentVO;
import com.hanson.soo.user.service.CommentService;
import com.hanson.soo.user.service.UserInfoService;
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
    private UserInfoService userInfoService;

    @GetMapping("/query")
    public PageListDTO<List<CommentVO>> query(@RequestParam("current") int current,
                                              @RequestParam("pageSize") int pageSize,
                                              @RequestParam("productId") String productId){
        PageListDTO<List<CommentDTO>> pageListDTO = commentService.listCommentsByProductId(current, pageSize, productId);
        List<CommentVO> commentVOs = new ArrayList<>(pageListDTO.getList().size());
        for(CommentDTO commentDTO : pageListDTO.getList()){
            String username = userInfoService.getUserInfoByUserId(commentDTO.getUserId()).username;
            CommentVO commentVO = ConverterUtils.commentDTO2VO(commentDTO);
            commentVO.setUsername(username);
            commentVOs.add(commentVO);
        }
        return new PageListDTO<>(commentVOs, pageListDTO.getTotal());
    }

    @PostMapping("/add")
    public boolean add(@RequestBody CommentDTO commentDTO){
        return commentService.insertComment(commentDTO);
    }
}
