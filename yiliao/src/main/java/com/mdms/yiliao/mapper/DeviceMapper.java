package com.mdms.yiliao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mdms.yiliao.entity.Device;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
  // @Insert("insert into device values (#{did}, #{dname}, #{dtype}, #{supplier}, #{hospital}, #{department})")
  // public int addDevice(Device device);

  // @Select("select * from device")
  // public List<Device> selectAll();

  // @Select("select * from device where (dname is null or dname like concat('%',#{dname},'%')) and (dtype is null or dtype = #{dtype}) and (supplier is null or supplier like concat('%',#{supplier},'%'))")
  // public List<Device> getDeviceByKeys(Device device);

  // @Update("update device set dname=#{dname}, dtype=#{dtype}, supplier=#{supplier}, hospital=#{hospital}, department=#{department} where did=#{dId}")
  // public int updateDevice(Device device);

  // @Select("select * from device where did=#{dId}")
  // public Device getDeviceById(Integer dId);
}
