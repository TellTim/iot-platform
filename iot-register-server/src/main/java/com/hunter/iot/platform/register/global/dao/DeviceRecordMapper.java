package com.hunter.iot.platform.register.global.dao;

import com.hunter.iot.platform.register.global.dao.DeviceRecord;
import com.hunter.iot.platform.register.global.dao.DeviceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DeviceRecordMapper {
    long countByExample(DeviceRecordExample example);

    int deleteByExample(DeviceRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeviceRecord record);

    int insertSelective(DeviceRecord record);

    List<DeviceRecord> selectByExampleWithRowbounds(DeviceRecordExample example, RowBounds rowBounds);

    List<DeviceRecord> selectByExample(DeviceRecordExample example);

    DeviceRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeviceRecord record, @Param("example") DeviceRecordExample example);

    int updateByExample(@Param("record") DeviceRecord record, @Param("example") DeviceRecordExample example);

    int updateByPrimaryKeySelective(DeviceRecord record);

    int updateByPrimaryKey(DeviceRecord record);
}