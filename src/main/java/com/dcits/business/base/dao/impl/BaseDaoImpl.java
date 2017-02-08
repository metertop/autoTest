package com.dcits.business.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dcits.business.base.bean.PageModel;
import com.dcits.business.base.dao.BaseDao;

/**
 * @author dcits
 * 通用DAO接口的实现类
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	private Class<T> clazz;
	

	public BaseDaoImpl() {
		//反射获取传入的类
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz=(Class)type.getActualTypeArguments()[0];	
	}
	
	public Integer save(T entity) {
		// TODO Auto-generated method stub
		Integer id=(Integer) getSession().save(entity);
		return id;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		getSession().delete(get(id));
	}

	public void edit(T entity) {
		// TODO Auto-generated method stub
		getSession().merge(entity);
	}

	
	public T get(Integer id) {
		// TODO Auto-generated method stub
		
		return (T)getSession().get(clazz, id);
	}

	public T load(Integer id) {
		// TODO Auto-generated method stub
		return (T)getSession().load(clazz, id);
	}

	public List<T> findAll() {
		// TODO Auto-generated method stub
		String hsql="select t from "+clazz.getSimpleName()+" t";
		
		return getSession().createQuery(hsql).setCacheable(true).list();
	}

	public int totalCount() {
		// TODO Auto-generated method stub
		int count=0;
		String hql="select count(t) from "+clazz.getSimpleName()+" t";
		Long temp=(Long)getSession().createQuery(hql).uniqueResult();
		if(temp!=null){
			count=temp.intValue();
		}
		return count;
	}

	public PageModel<T> findByPager(int dataNo, int pageSize,String orderDataName,String orderType,String searchValue,List<String> dataParams) {
		// TODO Auto-generated method stub
		PageModel<T> pm=new PageModel<T>(orderDataName, orderType,searchValue,dataParams,dataNo,pageSize);
		
		String hql="from "+clazz.getSimpleName();
		
		//增加搜索条件
		if(searchValue!=""){
			hql+=" where ";
			int i = 1;
			for(String s:dataParams){
				hql+=s+" like '%" + searchValue + "%'";
				i++;
				if(i<=dataParams.size()){
					hql+=" or ";
				}
			}
			
		}

		//增加排序
		if(orderDataName!=""){
			hql += " order by " + orderDataName + " " + orderType;
		}
		
		
		
		pm.setDatas(getSession().createQuery(hql).setFirstResult(dataNo).setMaxResults(pageSize).setCacheable(true).list());
		pm.setRecordCount(totalCount());
		return pm;
	}

	public void update(String sql) {
		// TODO Auto-generated method stub
		getSession().createQuery(sql).executeUpdate();
	}

	public T findUnique(String sql) {
		// TODO Auto-generated method stub
		
		return (T)getSession().createQuery(sql).uniqueResult();
	}
}
