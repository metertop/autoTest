var table;
//遮罩层覆盖区域
var $wrapper = $('#div-table-container');

var messageId; //当前messageid
var messageSceneId; //当前正在操作的sceneId
var currIndex;//当前正在操作的layer窗口的index


/**
 * ajax地址
 */
var SCENE_LIST_URL = "scene-list"; //获取场景列表
var SCENE_EDIT_URL = "scene-edit";  //场景编辑
var SCENE_GET_URL = "scene-get"; //获取指定场景信息
var SCENE_DEL_URL = "scene-del"; //删除指定场景

var templateParams = {
		tableTheads:["场景名","验证方式", "备注", "操作"],
		btnTools:[{
			type:"primary",
			size:"M",
			markClass:"add-object",
			iconFont:"&#xe600;",
			name:"添加场景"
		},{
			type:"danger",
			size:"M",
			markClass:"batch-del-object",
			iconFont:"&#xe6e2;",
			name:"批量删除"
		}],
		formControls:[
		{
			edit:true,
			label:"场景ID",  	
			objText:"messageSceneIdText",
			input:[{	
				hidden:true,
				name:"messageSceneId"
				}]
		},
		{
			required:true,
			label:"场景名称",  
			input:[{	
				name:"sceneName",
				placeholder:"输入场景名称"
				}]
		},
		{
			name:"message.messageId",
		},
		{
			label:"备注",  
			textarea:[{	
				name:"mark",
				placeholder:"输入场景备注或者备忘的查询数据用的SQL语句"
				}]
		},
		]		
	};

var columnsSetting = [
                      {
                      	"data":null,
                      	"render":function(data, type, full, meta){                       
                              return checkboxHmtl(data.sceneName, data.sceneId, "selectScene");
                          }},
                      {"data":"messageSceneId"},
                      ellipsisData("sceneName"),
                      ellipsisData("mark"),                   
                      {
                          "data":null,
                          "render":function(data, type, full, meta){
                            var context = [{
                            	title:"验证规则设定",
                	    		markClass:"validate-method",
                	    		iconFont:"&#xe654;"                           	
                            },{
                            	title:"场景测试",
                	    		markClass:"scene-test",
                	    		iconFont:"&#xe603;"
                            },{
                	    		title:"接口编辑",
                	    		markClass:"scene-edit",
                	    		iconFont:"&#xe6df;"
                	    	},{
                	    		title:"接口删除",
                	    		markClass:"scene-del",
                	    		iconFont:"&#xe6e2;"
                	    	}];                           
                          	return btnIconTemplate(context);
                          }}
                  ];
var eventList = {
		
};


var mySetting = {
		eventList:eventList,
		editPage:{
			editUrl:SCENE_EDIT_URL,
			getUrl:SCENE_GET_URL,
			rules:{
				sceneName:{
					required:true,
					minlength:2,
					maxlength:255
				}										
			}

		},		
		listPage:{
			listUrl:SCENE_LIST_URL,
			tableObj:".table-sort",
			columnsSetting:columnsSetting,
			columnsJson:[0,3,4]
		},
		templateParams:templateParams		
	};

$(function(){			
	publish.renderParams = $.extend(true,publish.renderParams,mySetting);
	publish.init();
});