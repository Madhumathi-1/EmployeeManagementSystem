package com.example.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
	private List<Employee> employees;

    private Connection getConnection() {
        String url = "jdbc:mysql://localhost/employeedb";
        String user = "madhumathi";
        String password = "mad@1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String execute() {
        employees = new ArrayList<>();
        retrieveEmployees();
        return SUCCESS;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String updateEmployee(Employee employee) {
        try (Connection conn = getConnection()) {
            String query = "UPDATE employees SET name = ?, age = ?, department = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, employee.getName());
                statement.setInt(2, employee.getAge());
                statement.setString(3, employee.getDepartment());
                statement.setInt(4, employee.getId());

                statement.executeUpdate();
            }
            retrieveEmployees();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String deleteEmployee(int id) {
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM employees WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            retrieveEmployees();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    private void retrieveEmployees() {
        employees = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM employees";
            try (PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String department = resultSet.getString("department");
                    employees.add(new Employee(id, name, age, department));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveEmployee(Employee employee) throws SQLException {
        try (Connection conn = getConnection()) {
            String query = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, employee.getName());
                statement.setInt(2, employee.getAge());
                statement.setString(3, employee.getDepartment());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                	retrieveEmployees();
                    return SUCCESS; // Redirect or show a success message
                    
                } else {
                    return ERROR; // Handle failure or no rows affected
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR; // Return error in case of an exception
        }
    }


    public class Employee {
        private int id;
        private String name;
        private int age;
        private String department;

        public Employee(int id, String name, int age, String department) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.department = department;
        }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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
}
