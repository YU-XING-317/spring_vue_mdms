package com.mdms.yiliao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mdms.yiliao.entity.Device;
import com.mdms.yiliao.entity.DeviceFeature;
import com.mdms.yiliao.entity.Did;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


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

    //查找指定id设备的'设备维保状态','是否超期未保','检验状态','检验结果'
    @Select("select did,deviceState,isOverdue,isChecked,checkResult from device where did=#{did}")
    public DeviceFeature listDevice(String did);

    //更新指定id的isRisk字段
    @Select("update device set isRisk=#{isRisk} where did=#{did}")
    public Integer updateDeviceRisk(@Param("did") String did,@Param("isRisk") Integer isRisk);

    //更新指定id的isChecked字段和checkResult字段
    @Select("update device set isChecked=#{isChecked},checkResult=#{checkResult} where did=#{did}")
    public Integer updateDeviceCheck(@Param("did") String did,@Param("isChecked") String isChecked,@Param("checkResult") String checkResult);

    //更新指定id的deviceState字段和isOverdue字段
    @Select("update device set deviceState=#{deviceState},isOverdue=#{isOverdue} where did=#{did}")
    public Integer updateDeviceState(@Param("did") String did,@Param("deviceState") String deviceState,@Param("isOverdue") String isOverdue);

    //查找所有设备的'设备维保状态','是否超期未保','检验状态','检验结果','设备风险状态'
    @Select("select did,deviceState,isOverdue,isChecked,checkResult,isRisk from device")
    public List<DeviceFeature> listAllDeviceRisk();

    //获取所有设备id
    @Select("select did from device")
    public List<Did> listAllDeviceId();

    //获取所有设备id、设备名称、设备类型、供应商、所属医院、所属科室
    @Select("select did,dname,dtype,supplier,hospital,department from device")
    public List<Device> listAllDevice();
}
