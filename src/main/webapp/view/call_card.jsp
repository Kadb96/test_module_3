<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/31/2025
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <style>
        :root {
            --primary: #0070f3;
            --primary-foreground: #ffffff;
            --secondary: #f5f5f5;
            --secondary-foreground: #111111;
            --accent: #f8f9fa;
            --accent-foreground: #343a40;
            --background: #ffffff;
            --foreground: #111111;
            --card: #ffffff;
            --card-foreground: #111111;
            --popover: #ffffff;
            --popover-foreground: #111111;
            --muted: #f5f5f5;
            --muted-foreground: #6c757d;
            --destructive: #dc3545;
            --destructive-foreground: #ffffff;
            --border: #dee2e6;
            --input: #ced4da;
            --ring: #0070f3;
        }

        body {
            background-color: var(--background);
            color: var(--foreground);
            font-family: system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial;
        }

        .table {
            background: var(--card);
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .table thead th {
            background-color: var(--accent);
            color: var(--accent-foreground);
            border-bottom: 2px solid var(--border);
        }

        .table tbody tr:hover {
            background-color: var(--muted);
        }

        .btn-primary {
            background-color: var(--primary);
            border-color: var(--primary);
            color: var(--primary-foreground);
        }

        .btn-danger {
            background-color: var(--destructive);
            border-color: var(--destructive);
            color: var(--destructive-foreground);
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--ring);
            box-shadow: 0 0 0 0.2rem rgba(0, 112, 243, 0.25);
        }

        .modal-content {
            background-color: var(--popover);
            color: var(--popover-foreground);
        }

        .modal-header {
            border-bottom: 1px solid var(--border);
        }

        .modal-footer {
            border-top: 1px solid var(--border);
        }

        input:invalid {
            border: 2px solid red;
        }

        @media (max-width: 768px) {
            .table-responsive {
                margin-bottom: 1rem;
                border: 0;
            }
        }
    </style>
</head>
<body>
<div class="container py-5">
    <header class="mb-5 text-center">
        <h1 class="display-4">Mượn sách</h1>
    </header>
    <form action="/callCards" method="post">
        <input type="hidden" name="action" value="borrowBook">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <tbody class="table-light">
                <tr>
                    <th><label class="form-label">Mã mượn sách</label></th>
                    <td>
                        <input type="text" class="form-control" name="callCardId" required
                               pattern="^MS-[0-9]{4}$" placeholder="MS-XXXX">
                    </td>
                </tr>
                <tr>
                    <th><label class="form-label">Tên sách</label></th>
                    <td>
                        <input type="hidden" name="bookId" value="${book.getBookId()}">
                        <input type="text" class="form-control" name="bookName" readonly value="${book.getBookName()}">
                    </td>
                </tr>
                <tr>
                    <th><label class="form-label">Tên học sinh</label></th>
                    <td>
                        <select name="studentId" class="form-select" required>
                            <c:forEach items="${students}" var="student">
                                <option value="${student.getStudentId()}">${student.getStudentName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label class="form-label">Ngày mượn sách</label></th>
                    <td>
                        <input type="text" name="borrowedAtString" value="${formatedDateNow}">
                    </td>
                </tr>
                <tr>
                    <th><label class="form-label">Ngày trả sách</label></th>
                    <td>
                        <input type="text" name="returnedAtString" required
                               pattern="^[0-9]{2}/[0-9]{2}/[0-9]{4}$" placeholder="dd/mm/yyyy">
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="row align-middle">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                <button type="submit" class="btn btn-primary">Mượn sách</button>
            </div>
        </div>
    </form>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete product <span id="deleteProductName"></span>?
            </div>

            <div class="modal-footer">
                <form action="/products" method="post">
                    <input type="hidden" name="action" value="deleteProduct">
                    <input type="hidden" name="id" id="deleteId">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    function getDeleteProductInfo(deleteId, deleteProductName) {
        document.getElementById("deleteProductName").innerText = deleteProductName;
        document.getElementById("deleteId").value = deleteId;
    }
</script>
</body>
</html>
