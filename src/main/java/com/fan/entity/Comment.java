package com.fan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
//    @NotEmpty(message = "blogId不能为空")
    private Long blogId;
//    @NotBlank(message = "userId不能为空")
    private Long userId;

    @NotBlank(message = "内容不能为空")
    private String content;

    private LocalDateTime created;

    private Integer status;

    @TableField(exist = false)
    private  String  username;
    @TableField(exist = false)
    private  String  avatar;


}
