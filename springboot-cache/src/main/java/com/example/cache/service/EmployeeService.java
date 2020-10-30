package com.example.cache.service;

import com.example.cache.bean.Employee;
import com.example.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author xiongtian
 * @create 2020/10/6-23:10
 */
//@CacheConfig(cacheNames="emp",cacheManager = "empRedisCacheManager") //全局的，抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，再要相同的数据就直接从缓存中获取，不用调用方法
     *
     * CacheManager:管理多个Catch组件的，对缓存的真正SRUD操作在Cache组件中，每一个缓存组件中有自己唯一一个名字；
     * 几个属性：
     *      cacheNames/value:指定缓存的名字；将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存
     *
     *      key:缓存数据时用到的key;可以用它来指定，默认是使用方法参数的值 1 - 方法的返回值
     *      编写SpEl: #id:参数id的值     #a0,#p0,#root.args[0]
     *      想要的结果：getEmp[2]  key="#root.methodName+'['+#id+']'"
     *
     *      keyGenerator:key的生成器:可以自己指定key的生成器的组件id
     *
     *      key/keyGenerator:二选一
     *
     *      cacheManager:指定缓存管理器，或者cacheResolver指定获取解析器
     *
     *      condition:指定符合条件的情况下才缓存。
     *          condition="#a0>1" :第一个参数的值>1的时候才进行缓存
     *      unless:否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存;可以获取到结果进行判断
     *          unless = "#a0==2";如果参数第一个值是2，结果不缓存
     *      sync:是否使用异步模式，异步不支持unless/
     *
     *
     *工作原理：
     *  1、自动配置类：CacheAutoConfiguration
     *  2、 缓存的配置类
     *      0 = "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     *      1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     *      2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     *      3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     *      4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     *      5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     *      6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     *      7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     *      8 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     *      9 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     * 3、哪个配置类默认生效：SimpleCacheConfiguration
     * 4、给容器中注册了一个CacheManager:ConcurrentMapCacheManager
     * 5、作用：可以获取和创建ConcurrentMapCache类型的缓存组件：他的作用将数据保存在ConcurrentMap<Object, Object> store里面；
     *
     *运行流程：
     *         @Cacheable
     *        1、方法运行之前，先去查询Cache(缓存组件)，按照cacheNames指定的名字获取
     *        (CacheManage先获取对应的缓存)，第一次获取缓存如果没有cache组件会自动创建。
     *        2、去Cache中查找缓存的内容，使用一个key,默认就是方法的参数;
     *        key是按照某种策略生成的，默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
     *              SimpleKeyGenerator生成key的默认策略：如果没有参数，key = new SimpleKey();
     *                                                  如果有一个参数：key = 参数的值
     *                                                  如果有三个参数：key = new SimpleKey(params):对参数进行包装
     *        3、没有查到缓存就调用目标方法
     *        4、将目标方法返回的结果，放进缓存里面；以后再来调用就可以直接使用缓存中的数据
     *
     *        @Cacheable标注的方法执行之前先来检查缓存中有这个没有数据，默认按照参数的值作为key去查询缓存，若果没有，运行方法，并将结果放入缓存
     *
     *    核心：
     *    1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *    2）、key是使用keyGenerator生成的，默认是SimpleKeyGenerator
     * @param id
     * @return
     */
   /* @Cacheable(cacheNames = {"emp","temp"},key="#root.methodName+'['+#id+']'" )*/
    /*@Cacheable(cacheNames = {"emp","temp"},keyGenerator = "myKeyGenerator",condition = "#a0>1 and #root.methodName eq  'aaa'")*/
    //@Cacheable(cacheNames = {"emp","temp"},key="#root.methodName+'['+#id+']'" ,unless = "#a0==2",cacheManager = "myCacheManager")
    @Cacheable(cacheNames = {"emp"})
    public Employee getEmp(Integer id) {
        System.out.println("查询：" + id + "号员工！");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }


    /*
    * @CachePut:即调用方法，又更新缓存数据；同步更新缓存
    * 修改了数据库的某个数据，同时更新缓存
    *运行时机：
    * 1、先调用目标方法
    * 2、将目标方法的结果缓存起来
    *
    * 测试步骤：
    * 1、查询一号员工，查到的结果会放到缓存中
    *    key: 1 value: lastName: 张三
    * 2、以后查询还是之前的结果
    * 3、更新1号员工：【lastName:Zhansan;gender:0】
    *    将方法的返回值也放进了缓存中
    *     key: 传入的employee对象  值: 传入的employee对象
    * 4、查询一号员工：
    *       应该是更新后的员工？
    *       key = "#employee.id": 使用传入的参数的员工id
    *       key = "#result.id": 返回后的id
    *               @Cacheable的key是不能使用"#result.id"的
     *      为什么是没更新前的?【1号员工没有在缓存中更新】
    * */
    @CachePut(value = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }


   /*
   *
   * @CacheEvict:缓存清除
   * key:指定要清除的数据
   * allEntries:是不是要删除emp中的所有数据；默认是false;true指定清除这个缓存中的所有数据
   * beforeInvocation: 缓存的清除是否在方法开始之前执行
   *                    默认代表是在方法执行之后执行的，如果出现异常，缓存就不会清除
   *                    true:代表清除缓存操作是在方法执行之前，无论方法是否出现异常，缓存都清除
   *
   * */
   @CacheEvict(value="emp",key="#id")
   public void deleteEmp(Integer id){
       System.out.println("deleteEmp:"+id);
       //employeeMapper.deleteEmpById(id);
   }

   /*
   *
   * @Caching:定义复杂的缓存规则；
   * */
   @Caching(
           cacheable = {
                   @Cacheable(value = "emp",key="#lastName")
           },
           put = {
                   @CachePut(value = "emp",key="#result.id"),
                   @CachePut(value = "emp",key="#result.email")
           }

   )
   public Employee getEmpByLastName(String lastName){
      return employeeMapper.getEmpByLastName(lastName);
   }
}
/*,key = "root.args[0]",condition = "#id>0",unless = "#result == null"*/