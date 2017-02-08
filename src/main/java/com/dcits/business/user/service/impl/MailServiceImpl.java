package com.dcits.business.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.user.bean.Mail;
import com.dcits.business.user.service.MailService;

@Service("mailService")
public class MailServiceImpl extends BaseServiceImpl<Mail> implements MailService{

	@Override
	public int getNoReadNum(Integer receiveUserId) {
		// TODO Auto-generated method stub
		return mailDao.getNoReadNum(receiveUserId);
	}

	@Override
	public List<Mail> findReadMails(Integer receiveUserId) {
		// TODO Auto-generated method stub
		return mailDao.findReadMails(receiveUserId);
	}

	@Override
	public List<Mail> findSendMails(Integer sendUserId) {
		// TODO Auto-generated method stub
		return mailDao.findSendMails(sendUserId);
	}

	@Override
	public void changeStatus(Integer mailId, String statusName, String status) {
		// TODO Auto-generated method stub
		mailDao.changeStatus(mailId, statusName, status);
	}

}
