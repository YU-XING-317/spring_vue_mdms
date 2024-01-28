package com.mdms.yiliao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mdms.yiliao.entity.Employer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployerMapper extends BaseMapper<Employer> {
  //  @Select("select * from employer where eid=#{eId}")
  // public Employer getEmployerById(Integer eId);
}
