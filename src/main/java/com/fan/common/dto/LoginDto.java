package com.fan.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data
//@Data是工具包，实现了loginDto.getUsername()
public class LoginDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

}
