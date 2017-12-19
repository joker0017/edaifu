package com.edaifu.web.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class SysOrgBizDTO {

    @JsonProperty("id")
    private String orgid;
    @JsonProperty("text")
    private String orgnm;
    private String porgid;
    private String email;
    private String remarks;
    private Timestamp createTime;
    @JsonProperty("children")
    private List<SysOrgBizDTO> childOrgs;
}
