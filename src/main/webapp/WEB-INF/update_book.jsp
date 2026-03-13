<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13/03/2026
  Time: 11:10 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  method="post">
    <table>

        <tr>
            <td>Name book</td>
            <td>
                <input type="text" name="name" id="name" value="${book.getName()}">
                <div class="error" id="nameError"></div>
            </td>
        </tr>

        <tr>
            <td>Description</td>
            <td>
                <input type="text" name="description" id="description" value="${book.getDescription()}">
                <div class="error" id="descError"></div>
            </td>
        </tr>

        <tr>
            <td>Image</td>
            <td>
                <input type="text" name="image" id="image" value="${book.getImage()}">
                <div class="error" id="imageError"></div>
            </td>
        </tr>

        <tr>
            <td>Status</td>
            <td>
                <label><input type="radio" name="status" value="NEW" ${book.getStatus()=="NEW" ? 'checked':''}> NEW</label>
                <label><input type="radio" name="status" value="OLD" ${book.getStatus()=="OLD" ?'checked':''}> OLD</label>
                <div class="error" id="statusError"></div>
            </td>
        </tr>

        <tr>
            <td>Category</td>
            <td>
                <select name="category_name" id="category" >
                    <c:forEach items="${category}" var="c">
                    <option value="${c}" ${book.getCategory_name()==c ? 'selected':''} >${c}</option>
                    </c:forEach>
            </td>
            </select>
        </tr>

        <tr>
            <td>Publisher</td>
            <td>
                <%--                    <input type="text" name="publisher_name" id="publisher">--%>
                <%--                    <div class="error" id="publisherError"></div>--%>
                <select name="publisher_name" id="publisher">
                    <c:forEach items="${publisher}" var="p">
                        <option value="${p}">${p}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <button type="submit">Add Book</button>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
