<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>

    <style>

        body{
            font-family: Arial;
            background: linear-gradient(135deg,#74ebd5,#ACB6E5);
            height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
        }

        /* FORM CARD */

        .form-container{
            background:white;
            padding:35px;
            border-radius:10px;
            width:420px;
            box-shadow:0 10px 25px rgba(0,0,0,0.2);
        }

        h2{
            text-align:center;
            margin-bottom:25px;
        }

        /* TABLE */

        table{
            width:100%;
        }

        td{
            padding:10px;
        }

        /* INPUT */

        input[type="text"]{
            width:100%;
            padding:8px;
            border:1px solid #ccc;
            border-radius:5px;
        }

        input[type="radio"]{
            margin-right:5px;
        }

        /* BUTTON */

        button{
            width:100%;
            padding:10px;
            background:#3498db;
            border:none;
            color:white;
            font-size:16px;
            border-radius:6px;
            cursor:pointer;
        }

        button:hover{
            background:#2980b9;
        }

        /* ERROR */

        .error{
            color:red;
            font-size:13px;
        }
        select{
            width:100%;
            padding:8px;
            border:1px solid #ccc;
            border-radius:5px;
            background:white;
            cursor:pointer;
        }

        select:focus{
            outline:none;
            border-color:#3498db;
            box-shadow:0 0 5px rgba(52,152,219,0.5);
        }

    </style>

</head>

<body>

<div class="form-container">

    <h2>Add Book</h2>

    <form method="post" onsubmit="return validateForm()">

        <table>

            <tr>
                <td>Name book</td>
                <td>
                    <input type="text" name="name" id="name">
                    <div class="error" id="nameError"></div>
                </td>
            </tr>

            <tr>
                <td>Description</td>
                <td>
                    <input type="text" name="description" id="description">
                    <div class="error" id="descError"></div>
                </td>
            </tr>

            <tr>
                <td>Image</td>
                <td>
                    <input type="text" name="image" id="image">
                    <div class="error" id="imageError"></div>
                </td>
            </tr>

            <tr>
                <td>Status</td>
                <td>
                    <label><input type="radio" name="status" value="NEW"> NEW</label>
                    <label><input type="radio" name="status" value="OLD"> OLD</label>
                    <div class="error" id="statusError"></div>
                </td>
            </tr>

            <tr>
                <td>Category</td>
                <td>
                <select name="category_name" id="category">
                    <c:forEach items="${category}" var="c">
                        <option value="${c}">${c}</option>
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

</div>

<script>

    function validateForm(){

        let valid=true;

        /* reset error */

        document.querySelectorAll(".error").forEach(e=>e.innerText="");

        /* name */

        let name=document.getElementById("name").value.trim();
        if(name===""){
            document.getElementById("nameError").innerText="Name is required";
            valid=false;
        }

        /* description */

        let desc=document.getElementById("description").value.trim();
        if(desc===""){
            document.getElementById("descError").innerText="Description is required";
            valid=false;
        }

        /* image */

        let image=document.getElementById("image").value.trim();
        if(image===""){
            document.getElementById("imageError").innerText="Image is required";
            valid=false;
        }

        /* category */

        let category=document.getElementById("category").value.trim();
        if(category===""){
            document.getElementById("categoryError").innerText="Category is required";
            valid=false;
        }

        /* publisher */

        let publisher=document.getElementById("publisher").value.trim();
        if(publisher===""){
            document.getElementById("publisherError").innerText="Publisher is required";
            valid=false;
        }

        /* status */

        let status=document.querySelector('input[name="status"]:checked');
        if(!status){
            document.getElementById("statusError").innerText="Please choose status";
            valid=false;
        }

        return valid;

    }

</script>

</body>
</html>