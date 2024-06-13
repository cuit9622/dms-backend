package cuit9622.dms.dormRepair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class DormRepair {

    @TableId(type = IdType.AUTO)
    private Long repairId;

    private Long userId;

    private String username;

    private String phone;

    private String dormName;

    private String repairText;

    private Date repairTime;

    private String status; // 0:待维修  1:已维修

}
