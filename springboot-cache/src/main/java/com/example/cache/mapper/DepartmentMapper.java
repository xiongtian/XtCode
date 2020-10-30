package com.example.cache.mapper;

import com.example.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiongtian
 * @create 2020/10/6-19:02
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM  department WHERE id =#{id}")
    Department getDeptById(Integer id);
}
