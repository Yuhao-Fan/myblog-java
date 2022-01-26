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
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog")

public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "摘要不能为空")
    private String description;
    @NotBlank(message = "内容不能为空")
    private String content;
    //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;
    private LocalDateTime lastEdit;

    private Integer likenum;
    //不能有下划线
    private Integer dislikenum;
    private Integer collectnum;
    private Integer sharenum;
    private Integer status;

    @TableField(exist = false)
    private List<User> listUsers;
//    private Integer favorite;


}
