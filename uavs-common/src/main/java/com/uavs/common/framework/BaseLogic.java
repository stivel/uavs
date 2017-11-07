package com.uavs.common.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uavs.common.dao.CommDAO;
@Component
public class BaseLogic {
	@Autowired
	protected CommDAO commDAO;
}
