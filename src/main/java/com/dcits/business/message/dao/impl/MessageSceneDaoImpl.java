package com.dcits.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.message.bean.MessageScene;
import com.dcits.business.message.dao.MessageSceneDao;

@Repository("messageSceneDao")
public class MessageSceneDaoImpl extends BaseDaoImpl<MessageScene> implements MessageSceneDao{

}
