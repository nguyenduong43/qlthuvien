<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Library</title>

    <style>
        html{
            scroll-behavior: smooth;
        }

        body{
            margin:0;
            font-family:Arial, sans-serif;
            background:linear-gradient(135deg,#74ebd5,#ACB6E5);
        }

        /* NAVBAR */

        .navbar{
            background:#2c3e50;
            padding:12px 40px;
            display:flex;
            align-items:center;
        }

        .navbar a{
            color:white;
            text-decoration:none;
            margin-right:20px;
            padding:8px 14px;
            border-radius:6px;
            transition:0.3s;
        }

        .navbar a:hover{
            background:#3498db;
        }

        /* CONTAINER */

        .container{
            width:85%;
            margin:40px auto;
            background:white;
            padding:30px;
            border-radius:12px;
            box-shadow:0 10px 30px rgba(0,0,0,0.2);
        }

        h1{
            text-align:center;
            margin-bottom:25px;
        }

        /* SEARCH BAR */

        .search-box{
            display:flex;
            justify-content:center;
            margin-bottom:25px;
        }

        .search-box input{
            padding:10px;
            width:300px;
            border-radius:6px 0 0 6px;
            border:1px solid #ccc;
            outline:none;
        }

        .search-box button{
            padding:10px 18px;
            border:none;
            background:#3498db;
            color:white;
            border-radius:0 6px 6px 0;
            cursor:pointer;
            font-weight:bold;
        }

        .search-box button:hover{
            background:#2980b9;
        }

        /* TABLE */

        table{
            width:100%;
            border-collapse:collapse;
        }

        th{
            background:#3498db;
            color:white;
            padding:12px;
        }

        td{
            padding:12px;
            text-align:center;
            border-bottom:1px solid #eee;
        }

        tr:hover{
            background:#f7f9fb;
        }

        /* IMAGE */

        img{
            border-radius:8px;
            object-fit:cover;
            box-shadow:0 4px 10px rgba(0,0,0,0.2);
        }

        /* STATUS */

        .status{
            padding:5px 12px;
            border-radius:20px;
            color:white;
            font-weight:bold;
            font-size:13px;
        }

        .status.NEW{
            background:#2ecc71;
        }

        .status.OLD{
            background:#e74c3c;
        }

        /* BUTTONS */

        .btn{
            padding:6px 12px;
            border-radius:6px;
            text-decoration:none;
            color:white;
            font-weight:bold;
            margin:0 3px;
        }

        .delete-btn{
            background:#e74c3c;
        }

        .delete-btn:hover{
            background:#c0392b;
        }

        .update-btn{
            background:#f39c12;
        }

        .update-btn:hover{
            background:#d68910;
        }

        /* ALERT */

        .alert{
            background:#2ecc71;
            color:white;
            padding:12px;
            border-radius:6px;
            text-align:center;
            margin-bottom:20px;
        }

        /* PAGINATION */

        .pagination{
            text-align:center;
            margin-top:25px;
        }

        .pagination a{
            display:inline-block;
            padding:8px 14px;
            margin:4px;
            border-radius:6px;
            text-decoration:none;
            background:#ecf0f1;
            color:#2c3e50;
            font-weight:bold;
        }

        .pagination a:hover{
            background:#3498db;
            color:white;
        }

    </style>

</head>

<body>

<div class="navbar">

    <a href="/lib">Home</a>
    <a href="/home?action=update-user">Update Profile</a>
    <a href="/home?action=change-pass">Change Password</a>

    <c:if test="${user.getPosition() eq 'admin'}">
        <a href="/register">Register</a>
        <a href="/home?action=select-user">List User</a>
        <a href="/lib?action=view-category">View Categories</a>
    </c:if>

    <a href="/lib?action=add-book">Add Book</a>
    <c:if test="${user.getPosition() eq 'librarian'}">
        <a href="/home?action=add-customer">Add customer</a>
        <a href="/home?action=view-customer">View customer</a>
        <a href="/home?action=create-borrow">Add borrow slips</a>
    </c:if>

    <a href="/home?action=logout">Logout</a>

</div>

<div class="container">

    <h1>Library</h1>

    <c:if test="${param.message == 'deleted'}">
        <div class="alert">
            Delete book successfully!
        </div>
    </c:if>

    <!-- SEARCH -->

    <form class="search-box" method="get" action="/lib">
        <input type="hidden" name="action" value="search-book">
        <input type="text" name="search" placeholder="Search book...">
        <button type="submit">🔍</button>
    </form>

    <table>

        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Image</th>
            <th>Status</th>
            <th>Category</th>
            <th>Publisher</th>
            <th>Action</th>
        </tr>

        <c:forEach items="${libraries}" var="book">

            <tr>

                <td>${book.getName()}</td>

                <td>${book.getDescription()}</td>

                <td>
                    <img src="${book.getImage()}" width="100" height="100">
                </td>

                <td>
<span class="status ${book.getStatus()}">
        ${book.getStatus()}
</span>
                </td>

                <td>${book.getCategory_name()}</td>

                <td>${book.getPublisher_name()}</td>

                <td>

                    <a class="btn update-btn"
                       href="/lib?action=update-book&id=${book.getId()}">
                        Update
                    </a>

                    <a class="btn delete-btn"
                       href="/lib?action=delete-book&id=${book.getId()}"
                       onclick="return confirmDelete()">
                        Delete
                    </a>

                </td>

            </tr>

        </c:forEach>

    </table>

    <!-- PAGINATION -->

    <div class="pagination">

        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="/lib?page=${i}&search=${search1}">${i}</a>
        </c:forEach>

    </div>

</div>

<script>

    function confirmDelete(){
        return confirm("Are you sure you want to delete this book?");
    }

</script>

</body>
</html>