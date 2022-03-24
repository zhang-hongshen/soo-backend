package com.hanson.soo.user.pojo.dto;

import lombok.Data;

@Data
public class ConsigneeDTO {
    Long id;
    String userId;
    String consigneeName;
    String phone;
    String address;
}
