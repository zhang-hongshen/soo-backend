package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.user.dao.CommentDao;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.CommentDO;
import com.hanson.soo.user.pojo.dto.CommentDTO;
import com.hanson.soo.user.service.CommentService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    @Transactional(readOnly = true)
    public PageDTO<List<CommentDTO>> listCommentsByProductId(int current, int pageSize, String productId) {
        IPage<CommentDO> page = commentDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<CommentDO>()
                .eq(CommentDO::getProductId, productId)
                .orderByDesc(CommentDO::getCreateTime));
        List<CommentDTO> commentDTOs = page.getRecords().stream()
                .map(ConverterUtils::commentDO2DTO)
                .collect(Collectors.toList());
        return new PageDTO<>(commentDTOs, (int) page.getTotal());
    }

    @Override
    public boolean insertComment(CommentDTO commentDTO) {
        return commentDao.insert(ConverterUtils.commentDTO2DO(commentDTO)) > 0;
    }
}
