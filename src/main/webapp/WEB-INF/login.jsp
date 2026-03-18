<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }

        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(45deg, #1a2a6c, #b21f1f, #fdbb2d);
            background-size: 400% 400%;
            animation: gradientBG 10s ease infinite;
        }

        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .login-card {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(15px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            padding: 40px;
            border-radius: 20px;
            width: 350px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.3);
            text-align: center;
        }

        h2 { color: white; margin-bottom: 30px; letter-spacing: 2px; text-transform: uppercase; }

        .input-box {
            position: relative;
            margin-bottom: 25px;
        }

        .input-box input {
            width: 100%;
            padding: 12px 10px 12px 40px;
            background: rgba(255, 255, 255, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.4);
            border-radius: 8px;
            color: white;
            outline: none;
            transition: 0.3s;
        }

        .input-box i {
            position: absolute;
            left: 12px;
            top: 50%;
            transform: translateY(-50%);
            color: rgba(255, 255, 255, 0.7);
        }

        .input-box input:focus {
            background: rgba(255, 255, 255, 0.2);
            border-color: #fff;
        }

        .input-box input::placeholder { color: rgba(255, 255, 255, 0.6); }

        .btn-login {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: #fff;
            color: #1a2a6c;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
            font-size: 16px;
        }

        .btn-login:hover {
            transform: scale(1.05);
            box-shadow: 0 0 20px rgba(255, 255, 255, 0.4);
        }

        .error-msg {
            color: #ffcccc;
            font-size: 13px;
            margin-bottom: 15px;
            display: block;
        }

        /* Hiệu ứng rung khi để trống */
        .shake { animation: shake 0.4s; }
        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-8px); }
            75% { transform: translateX(8px); }
        }
    </style>
</head>
<body>

<div class="login-card" id="card">
    <h2>SIGN IN</h2>

    <form method="post" action="/login" onsubmit="return validate()">
        <div id="msg" class="error-msg">
            <c:out value="${message}"/>
            <c:remove var="message"/>
        </div>

        <div class="input-box">
            <i class="fas fa-user"></i>
            <input type="text" name="username" id="user" placeholder="Username">
        </div>

        <div class="input-box">
            <i class="fas fa-lock"></i>
            <input type="password" name="password" id="pass" placeholder="Password">
        </div>

        <input type="submit" value="LOGIN" class="btn-login">
    </form>
</div>

<script>
    function validate() {
        const u = document.getElementById("user");
        const p = document.getElementById("pass");
        const m = document.getElementById("msg");
        const card = document.getElementById("card");

        if (u.value.trim() === "" || p.value.trim() === "") {
            m.innerHTML = "<i class='fas fa-exclamation-triangle'></i> Không được để trống!";

            // Hiệu ứng rung form
            card.classList.remove("shake");
            void card.offsetWidth; // Trigger reflow để reset animation
            card.classList.add("shake");

            // Đổi màu viền ô trống
            if(u.value === "") u.style.borderColor = "#ff4d4d";
            else u.style.borderColor = "rgba(255, 255, 255, 0.4)";

            if(p.value === "") p.style.borderColor = "#ff4d4d";
            else p.style.borderColor = "rgba(255, 255, 255, 0.4)";

            return false;
        }
        return true;
    }
</script>

</body>
</html>