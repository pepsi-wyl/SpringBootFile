package com.pepsiwyl.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.*;
import org.apache.ibatis.type.Alias;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 14:37
 */

@Builder
@Accessors(chain = true)

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Alias("user")
@TableName(schema = "file_boot", value = "t_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

}
