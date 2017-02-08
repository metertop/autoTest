package com.dcits.business.user.service;

import java.util.List;

import com.dcits.business.base.service.BaseService;
import com.dcits.business.user.bean.Mail;

public interface MailService extends BaseService<Mail>{
	
public int getNoReadNum(Integer receiveUserId);
	
	 List<Mail> findReadMails(Integer receiveUserId);
	
	 List<Mail> findSendMails(Integer sendUserId);
	
	 void changeStatus(Integer mailId,String statusName,String status);
}
