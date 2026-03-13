<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>

    <style>
        body{
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg,#74ebd5,#ACB6E5);
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .container{
            background:white;
            padding:30px;
            width:320px;
            border-radius:8px;
            box-shadow:0 0 10px rgba(0,0,0,0.1);
        }

        h2{
            text-align:center;
            margin-bottom:20px;
        }

        input{
            width:100%;
            padding:10px;
            margin:8px 0;
            border:1px solid #ccc;
            border-radius:4px;
        }

        button{
            width:100%;
            padding:10px;
            background:#007bff;
            color:white;
            border:none;
            border-radius:4px;
            cursor:pointer;
        }

        button:hover{
            background:#0056b3;
        }

        .error{
            color:red;
            font-size:14px;
            margin-top:5px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Change Password</h2>

    <form method="post" onsubmit="return validateForm()">

        <input type="password" name="oldPassword" id="oldPassword" placeholder="Old Password">

        <input type="password" name="newPassword" id="newPassword" placeholder="New Password">

        <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password">

        <p id="errorMessage" class="error"></p>
        <p class="error">${message}</p>

        <button type="submit">Submit</button>

    </form>

</div>

<script>
    function validateForm(){
        let oldPass = document.getElementById("oldPassword").value;
        let newPass = document.getElementById("newPassword").value;
        let confirmPass = document.getElementById("confirmPassword").value;
        let error = document.getElementById("errorMessage");

        error.innerText = "";

        if(oldPass === "" || newPass === "" || confirmPass === ""){
            error.innerText = "Không được để trống các trường";
            return false;
        }

        if(newPass !== confirmPass){
            error.innerText = "Mật khẩu xác nhận không khớp";
            return false;
        }

        return true;
    }
</script>

</body>
</html>