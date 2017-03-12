package com.dcits.business.message.action;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.message.bean.MessageScene;

/**
 * 报文场景Action
 * 
 * @author xuwangcheng
 * @version 1.0.0.0,2017.3.6
 */

@Controller
@Scope("prototype")
public class MessageSceneAction extends BaseAction<MessageScene>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(MessageSceneAction.class.getName());

}
