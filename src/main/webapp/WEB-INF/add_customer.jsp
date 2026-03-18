<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>

    <style>
        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family: Arial;
        }

        body{
            background: linear-gradient(135deg,#74ebd5,#ACB6E5);
            height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
        }

        .form-container{
            background:white;
            padding:30px 40px;
            border-radius:15px;
            box-shadow:0 8px 25px rgba(0,0,0,0.2);
            width:420px;
        }

        .form-container h2{
            text-align:center;
            margin-bottom:20px;
        }

        table{
            width:100%;
        }

        td{
            padding:10px;
        }

        input{
            width:100%;
            padding:10px;
            border:1px solid #ccc;
            border-radius:6px;
        }

        input:focus{
            border-color:#4facfe;
        }

        .btn{
            width:100%;
            padding:12px;
            background:#4facfe;
            border:none;
            color:white;
            border-radius:8px;
            cursor:pointer;
            font-size:15px;
        }

        .btn:hover{
            background:#00c6ff;
        }

        .error{
            color:red;
            font-size:14px;
            text-align:center;
            margin-bottom:10px;
        }

    </style>

</head>

<body>

<div class="form-container">

    <h2>Add Customer</h2>

    <p id="errorMessage" class="error">${message}</p>
    <form method="post" onsubmit="return validateForm()">

        <table>

            <tr>
                <td>Customer Code</td>
                <td>
                    <input type="text" id="customer_code" name="customer_code">
                </td>
            </tr>

            <tr>
                <td>Name</td>
                <td>
                    <input type="text" id="name" name="name">
                </td>
            </tr>

            <tr>
                <td>Customer Class</td>
                <td>
                    <input type="text" id="customer_class" name="customer_class">
                </td>
            </tr>

            <tr>
                <td>Address</td>
                <td>
                    <input type="text" id="address" name="address">
                </td>
            </tr>

            <tr>
                <td>Birthday</td>
                <td>
                    <input type="date" id="birthday" name="birthday">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input class="btn" type="submit" value="Add Customer">
                </td>
            </tr>

        </table>

    </form>

</div>

<script>

    function validateForm(){

        let code=document.getElementById("customer_code").value.trim();
        let name=document.getElementById("name").value.trim();
        let cclass=document.getElementById("customer_class").value.trim();
        let address=document.getElementById("address").value.trim();
        let birthday=document.getElementById("birthday").value;

        let error=document.getElementById("errorMessage");

        error.innerText="";

        if(code==""||name==""||cclass==""||address==""||birthday==""){
            error.innerText="Không được để trống các trường";
            return false;
        }

        if(isNaN(code)){
            error.innerText="Customer Code phải là số";
            return false;
        }

        let nameRegex=/^[A-Za-zÀ-ỹ\s]+$/;

        if(!nameRegex.test(name)){
            error.innerText="Tên không được chứa số";
            return false;
        }

        return true;

    }

</script>

</body>
</html>