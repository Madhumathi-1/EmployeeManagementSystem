<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add or Update Employee</title>
</head>
<body>
    <h1>Add or Update Employee</h1>
    <s:form action="saveEmployee">
        <s:textfield name="employee.name" label="Name"/>
        <s:textfield name="employee.age" label="Age"/>
        <s:textfield name="employee.department" label="Department"/>
        <s:submit value="Save"/>
    </s:form>
</body>
</html>
