<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
	<head>
		<title>Главная страница</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" href="../image/icon.png" type="image/png">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
		<link rel="stylesheet" href="../styles.css">
		<!--Шрифты-->
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200..700&display=swap" rel="stylesheet">
	</head>

	<body>
		<nav class="navbar navbar-expand-lg bg-body-tertiary pt-0">
			<div class="container-fluid bg-success">
				<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="white" class="bi bi-building-fill me-2" viewBox="0 0 16 16">
					<path d="M3 0a1 1 0 0 0-1 1v14a1 1 0 0 0 1 1h3v-3.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5V16h3a1 1 0 0 0 1-1V1a1 1 0 0 0-1-1zm1 2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3.5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5M4 5.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zM7.5 5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5m2.5.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zM4.5 8h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5m2.5.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3.5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5"></path>
				</svg>
				<form action="home">
					<a class="navbar-brand text-white" href="home">Офис 1</a>
				</form>
				<button class="navbar-toggler text-white" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav">
						<li class="nav-item">
							<form action="login">
								<a class="nav-link active text-white" aria-current="page" href="login">Войти в служебную учетную запись</a>
							</form>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<div>
		<form class = "m-5"  method="post" action="/home">
			<div class="mb-3">
				<label>ФИО заявителя</label>
				<input class="form-control" name="applicantName">
			</div>
			<div class="mb-3">
				<label>ФИО руководителя</label>
				<input class="form-control" name="managerName">
			</div>
			<div class="mb-3">
				<label>Адрес</label>
				<input class="form-control" name="address">
			</div>
			<div class="mb-3">
				<label>Тематика</label>
				<input class="form-control" name="theme">
			</div>
			<div class="mb-3">
				<label>Содержание</label>
				<textarea class="form-control form-control-lg" id="largeTextarea" rows="4" name="content"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">Подтвердить</button>
		</form>
		</div>
		<div class="container mt-3">
			<%-- Сообщение об успехе --%>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success alert-dismissible fade show">
						${successMessage}
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				</div>
			</c:if>

			<%-- Сообщение об ошибке --%>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger alert-dismissible fade show">
						${errorMessage}
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				</div>
			</c:if>
		</div>
	</body>
</html>