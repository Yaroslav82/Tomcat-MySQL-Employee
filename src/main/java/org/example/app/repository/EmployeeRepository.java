package org.example.app.repository;

import org.example.app.database.DBConn;
import org.example.app.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepository {

    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, position, phone) VALUES (?, ?, ?)";

        try (Connection conn = DBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setString(3, employee.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DBConn.connect();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("phone")));
            }
            return employees;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Employee readEmployeeById(int id) {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = DBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                employee = new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return employee;
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, position = ?, phone = ? WHERE id = ?";

        try (Connection conn = DBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setString(3, employee.getPhone());
            pstmt.setInt(4, employee.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
