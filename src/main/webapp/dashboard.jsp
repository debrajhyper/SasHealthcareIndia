<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .search-container {
            max-width: 300px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="dashboard">User Dashboard</a>
            <div class="d-flex">
                <span class="navbar-text fs-6 fw-bolder me-5">
                    Welcome, ${sessionScope.user.username}!
                </span>
                <a href="logout" class="btn btn-outline-primary">Logout</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col">
                <h2>User List</h2>
            </div>
            <div class="col-auto">
                <div class="search-container">
                    <input type="text" id="searchInput" class="form-control" placeholder="Search users...">
                </div>
            </div>
        </div>

        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Created At</th>
                </tr>
            </thead>
            <tbody id="userTableBody">
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td><fmt:formatDate value="${user.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const searchInput = document.getElementById('searchInput');
            const userTableBody = document.getElementById('userTableBody');
            const originalRows = Array.from(userTableBody.querySelectorAll('tr'));

            searchInput.addEventListener('input', function() {
                const searchTerm = this.value.toLowerCase();
                const filteredRows = originalRows.filter(row => {
                    const username = row.cells[1].textContent.toLowerCase();
                    return username.includes(searchTerm);
                });

                userTableBody.innerHTML = '';
                filteredRows.forEach(row => userTableBody.appendChild(row.cloneNode(true)));
            });
        });
    </script>
</body>
</html>