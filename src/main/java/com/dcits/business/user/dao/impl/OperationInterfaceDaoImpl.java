package com.dcits.business.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.user.bean.OperationInterface;
import com.dcits.business.user.dao.OperationInterfaceDao;

@Repository("operationInterfaceDao")
public class OperationInterfaceDaoImpl extends BaseDaoImpl<OperationInterface> implements OperationInterfaceDao{

}
