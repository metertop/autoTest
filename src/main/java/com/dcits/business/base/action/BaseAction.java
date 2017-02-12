package com.dcits.business.base.action;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.dcits.business.base.bean.PageModel;
import com.dcits.business.base.service.BaseService;
import com.dcits.business.message.service.InterfaceInfoService;
import com.dcits.business.message.service.InterfaceMockService;
import com.dcits.business.message.service.MessageSceneService;
import com.dcits.business.message.service.MessageService;
import com.dcits.business.message.service.ParameterService;
import com.dcits.business.message.service.SceneValidateRuleService;
import com.dcits.business.message.service.TestConfigService;
import com.dcits.business.message.service.TestDataService;
import com.dcits.business.message.service.TestReportService;
import com.dcits.business.message.service.TestResultService;
import com.dcits.business.message.service.TestSetService;
import com.dcits.business.system.service.DataDBService;
import com.dcits.business.system.service.GlobalSettingService;
import com.dcits.business.user.service.MailService;
import com.dcits.business.user.service.OperationInterfaceService;
import com.dcits.business.user.service.RoleService;
import com.dcits.business.user.service.UserService;
import com.dcits.constant.ReturnCodeConstant;
import com.dcits.util.StrutsUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 通用Action类
 * @author dcits
 *
 * @param <T>
 */

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	private static final long serialVersionUID = 1L;
	
	protected BaseService<T> baseService;
	
	//ajax调用返回的map
	protected Map<String,Object> jsonMap=new HashMap<String,Object>();

	protected Class clazz;
	
	protected T model;
	
	protected Integer id;
		
	//dataTable服务器处理参数
	//请求计数保证不混淆前后请求展示的数据顺序
	protected Integer draw;
	//请求数据开始下标
	protected Integer start;
	//本次请求的数据长度
	protected Integer length;
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected RoleService roleService;
	@Autowired
	protected OperationInterfaceService operationInterfaceService;
	@Autowired
	protected MailService mailService;
	@Autowired
	protected GlobalSettingService globalSettingService;
	@Autowired
	protected DataDBService dataDBService;
	@Autowired
	protected InterfaceInfoService interfaceInfoService;
	@Autowired
	protected InterfaceMockService interfaceMockService;
	@Autowired
	protected MessageService messageService;
	@Autowired
	protected MessageSceneService messageSceneService;
	@Autowired
	protected ParameterService parameterService;
	@Autowired
	protected SceneValidateRuleService sceneValidateRuleService;
	@Autowired
	protected TestConfigService testConfigService;
	@Autowired
	protected TestDataService testDataService;
	@Autowired
	protected TestReportService testReportService;
	@Autowired
	protected TestResultService testResultService;
	@Autowired
	protected TestSetService testSetService;
	
	/**
	 * 通用 list方法
	 * 分页展示对应实体的集合
	 * @return
	 */
	public String list(){
		Map<String,Object>  dt = StrutsUtils.getDTParameters();
		PageModel<T> pu = baseService.findByPager(start, length,(String)dt.get("orderDataName"),(String)dt.get("orderType"),(String)dt.get("searchValue"),(List<String>)dt.get("dataParams"));
		jsonMap.put("draw", draw);
		jsonMap.put("data",processListData(pu.getDatas()));
		jsonMap.put("recordsTotal", pu.getRecordCount());
		if(!((String)dt.get("searchValue")).equals("")){
			jsonMap.put("recordsFiltered", pu.getDatas().size());
		}else{
			jsonMap.put("recordsFiltered", pu.getRecordCount());
		}
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	/**
	 * 通用方法 listAll
	 * 获取所有的对应实体集合
	 * @return
	 */
	public String listAll(){
		List<T> ts = baseService.findAll();
		jsonMap.put("data", processListData(ts));
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	
	/**
	 * 通用方法 del
	 * 根据传入的id删除对应实体
	 * @return
	 */
	public String del(){
		baseService.delete(id);
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	/**
	 * 通用方法 get
	 * 根据id获取指定实体信息
	 * @return
	 */
	public String get(){
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		jsonMap.put("object", baseService.get(id));
		return SUCCESS;
	}
	
	/**
	 * 通用方法 edit
	 * 编辑实体 如使用父类中此方法 保证从前台传入的属性是完整的，否则请在子类中重写该方法
	 * @return
	 */
	public String edit(){
		baseService.edit(model);
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	/**
	 * 通用方法 save
	 * 保证新的实体对象,同时返回新增的实体ID的值  保证传入的属性是完整的  否则请在子类中重写该方法
	 * @return
	 */
	public String save(){
		jsonMap.put("id", baseService.save(model));
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	/**
	 * 通过反射动态的创建对象
	 */
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 初始化将baseService 替换为指定的***Service
	 * 具体说明参考BaserServiceImpl中的init方法
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception
	{
		String clazzName = clazz.getSimpleName();
		String clazzServiceName = clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Service";//toLowerCase首字母小写
		Field serviceNameField = this.getClass().getSuperclass().getDeclaredField(clazzServiceName);
		Object object = serviceNameField.get(this);
		Field baseServiceNameField = this.getClass().getSuperclass().getDeclaredField("baseService");
		baseServiceNameField.set(this, object);		
	}
	
	/**
	 * list数据的加工
	 * 子类可重写该方法对发给前台的数据进行再一次处理
	 * @param o
	 * @return
	 */
	public Object processListData(Object o){
		return o;
	}
	
	/************************************GET-SET***********************************************/
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	
	public void setStart(Integer start) {
		this.start = start;
	}
	
	public void setLength(Integer length) {
		this.length = length;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JSON(serialize=false)
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

}
