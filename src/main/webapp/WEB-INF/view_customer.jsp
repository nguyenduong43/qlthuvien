<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Borrow List</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #74ebd5, #ACB6E5);
            min-height: 100vh;
            padding: 40px 20px;
        }

        .container {
            max-width: 1150px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .search-box {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-bottom: 25px;
        }

        .search-box input[type="text"] {
            width: 320px;
            padding: 12px 14px;
            border: 1px solid #ccc;
            border-radius: 8px;
            outline: none;
            font-size: 14px;
        }

        .search-box input[type="text"]:focus {
            border-color: #4facfe;
        }

        .search-box button {
            padding: 12px 18px;
            border: none;
            background: #4facfe;
            color: white;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
            transition: 0.3s;
        }

        .search-box button:hover {
            background: #00c6ff;
        }

        .table-wrapper {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 12px;
            overflow: hidden;
        }

        th {
            background: #4facfe;
            color: white;
            padding: 14px 12px;
            text-align: center;
            font-size: 14px;
        }

        td {
            padding: 12px 10px;
            text-align: center;
            border-bottom: 1px solid #eee;
            font-size: 14px;
            color: #333;
        }

        tr:nth-child(even) {
            background: #f9f9f9;
        }

        tr:hover {
            background: #eef7ff;
        }

        .pagination {
            margin-top: 25px;
            text-align: center;
        }

        .pagination a {
            display: inline-block;
            margin: 0 5px;
            padding: 10px 14px;
            text-decoration: none;
            color: #4facfe;
            border: 1px solid #4facfe;
            border-radius: 8px;
            transition: 0.3s;
            font-size: 14px;
        }

        .pagination a:hover {
            background: #4facfe;
            color: white;
        }
        .action-btn {
            padding: 6px 12px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 13px;
            color: white;
            transition: 0.3s;
            display: inline-block;
        }

        /* RETURN */
        .btn-return {
            background-color: #4facfe;
        }

        .btn-return:hover {
            background-color: #00c6ff;
        }

        /* DELETE */
        .btn-delete {
            background-color: #e74c3c;
        }

        .btn-delete:hover {
            background-color: #c0392b;
        }

        .status {
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 13px;
            font-weight: bold;
            display: inline-block;
            min-width: 95px;
        }

        .borrowing {
            background-color: #e3f2fd;
            color: #1976d2;
        }

        .overdue {
            background-color: #ffebee;
            color: #d32f2f;
        }

        .returned {
            background-color: #e8f5e9;
            color: #2e7d32;
        }

        .empty-message {
            text-align: center;
            padding: 20px;
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Customer Borrow List</h2>

    <form class="search-box" method="get" action="/home">
        <input type="hidden" name="action" value="view-customer">
        <input type="text" name="search" value="${search1}" placeholder="Search customer...">
        <button type="submit">🔍</button>
    </form>

    <div class="table-wrapper">
        <table>
            <tr>
                <th>Customer code</th>
                <th>Name</th>
                <th>Class</th>
                <th>Address</th>
                <th>Birthday</th>
                <th>Book name</th>
                <th>Borrow date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:choose>
                <c:when test="${not empty customers}">
                    <c:forEach items="${customers}" var="customer">
                        <tr>
                            <td>${customer.getCustomer_code()}</td>
                            <td>${customer.getName()}</td>
                            <td>${customer.getCustomer_class()}</td>
                            <td>${customer.getAddress()}</td>
                            <td>${customer.getBirthday()}</td>
                            <td>${customer.getBook_name()}</td>
                            <td>${customer.getBorrow_date()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${customer.getStatus() eq 'BORROWING'}">
                                        <span class="status borrowing">BORROWING</span>
                                    </c:when>
                                    <c:when test="${customer.getStatus() eq 'OVERDUE'}">
                                        <span class="status overdue">OVERDUE</span>
                                    </c:when>
                                    <c:when test="${customer.getStatus() eq 'RETURNED'}">
                                        <span class="status returned">RETURNED</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status">${customer.getStatus()}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${customer.getStatus() eq 'RETURNED'}">
                                        <a class="action-btn btn-delete"
                                           href="/home?action=delete-borrow&id=${customer.getId()}"
                                           onclick="return confirm('Bạn có chắc muốn xoá không?')">
                                            DELETE
                                        </a>
                                    </c:when>

                                    <c:otherwise>
                                        <a class="action-btn btn-return"
                                           href="/home?action=returned&id=${customer.getId()}"
                                           onclick="return confirm('Xác nhận đã trả sách?')">
                                            RETURNED
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <tr>
                        <td colspan="8" class="empty-message">No customer data found</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </div>

    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="/home?action=view-customer&page=${i}&search=${search1}">${i}</a>
        </c:forEach>
    </div>
</div>
</body>
</html>