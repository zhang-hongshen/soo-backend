package com.hanson.soo.admin.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdminDTO {
    List<String> role;
    String introduction;
    String avatar;
    String name;
}
