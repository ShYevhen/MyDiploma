<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>MyBookShop-Login</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/logstyle.css">
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
							<input type="text" name="user_name" required="required"
					pattern="[A-Za-z0-9_-]{4,12}" placeholder="Login">
							<input type="password" name="user_password"  required="required"
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
						<a href="/authors">Авторы</a>
					</li>
					<li class="g_menu">
						<a href="/books/business-economics-legal">Бизнес, экономика и право</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/business-economics-legal/business-psychology">Бизнес и психология</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/bookkeeping">Бухгалтерия</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/logistics">Логистика</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/marketing">Маркетинг</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/banking">Банковское дело</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/economy">Экономика</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/administrative-law">Административное право</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/civil-law">Гражданское право</a>
							</li>
							<li>
								<a href="/books/business-economics-legal/constitutional-law">Конституционное право</a>
							</li>
						</ul>
					</li>
					<li class="g_menu">
						<a href="/books/children">Для детей</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/children/encyclopedia">Энциклопедии</a>
							</li>
							<li>
								<a href="/books/children/for-reading">Для чтения</a>
							</li>
							<li>
								<a href="/books/children/fairytales-myths-legends">Сказки, мифы и легенды</a>
							</li>
							<li>
								<a href="/books/children/fantasy">Фантастика</a>
							</li>
							<li>
								<a href="/books/children/detective">Детективы</a>
							</li>
							<li>
								<a href="/books/children/adventure">Приключения</a>
							</li>
						</ul>
					</li>
					<li class="g_menu">
						<a href="/books/documentary">Документальные</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/documentary/biography">Биорафии</a>
							</li>
							<li>
								<a href="/books/documentary/memoirs">Мемуары</a>
							</li>
							<li>
								<a href="/books/documentary/journalism">По публицистике</a>
							</li>
						</ul>
					</li>
					<li class="g_menu">
						<a href="/books/house-lifestyle-hobby">Дом, досуг и хобби</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/house-lifestyle-hobby/pets">Домашние животные</a>
							</li>
							<li>
								<a href="/books/house-lifestyle-hobby/needlework">Рукоделие</a>
							</li>
							<li>
								<a href="/books/house-lifestyle-hobby/cookery">Готовка</a>
							</li>
							<li>
								<a href="/books/house-lifestyle-hobby/gardening">Садоводство</a>
							</li>
						</ul>
					</li>
					<li class="g_menu">
						<a href="/books/art-culture-religion">Искусство, культура и религия</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/art-culture-religion/religion">Религия</a>
							</li>
							<li>
								<a href="/books/art-culture-religion/architecture">Архитектура</a>
							</li>
							<li>
								<a href="/books/art-culture-religion/art">Искусство</a>
							</li>
							<li>
								<a href="/books/art-culture-religion/cinematography-photo-theater">Кино, фото и театр</a>
							</li>
							<li>
								<a href="/books/art-culture-religion/culture-civilization">Культура и цивилизации</a>
							</li>
							<li>
								<a href="/books/art-culture-religion/music">Музыка</a>
							</li>
						</ul>
					</li>
					<li class="g_menu"><a href="/books/computer">Компьютер</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/computer/database">Базы данных</a>
							</li>
							<li>
								<a href="/books/computer/1c">1С</a>
							</li>
							<li>
								<a href="/books/computer/graphics-design">Графический дизайн</a>
							</li>
							<li>
								<a href="/books/computer/programming">Программирование</a>
							</li>
							<li>
								<a href="/books/computer/os">Операционные системы</a>
							</li>
							<li>
								<a href="/books/computer/computer-networks">Компьютерные сети</a>
							</li>
						</ul>
					</li>
					<li class="g_menu"><a href="/books/science-technology-medicine">Наука, технология и медицинаhumanities-social-sciences</a>
						<ul class="sg_menu">
							<li class="third_genre">
								<a href="/books/science-technology-medicine/humanities-social-sciences">Гуманитарные и общественные науки</a>
								<ul class="tg_menu">
									<li>
										<a href="/books/science-technology-medicine/humanities-social-sciences/warfare">Военное дело</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/humanities-social-sciences/policy">Политика</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/humanities-social-sciences/sociology">Социология</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/humanities-social-sciences/philosophy">Философия</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/humanities-social-sciences/history">История</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/humanities-social-sciences/psychology">Психология</a>
									</li>
								</ul>
							</li>
							<li class="third_genre">
								<a href="/books/science-technology-medicine/applied-natural-sciences">Прикладные и естественные науки</a>
								<ul class="tg_menu">
									<li>
										<a href="/books/science-technology-medicine/applied-natural-sciences/communications">Связь</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/applied-natural-sciences/medicine">Медицина</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/applied-natural-sciences/earth-science">Науки о земле</a>
									</li>
									<li>
										<a href="/books/science-technology-medicine/applied-natural-sciences/building">Строительство</a>
									</li>
									<li class="fourth_genre">
										<a href="/books/science-technology-medicine/applied-natural-sciences/transport">Транспорт</a>
										<ul class="fg_menu">
											<li>
												<a href="/books/science-technology-medicine/applied-natural-sciences/transport/cars">Машины</a>
											</li>
											<li>
												<a href="/books/science-technology-medicine/applied-natural-sciences/transport/ships">Корабли</a>
											</li>
											<li>
												<a href="/books/science-technology-medicine/applied-natural-sciences/transport/planes">Самолеты</a>
											</li>
											<li>
												<a href="/books/science-technology-medicine/applied-natural-sciences/transport/others">Прочий транспорт</a>
											</li>

										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="g_menu">
						<a href="/books/educational-reference">Справочники и образование</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/educational-reference/preschool">Дошкольное образование</a>
							</li>
							<li>
								<a href="/books/educational-reference/school">Для школьников</a>
							</li>
							<li>
								<a href="/books/educational-reference/dictionaries-reference">Словари и справочники</a>
							</li>
						</ul>
					</li>
					<li class="g_menu">
						<a href="/books/fiction">Художественная литература</a>
						<ul class="sg_menu">
							<li>
								<a href="/books/fiction/folklore">Фолькльор</a>
							</li>
							<li>
								<a href="/books/fiction/classic">Классика</a>
							</li>
							<li>
								<a href="/books/fiction/detectives-action-thrillers">Детективы, боевики и триллеры</a>
							</li>
							<li>
								<a href="/books/fiction/historical-adventure-novels">Исторические и приключенческие романы</a>
							</li>
							<li>
								<a href="/books/fiction/fantastic">Фантастика</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="centre">
		<div id="log">
			<form class="login-form" action="" method="POST">
				<p>
					<label for="login">Логин</label>
					<input type="text" name="user_name" id="login" required="required"
					pattern="[A-Za-z0-9_-]{4,12}" />
				</p>
				<p>
					<label for="password">Пароль</label>
					<input type="text" name="user_password" id="password" required="required"
					pattern="[A-Za-z0-9_-]{6,20}" />
				</p>
				<p class="submit">
					<input type="submit" class="btm" id="log_btm" value="Войти" />
				</p>
				<p>
					<a href="/registration" id="registrations">Регистрация</a>
				</p>
			</form>
		</div>
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