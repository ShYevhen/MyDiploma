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
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/author.css">
</head>
<body>
	<header>
		<div>
			<div class="logo">
				<a href="/" class="logo">
					<img src="/img/mybookshop-logo.png" alt="MyBookShop">
				</a>
			</div>
			<div class="books_logo"></div>
			<div class="header_logo">
				<div class="login">
					<c:if test="${user ne null}">
						<div class="user">
							<p id="user_name">Здравствуйте <a href="/profile/user"><c:out value="${user}"></c:out></a></p>
							<c:url value="logout" var="logoutUrl" />
							<a href="/${logoutUrl}">Выйти</a>
						</div>
					</c:if>
					<c:if test="${user eq null}">
						<form class="login-form" action="/login" method="POST">
							<div class="login-inputs">
								<input type="text" name="user_name" required="required"
									pattern="[A-Za-z0-9_-]{4,12}" placeholder="Login">
								<input type="password" name="user_password" required="required"
									pattern="[A-Za-z0-9_-]{6,20}" placeholder="Password">
							</div>
							<button type="submit" class="btm" id="btm_login">Войти</button>
						</form>
						<div class="regist_i"><a href="/registration" id="registration">Регистрация</a></div>
					</c:if>
				</div>
				<div class="basket">
					<a href="/basket">
						<c:if test="${basket eq 'empty'}">
							<img id="basket" src="/img/cart-empty.png">
						</c:if>
						<c:if test="${basket eq 'full'}">
							<img id="basket" src="/img/cart-full.png">
						</c:if>
					</a>
				</div>
				<div class="contacts">
					<div class="phones">
						<img class="phones" src="/img/phones.png" alt="+380(93)930-30-30
							+380(67)930-30-30
							+380(50)930-30-30">
					</div>
					<div class="worktime">
						<img class="worktime" src="/img/worktime.png" alt="Пн - Пт
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
				<li class="g_menu"><a href="/books/science-technology-medicine">Наука, технология и медицина</a>
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
				<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
					<li>
						<a href="/authors/add">Добавить нового автора</a>
					</li>
					<li>
						<a href="/books/add">Добавить новую книгу</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
	<div class="centre">
		<div class="data">
			<c:choose>
				<c:when test="${newAuthor eq true}">
					<form id="formNewAuthor" action="" method="POST">
						<div id="surnameDiv">
							<label for="surname">Фамилия: </label> 
							<input type="text" id="surnameInp" class="createAuthor" name="surname" placeholder="Фамилия"  required="required">
						</div>
						<div id="nameDiv">
							<label for="name">Имя: </label>
							<input type="text" id="nameInp" class="createAuthor" name="name" placeholder="Имя" required="required">
						</div>
						<div id="birthdayDiv">
							<label for="birthday">Дата рождения: </label>
							<input type="date" id="birthday" class="createAuthor" name="birthday" required="required">
						</div>
						<div id="biographyDiv">
							<label for="biography">Биография: </label>
							<textarea id="biographyText" class="createAuthor" name="biography" cols="40" rows="4" required="required"></textarea>
						</div>
						<div id="pictureDiv">
							<label for="img">Фотография автора: </label>
							<button type="button" id="file" class="createAuthor" onclick="$('#picture').click()">Выбрать картинку</button>
							<input type="file" id="picture" name="img" style="display: none;" accept="image/jpeg,image/png,image/gif" required="required">
						</div>
						<input type="submit" id="addBtn" class="createAuthor" name="addBtn" value="Сохранить" onclick="return addAuthor(this.form)">
					</form>
				</c:when>
				<c:otherwise>
					<form id="authorForm" action="" method="POST" >
						<div class='image'>
							<img src="${author.image}" alt="${author.name}">
							<button type="button" id="file" class="visibility" onclick="$('#picture').click()">Выбрать картинку</button>
							<input type="file" id="picture" name="img" style="display: none;" accept="image/jpeg,image/png,image/gif">
						</div>
						<div class="descrip">
							<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
								<div id="changeAuthor">
									<input type="submit" id="changeBtn" name="changeBtn" onclick="return false" value="Изменить">
								</div>
							</c:if>
							<div class="authorData">
								<p id="fioP">
									<h2><em>${author.name} ${author.surname}</em></h2>
									<input type="text" class="visibility" id="nameInp" name="name" placeholder="Имя" >
									<input type="text" class="visibility" id="surnameInp" name="surname" placeholder="Фамилия" >
								</p>
								<p id="birthdayP">
									<b>Родился:</b> ${author.birthday}
									<input type="date" id="birthday" class="visibility" name="birthday">
								</p>
							</div>
							<div>
								<p id="biographyP"><b>Биография:</b> ${author.biography}</p>
								<textarea class="visibility" id="biographyText" name="biography" cols="40" rows="4" placeholder="${author.biography}"></textarea>
							</div>
							<input type="submit" class="visibility" id="sendBtn" name="sendBtn" onclick="return authorUpdate('${author.id}',this.form)" value="Сохранить">
						</div>
					</form>
					<div class="table">
						<table class="table_blur">
							<tr>
								<th>Название</th>
								<th>Серия</th>
								<th>Номер в серии</th>
								<th>Год издания</th>
								<th>Цена</th>
							</tr>
							<c:forEach var="book" items="${author.books}">
								<tr onclick="window.location.replace('/books?id=${book.id}')">
									<td>${book.name}</td>
									<td>${book.bookSeries}</td>
									<td>${book.bookInSeries}</td>
									<td>${book.imprintDate}</td>
									<td>${book.price}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="right_menu">
		<div>
			<a href="#"><img src="/img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
		</div>
		<div>
			<a href="#"><img src="/img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
		</div>
		<div>
			<a href="#"><img src="/img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
		</div>
		<div>
			<a href="#"><img src="/img/advertising.png" alt="Here can be your advertising" class="advertising"></a>
		</div>
	</div>
</div>
<footer>
	<div>
		<div id="partner">
			<a href="#"><img src="/img/partner1.png" alt="Partner 1" class="partner_img"></a>
			<a href="#"><img src="/img/partner2.png" alt="Partner 2" class="partner_img"></a>
			<a href="#"><img src="/img/partner3.png" alt="Partner 3" class="partner_img"></a>
			<a href="#"><img src="/img/partner4.png" alt="Partner 4" class="partner_img"></a>
		</div>
		<div id="social">
			<a href="https://www.facebook.com/"><img src="/img/facebook.png" alt="Facebook" class="social_logo"></a>
			<a href="https://twitter.com/"><img src="/img/twiter.png" alt="Twitter" class="social_logo"></a>
			<a href="https://www.instagram.com/"><img src="/img/instagram.png" alt="Instagram" class="social_logo"></a>
		</div>
	</div>
</footer>
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/author.js"></script>
</body>
</html>