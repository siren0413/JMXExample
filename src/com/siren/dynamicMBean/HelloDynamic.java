package com.siren.dynamicMBean;

import java.util.Iterator;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

public class HelloDynamic implements DynamicMBean {

	private String name;
	private MBeanInfo mBeanInfo = null;
	private String className;
	private String description;
	private MBeanAttributeInfo[] attributes;
	private MBeanConstructorInfo[] constructors;
	private MBeanOperationInfo[] operations;
	MBeanNotificationInfo[] mBeanNotificationInfoArray;

	public HelloDynamic() {
		init();
		buildDynamicMBean();
	}

	private void init() {
		className = this.getClass().getName();
		description = "simple implementation of dynamic MBean";
		attributes = new MBeanAttributeInfo[1];
		constructors = new MBeanConstructorInfo[1];
		operations = new MBeanOperationInfo[1];
		mBeanNotificationInfoArray = new MBeanNotificationInfo[0];
	}

	private void buildDynamicMBean() {
		constructors[0] = new MBeanConstructorInfo("HelloDynamic():Constructs a HelloDynamic object", this.getClass()
				.getConstructors()[0]);
		attributes[0] = new MBeanAttributeInfo("Name", "java.lang.String", "Name:name string.", true, true, false);

		MBeanParameterInfo[] params = null;
		operations[0] = new MBeanOperationInfo("print", "print():print the name", params, "void", MBeanOperationInfo.INFO);
		mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
	}

	private void dynamicAddOperation() {
		init();
		operations = new MBeanOperationInfo[2];
		buildDynamicMBean();
		operations[1] = new MBeanOperationInfo("print1", "print1():print the name", null, "void", MBeanOperationInfo.INFO);
		mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
	}

	@Override
	public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
		if (attribute == null)
			return null;
		if(attribute.equals("Name"))
			return name;
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		if(attribute == null)
			return;
		String attr_name = attribute.getName();
		Object value = attribute.getValue();
		try {
			if(attr_name.equals("Name")) {
				if(value == null)
					name = null;
				else if ((Class.forName("java.lang.String")).isAssignableFrom(value.getClass())) {
					name = (String)value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		if(attributes == null)
			return null;
		AttributeList resultList = new AttributeList();
		if(attributes.length ==0)
			return resultList;
		for(int i = 0 ; i < attributes.length; i ++) {
			try {
				Object value = getAttribute(attributes[i]);
				resultList.add(new Attribute(attributes[i], value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		if(attributes == null)
			return null;
		AttributeList resultList = new AttributeList();
		if(attributes.isEmpty())
			return resultList;
		for(Iterator i = attributes.iterator(); i.hasNext();) {
			Attribute attr = (Attribute)i.next();
			try {
				setAttribute(attr);
				String name = attr.getName();
				Object value = getAttribute(name);
				resultList.add(new Attribute(name, value));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return resultList;
			
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
		if(actionName.equals("print")) {
			System.out.println("Hello,"+name+",this is HelloDynamic!");
			dynamicAddOperation();
			return null;
		}else if (actionName.equals("print1")) {
			System.out.println("this is dynamic added method"+actionName);
			return null;
		}else {
			throw new RuntimeException();
		}
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		return mBeanInfo;
	}

}
