package cn.crabapples.system.sysArea.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 全国省市区
 */
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data(staticConstructor = "create")
@Accessors(chain = true)
@TableName(value = "sys_area")
//@NoArgsConstructor
public class SysArea extends Model<SysArea> {
    @TableId
    private Integer id;
    private String code;
    private String name;
    private Integer lvl;
    private String parentCode;
    @TableField(exist = false)
    private List<SysArea> children;
}
