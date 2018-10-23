package com.dao;

import com.entity.Dept;
import com.entity.Emp;

import java.util.List;

/**
 * 测试 hibernate的加载策略
 *
 * 立即加载
 *
 * 延迟加载
 * 概念说明<不同角度>
 * 返回的对象是代理对象
 * 或，
 * session关闭后，如在关闭前没有初始化，则会出现懒加载异常（why? 对象的初始化需要session连接操作数据库：获取属性值）
 *
 */
public class DeptDao {

    /**
     * 测试：load懒加载
     *
     * 可能的后果：懒加载异常
     *
     * 解决方法：
     * 1) 配置：load ---> get
     * 2) session关闭前，访问目标属性，对代理对象进行初始化
     *
     * ？ 其它未访问的属性，其值是否会初始化
     *
     */
    public Dept loadDept(){

    }




    /**
     * session.get() 加载策略
     *
     * 默认：
     * 类级别：立即加载
     * 关联对象：延迟加载，即返回值为代理对象，只有OID初始化了，其它为Null。
     *
     * 解决：
     * 配置
     * 代码中初始化（受配置的影响，即lazy的程度）
     */
    public List<Emp> queryDeptById(){


    }

    /**
     * 测试 query.list()
     *
     * 在类级别，总是立即加载
     * 在关联级别，延迟加载
     *
     *
     * @return
     */
    public List<Dept> queryDepts(){

    }
}
