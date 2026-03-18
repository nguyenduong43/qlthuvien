<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Book Form</title>

    <style>

        body{
            margin:0;
            font-family:Arial, sans-serif;
            background:linear-gradient(135deg,#74ebd5,#ACB6E5);
        }

        .container{
            width:500px;
            margin:80px auto;
            background:white;
            padding:30px;
            border-radius:12px;
            box-shadow:0 10px 30px rgba(0,0,0,0.2);
        }

        h2{
            text-align:center;
            margin-bottom:25px;
        }

        table{
            width:100%;
        }

        td{
            padding:10px;
            vertical-align:top;
        }

        input[type="text"], select{
            width:100%;
            padding:8px;
            border-radius:6px;
            border:1px solid #ccc;
            outline:none;
            transition:0.3s;
            box-sizing:border-box;
        }

        input[type="text"]:focus, select:focus{
            border-color:#3498db;
        }

        .radio-group{
            display:flex;
            gap:20px;
        }

        .btn{
            padding:10px 16px;
            border:none;
            border-radius:6px;
            cursor:pointer;
            font-weight:bold;
        }

        .save-btn{
            background:#2ecc71;
            color:white;
        }

        .save-btn:hover{
            background:#27ae60;
        }

        .cancel-btn{
            background:#e74c3c;
            color:white;
            text-decoration:none;
            padding:10px 16px;
            border-radius:6px;
            display:inline-block;
        }

        .cancel-btn:hover{
            background:#c0392b;
        }

        .error{
            color:red;
            font-size:12px;
            margin-top:4px;
        }

        .btn-group{
            text-align:center;
            margin-top:15px;
        }

        .input-error{
            border:1px solid red !important;
        }

    </style>

</head>

<body>

<div class="container">

    <h2>Book Information</h2>

    <form method="post" onsubmit="return validateForm()">

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
                    <div class="radio-group">
                        <label>
                            <input type="radio" name="status" value="NEW"
                            ${book.getStatus()=="NEW" ? 'checked' : ''}>
                            NEW
                        </label>

                        <label>
                            <input type="radio" name="status" value="OLD"
                            ${book.getStatus()=="OLD" ? 'checked' : ''}>
                            OLD
                        </label>
                    </div>
                    <div class="error" id="statusError"></div>
                </td>
            </tr>

            <tr>
                <td>Category</td>
                <td>
                    <select name="category_name" id="category">
                        <option value="">-- Select category --</option>
                        <c:forEach items="${category}" var="c">
                            <option value="${c}" ${book.getCategory_name()==c ? 'selected' : ''}>
                                    ${c}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="error" id="categoryError"></div>
                </td>
            </tr>

            <tr>
                <td>Publisher</td>
                <td>
                    <select name="publisher_name" id="publisher">
                        <option value="">-- Select publisher --</option>
                        <c:forEach items="${publisher}" var="p">
                            <option value="${p}" ${book.getPublisher_name()==p ? 'selected' : ''}>
                                    ${p}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="error" id="publisherError"></div>
                </td>
            </tr>

        </table>

        <div class="btn-group">
            <button class="btn save-btn" type="submit">Save Book</button>
            <a class="cancel-btn" href="/lib">Cancel</a>
        </div>

    </form>

</div>

<script>
    function validateForm(){
        let valid = true;

        document.querySelectorAll(".error").forEach(function(e){
            e.innerText = "";
        });

        document.querySelectorAll("input, select").forEach(function(e){
            e.classList.remove("input-error");
        });

        let name = document.getElementById("name").value.trim();
        if(name === ""){
            document.getElementById("nameError").innerText = "Name is required";
            document.getElementById("name").classList.add("input-error");
            valid = false;
        }

        let desc = document.getElementById("description").value.trim();
        if(desc === ""){
            document.getElementById("descError").innerText = "Description is required";
            document.getElementById("description").classList.add("input-error");
            valid = false;
        }

        let image = document.getElementById("image").value.trim();
        if(image === ""){
            document.getElementById("imageError").innerText = "Image is required";
            document.getElementById("image").classList.add("input-error");
            valid = false;
        }

        let status = document.querySelector('input[name="status"]:checked');
        if(!status){
            document.getElementById("statusError").innerText = "Please choose status";
            valid = false;
        }

        let category = document.getElementById("category").value;
        if(category === ""){
            document.getElementById("categoryError").innerText = "Please select category";
            document.getElementById("category").classList.add("input-error");
            valid = false;
        }

        let publisher = document.getElementById("publisher").value;
        if(publisher === ""){
            document.getElementById("publisherError").innerText = "Please select publisher";
            document.getElementById("publisher").classList.add("input-error");
            valid = false;
        }

        return valid;
    }
</script>

</body>
</html>