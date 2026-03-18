<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Category</title>

    <style>
        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family: Arial;
        }

        body{
            background: linear-gradient(135deg,#74ebd5,#ACB6E5);
            padding:40px;
        }

        .container{
            background:white;
            padding:30px;
            border-radius:16px;
            max-width:900px;
            margin:auto;
            box-shadow:0 10px 30px rgba(0,0,0,0.2);
        }

        .top-bar{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:20px;
        }

        h2{
            color:#333;
        }

        .btn-add{
            padding:10px 16px;
            background:#4facfe;
            color:white;
            border:none;
            border-radius:8px;
            cursor:pointer;
        }

        .btn-add:hover{
            background:#00c6ff;
        }

        .message{
            text-align:center;
            margin-bottom:15px;
            padding:10px;
            border-radius:8px;
            background:#ffecec;
            color:#e74c3c;
        }

        table{
            width:100%;
            border-collapse:collapse;
        }

        th{
            background:#4facfe;
            color:white;
            padding:12px;
        }

        td{
            padding:12px;
            text-align:center;
            border-bottom:1px solid #eee;
        }

        tr:nth-child(even){
            background:#f9f9f9;
        }

        tr:hover{
            background:#eef7ff;
        }

        .action a{
            padding:6px 10px;
            border-radius:6px;
            color:white;
            text-decoration:none;
        }

        .update{
            background:#2ecc71;
        }

        .delete{
            background:#e74c3c;
        }

        /* MODAL */
        .modal{
            display:none;
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            background:rgba(0,0,0,0.5);
            justify-content:center;
            align-items:center;
        }

        .modal-content{
            background:white;
            padding:25px;
            border-radius:10px;
            width:350px;
            position:relative;
        }

        .modal-content input{
            width:100%;
            padding:10px;
            margin-bottom:10px;
        }

        .modal-content button{
            width:100%;
            padding:10px;
            background:#4facfe;
            color:white;
            border:none;
            border-radius:6px;
        }

        .close{
            position:absolute;
            top:10px;
            right:15px;
            cursor:pointer;
        }

    </style>
</head>

<body>

<div class="container">

    <div class="top-bar">
        <h2>Category List</h2>
        <button class="btn-add" onclick="openModal()">+ Add</button>
    </div>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
        <c:remove var="message" scope="session"/>
    </c:if>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Action</th>
        </tr>

        <c:forEach items="${category}" var="item">
            <tr>
                <td>#${item.getCategory_id()}</td>
                <td>${item.getCategory_name()}</td>
                <td class="action">
                    <a class="update"
                       href="javascript:void(0)"
                       onclick="openEditModal('${item.getCategory_id()}','${item.getCategory_name()}')">
                        Update
                    </a>
                    <a class="delete"
                       href="/lib?action=delete-category&id=${item.getCategory_id()}"
                       onclick="return confirm('Xoá không?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<!-- MODAL -->
<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">X</span>

        <h3>Add Category</h3>

        <form method="post" action="/lib?action=add-category" onsubmit="return validateForm()">
            <input type="text" id="name" name="name" placeholder="Enter name">
            <button type="submit">Add</button>
        </form>
        <p  id="error" style="color:red;text-align:center;margin-top:5px;"></p>
    </div>
</div>
<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditModal()">X</span>

        <h3>Update Category</h3>

        <form method="post" action="/lib?action=update-category" onsubmit="return validateEdit()">

            <!-- hidden id -->
            <input type="hidden" id="edit_id" name="id">

            <input type="text" id="edit_name" name="name">

            <button type="submit">Update</button>
        </form>

        <p id="edit_error" style="color:red;text-align:center;margin-top:5px;"></p>
    </div>
</div>
<script>
    function openModal(){
        document.getElementById("modal").style.display="flex";
    }

    function closeModal(){
        document.getElementById("modal").style.display="none";
        document.getElementById("error").innerText="";
    }

    window.onclick=function(e){
        let modal=document.getElementById("modal");
        if(e.target===modal){
            modal.style.display="none";
        }
    }

    function validateForm(){
        let name=document.getElementById("name").value.trim();
        let error=document.getElementById("error");

        error.innerText="";

        if(name===""){
            error.innerText="Không được để trống";
            return false;
        }

        return true;
    }
    function openEditModal(id, name){
        document.getElementById("editModal").style.display="flex";

        document.getElementById("edit_id").value = id;
        document.getElementById("edit_name").value = name;
    }

    function closeEditModal(){
        document.getElementById("editModal").style.display="none";
        document.getElementById("edit_error").innerText="";
    }
    function validateEdit(){
        let name = document.getElementById("edit_name").value.trim();
        let error = document.getElementById("edit_error");

        error.innerText="";

        if(name===""){
            error.innerText="Không được để trống";
            return false;
        }

        return true;
    }
</script>

</body>
</html>