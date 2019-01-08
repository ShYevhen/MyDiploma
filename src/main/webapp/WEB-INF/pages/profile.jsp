<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isELIgnored="false"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>${user}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/book.css">
	<link rel="stylesheet" type="text/css" href="/css/chosen.css">
	<link rel="stylesheet" type="text/css" href="/css/profile.css">
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
								<input type="text" id="uname" name="user_name" required="required"
									pattern="[A-Za-z0-9_-]{4,12}" placeholder="Login">
								<input type="password" id="upassword" name="user_password" required="required"
									pattern="[A-Za-z0-9_-]{6,20}" placeholder="Password">
							</div>
							<button type="submit" class="btm" id="btm_login" onclick="return login(this.form)">Войти</button>
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
						<a href="/profile/user">Профиль</a>
					</li>
					<li>
						<a href="/profile/favorite">Список желаний</a>
					</li>
					<li>
						<a href="/profile/orders">Список заказов</a>
					</li>
					<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
						<li>
							<a href="/profile/operators">Операторы и администраторы</a>
						</li>
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
			<c:if test="${profile ne null}">
				<div class="profile">
					<form action="" method="POST">
					<div class='profImage'>
						<img src="${profile.image}" onerror="if (this.src != '/img/users/default.png') this.src = '/img/users/default.png';" alt="${profile.login}">
						<input type="file" id="picture" name="img" accept="image/jpeg,image/png,image/gif">
					</div>
					<div class='description'>
							<div class="profDesc" id="profLogin">Логин: ${profile.login}</div>
							<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
	  							<div class="profDesc" id="profRole">Права: ${role}</div>
	  						</c:if>
							<div class="profDesc" id="profName">
								<label for="name">Имя: ${profile.name} </label>
								<input type="text" name="name" id="name" />
							</div>
							<div class="profDesc" id="profSurname">
								<label for="surname">Фамилия: ${profile.surname} </label>
								<input type="text" name="surname" id="surname" />
							</div>
							<div class="profDesc" id="profMail">
								<label for="eMail">Почта: ${profile.eMail} </label>
								<input type="email" name="eMail" id="eMail" />
							</div>
							<div class="profDesc" id="profPhone">
								<label for="phone">Телефон: ${profile.phone} </label>
								<input type="text" name="phone" id="phone" pattern="[0-9+]{13}" placeholder="+380XXYYYYYYY" />
							</div>
							<c:if test="${profile.address ne null}">
								<div class="profDesc" id="profAddress">Адрес последней доставки: ${profile.address}</div>
							</c:if>
	  						<div class="profDesc" id="profOldPass">
	  							<label for="old_password">Пароль: </label>
								<input type="password" name="old_password" id="old_password" required="required"
									pattern="[A-Za-z0-9_-]{6,20}"  onchange="checkPass(this.form)" />
	  						</div>
	  						<div class="profDesc" id="profPass">
	  							<label for="password">Новый пароль: </label>
								<input type="password" name="password" id="password"
									pattern="[A-Za-z0-9_-]{6,20}" onchange="checkPass(this.form)" />
	  						</div>
	  						<div class="profDesc" id="profRepPass">
	  							<label for="r_password">Повторить новый пароль: </label>
								<input type="password" name="r_password" id="r_password"
									pattern="[A-Za-z0-9_-]{6,20}" onchange="checkPass(this.form)"/>
	  						</div>
  					</div>
  					<div id="profBtnDiv">
  						<button id="changeBtn" onclick="return sendChangeData(this.form)">Сохранить изменения</button>
  					</div>
  					</form>
				</div>
			</c:if>
			<c:if test="${favorite ne null}">
				<div id="bookNavibar">
					<select id="orderSel" data-placeholder="Сортировать по ..." class="chosen-select" onchange="orderSelected()">
						<option value=""></option>
						<option value="${favoriteLink}">Новинки</option>
						<option value="${favoriteLink}?order=populary">По рейтингу</option>
						<option value="${favoriteLink}?order=price&sortS=ASC">От дешевых к дорогим</option>
						<option value="${favoriteLink}?order=price&sortS=DESC">От дорогих к дешевым</option>
						<option value="${favoriteLink}?order=name&sortS=ASC">По названию(А...Я)</option>
						<option value="${favoriteLink}?order=name&sortS=DESC">По названию(Я...А)</option>
					</select>
				</div>
				<div class="favorite">
					<c:forEach var = "book" items="${favorite}">
						<div class='book'>
							<div class='image'>
								<a href="/books?id=${book.id}"><img src="${book.image}" alt="${book.name}"></a>
							</div>
							<div class='description'>
								<a href="/books?id=${book.id}"><p>${book.name}</p></a>
		  						<p>Цена: ${book.price} грн</p>
		  						<c:choose>
			                        <c:when test="${book.availability > 0}">
			                            <button id="inBasket" onclick="addToBasket(${book.id})">В корзину</button>
			                        </c:when>
			                        <c:otherwise>
			                            <p>Ожидается поставка</p>
			                        </c:otherwise>
			                    </c:choose>
			                    <button id="fromFavorite" onclick="deleteFromFavorite(${book.id})">Удалить</button>
		  					</div>
		  				</div>
					</c:forEach>
				</div>
				<div id="pageNav">
					<nav aria-label="Page navigation">
	            	    <ul class="pagination">
	            	    	<c:choose>
	            	    		<c:when test="${thisPage - 2 >= 0}">
	            	    			<li><a href="${pageLink}&page=0"><<</a></li>
	            	    			<li><a href="${pageLink}&page=<c:out value="${(thisPage - 1) > 0 ? (thisPage - 1): 0}"/>"><</a></li>
			                	        <c:forEach var="i" begin="${thisPage - 2}" end="${(thisPage + 2) < maxPage? (thisPage + 2) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}">
			                	        	<c:choose>
	            	    						<c:when test="${thisPage == i}">
			                    	        		<li class="active"><a href="${pageLink}&page=<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
			                    	        	</c:when>
			                    	        	<c:otherwise>
			                    	        		<li><a href="${pageLink}&page=<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
			                    	        	</c:otherwise>
			                    	        </c:choose>
			                        	</c:forEach>
			                        <li><a href="${pageLink}&page=<c:out value="${(thisPage + 1) < maxPage? (thisPage + 1) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}"/>">></a></li>
			                        <li><a href="${pageLink}&page=<c:out value="${(maxPage - 1) >= 0? (maxPage - 1): 0}"/>">>></a></li>
	            	    		</c:when>
	            	    		<c:otherwise>
			            	    	<li><a href="${pageLink}&page=0"><<</a></li>
	            	    			<li><a href="${pageLink}&page=<c:out value="${(thisPage - 1) > 0 ? (thisPage - 1): 0}"/>"><</a></li>
			                	        <c:forEach var="i" begin="0" end="${(thisPage + 2) < maxPage? (thisPage + 2) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}">
			                    	        <c:choose>
	            	    						<c:when test="${thisPage == i}">
			                    	        		<li class="active"><a href="${pageLink}&page=<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
			                    	        	</c:when>
			                    	        	<c:otherwise>
			                    	        		<li><a href="${pageLink}&page=<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
			                    	        	</c:otherwise>
			                    	        </c:choose>
			                        	</c:forEach>
			                        <li><a href="${pageLink}&page=<c:out value="${(thisPage + 1) < maxPage? (thisPage + 1) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}"/>">></a></li>
			                        <li><a href="${pageLink}&page=<c:out value="${(maxPage - 1) >= 0? (maxPage - 1): 0}"/>">>></a></li>
	                        	</c:otherwise>
	                        </c:choose>
	            	    </ul>
		        	</nav>
		        </div>
			</c:if>
			<c:if test="${operators ne null}">
				<div class="new_operator">
					<c:if test="${role eq 'ROLE_ADMIN'}">
						<form action="" method="POST" class="new_oper">
							<div class="inputsOper">
								<input type="text" name="login" required="required" pattern="[A-Za-z0-9_-]{4,12}" placeholder="Логин">
								<input type="password" name="password" required="required" pattern="[A-Za-z0-9_-]{6,20}" placeholder="Пароль" onchange="checkPassword(this.form)">
								<input type="password" name="r_password" required="required" pattern="[A-Za-z0-9_-]{6,20}" placeholder="Повторить пароль" onchange="checkPassword(this.form)">
							</div>
							<select id="selRole" class="chosen-select" name="role">
								<option value="ADMIN">Admin</option>
								<option selected="selected" value="OPERATOR">Operator</option>
							</select>
							<button id="changeBtn" onclick="return createOper(this.form)">Добавить сотрудника</button>
						</form>
					</c:if>
				</div>
				<div class="operators">
					<table class="table_blur">
						<tr>
							<th>Логин</th><th>Имя и фамилия</th><th>Права</th>
						</tr>
						<c:forEach var = "operator" items="${operators}">
							<tr>
								<td><a href="/profile/operators?id=${operator.id}">${operator.login}</a></td>
								<td>${operator.name} ${operator.surname}</td>
								<td id="setRole">
									<c:choose>
										<c:when test="${role eq 'ROLE_ADMIN'}">
											<select class="chosen-select" id="${operator.login}">
												<c:choose>
							                        <c:when test="${operator.role eq 'ADMIN'}">
							                            <option selected="selected" value="ADMIN">Admin</option>
							                            <option value="OPERATOR">Operator</option>
							                        </c:when>
							                        <c:otherwise>
							                            <option value="ADMIN">Admin</option>
							                            <option selected="selected" value="OPERATOR">Operator</option>
							                        </c:otherwise>
							                    </c:choose>	
						                    </select>
					                	</c:when>
				                		<c:otherwise>${operator.role}</c:otherwise>
				                	</c:choose>
								</td>
								<c:if test="${role eq 'ROLE_ADMIN'}">
									<td>
										<button id="updateOper" onclick="changeOper('${operator.login}')">Изменить</button>
									</td>
									<td>
										<button id="deleteOper" onclick="deleteOper(${operator.id})">Удалить</button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
			<c:if test="${operator ne null}">
				<div class="operator">
					<div class="operInfo">Логин: ${operator.login}</div>
		  			<div class="operInfo">Имя: ${operator.name}</div>
		  			<div class="operInfo">Фамилия: ${operator.surname}</div>
		  			<div class="operInfo">Почта: ${operator.eMail}</div>
		  			<div class="operInfo">Телефон: ${operator.phone}</div>
		  			<div class="operInfo">Права: <c:choose>
		  				<c:when test="${role eq 'ROLE_ADMIN'}">
		  					<select class="chosen-select" id="${operator.login}">
		  						<c:choose>
			                        <c:when test="${operator.role eq 'ADMIN'}">
			                            <option selected="selected" value="ADMIN">Admin</option>
			                            <option value="OPERATOR">Operator</option>
			                        </c:when>
			                        <c:otherwise>
			                            <option value="ADMIN">Admin</option>
			                            <option selected="selected" value="OPERATOR">Operator</option>
			                        </c:otherwise>
			                    </c:choose>	
		  					</select>
		  				</c:when>
		  				<c:otherwise>${operator.role}</c:otherwise></c:choose></div>
		  			<c:if test="${role eq 'ROLE_ADMIN'}">
				        <button id="updateOper" onclick="changeOper('${operator.login}')">Изменить</button>
				        <button id="deleteOper" onclick="deleteOper(${operator.id})">Удалить</button>
				    </c:if>
				</div>
			</c:if>
			<c:if test="${orders ne null or search ne null}">
				<div class="serch">
					<form action="/search" method="GET">
						<div class="input" id="search">
							<c:choose>
								<c:when test="${role eq 'ROLE_USER'}">
									<input type="text" name="search_order" required="required" placeholder="Найти заказ по номеру">
								</c:when>
								<c:otherwise>
									<input type="text" name="search_order" required="required" placeholder="Найти заказ по номеру или логину">
								</c:otherwise>
							</c:choose>
						</div>
						<button type="submit" class="btm" id="btm_search">Найти</button>
					</form>
				</div>
				<div class="table">
					<table class="table_blur">
						<tr>
							<th>Номер заказа</th>
							<th>Дата заказа</th>
							<th>Кол-ко книг</th>
							<th>Сумма, грн</th>
							<th>Статус</th>

						</tr>
						<c:forEach var = "order" items="${orders}">
							<tr>
								<td onclick="window.location.replace('/profile/orders?id=${order.id}')">${order.id}</a></td>
								<td>${order.createDate}</td>
								<td>${order.booksCount}</td>
								<td>${order.totalPrice}</td>
								<c:if test="${role eq 'ROLE_USER'}">
									<td>
										<c:choose>
											<c:when test="${order.status}">Выполнен</c:when>
											<c:otherwise>Обрабатывается</c:otherwise>
										</c:choose>
									</td>
								</c:if>
								<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
									<td>
										<select class="chosen-select" id="${order.id}">
											<c:choose>
						                        <c:when test="${order.status}">
						                            <option selected="selected" value=true>Выполнен</option>
						                            <option value=false>Обрабатывается</option>
						                        </c:when>
						                        <c:otherwise>
						                            <option value=true>Выполнен</option>
						                            <option selected="selected" value=false>Обрабатывается</option>
						                        </c:otherwise>
						                    </c:choose>	
										</select>
									</td>
									<td>
										<button id="changeStatus" onclick="changeOrder('${order.id}', 'f')">Изменить</button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="pageNav">
					<nav aria-label="Page navigation">
		            	<ul class="pagination">
		            	   	<c:choose>
		            	   		<c:when test="${thisPage - 2 >= 0}">
		            	   			<li><a href="${pageLink}0"><<</a></li>
		            	   			<li><a href="${pageLink}<c:out value="${(thisPage - 1) > 0 ? (thisPage - 1): 0}"/>"><</a></li>
				               	        <c:forEach var="i" begin="${thisPage - 2}" end="${(thisPage + 2) < maxPage? (thisPage + 2) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}">
				               	        	<c:choose>
		                						<c:when test="${thisPage == i}">
				                   	        		<li class="active"><a href="${pageLink}<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
				                   	        	</c:when>
				                   	        	<c:otherwise>
				                   	        		<li><a href="${pageLink}<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
			                    	        	</c:otherwise>
			                    	        </c:choose>
				                       	</c:forEach>
				                    <li><a href="${pageLink}<c:out value="${(thisPage + 1) < maxPage? (thisPage + 1) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}"/>">></a></li>
				                    <li><a href="${pageLink}<c:out value="${(maxPage - 1) >= 0? (maxPage - 1): 0}"/>">>></a></li>
		           	    		</c:when>
		           	    		<c:otherwise>
				           	    	<li><a href="${pageLink}0"><<</a></li>
		           	    			<li><a href="${pageLink}<c:out value="${(thisPage - 1) > 0 ? (thisPage - 1): 0}"/>"><</a></li>
			                	        <c:forEach var="i" begin="0" end="${(thisPage + 2) < maxPage? (thisPage + 2) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}">
			                    	        <c:choose>
		            	    					<c:when test="${thisPage == i}">
				                            		<li class="active"><a href="${pageLink}<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
				                   	        	</c:when>
				                   	        	<c:otherwise>
				                   	        		<li><a href="${pageLink}<c:out value="${i}"/>"><c:out value="${i + 1}"/></a></li>
				                   	        	</c:otherwise>
				                   	        </c:choose>
				                       	</c:forEach>
				                    <li><a href="${pageLink}<c:out value="${(thisPage + 1) < maxPage? (thisPage + 1) : ((maxPage - 1) >= 0? (maxPage - 1): 0)}"/>">></a></li>
				                    <li><a href="${pageLink}<c:out value="${(maxPage - 1) >= 0? (maxPage - 1): 0}"/>">>></a></li>
		                       	</c:otherwise>
		                    </c:choose>
		           	    </ul>
		        	</nav>
		        </div>
			</c:if>
			<c:if test="${order ne null}">
				<div class="order">
					<c:if test="${role eq 'ROLE_USER'}">
						<div class="orderInf">
							<div class="orderId">Номер заказа: ${order.id}</div>
				  			<h3>Контактная информация</h3>
				  			<div class="orderName">Имя: ${order.name}</div>
				  			<div class="orderSurname">Фамилия: ${order.surname}</div>
				  			<div class="orderPhone">Телефон: ${order.phone}</div>
				  			<div class="orderAddress">Адрес доставки: ${order.address}</div>
				  			<h3>Заказ</h3>
				  			<div class="orderCreateDate">Дата заказа: ${order.createDate}</div>
				  			<div class="orderBooksCount">Количество книг: ${order.booksCount}</div>
				  			<div class="orderBooksPrice">Стоимость книг: ${order.totalPrice - order.deliveryPrice}</div>
				  			<div class="orderDeliveryPrice">Стоимость доставки: ${order.deliveryPrice}</div>
				  			<div class="orderTotalPrice">Общая стоимость: ${order.totalPrice}</div>
				  			<div class="orderStatus">Статус заказа: 
				  				<c:choose>
									<c:when test="${order.status}">Выполнен</c:when>
									<c:otherwise>Обрабатывается</c:otherwise>
								</c:choose>
				  			</div>
				  			<c:if test="${order.status}">
				  				<div class="orderDeliveryDate">Дата доставки: ${order.deliveryDate}</div>
				  			</c:if>
				  		</div>
				  		<div>
			  				<table class="table_blur">
				  				<tr>
				  					<th>Книга</th><th>Название</th><th>Цена, грн</th>
				  				</tr>
				  				<c:forEach var = "book" items="${order.books}">
				  					<tr onclick="window.location.replace('/books?id=${book.id}')">
				  						<td class="table_img"><img src="${book.image}" alt="${book.name}"></td>
				  						<td>${book.name}</td>
				  						<td>${book.price}</td>
				  					</tr>
				  				</c:forEach>
				  			</table>
				  		</div>
			    	</c:if>
			    	<c:if test="${role eq 'ROLE_ADMIN' or role eq 'ROLE_OPERATOR'}">
			    		<div class="orderInf">
				    		<div class="orderId">Номер заказа: ${order.id}</div>
				  			<h3>Контактная информация</h3>
				  			<div class="orderName">
				  				<label for="name">Имя: ${order.name}</label>
				  				<input type="text" name="name" id="name" />
				  			</div>
				  			<div class="orderSurname">
				  				<label for="surname">Фамилия: ${order.surname}</label>
				  				<input type="text" name="surname" id="surname" />
				  			</div>
				  			<div class="orderPhone">
				  				<label for="phone">Телефон: ${order.phone}</label>
				  				<input type="text" name="phone" id="phone" pattern="[0-9+]{13}" placeholder="+380XXYYYYYYY" />
				  			</div>
				  			<div class="orderAddress">Адрес доставки: ${order.address}</div>
				  			<h3>Заказ</h3>
				  			<div class="orderCreateDate">Дата заказа: ${order.createDate}</div>
				  			<div class="orderBooksCount" id="booksCount">Количество книг: ${order.booksCount}</div>
				  			<div class="orderBooksPrice">Стоимость книг: ${order.totalPrice - order.deliveryPrice}</div>
				  			<div class="orderDeliveryPrice" id="deliveryPrice">Стоимость доставки: ${order.deliveryPrice}</div>
				  			<div class="orderTotalPrice" id="totalPrice">Общая стоимость: ${order.totalPrice}</div>
				  			<div class="orderStatus">Статус заказа: 
				  				<select class="chosen-select" id="${order.id}">
									<c:choose>
							        	<c:when test="${order.status}">
							                <option selected="selected" value=true>Выполнен</option>
							                <option value=false>Обрабатывается</option>
							            </c:when>
							            <c:otherwise>
							                <option value=true>Выполнен</option>
							                <option selected="selected" value=false>Обрабатывается</option>
							            </c:otherwise>
							        </c:choose>	
								</select>
				  			</div>
				  			<c:if test="${order.status}">
				  				<div class="orderDeliveryDate">Дата доставки: ${order.deliveryDate}</div>
				  			</c:if>
				  			<div>
				  				<c:choose>
				  					<c:when test="${role eq 'ROLE_OPERATOR'}">
				  							<button id="changeStatus" onclick="changeOrder('${order.id}', 't')">Изменить</button>
				  					</c:when>
				  					<c:otherwise>
				  						<button id="changeStatus" onclick="changeOrder('${order.id}', 't')">Изменить</button>
				  						<button id="deleteOrder" onclick="deleteOrder('${order.id}')">Удалить</button>
				  					</c:otherwise>
				  				</c:choose>
				  			</div>
				  		</div>
				  		<div>
				        	<table class="table_blur">
				  				<tr>
				  					<th>Книга</th><th>Название</th><th>Цена, грн</th>
				  				</tr>
				  				<c:forEach var = "book" items="${order.books}">
				  					<tr>
				  						<td class="table_img" onclick="window.location.replace('/books?id=${book.id}')"><img src="${book.image}" alt="${book.name}"></td>
				  						<td>${book.name}</td>
				  						<td>${book.price}</td>
				  						<c:if test="${order.status ne 'true'}">
				  							<td><button id="deleteOper" onclick="deleteFromOrder('${order.id}', '${book.id}')">Удалить книгу</button></td>
				  						</c:if>
				  					</tr>
				  				</c:forEach>
				  			</table>
				  		</div>
			    	</c:if>
				</div>
			</c:if>
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
<script type="text/javascript" src="/js/profile.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>