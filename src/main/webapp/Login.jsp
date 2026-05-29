
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Web Service Login</title>
    <style>
        * {
            box-sizing: border-box;
        }
        html,body{
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
        }
        .title_bar{
            background-color: deepskyblue;
            color: white;
            padding: 10px;
            margin: 0 0 3px 0;
            width: 100%;
            height: 10%;
        }
        .title_bar h1{
            font-weight: bold;
        }
        .login_bar{
            background-color: aliceblue;
            width: 30%;
            height: 80%;
            margin: auto;
            padding: 20px;
            border: 10px solid deepskyblue;
        }
        #loginForm{
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
        }
        .form_content{
            width: 70%;
            margin: 10px auto;
            display: flex;
        }
        .f1{
            justify-content: space-around;
        }
        .f2{
            justify-content: space-between;
        }
        footer{
            margin: 3px 0 0 0 ;
            display: flex;
            justify-content: center;
        }
        .footer{
            width: 100%;
            height: 15%;
            background-color: gray;
            color: black;
            margin: 0 auto;
        }
        .footer p{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="title_bar">
    <h1>Student Information Management System Web Service Login</h1>
</div>
<div class="login_bar">
    <div id="loginForm">
        <div class="form_content f1"><h1>Login</h1></div>
        <div class="form_content f2"><label for="userId">User ID: </label><input type="text" lastName="userID" id="userId" v-model="info.userID"></div>
        <div class="form_content f2"><label for="password">Password: </label><input type="password" lastName="password" id="password" v-model="info.password"></div>
        <div class="form_content f1">
            <button type="button" value="Login" id="Login" v-on:click="login">Login</button>
            <button type="button" value="Reset" id="Reset" v-on:click="reset">Reset</button>
        </div>
    </div>
</div>
<footer>
    <div class="footer">
        <p>COMP2013J Group19 Assignment</p>
    </div>
</footer>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="module">
    import { createApp } from "https://unpkg.com/vue@3/dist/vue.esm-browser.js";
    createApp({
        data() {
            return {
                info: {
                    userID: '',
                    password: ''
                },
            }
        },
        methods: {
            login() {
                console.log('start to login');
                axios.post('http://localhost:8080/api/login', {
                    userID: this.info.userID,
                    password: this.info.password
                }).then(function (response) {
                    if (response.data.status === true) {
                        console.log('the user login success');
                        window.location.href = './Homepage.jsp';
                    } else {
                        console.log('the user login failed');
                        alert('login failed, userID not exist or password incorrect');
                    }
                })
            },
            reset() {
                this.info.userID = '';
                this.info.password = '';
            }
        }
    }).mount('#loginForm')
</script>

</body>
</html>