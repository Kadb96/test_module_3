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
        <h1 class="display-4">Danh sách sách</h1>
    </header>
    <form action="/callCards" method="get">
        <input type="hidden" name="action" value="showAll">
        <button class="btn btn-primary btn-sm" type="submit">
            Thống kê sách đang cho mượn
        </button>
    </form>
    <div class="table-responsive">
        <table class="table table-hover align-middle">
            <thead class="table-light">
            <tr>
                <th>Mã sách</th>
                <th>Tên sách</th>
                <th>Tác giả</th>
                <th>Số lượng</th>
                <th>Mô tả</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.getBookId()}</td>
                    <td>${book.getBookName()}</td>
                    <td>${book.getBookAuthor()}</td>
                    <td>${book.getBookQuantity()}</td>
                    <td>${book.getBookDescription()}</td>
                    <td>
                        <c:if test="${book.getBookQuantity() > 0}">
                            <form action="/books" method="post">
                                <input type="hidden" name="action" value="borrowBook">
                                <input type="hidden" name="bookId" value="${book.getBookId()}">
                                <button class="btn btn-primary btn-sm" type="submit">
                                    Mượn
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${book.getBookQuantity() == 0}">
                            <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#borrowModal">
                                Mượn
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Borrow Modal -->
    <div class="modal fade" id="borrowModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Lỗi!!!!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    Cuốn sách này tạm thời đã cho mượn hết, vui lòng chọn sách khác
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
