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
        <h1 class="display-4">Thống kê sách đang cho mượn</h1>
    </header>
    <div class="row mb-4">
        <form action="/callCards" method="post">
            <div class="row mb-4">
                <input type="hidden" name="action" value="search">
                <div class="col-md-2">
                    <label class="form-label">Tên sách</label>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="bookName">
                </div>
                <div class="col-md-2">
                    <label class="form-label">Tên học sinh</label>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="studentName">
                </div>
                <div class="col-md-2">
                    <button class="btn btn-light w-100" type="submit">
                        Tìm kiếm
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table table-hover align-middle">
            <thead class="table-light">
            <tr>
                <th>Mã mượn sách</th>
                <th>Tên sách</th>
                <th>Tác giả</th>
                <th>Tên học sinh</th>
                <th>Lớp</th>
                <th>Ngày mượn</th>
                <th>Ngày trả</th>
                <th>Trả sách</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${callCards}" var="callCard">
                <tr>
                    <td>${callCard.getCallCardId()}</td>
                    <td>${callCard.getBookName()}</td>
                    <td>${callCard.getBookAuthor()}</td>
                    <td>${callCard.getStudentName()}</td>
                    <td>${callCard.getStudentClass()}</td>
                    <td>${callCard.getBorrowedAtString()}</td>
                    <td>${callCard.getReturnedAtString()}</td>
                    <td>
                        <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#returnModal"
                                onclick="getReturnInfo(`${callCard.getStudentName()}`, `${callCard.getBookName()}`, `${callCard.getCallCardId()}`, `${callCard.getBookId()}`)">
                            Trả sách
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Return Confirmation Modal -->
    <div class="modal fade" id="returnModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Trả sách</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    Học sinh <span id="returnStudentName"></span> thực hiện trả sách <span id="returnBookName"></span>
                </div>

                <div class="modal-footer">
                    <form action="/callCards" method="post">
                        <input type="hidden" name="action" value="returnBook">
                        <input type="hidden" name="callCardId" id="returnCallCardId">
                        <input type="hidden" name="bookId" id="returnBookId">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
                        <button type="submit" class="btn btn-danger">Trả sách</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function getReturnInfo(returnStudentName, returnBookName, returnCallCardId, returnBookId) {
        document.getElementById("returnBookName").innerText = returnBookName;
        document.getElementById("returnStudentName").innerText = returnStudentName;
        document.getElementById("returnCallCardId").value = returnCallCardId;
        document.getElementById("returnBookId").value = returnBookId;
    }
</script>
</body>
</html>
