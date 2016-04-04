package com.przepisy.web.dao;

import org.hibernate.search.bridge.StringBridge;

public class UserBridge implements StringBridge {

	public String objectToString(Object object) {

		return object.toString();
	}
}