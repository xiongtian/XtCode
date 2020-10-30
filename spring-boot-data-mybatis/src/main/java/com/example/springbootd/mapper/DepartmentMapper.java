package com.example.springbootd.mapper;

import com.example.springbootd.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author xiongtian
 * @create 2020/10/1-19:28
 */
// 指定这是一个操作数据库的mapper
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteById(Integer id);

     @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
