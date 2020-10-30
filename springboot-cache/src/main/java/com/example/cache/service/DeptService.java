package com.example.cache.service;

import com.example.cache.bean.Department;
import com.example.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

//@CacheConfig(cacheNames="dept",cacheManager = "deptRedisCacheManager") //全局的，抽取缓存的公共配置
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptRedisCacheManager")
    @Autowired
    RedisCacheManager cacheManager;
    /*
    * 缓存的数据能存redis，
    * 第二次从缓存中查询就不能反序列化了
    * 存的是Dept的json数据,CachaManager默认使用的RedisTemplate<Object,Employee>来操作redis的
    * 只能帮我们把employee的对象反序列化
    *
    * */
    //@Cacheable(cacheNames = "dept")
/*    @Cacheable(cacheNames="dept",cacheManager = "deptRedisCacheManager")
    public Department getDept(Integer id) {
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
       return department;
    }*/

    // 使用缓存管理器得到缓存，进行api调用
    public Department getDept(Integer id) {
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        // 获取缓存
        Cache dept = cacheManager.getCache("dept");
        dept.put("dept-1",department);
        return department;
    }
}
