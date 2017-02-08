package com.dcits.business.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.user.bean.Mail;
import com.dcits.business.user.dao.MailDao;

@Repository("mailDao")
public class MailDaoImpl extends BaseDaoImpl<Mail> implements MailDao{
	
	public int getNoReadNum(Integer receiveUserId){
		String hql = "select count(*) from Mail m where m.receiveUser.userId=:receiveUserId and m.readStatus='1' and m.sendStatus='0'";
		return ((Number)getSession().createQuery(hql).setInteger("receiveUserId", receiveUserId).uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Mail> findReadMails(Integer receiveUserId){
		String hql = "From Mail m where m.receiveUser.userId=:receiveUserId and m.sendStatus='0'";
		return getSession().createQuery(hql).setInteger("receiveUserId", receiveUserId).setCacheable(true).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Mail> findSendMails(Integer sendUserId){
		String hql = "From Mail m where m.sendUser.userId=:sendUserId";
		return getSession().createQuery(hql).setInteger("sendUserId", sendUserId).setCacheable(true).list();
	}
	
	public void changeStatus(Integer mailId,String statusName,String status){
		String hql = "update Mail set "+statusName+"=:status where mailId=:mailId";
		getSession().createQuery(hql).setString("status",status).setInteger("mailId", mailId).executeUpdate();
	}
}
