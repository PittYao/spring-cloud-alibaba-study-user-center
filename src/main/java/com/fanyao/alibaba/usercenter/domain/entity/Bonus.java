package com.fanyao.alibaba.usercenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bonus_event_log")
public class Bonus {
    /**
     * Id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    // 积分操作值
    @Column(name = "value")
    private Integer value;

    // 发生的事件
    @Column(name = "event")
    private String event;

    @Column(name = "create_time")
    private Date createTime;

    // 描述
    @Column(name = "description")
    private String description;

}