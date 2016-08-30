package utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory sf;
	
	/*
	 * ���������ļ�
	 */
	static{
		SqlSessionFactoryBuilder builder  = new SqlSessionFactoryBuilder();
		sf= builder.build(MybatisUtil.class.getClassLoader().getResourceAsStream("mybatisConf.xml"));
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public static SqlSession getSqlSession(){
		return sf.openSession();
	}
	
	/**
	 * �ر�����
	 * @param session
	 */
	public static void close(SqlSession session){
		if(session!=null){
			session.close();
		}
	}
}
