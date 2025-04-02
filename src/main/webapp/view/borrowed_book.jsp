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
        <h1 class="display-4">Danh sách sách</h1>
    </header>
    <div class="row mb-4">
        <form action="/books" method="post" class="row mb-4 col-md-10">
            <input type="hidden" name="action" value="showBook">
            <div class="col-md-6">
                <input type="text" class="form-control" placeholder="Search by product name" id="searchInput"
                       name="productName">
            </div>
            <div class="col-md-3">
                <select class="form-select" id="typeFilter" name="productTypeId">
                    <option value="0">All Product Types</option>
                    <c:forEach items="${productTypes}" var="productType">
                        <option value="${productType.getId()}">${productType.getProductTypeName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-1">
                <button class="btn btn-light w-100" type="submit">
                    Search
                </button>
            </div>
        </form>
        <div class="col-md-2">
            <button class="btn btn-primary w-100" data-bs-toggle="modal" data-bs-target="#addProductModal"> + Add New
                Product
            </button>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-hover align-middle">
            <thead class="table-light">
            <tr>
                <th>Product Code</th>
                <th>Product Name</th>
                <th>Calculation Unit</th>
                <th>Product Price</th>
                <th>Product Type</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.getProductCode()}</td>
                    <td>${product.getProductName()}</td>
                    <td>${product.getCalculationUnit()}</td>
                    <td>${product.getProductPriceToString()} vnd</td>
                    <td>${product.getProductTypeName()}</td>
                    <td>
                        <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal"
                                onclick="getDeleteProductInfo(`${product.getId()}`, `${product.getProductName()}`)">
                            Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Add Product Modal -->
    <div class="modal fade" id="addProductModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add New Product</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form id="addProductForm" action="/products" method="post">
                        <input type="hidden" name="action" value="addProduct">
                        <div class="mb-3">
                            <label class="form-label">Product Code</label>
                            <input type="text" class="form-control" name="productCode" required
                                   pattern="^MHH-[A-Z0-9]{5}$" placeholder="MHH-XXXXX">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Product Name</label>
                            <input type="text" class="form-control" name="productName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Calculation Unit</label>
                            <select name="calculationUnit" class="form-select" required>
                                    <option>Kg</option>
                                    <option>Túi</option>
                                    <option>Bó</option>
                                    <option>Chiec</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Product Price</label>
                            <input type="number" class="form-control" name="productPrice" required min="1000" step="1">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Product Type</label>
                            <select name="productTypeId" class="form-select" required>
                                <c:forEach items="${productTypes}" var="productType">
                                    <option value="${productType.getId()}">${productType.getProductTypeName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" form="addProductForm" class="btn btn-primary">Add Product</button>
                </div>
            </div>
        </div>
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
