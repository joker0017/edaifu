package com.edaifu.web.dto;

import lombok.Data;

@Data
public class SysAccountBizDTO {
    private String accountId;
    private String password;
    private Integer status;
    private String name;
    private String rolesNames;
    private String orgName;
    private String groupNames;
    private String tel;
    private String phone;
    private String email;
    private String address;
    private Integer sex;

}
