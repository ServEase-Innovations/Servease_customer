package com.cus.customertab.service;

import com.cus.customertab.dto.CustomerRequestCommentDTO;
import java.util.List;

public interface CustomerRequestCommentService {
    List<CustomerRequestCommentDTO> getAllComments();
    CustomerRequestCommentDTO getCommentById(Long id);
    String addComment(CustomerRequestCommentDTO commentDTO);
    String updateComment(Long id, CustomerRequestCommentDTO commentDTO);
    String deleteComment(Long id);
}
