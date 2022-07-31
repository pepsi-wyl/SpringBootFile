package com.pepsiwyl.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 16:33
 */

@Builder
@Accessors(chain = true)

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Alias("file")
@TableName(schema = "file_boot", value = "t_file")
public class File {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String oldFilename;
    private String newFilename;
    private String path;
    private String size;
    private String ext;
    private String type;
    private Integer downCount;
    private Date uploadTime;
    private String isImg;

    // 用户外键
    private Integer userId;

}
