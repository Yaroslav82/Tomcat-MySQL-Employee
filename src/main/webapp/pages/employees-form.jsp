<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Employees</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>

<body>

  <div class="container">
    <div class="pt-3 d-flex flex-column align-items-center">
      <h3 class="pb-3">Employees Management</h3>

      <div class="btn-box">
        <a href="<%=request.getContextPath()%>/list" class="btn btn-success">View Employees</a>
      </div>
    </div>

    <div class="text-center pt-5 pb-3">
      <h4>
        <c:if test="${employee != null}">
            Edit Employee
        </c:if>
        <c:if test="${employee == null}">
            Add New Employee
        </c:if>
      </h4>
    </div>

    <c:if test="${employee != null}">
        <form action="update" method="post">
    </c:if>
    <c:if test="${employee == null}">
        <form action="insert" method="post">
    </c:if>
        <c:if test="${employee != null}">
            <input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
        </c:if>
        <div class="mb-3">
            <label class="form-label">Name:</label>
            <input required class="form-control" type="text" name="name" value="<c:out value='${employee.name}' />">
        </div>
        <div class="mb-3">
            <label class="form-label">Position:</label>
            <input required class="form-control" type="text" name="position" value="<c:out value='${employee.position}' />">
        </div>
        <div class="mb-3">
            <label class="form-label">Phone:</label>
            <input required class="form-control" type="text" name="phone" value="<c:out value='${employee.phone}' />">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
      </form>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
</body>

</html>