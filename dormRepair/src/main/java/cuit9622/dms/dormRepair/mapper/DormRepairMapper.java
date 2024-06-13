package cuit9622.dms.dormRepair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.dormRepair.entity.DormRepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DormRepairMapper extends BaseMapper<DormRepair> {

    int getRoleId(@Param("userId")Long userId);


    Page<DormRepair> page(@Param("page")Page<DormRepair> pageInfo,@Param("name") String dormName);
}
