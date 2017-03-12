var table;
//遮罩层覆盖区域
var $wrapper = $('#div-table-container');

var interfaceId; //当前interfaceId
var messageId; //当前正在操作的messageid
var currIndex;//当前正在操作的layer窗口的index

/**
 * ajax地址
 */
var MESSAGE_LIST_URL = "message-list"; //获取接口列表
var MESSAGE_EDIT_URL = "message-edit";  //接口编辑
var MESSAGE_GET_URL = "message-get"; //获取指定接口信息
var MESSAGE_DEL_URL = "message-del"; //删除指定接口
var MESSAGE_FORMAT_URL = "message-format";//格式json串
var MESSAGE_VALIDATE_JSON_URL = "message-validateJson";//报文json串验证

var templateParams = {
		tableTheads:["接口","报文名", "创建时间", "状态", "创建用户", "最后修改", "入参", "场景", "操作"],
		btnTools:[{
			type:"primary",
			size:"M",
			markClass:"add-object",
			iconFont:"&#xe600;",
			name:"添加报文"
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
			label:"报文ID",  	
			objText:"messageIdText",
			input:[{	
				hidden:true,
				name:"messageId"
				}]
		},
		{
			required:true,
			label:"报文名称",  
			input:[{	
				name:"messageName"
				}]
		},
		{
			name:"interfaceInfo.interfaceId",
		},
		{
			required:true,
			label:"报文入参",  
			input:[{
				hidden:true,
				name:"parameterJson"
				}],
			button:[{
				style:"danger",
				value:"验证",
				markClass:"validate-parameter-json"
			},{
				style:"info",
				value:"格式化",
				embellish:"<br><br>",
				markClass:"format-parameter-json"
			}],
			textarea:[{
				placeholder:"输入报文的JSON串"
			}]
		},
		{
			label:"请求地址",  
			input:[{	
				name:"requestUrl",
				placeholder:"请输入该报文指定的请求地址"
				}]
		},
		{	
			required:true,
			label:"报文状态",  			
			select:[{	
				name:"status",
				option:[{
					value:"0",
					text:"可用"
				},{
					value:"1",
					text:"禁用"
				}]
				}]
		},		
		{
			edit:true,			
			label:"创建日期",  
			objText:"createTimeText",
			input:[{	
				hidden:true,
				name:"createTime"
				}]
		},
		{
			edit:true,			
			label:"创建用户",  
			objText:"user.realNameText",
		},
		{
			edit:true,
			name:"user.userId"
							
		},
		{
			edit:true,			
			label:"最后修改",  
			objText:"lastModifyUserText",
			input:[{	
				hidden:true,
				name:"lastModifyUser"
				}]
		}
		]		
	};
//["报文名", "创建时间", "状态", "创建用户", "最后修改", "入参", "场景", "操作"],
var columnsSetting = [
                       {
                      	"data":null,
                      	"render":function(data, type, full, meta){                       
                              return checkboxHmtl(data.messageName,data.messageId,"selectMessage");
                          }},
                       {"data":"messageId"},
                       {
                         "className":"ellipsis",
               		     "data":"interfaceName",
                         "render":CONSTANT.DATA_TABLES.COLUMNFUN.ELLIPSIS
                         },
                       {
                      	"className":"ellipsis",
            		    "data":"messageName",
                      	"render":CONSTANT.DATA_TABLES.COLUMNFUN.ELLIPSIS
                      	},
                       {
                    	"className":"ellipsis",
              		    "data":"createTime",
              		    "render":CONSTANT.DATA_TABLES.COLUMNFUN.ELLIPSIS  
              		    },
                       {
                        	"data":null,
                        	"render":function(data, type, full, meta ){
                                return labelCreate(data.status);
                                }
              		    },
              		    {"data":"user.realName"},{"data":"lastModifyUser"},
              		    {
                            "data":null,
                            "render":function(data, type, full, meta){
                            	var context =
                            		[{
                          			type:"primary",
                          			size:"M",
                          			markClass:"get-params",
                          			name:"获取"
                          		}];
                                return btnTextTemplate(context);
                                }
              		    },
              		    {
              		    	"data":null,
                            "render":function(data, type, full, meta){
                            	var context =
                            		[{
                          			type:"default",
                          			size:"M",
                          			markClass:"show-scenes",
                          			name:data.sceneNum
                          		}];
                                return btnTextTemplate(context);
                                }
              		    },
              		    {
                            "data":null,
                            "render":function(data, type, full, meta){
                              var context = [{
	                  	    		title:"报文编辑",
	                  	    		markClass:"message-edit",
	                  	    		iconFont:"&#xe6df;"
                  	    		},{
	                  	    		title:"报文删除",
	                  	    		markClass:"message-del",
	                  	    		iconFont:"&#xe6e2;"
                  	    		}];
                            	return btnIconTemplate(context);
                            }}
              		    ];

var eventList = {
		".get-params":function(){
			var data = table.row( $(this).parents('tr') ).data();
			messageId = data.messageId;
			var paramsHmtl = '<div class="page-container" id="parameter-json-textarea" style="padding:10px;">'
				+ '<div class="cl pd-5 bg-1 bk-gray"> <span class="l">'
				+ '<a href="javascript:;" id="copy-message-json" class="btn btn-primary radius">复制</a></span>'
				+ '&nbsp;<span class="r"><a href="javascript:;" onclick="getParameterJson();" class="btn btn-primary radius">刷新</a></span>'
				+ '</div><textarea class="textarea radius dct-message-json"></textarea></div>';
			
			layer_show(data.messageName + "-[入参报文]", paramsHmtl, '800', '500', 1, function(){
				getParameterJson();					
				$("#copy-message-json").zclip({
					path: "../../libs/ZeroClipboard.swf",
					copy: function(){
					return $(".textarea").val();
					},
					afterCopy:function(){/* 复制成功后的操作 */
						layer.msg('复制成功,CTRL+V粘贴',{icon:1,time:1500});
			        }
				});
			});
				
		},
		".show-scenes":function(){
			var data = table.row( $(this).parents('tr') ).data();			
			currIndex = layer_show(data.messageName + "-场景列表", "messageScene.html?messageId=" + data.messageId
					, "" , "", 2);
			layer.full(currIndex);
		},
		".add-object":function() {
			publish.renderParams.editPage.modeFlag = 0;					
			currIndex = layer_show("增加报文", editHtml, "800", "450",1);
			//layer.full(index);
			publish.init();			
		},
		".batch-del-object":function() {
			var checkboxList = $(".selectMessage:checked");
			batchDelObjs(checkboxList,MESSAGE_DEL_URL);
		},
		".message-del":function() {
			var data = table.row( $(this).parents('tr') ).data();
			delObj("确定删除此报文？此操作同时会删除该报文下所有的场景及相关数据,请谨慎操作!",MESSAGE_DEL_URL,data.messageId,this);
		},
		".message-edit":function() {
			var data = table.row( $(this).parents('tr') ).data();
			publish.renderParams.editPage.modeFlag = 1;
			publish.renderParams.editPage.objId = data.messageId;
			layer_show("编辑报文信息", editHtml, "850", "610",1);
			publish.init();	
		},
		".validate-parameter-json":function() {
			var jsonStr = $(".textarea").val();
			if(jsonStr == null || jsonStr == "") {
				layer.msg('你还没有输入任何内容',{icon:5,time:1500});
				return false;
			}
			$.post(MESSAGE_VALIDATE_JSON_URL,{parameterJson:jsonStr,interfaceId:interfaceId},function(data){
				if(data.returnCode==0){
					layer.msg('验证通过,请执行下一步操作',{icon:1, time:1500});
					$("#parameterJson").val(jsonStr);
				}else if(data.returnCode == 912){
					layer.msg('JSON格式错误,请检查',{icon:5, time:1500});
				}else{
					layer.msg(data.msg,{icon:5, time:1500});
				}
			});
		},
		".format-parameter-json":function(){
			var jsonStr = $(".textarea").val();
			if(jsonStr == null || jsonStr == "") {
				layer.msg('你还没有输入任何内容',{icon:5,time:1500});
				return false;
			}
			$.post(MESSAGE_FORMAT_URL,{parameterJson:jsonStr},function(data) {
				if(data.returnCode == 0){
					$(".textarea").val(data.returnJson);
				} else {
					layer.msg(data.msg,{icon:5,time:1500});
				}
			});
		}
};

var mySetting = {
		eventList:eventList,
		templateCallBack:function(df){
			interfaceId = GetQueryString("interfaceId");
			if (interfaceId != null) {
				publish.renderParams.listPage.listUrl = MESSAGE_LIST_URL + "?interfaceId=" + interfaceId;
			} else {
				publish.renderParams.listPage.listUrl = MESSAGE_LIST_URL;
				$(".add-object").hide();
			}			
   		 	df.resolve();
   	 	},
		editPage:{
			editUrl:MESSAGE_EDIT_URL,
			getUrl:MESSAGE_GET_URL,
			beforeInit:function(df){
				$("#interfaceInfo\\.interfaceId").val(interfaceId);
       		 	df.resolve();
       	 	},
			rules:{
				messageName:{
					required:true
				},				
				parameterJson:{
					required:true
				},
				requestUrl:{
					url:true
				},
				status:{
					required:true
				}			
			},
			messages:{
				messageName:"请输入报文名称",
				parameterJson:"请输入正确的报文 入参并点击验证"
			},
			renderCallback:function(obj){
				$("#parameterJson").val();
				$(".textarea").val(obj.parameterJson);
			}
		},		
		listPage:{
			tableObj:".table-sort",
			columnsSetting:columnsSetting,
			columnsJson:[0, 8, 9, 10]
		},
		templateParams:templateParams		
	};

$(function(){	
	publish.renderParams = $.extend(true,publish.renderParams,mySetting);
	publish.init();
});

/**************************************************************************************/
function getParameterJson(){
	$("#parameter-json-textarea").spinModal();
	$.get("message-get",{id:messageId},function(data){
		if(data.returnCode==0){						
			$(".textarea").val(data.object.parameterJson);
			if (data.object.parameterJson == null || data.object.parameterJson == "") {
				$(".textarea").attr("placeholder","该报文没有设置入参json！");
			}
			$("#parameter-json-textarea").spinModal(false);
		}else{
			layer.alert(data.msg, {icon: 5});
		}
	});
}
