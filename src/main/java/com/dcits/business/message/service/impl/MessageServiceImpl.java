package com.dcits.business.message.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.Message;
import com.dcits.business.message.service.MessageService;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{

}
