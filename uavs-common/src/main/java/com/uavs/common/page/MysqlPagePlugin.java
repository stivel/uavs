package com.uavs.common.page;
//package com.uavs.common.page;
//
//import java.util.List;
//
//import org.mybatis.generator.api.CommentGenerator;
//import org.mybatis.generator.api.IntrospectedTable;
//import org.mybatis.generator.api.PluginAdapter;
//import org.mybatis.generator.api.dom.java.Field;
//import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
//import org.mybatis.generator.api.dom.java.JavaVisibility;
//import org.mybatis.generator.api.dom.java.Method;
//import org.mybatis.generator.api.dom.java.Parameter;
//import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
//import org.mybatis.generator.api.dom.java.TopLevelClass;
//import org.mybatis.generator.api.dom.xml.Attribute;
//import org.mybatis.generator.api.dom.xml.TextElement;
//import org.mybatis.generator.api.dom.xml.XmlElement;
//
///**
// * 
// * Mysql分页
// * 
// * @author wangshi
// * @version 1.0
// * @date 2015年7月24日 下午2:32:50
// * @since 1.0
// */
//public class MysqlPagePlugin extends PluginAdapter {
//    
//    /**
//     * function description
//     * 
//     * @param topLevelClass
//     * @param introspectedTable
//     * @return
//     */
//    @Override
//    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,IntrospectedTable introspectedTable) {
//        // add field, getter, setter for limit clause
//        addLimit(topLevelClass, introspectedTable, "limitStart");
//        addLimit(topLevelClass, introspectedTable, "limitEnd");
//        addPage(topLevelClass, introspectedTable, "page");
//        return super.modelExampleClassGenerated(topLevelClass,introspectedTable);
//    }
//
//    /** 
//     * function description
//     * 
//     * @param element
//     * @param introspectedTable
//     * @return
//     */
//    @Override
//    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//        // XmlElement isParameterPresenteElemen = (XmlElement) element
//        // .getElements().get(element.getElements().size() - 1);
//        XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$  
//        isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitStart>=0")); //$NON-NLS-1$ //$NON-NLS-2$  
//        //      isNotNullElement.addAttribute(new Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$  
//        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitEnd}"));
//        // isParameterPresenteElemen.addElement(isNotNullElement);
//        element.addElement(isNotNullElement);
//        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,introspectedTable);
//    }
//
//    /**
//     * function description
//     * 
//     * @param topLevelClass
//     * @param introspectedTable
//     * @param name
//     */
//    private void addLimit(TopLevelClass topLevelClass,IntrospectedTable introspectedTable, String name) {
//        CommentGenerator commentGenerator = context.getCommentGenerator();
//        Field field = new Field();
//        field.setVisibility(JavaVisibility.PROTECTED);
//        // field.setType(FullyQualifiedJavaType.getIntInstance());
//        field.setType(PrimitiveTypeWrapper.getIntegerInstance());
//        field.setName(name);
//        // field.setInitializationString("-1");
//        commentGenerator.addFieldComment(field, introspectedTable);
//        topLevelClass.addField(field);
//        char c = name.charAt(0);
//        String camel = Character.toUpperCase(c) + name.substring(1);
//        Method method = new Method();
//        method.setVisibility(JavaVisibility.PUBLIC);
//        method.setName("set" + camel);
//        method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));
//        method.addBodyLine("this." + name + "=" + name + ";");
//        commentGenerator.addGeneralMethodComment(method, introspectedTable);
//        topLevelClass.addMethod(method);
//        method = new Method();
//        method.setVisibility(JavaVisibility.PUBLIC);
//        method.setReturnType(PrimitiveTypeWrapper.getIntegerInstance());
//        method.setName("get" + camel);
//        method.addBodyLine("return " + name + ";");
//        commentGenerator.addGeneralMethodComment(method, introspectedTable);
//        topLevelClass.addMethod(method);
//    }
//    
//    /**
//     * function description
//     * 
//     * @param topLevelClass
//     * @param introspectedTable
//     * @param name
//     */
//    private void addPage(TopLevelClass topLevelClass,IntrospectedTable introspectedTable, String name) {
//        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.ngear.common.page.Page"));
//        CommentGenerator commentGenerator = context.getCommentGenerator();
//        Field field = new Field();
//        field.setVisibility(JavaVisibility.PROTECTED);
//        field.setType(new FullyQualifiedJavaType("com.ngear.common.page.Page"));
//        field.setName(name);
//        commentGenerator.addFieldComment(field, introspectedTable);
//        topLevelClass.addField(field);
//        char c = name.charAt(0);
//        String camel = Character.toUpperCase(c) + name.substring(1);
//        Method method = new Method();
//        method.setVisibility(JavaVisibility.PUBLIC);
//        method.setName("set" + camel);
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("com.ngear.common.page.Page"), name));
//        method.addBodyLine("this." + name + "=" + name + ";");
//        commentGenerator.addGeneralMethodComment(method, introspectedTable);
//        topLevelClass.addMethod(method);
//        method = new Method();
//        method.setVisibility(JavaVisibility.PUBLIC);
//        method.setReturnType(new FullyQualifiedJavaType("com.ngear.common.page.Page"));
//        method.setName("get" + camel);
//        method.addBodyLine("return " + name + ";");
//        commentGenerator.addGeneralMethodComment(method, introspectedTable);
//        topLevelClass.addMethod(method);
//    }
//
//    /**
//     * function description
//     * 
//     * @param warnings
//     * @return
//     */
//    public boolean validate(List<String> warnings) {
//        return true;
//    }
//}