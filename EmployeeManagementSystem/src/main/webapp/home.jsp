<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h1>Employee List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Department</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="employees">
                <tr>
                    <td><s:property value="id"/></td>
                    <td><s:property value="name"/></td>
                    <td><s:property value="age"/></td>
                    <td><s:property value="department"/></td>
                    <td>
                        <s:a href="updateEmployee?id=<s:property value='id'">Edit</s:a>
                        <s:a href="deleteEmployee?id=<s:property value='id'">Delete</s:a>
                    </td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
    <s:a href="addUpdateEmployee">Add New Employee</s:a>
</body>
</html>
