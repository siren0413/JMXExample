package com.siren.modelMBean;

import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.RequiredModelMBean;

public class ModelMBeanUtils {

	private static final boolean READABLE = true;
	private static final boolean WRITABLE = true;
	private static final boolean BOOLEAN = true;
	private static final String STRING_CLASS = "java.lang.String";
	public static RequiredModelMBean createModlerMBean() {
		RequiredModelMBean model = null;
		try {
			model = new RequiredModelMBean();
			model.setManagedResource(new Hello(), "ObjectReference");
			ModelMBeanInfo info = createModelMBeanInfo();
			model.setModelMBeanInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	private static ModelMBeanInfo createModelMBeanInfo() {

		ModelMBeanAttributeInfo nameAttrInfo = new ModelMBeanAttributeInfo("name", STRING_CLASS, "people name", READABLE,
				WRITABLE, BOOLEAN, null);

		ModelMBeanOperationInfo print1Info = new ModelMBeanOperationInfo("printHello", null, null, "void",
				MBeanOperationInfo.INFO, null);

		ModelMBeanOperationInfo print2Info;
		MBeanParameterInfo[] param2 = new MBeanParameterInfo[1];
		param2[0] = new MBeanParameterInfo("name", STRING_CLASS, "say hello to who");
		print2Info = new ModelMBeanOperationInfo("printHello", null, param2, "void", MBeanOperationInfo.INFO, null);

		// create ModelMBeanInfo
		ModelMBeanInfo mbeanInfo = new ModelMBeanInfoSupport(RequiredModelMBean.class.getName(), null,
				new ModelMBeanAttributeInfo[]{nameAttrInfo}, null, new ModelMBeanOperationInfo[]{print1Info, print2Info}, null,
				null);
		return mbeanInfo;
		
		
	}

}
