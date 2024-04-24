package com.example.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

public class SaveEmployeeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
    private String name;
    private int age;
    private String department;

    public String execute() {
        String url = "jdbc:mysql://localhost/employeedb";
        String user = "madhumathi"; // Your database username
        String password = "mad@1"; // Your database password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setInt(2, age);
                statement.setString(3, department);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return SUCCESS;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
