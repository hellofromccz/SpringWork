<script>

	import router from './router';
	import axios from 'axios';
	console.log('app is running...')

	export default {
		name: 'App',
	}
</script>

<template>
	<div>
		<router-view></router-view>
	</div>
	
</template>

<style>
.background {
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background: url(./assets/cover.png)  no-repeat center 0px;
	background-size: cover;
	background-position: center 0; 
	background-repeat: no-repeat;  
	background-attachment: fixed; 
	-webkit-background-size: cover;  
	-o-background-size: cover;  
	-moz-background-size: cover;  
	-ms-background-size: cover;
}

.login-box{
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%,-50%);
	width: 400px;
	padding:40px;
	background: rgba(0,0,0,.8);
	box-sizing: border-box;
	box-shadow: 0 15px  25px rgba(0,0,0,.5);
	border-radius: 10px;
}
.login-box h2{
	margin: 0 0 30px;
	padding: 0;
	text-align: center;
	color: #fff;
}
.login-box .login-field{
	position: relative;
}
.login-box .login-field  input{
	width: 100%;
	padding: 10px 0;
	font-size: 16px;
	color: #fff;
	margin-bottom: 30px;
	border: none;
	border-bottom: 1px solid #fff;
	outline: none;
	background: transparent;
}
.login-box .login-field  label{
	position: absolute;
	top: 0;
	left: 0;
	letter-spacing: 1px;
	padding: 10px 0;
	font-size: 16px;
	color: #fff;
	pointer-events: none;
	transition: .5s;
}
.login-box .login-field input:focus ~ label,
.login-box .login-field input:valid ~ label{
	top: -23px;
	left: 0;
	color: #ffffff;
	font-size: 12px;
}
.login-box button{
	background: transparent;
	border: none;
	outline: none;
	color: #fff;
	background: #000066;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 5px;
}
.login-box button:hover{
	background: transparent;
	border: none;
	outline: none;
	color: #6699ff;
	background: #000066;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 5px;
}

</style>
