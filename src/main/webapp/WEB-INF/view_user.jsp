<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User List</title>

    <style>
        body{
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg,#74ebd5,#ACB6E5);
            margin:0;
            padding:40px;
        }

        h2{
            text-align:center;
            color:white;
        }

        table{
            margin:auto;
            border-collapse: collapse;
            background:white;
            width:80%;
            box-shadow:0 8px 20px rgba(0,0,0,0.2);
            border-radius:8px;
            overflow:hidden;
        }

        th{
            background:#4CAF50;
            color:white;
            padding:12px;
        }

        td{
            padding:10px;
            text-align:center;
            border-bottom:1px solid #ddd;
        }

        tr:hover{
            background:#f2f2f2;
        }

        img{
            border-radius:6px;
        }

        .back{
            display:block;
            width:150px;
            text-align:center;
            margin:30px auto;
            padding:10px;
            background:#2196F3;
            color:white;
            text-decoration:none;
            border-radius:5px;
        }

        .back:hover{
            background:#1976D2;
        }
        .action-btn{
            padding:6px 12px;
            text-decoration:none;
            border-radius:5px;
            font-size:14px;
            color:white;
        }

        .delete-btn{
            background-color:#e74c3c;
        }

        .delete-btn:hover{
            background-color:#c0392b;
        }

        .restore-btn{
            background-color:#2ecc71;
        }

        .restore-btn:hover{
            background-color:#27ae60;
        }
        .pagination{
            text-align:center;
            margin-top:20px;
        }

        .pagination a{
            display:inline-block;
            padding:8px 14px;
            margin:4px;
            text-decoration:none;
            background:white;
            color:#333;
            border-radius:5px;
            border:1px solid #ddd;
            transition:0.3s;
        }

        .pagination a:hover{
            background:#4CAF50;
            color:white;
        }
    </style>
</head>

<body>

<h2>User List</h2>

<table>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Birthday</th>
        <th>Image</th>
        <th>Position</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getPhone()}</td>
            <td>${user.getBirthday()}</td>
            <td>
                <img src="${user.getImage()}" width="80" height="80">
            </td>
            <td>${user.getPosition()}</td>
            <td>${user.getStatus()}</td>
            <td>
                <c:choose>

                    <c:when test="${user.getStatus() == 'active'}">
                        <a class="action-btn delete-btn"
                           href="/home?action=delete-user&id=${user.getId()}"
                           onclick="return confirmDelete()">
                            Delete
                        </a>
                        <a class="action-btn restore-btn"
                           href="/home?action=update-listuser&id=${user.getId()}">
                           update
                        </a>

                    </c:when>

                    <c:otherwise>
                        <a class="action-btn restore-btn"
                           href="/home?action=restore-user&id=${user.getId()}">
                            Restore
                        </a>
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>
    </c:forEach>

</table>
<div class="pagination">
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="/home?action=select-user&page=${i}">${i}</a>
    </c:forEach>
</div>
<a class="back" href="/lib">Back to Home</a>
<script>

    function confirmDelete(){
        return confirm("Bạn có chắc chắn muốn xoá user này?");
    }

</script>
</body>
</html>