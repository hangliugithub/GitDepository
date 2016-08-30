package utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory sf;
	
	/*
	 * 加载配置文件
	 */
	static{
		SqlSessionFactoryBuilder builder  = new SqlSessionFactoryBuilder();
		sf= builder.build(MybatisUtil.class.getClassLoader().getResourceAsStream("mybatisConf.xml"));
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static SqlSession getSqlSession(){
		return sf.openSession();
	}
	
	/**
	 * 关闭连接
	 * @param session
	 */
	public static void close(SqlSession session){
		if(session!=null){
			session.close();
		}
	}
}
