<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isELIgnored="false"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>${book.name}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/chosen.css">
	<link rel="stylesheet" type="text/css" href="/css/bookpage.css">
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
		<c:choose>
			<c:when test="${newBook eq true}">
				<div class="data" id="bData">
					<form action="" method="POST">
						<div id="bNameDiv">
							<label class="labels" for="name"><span class="formTextRed">*</span>Название: </label>
							<input id="bName" type="text" name="name" required>
						</div>
						<div id="bAuthorDiv">
							<label class="labels" for="authors"><span class="formTextRed">*</span>Автор: </label>
							<select id="bAuthor" data-placeholder="Автор" multiple name="authors" class="chosen-select" style="width: 180px">
								<option value=""></option>
								<c:forEach var="author" items="${authorsList}">
									<option value="id:${author.id},name:${author.name},surname:${author.surname}">${author.surname} ${author.name}</option>
								</c:forEach>
							</select>
						</div>
						<div id="bGenreDiv">
							<label class="labels" for="genre"><span class="formTextRed">*</span>Жанр: </label>
							<select id="bGenre" data-placeholder="Жанр" name="genre" class="chosen-select">
								<option value=""></option>
								<optgroup label="Бизнес, экономика и право">
									<c:forEach var="gen" items="${genresBEL}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Для детей">
									<c:forEach var="gen" items="${genresCh}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Документальные">
									<c:forEach var="gen" items="${genresDoc}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Дом, досуг и хобби">
									<c:forEach var="gen" items="${genresHLH}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Искусство, культура и религия">
									<c:forEach var="gen" items="${genresACR}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Компьютер">
									<c:forEach var="gen" items="${genresComp}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Наука, технология и медицина">
									<optgroup label="Гуманитарные и общественные науки">
										<c:forEach var="gen" items="${genresHSS}">
											<option value="${gen.path}">${gen.name}</option>
										</c:forEach>
									</optgroup>
									<optgroup label="Прикладные и естественные науки">
										<c:forEach var="gen" items="${genresANS}">
											<option value="${gen.path}">${gen.name}</option>
										</c:forEach>
										<optgroup label="Транспорт">
											<c:forEach var="gen" items="${genresTran}">
												<option value="${gen.path}">${gen.name}</option>
											</c:forEach>
										</optgroup>
									</optgroup>
								</optgroup>
								<optgroup label="Справочники и образование">
									<c:forEach var="gen" items="${genresER}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
								<optgroup label="Художественная литература">
									<c:forEach var="gen" items="${genresFic}">
										<option value="${gen.path}">${gen.name}</option>
									</c:forEach>
								</optgroup>
							</select>
						</div>
						<div id="bSeriesDiv">
							<label class="labels" for="bookSeries">Название серии: </label>
							<input id="bSeries" type="text" name="bookSeries" >
						</div>
						<div id="bInSeriesDiv">
							<label class="labels" for="bookInSeries">Номер книги в серии: </label>
							<input id="bInSeries" type="number" name="bookInSeries" min="0" >
						</div>
						<div id="bImprintDateDiv">
							<label class="labels" for="imprintDate"><span class="formTextRed">*</span>Год издания: </label>
							<input id="bImprintDate" type="text" name="imprintDate" pattern="[0-9]{4}" required>
						</div>
						<div id="bPagesDiv">
							<label class="labels" for="pages"><span class="formTextRed">*</span>Страниц: </label>
							<input id="bPages" type="number" name="pages" min="1" required>
						</div>
						<div id="bCoverDiv">
							<label class="labels" for="bookCover"><span class="formTextRed">*</span>Переплет: </label>
							<select id="bCover" data-placeholder="Переплет" name="bookCover" class="chosen-select">
								<option value=""></option>
				                <option value="HARD">жесткий</option>
				                <option value="SOFT">мягкий</option>
				            </select>
						</div>
						<div id="bLanguageDiv">
							<label class="labels" for="language"><span class="formTextRed">*</span>Язык: </label>
							<select id="bLanguage" data-placeholder="Язык" name="language" class="chosen-select">
								<option value=""></option>
								<option value="Английский">Английский</option>
								<option value="Украинский">Украинский</option>
								<option value="Русский">Русский</option>
							</select>
						</div>
						<div id="bPriceDiv">
							<label class="labels" for="price"><span class="formTextRed">*</span>Цена: </label>
							<input id="bPrice" type="text" name="price" pattern="^[0-9]*[.,]?[0-9]{2}" required>
						</div>
						<div id="bAvailabilityDiv">
							<label class="labels" for="availability">В наличии: </label>
							<input id="bAvailability" type="number" name="availability" min="0">
						</div>
						<div id="bPictureDiv">
							<label class="labels" for="img"><span class="formTextRed">*</span>Фото книги: </label>
							<input type="file" id="picture" name="img" accept="image/jpeg,image/png,image/gif" required>
						</div>
						<div id="bDescriptionDiv">
							<label class="labels" for="description"><span class="formTextRed">*</span>Описание: </label>
							<textarea id="bDescription" name="description" cols="40" rows="4" required></textarea>
						</div>
						<div id="bVisibleDiv">
							<label class="labels" for="visible">Доступна пользователям: </label>
							<select id="bVisible" data-placeholder="Доступна" class="chosen-select" name="visible">
					            <option value="true">true</option>
					            <option selected value="false">false</option>
					        </select>
						</div>
						<div id="bBtnDiv">
							<input type="submit" id="addBtn" name="addBtn" onclick="return addNewBook(this.form)">
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="top">
					<div class="navGenre">
						<ul>
							<c:forEach var = "genre" items="${genres}">
								<li><a href="${genre.path}">${genre.name}</a></li>
							</c:forEach>
							<li><a href="${book.genre.path}">${book.genre.name}</a></li>
						</ul>
					</div>
				</div>
				<div class="data">
					<div class="theBook">
						<div class="image">
							<img src="${book.image}" alt="${book.name}">
						</div>
						<div class='description'>
							<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
								<div id="changeDiv">
									<input type="submit" id="changeBtn" name="changeBtn" onclick="return false" value="Изменить">
								</div>
							</c:if>
							<form>
								<p id="pictureP">
									<input type="file" id="picture" class="visibility" name="img" accept="image/jpeg,image/png,image/gif">
								</p>
								<p id="nameP">${book.name}
									<input type="text" id="nameInp" name="name" class="visibility" placeholder="${book.name}">
								</p>
								<p id="authorP">Автор: 
									<div id="authorDiv">
									<c:forEach var = "author" items="${book.authors}">
										<a class="authorLink" href="/authors/author?id=${author.id}">${author.surname} ${author.name}</a>
									</c:forEach>
									</div>
									<div id="authorSel" class="visibility">
										<select data-placeholder="Автор" multiple name="authors" id="authors" class="chosen-select">
											<option value=""></option>
											<c:forEach var="author" items="${authorsList}">
												<c:forEach var="auth" items="${book.authors}">
													<c:choose>
														<c:when test="${author.id eq auth.id}">
															<option selected value="id:${author.id},name:${author.name},surname:${author.surname}">${author.surname} ${author.name}</option>
														</c:when>
														<c:otherwise>
															<option value="id:${author.id},name:${author.name},surname:${author.surname}">${author.surname} ${author.name}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:forEach>
										</select>
									</div>
								</p>
								<div id="genreP">
									<div id="genreDiv" >Жанр: ${book.genre.name}</div>
									<div id="genreSel" class="visibility">
										<select data-placeholder="Жанр" name="genre" class="chosen-select">
											<option value=""></option>
											<optgroup label="Бизнес, экономика и право">
												<c:forEach var="gen" items="${genresBEL}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Для детей">
												<c:forEach var="gen" items="${genresCh}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Документальные">
												<c:forEach var="gen" items="${genresDoc}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Дом, досуг и хобби">
												<c:forEach var="gen" items="${genresHLH}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Искусство, культура и религия">
												<c:forEach var="gen" items="${genresACR}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Компьютер">
												<c:forEach var="gen" items="${genresComp}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Наука, технология и медицина">
												<optgroup label="Гуманитарные и общественные науки">
													<c:forEach var="gen" items="${genresHSS}">
														<option value="${gen.path}">${gen.name}</option>
													</c:forEach>
												</optgroup>
												<optgroup label="Прикладные и естественные науки">
													<c:forEach var="gen" items="${genresANS}">
														<option value="${gen.path}">${gen.name}</option>
													</c:forEach>
													<optgroup label="Транспорт">
														<c:forEach var="gen" items="${genresTran}">
															<option value="${gen.path}">${gen.name}</option>
														</c:forEach>
													</optgroup>
												</optgroup>
											</optgroup>
											<optgroup label="Справочники и образование">
												<c:forEach var="gen" items="${genresER}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
											<optgroup label="Художественная литература">
												<c:forEach var="gen" items="${genresFic}">
													<option value="${gen.path}">${gen.name}</option>
												</c:forEach>
											</optgroup>
										</select>
									</div>
								</div>
								<c:choose>
									<c:when test="${book.bookSeries ne null}">
										<p id="bookSeriesP">Серия: ${book.bookSeries}
											<input type="text" id="bookSeriesInp" name="bookSeries" class="visibility" placeholder="${book.bookSeries}">
										</p>
										<p id="bookInSeriesP">Книга в серии: ${book.bookInSeries}
											<input type="number" id="bookInSeriesInp" name="bookInSeries" class="visibility" min="1" placeholder="${book.bookInSeries}">
										</p>
									</c:when>
									<c:otherwise>
										<p id="bookSeriesPs" class="visibility">Серия:
											<input type="text" id="bookSeriesInp" name="bookSeries" placeholder="${book.bookSeries}">
										</p>
										<p id="bookInSeriesPs" class="visibility">Книга в серии: 
											<input type="number" id="bookInSeriesInp" name="bookInSeries" min="0" placeholder="${book.bookInSeries}">
										</p>
									</c:otherwise>
								</c:choose>
								<p id="imprintDateP">Год: ${book.imprintDate}
									<input type="text" id="imprintDateInp" name="imprintDate" class="visibility" placeholder="${book.imprintDate}" pattern="[0-9]{4}">
								</p>
								<p id="pagesP">Кол-во страниц: ${book.pages}
									<input type="number" id="pagesInp" name="pages" class="visibility" min="1" placeholder="${book.pages}">
								</p>
			                    <div id="bookCoverP">
			                    	<div id="bookCoverDiv">Переплет: 
			                    		<c:choose>
				                    		<c:when test="${book.bookCover eq 'HARD'}">жесткий</c:when>
				                    		<c:otherwise>мягкий</c:otherwise>
				                    	</c:choose>
				                    </div>
			                    	<div id="bookCoverSel" class="visibility">
				                        <select data-placeholder="Переплет" name="bookCover" class="chosen-select">
				                        	<option value=""></option>
				                            <option value="HARD">жесткий</option>
				                            <option value="SOFT">мягкий</option>
				                        </select>
			                    	</div>
			                    </div>
								<div id="languageP">
									<div id="languageDiv">Язык: ${book.language}</div>
									<div id="languageSel" class="visibility">
										<select data-placeholder="Язык" name="language" class="chosen-select">
											<option value=""></option>
											<option value="Английский">Английский</option>
											<option value="Украинский">Украинский</option>
											<option value="Русский">Русский</option>
										</select>
									</div>
								</div>
		  						<p id="priceP">Цена: ${book.price} грн
		  							<input type="text" id="priceInp" name="price" class="visibility" placeholder="${book.price}" pattern="^[0-9]*[.,]?[0-9]{2}">
		  						</p>
		  						<c:if test="${user ne null}">
		  							<button id="inFavorite" class="btnBook" onclick="return addToFavorite(${book.id})">В список желаний</button>
		  						</c:if>
		  						<c:choose>
			                        <c:when test="${book.availability > 0}">
			                            <button id="inBasket" class="btnBook" onclick="return addToBasket(${book.id})">В корзину</button>
			                        </c:when>
			                        <c:otherwise>
			                            <p id="availabilityPn">Ожидается поставка</p>
			                        </c:otherwise>
			                    </c:choose>
			                    <p id="availabilityP" class="visibility">
			                    	Остаток: ${book.availability}
			                    	<input type="number" id="availabilityInp" name="availability" min="0" placeholder="${book.availability}">
			                    </p>
		  						<p id="descriptionP">Описание: ${book.description}
		  							<textarea id="descriptionText" name="description" class="visibility" cols="40" rows="4" placeholder="${book.description}"></textarea>
		  						</p>
			                    <c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
				                    <div id="visibleP">
				                    	<div id="visibleDiv">Видна всем: ${book.visible}</div>
				                    	<div id="visibleSel" class="visibility">
					                    	<select data-placeholder="Доступна" name="visible" class="chosen-select">
					                    		<option value=""></option>
					                    		<option value="true">true</option>
					                    		<option value="false">false</option>
					                    	</select>
				                    	</div>
				                    </div>
				                </c:if>
			                    <p id="addDateP" class="visibility">
			                    	Последнее изменение: ${book.addDate}
			                    </p>
		  						<input type="submit" class="visibility" id="changeBtn" name="changeBtn" onclick="return changeBook('${book.id}', this.form, '${book.visible}')">
		  					</form>
		  				</div>
		  			</div>
				</div>
			</c:otherwise>
		</c:choose>
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
<script type="text/javascript" src="/js/book.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.js"></script>
</body>
</html>