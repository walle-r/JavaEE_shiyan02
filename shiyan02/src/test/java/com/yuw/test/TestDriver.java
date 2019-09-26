package com.yuw.test;

import com.yuw.bean.UserInfoBean;
import com.yuw.dao.UserInfoBeanMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

import java.util.List;

public class TestDriver {

	@Test
	public void test01() {
		// String resource = "org/mybatis/example/mybatis-config.xml";
		// 使用输入流对象加载mybatis的xml配置文件
		InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
		// 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 使用try-with方式释放sqlsession对象：sqlsession的事务不是自动提交的，需要手提交
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
			UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);
			// 通过调用mapper接口的映射文件中的接口方法，来操作数据库：
			// 新增一条记录
			// 创建一个新增的实体类对象：
			UserInfoBean userInfoBean = new UserInfoBean();

			userInfoBean.setName("奔驰");
			userInfoBean.setPrice("1220000");
			// 调用接口的保存方法
			int i = userInfoBeanMapper.insertSelective(userInfoBean);
			// 输出操作结果
			System.out.println("新增结果：" + i);

			// 手动提交sqlsession的事务，后期由spring通过aop来进行统一管理
			session.commit();
		}
	}

	/**
	 * 普通查询
	 */
	@Test
	public void test02() {
		// String resource = "org/mybatis/example/mybatis-config.xml";
		// 使用输入流对象加载mybatis的xml配置文件
		InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
		// 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 使用try-with方式释放sqlsession对象
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
			UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);
			// 通过调用mapper接口的映射文件中的接口方法，来操作数据库
			List<UserInfoBean> lstUserInfos = userInfoBeanMapper.selectByPrimaryKey(3);
			// 输出查询结果
			System.out.println("查询结果：" + lstUserInfos != null ? lstUserInfos.get(0).toString() : null);
		}
	}

	/**
	 * 修改
	 */
	@Test
	public void test03() {
		InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
		// 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 使用try-with方式释放sqlsession对象：sqlsession的事务不是自动提交的，需要手提交
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
			UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);
			UserInfoBean userInfoBean = new UserInfoBean();
			userInfoBean.setId(3);
			userInfoBean.setName("鸡腿");
			userInfoBean.setPrice("100");
			// 调用接口的保存方法
			int i = userInfoBeanMapper.updateByPrimaryKey(userInfoBean);

			// 输出操作结果
			System.out.println("修改结果：" + i);

			// 手动提交sqlsession的事务，后期由spring通过aop来进行统一管理
			session.commit();
		}
	}

	/**
	 * 删除
	 */

	@Test
	public void test04() {
		InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
		// 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 使用try-with方式释放sqlsession对象：sqlsession的事务不是自动提交的，需要手提交
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
			UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);
			UserInfoBean userInfoBean = new UserInfoBean();
			userInfoBean.setId(4);

			// 调用接口的保存方法
			int i = userInfoBeanMapper.deleteByPrimaryKey(userInfoBean);

			// 输出操作结果
			System.out.println("删除结果：" + i);

			// 手动提交sqlsession的事务，后期由spring通过aop来进行统一管理
			session.commit();
		}
	}

	/**
	 * 模糊查询： 根据用户输入的模糊查询条件，拼写动态的sql语句块，并将查询结果以List的形式进行返回。
	 */
	@Test
	public void test05() {
		// 使用输入流对象加载mybatis的xml配置文件
		InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
		// 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 使用try-with方式释放sqlsession对象：sqlsession的事务不是自动提交的，需要手提交
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
			UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);

			// 模拟：查询条件实体类参数
			UserInfoBean userInfoBean = new UserInfoBean();
			userInfoBean.setName("奔驰");
			userInfoBean.setPrice("1220000");
			// 第二个价格区间的参数怎么处理
			userInfoBean.setPrice2("150");

			// 调用接口的保存方法
			List<UserInfoBean> lstUserinfoBs = userInfoBeanMapper.selectUserInfoByParams(userInfoBean);
			// 输出操作结果
			System.out.println("新增结果：" + lstUserinfoBs);

			// session.commit(); // 查询操作不需要手动提交事务
		}
	}
}
