<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Borrow Book</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:Arial;
        }

        body{
            background:linear-gradient(135deg,#74ebd5,#ACB6E5);
            min-height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            padding:20px;
        }

        .container{
            background:white;
            padding:35px;
            border-radius:15px;
            box-shadow:0 10px 30px rgba(0,0,0,0.2);
            width:420px;
            max-width:100%;
        }

        h2{
            text-align:center;
            margin-bottom:25px;
            color:#333;
        }

        table{
            width:100%;
        }

        td{
            padding:10px;
            font-size:14px;
        }

        input,select{
            width:100%;
            padding:10px;
            border:1px solid #ccc;
            border-radius:6px;
            outline:none;
        }

        input:focus,select:focus{
            border-color:#4facfe;
        }

        button{
            width:100%;
            padding:12px;
            background:#4facfe;
            border:none;
            color:white;
            font-size:15px;
            border-radius:8px;
            cursor:pointer;
            transition:0.3s;
        }

        button:hover{
            background:#00c6ff;
        }

        .success{
            color:green;
            text-align:center;
            margin-bottom:15px;
            font-weight:bold;
        }

        .error{
            color:red;
            text-align:center;
            margin-bottom:10px;
        }

        @media(max-width:600px){

            .container{
                padding:25px;
            }

            td{
                display:block;
                width:100%;
            }

            tr{
                display:block;
                margin-bottom:10px;
            }

        }

    </style>

</head>

<body>

<div class="container">

    <h2>Borrow Book</h2>

    <c:if test="${message != null}">
        <p class="success">${message}</p>
        <c:remove var="message" scope="session"/>
    </c:if>

    <p id="errorMessage" class="error"></p>

    <form method="post" onsubmit="return validateForm()">

        <table>

            <tr>
                <td>Customer</td>
                <td>

                    <select name="customer_code" id="customer">

                        <c:forEach items="${listCustomer}" var="customer">
                            <option value="${customer.getCustomer_code()}">
                                    ${customer.getCustomer_code()} - ${customer.getName()}
                            </option>
                        </c:forEach>

                    </select>

                </td>
            </tr>

            <tr>
                <td>Book</td>
                <td>

                    <select name="book_name" id="book">

                        <c:forEach items="${listBook}" var="book">
                            <option value="${book}">
                                    ${book}
                            </option>
                        </c:forEach>

                    </select>

                </td>
            </tr>

            <tr>
                <td>Date Borrow</td>
                <td>
                    <input type="date" id="date" name="borrow_date">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button type="submit">Add Borrow</button>
                </td>
            </tr>

        </table>

    </form>

</div>

<script>

    function validateForm(){

        let customer=document.getElementById("customer").value;
        let book=document.getElementById("book").value;
        let date=document.getElementById("date").value;

        let error=document.getElementById("errorMessage");

        error.innerText="";

        if(customer=="" || book=="" || date==""){
            error.innerText="Please fill all fields";
            return false;
        }

        let today=new Date();
        let borrowDate=new Date(date);

        if(borrowDate>today){
            error.innerText="Borrow date cannot be in the future";
            return false;
        }

        return true;

    }

</script>

</body>
</html>