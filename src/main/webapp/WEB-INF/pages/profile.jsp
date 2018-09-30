<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>MyBookShop</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	
	<header>
		<div>
			<div class="logo">
				<a href="/" class="logo">
					<img src="img/mybookshop-logo.png" alt="MyBookShop">
				</a>
			</div>
			<div class="books_logo"></div>
			<div class="header_logo">
				<div class="login">
				<c:if test="${user ne null}">
					<div class="user">
						<p id="user_name">Здравствуйте <c:out value="${user}"></c:out></p>
						<a href="${logoutUrl}">Выйти</a>
					</div>
				</c:if>
				<c:if test="${user eq null}">
					<form class="login-form" action="" method="POST">
						<div class="login-inputs">
							<input type="text" name="login" required="required"
					pattern="[A-Za-z0-9_-]{4,12}" placeholder="Login">
							<input type="password" name="password"  required="required"
					pattern="[A-Za-z0-9_-]{6,20}"placeholder="Password">
						</div>
						<button type="submit" class="btm" id="btm_login">Войти</button>
					</form>
					<div class="regist_i"><a href="/registration" id="registration">Регистрация</a></div>
				</c:if>
					
				</div>
				<div class="basket">
					<a href="/basket">
						<img id="basket" src="img/cart-empty.png">
					</a>
				</div>
				<div class="contacts">
					<div class="phones">
						<img class="phones" src="img/phones.png" alt="+380(93)930-30-30
						+380(67)930-30-30
						+380(50)930-30-30">
					</div>
					<div class="worktime">
						<img class="worktime" src="img/worktime.png" alt="Пн - Пт
						09:00 - 17:00
						Сб - Вс
						09:00 - 15:00">
					</div>
				</div>
			</div>

		</div>
	</header>
	<div class="body">
		<div class="left_menu">
			<div class="sidebar">
				<ul class="menu">
					<li>
						<a href="/profile">Профиль</a>
					</li>
					<c:if test="${user.role eq 'USER'}">
					<li>
						<a href="/profile/favorite">Список желаний</a>
					</li>
					</c:if>
					<c:if test="${user.role eq 'ADMIN' or user.role eq 'OPERATOR'}">
					<li>
						<a href="/profile/operators">Операторы и администраторы</a>
					</li>
					</c:if>
					<li>
						<a href="/profile/orders">Список заказов</a>
					</li>
					
					
				</ul>
			</div>
		</div>
		<div class="centre">
			<c:if test="${about eq 'profile' }">
				<div class="profile">
				
				</div>
			</c:if>
			<c:if test="${about eq 'favorite' }">
				<div class="favorite">
				
				</div>
			</c:if>
			<c:if test="${about eq 'operators' }">
				<div class="operators">
				
				</div>
			</c:if>
			<c:if test="${about eq 'orders' }">
				<div class="orders">
				
				</div>
			</c:if>
		</div>
		<div class="right_menu">
			
				<div>
					<a href="#"><img src="img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
				</div>
				<div>
					<a href="#"><img src="img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
				</div>
				<div>
					<a href="#"><img src="img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
				</div>
				<div>
					<a href="#"><img src="img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
				</div>
		</div>
	</div>
	<footer>
		<div>
			<div id="partner">
				<a href="#"><img src="img/partner1.png" alt="Partner 1" class="partner_img"></a>
				<a href="#"><img src="img/partner2.png" alt="Partner 1" class="partner_img"></a>
				<a href="#"><img src="img/partner3.png" alt="Partner 1" class="partner_img"></a>
				<a href="#"><img src="img/partner4.png" alt="Partner 1" class="partner_img"></a>
			</div>
			<div id="social">
				<a href="https://www.facebook.com/"><img src="img/facebook.png" alt="Facebook" class="social_logo"></a>
				<a href="https://twitter.com/"><img src="img/twiter.png" alt="Twitter" class="social_logo"></a>
				<a href="https://www.instagram.com/"><img src="img/instagram.png" alt="Instagram" class="social_logo"></a>
			</div>
		</div>
	</footer>
	<script src="js/header_text.js" type="text/javascript">
	</script>
</body>
</html>