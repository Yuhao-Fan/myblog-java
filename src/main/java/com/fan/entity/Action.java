package com.fan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
@TableName("blog_action")

public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @NotBlank(message = "标题不能为空")
    private Long userId;
    @NotBlank(message = "标题不能为空")
    private Long blogId;
    private Integer actionMode;
    private Boolean isLike;
    private Boolean isDislike;
    private Boolean isCollection;
    private Boolean isShare;
//    private Boolean isLike;


}
