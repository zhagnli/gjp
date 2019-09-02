package cn.zl.gjp.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.zl.gjp.domain.ZhangWu;
import cn.zl.gjp.tools.JDBCUtils;

/*
 * ʵ�ֶ����ݱ�gjp_zhangwu���ݵ���ɾ�Ĳ����
 * ʹ�ù�����dbutils��ɣ�����ĳ�Ա�д���������QueryRunner����ָ������Դ
 */
public class ZhangwuDao {
	private QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	
	/*
	 * ���巽����ʵ��ɾ������
	 * ҵ�����ã���������idֵ
	 */	
	public void deleteZhangWu(int zwid) {
		try {
	//		ƴдɾ������SQL
			String sql="DELETE FROM gjp_zhangwu WHERE zwid=?";
			qr.update(sql,zwid);
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("ɾ������ʧ��");
		}
	}
	
	/*
	 * ���巽����ʵ�ֱ༭������
	 * ��ҵ�����ã�����ZhangWu����
	 * �������е����ݣ����µ����ݱ�
	 */
	public void editZhangWu(ZhangWu zw) {
		try {
	//		�������ݵ�SQL
			String sql="UPDATE gjp_zhangwu SET flname=?,money=?,zhanghu=?,createtime=?,description=? "
					   + "WHERE zwid=?";
	//		����������飬�����еĲ���
			Object[] params= {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),
					   zw.getDescription(),zw.getZwid()};
	//		����qr���󷽷�updateִ�и��¼���
			qr.update(sql,params);
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("����༭ʧ��");
		}
	}
	
	/*
	 * ���巽����ʵ������������
	 * ��ҵ�����ã�����ZhangWu����
	 * ��ZhangWu�����е����ݣ����ӵ����ݱ�
	 */
	public void addZhangWu(ZhangWu zw) {
		try {
	//		ƴд�������ݵ�SQL
			String sql="INSERT INTO gjp_zhangwu (flname,money,zhanghu,createtime,description)"
					   + " VALUES (?,?,?,?,?)";
	//		�����������飬�洢5��ռλ����ʵ�ʲ���
	//		ʵ�ʲ�����Դ�ǣ����ݹ����Ķ���ZhangWu
			Object[] params= {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),
					          zw.getCreatetime(),zw.getDescription()};
	//		����qr�����еķ���updateִ������
			qr.update(sql,params);
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("��������ʧ��");
		}
	}
	
	/*
	 * ���巽������ѯ���ݿ⣬��������ȥ��ѯ�����
	 * ��ҵ�����ã���ѯ������洢��Bean���󣬴洢��List����
	 * �����ߴ���2�����ڵ��ַ���
	 */
	public List<ZhangWu> select(String startDate,String endDate){
		try {
	//		ƴд������ѯ��SQL���
			String sql="SELECT * FROM gjp_zhangwu WHERE createtime BETWEEN ? AND ?";
	//		����һ���������飬�洢�ʺ�ռλ��
			Object[] params= {startDate,endDate};
	//		����qr����ķ���query��ѯ���ݱ�����ȡ�����
			return qr.query(sql, new BeanListHandler<>(ZhangWu.class),params);
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("������ѯʧ��");
		}
	}
	
	/*
	 * ���巽������ѯ���ݿ⣬��ȡ������������
	 * ��������ҵ������
	 * ������������е��������ݴ洢��Bean�����У��洢��������
	 */
	public List<ZhangWu> selectAll() {
		try {
	//		��ѯ�������ݵ�SQL���
			String sql="select * from gjp_zhangwu";
	//		����qr����ķ�����query�����������BeanListHandler
			List<ZhangWu> list=qr.query(sql, new BeanListHandler<>(ZhangWu.class));
			return list;
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("��ѯ��������ʧ��");
		}
	}

	

	
}