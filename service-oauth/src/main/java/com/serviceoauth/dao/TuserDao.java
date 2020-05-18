package com.serviceoauth.dao;

import com.serviceoauth.entity.userdb.Tuser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TuserDao extends Mapper<Tuser> {

    List<String> findPermissionsByUserId(@Param("id") Long id);
}