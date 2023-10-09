package org.example.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.entity.Employee;
import org.example.app.repository.EmployeeRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController extends HttpServlet {

    private final EmployeeRepository repository = new EmployeeRepository();

    // Нажаль тег <form> в html підтримує тільки Get та Post запити,
    // тому методи doDelete та doPut я використав у doPost
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteEmployee(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateEmployee(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/insert" -> insertEmployee(request, response);
                case "/delete" -> doDelete(request, response);
                case "/update" -> doPut(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new" -> showNewForm(request, response);
                case "/edit" -> showEditForm(request, response);
                default -> listEmployee(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = repository.readEmployees();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/employees.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/employees-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = repository.readEmployeeById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/employees-form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        Employee newEmployee = new Employee(name, position, phone);
        repository.createEmployee(newEmployee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        Employee employee = new Employee(id, name, position, phone);
        repository.updateEmployee(employee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = new Employee(id);
        repository.deleteEmployee(employee);
        response.sendRedirect("list");
    }
}
