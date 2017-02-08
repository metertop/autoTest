package com.dcits.business.user.dao;

import java.util.List;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.user.bean.Mail;

public interface MailDao extends BaseDao<Mail>{
	
	 int getNoReadNum(Integer receiveUserId);
	
	 List<Mail> findReadMails(Integer receiveUserId);
	
	 List<Mail> findSendMails(Integer sendUserId);
	
	 void changeStatus(Integer mailId,String statusName,String status);

}
