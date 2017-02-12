package com.dcits.business.base.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.dcits.business.base.bean.PageModel;
import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.base.service.BaseService;
import com.dcits.business.message.dao.InterfaceInfoDao;
import com.dcits.business.message.dao.InterfaceMockDao;
import com.dcits.business.message.dao.MessageDao;
import com.dcits.business.message.dao.MessageSceneDao;
import com.dcits.business.message.dao.ParameterDao;
import com.dcits.business.message.dao.SceneValidateRuleDao;
import com.dcits.business.message.dao.TestConfigDao;
import com.dcits.business.message.dao.TestDataDao;
import com.dcits.business.message.dao.TestReportDao;
import com.dcits.business.message.dao.TestResultDao;
import com.dcits.business.message.dao.TestSetDao;
import com.dcits.business.system.dao.DataDBDao;
import com.dcits.business.system.dao.GlobalSettingDao;
import com.dcits.business.user.dao.MailDao;
import com.dcits.business.user.dao.OperationInterfaceDao;
import com.dcits.business.user.dao.RoleDao;
import com.dcits.business.user.dao.UserDao;

/**
 * 通用service实现类
 * @author dcits
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T>{
	
	private Class clazz=null;
	
	protected BaseDao<T> baseDao;
	
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected GlobalSettingDao globalSettingDao;
	@Autowired
	protected OperationInterfaceDao operationInterfaceDao;
	@Autowired
	protected MailDao mailDao;
	@Autowired
	protected RoleDao roleDao;
	@Autowired
	protected DataDBDao dataDBDao;
	@Autowired
	protected InterfaceInfoDao interfaceInfoDao;
	@Autowired
	protected InterfaceMockDao interfaceMockDao;
	@Autowired
	protected MessageDao messageDao;
	@Autowired
	protected MessageSceneDao messageSceneDao;
	@Autowired
	protected ParameterDao parameterDao;
	@Autowired
	protected SceneValidateRuleDao sceneValidateRuleDao;
	@Autowired
	protected TestConfigDao testConfigDao;
	@Autowired
	protected TestDataDao testDataDao;
	@Autowired
	protected TestReportDao testReportDao;
	@Autowired
	protected TestResultDao testResultDao;
	@Autowired
	protected TestSetDao testSetDao;
	
	public BaseServiceImpl(){
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		clazz=(Class)type.getActualTypeArguments()[0];
	}

	/**
	 * 这个方法会在构造函数和spring以来注入之后执行
	 * @Title: init
	 * @Description: TODO(通过反射来实例化baseDao)
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 */
	@PostConstruct
	public void init() throws Exception
	{
		// 根据相应的clazz,吧相应  ****Dao 赋值给BaseDao即可
		// 1: 获取当前clazz的类型,然后获取相应的类名称
		String clazzName = clazz.getSimpleName();
		// 2:Account===>account===>account+Dao  Category====>CategoryDao
		String clazzDaoName = clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Dao";//toLowerCase首字母小写
		// 3: 通过clazzDaoName获取相应 Field字段    this.getClass().getSuperclass():获取到相应BaseServiceImpl
		Field daoNameField = this.getClass().getSuperclass().getDeclaredField(clazzDaoName);
		Object object = daoNameField.get(this);
		// 4: 获取baseDao 字段
		Field baseDaoNameField = this.getClass().getSuperclass().getDeclaredField("baseDao");
		baseDaoNameField.set(this, object);		
	}
	
	@Override
	public Integer save(T entity) {
		// TODO Auto-generated method stub
		return baseDao.save(entity);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);		
	}

	@Override
	public void edit(T entity) {
		// TODO Auto-generated method stub
		baseDao.edit(entity);		
	}

	@Override
	public T get(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get(id);
	}

	@Override
	public T load(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.load(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return baseDao.findAll();
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return baseDao.totalCount();
	}

	@Override
	public PageModel<T> findByPager(int dataNo, int pageSize,String orderDataName,String orderType,String searchValue,List<String> dataParams) {
		// TODO Auto-generated method stub
		return baseDao.findByPager(dataNo, pageSize,orderDataName,orderType,searchValue,dataParams);
	}
	
	
}
