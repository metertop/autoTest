package com.dcits.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.message.bean.Message;
import com.dcits.business.message.dao.MessageDao;

@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao{

}
