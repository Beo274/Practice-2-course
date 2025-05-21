<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
	<title>Главная страница</title>
	<meta charset="utf-8">
	<link rel="shortcut icon" href="/image/icon.png" type="image/png">

	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200..700&display=swap" rel="stylesheet">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">

	<link rel="stylesheet" href="../css/styles.css">
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
		<button class="navbar-toggler text-white" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
				aria-expanded="false" aria-label="Toggle navigation">
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

<div class="container mt-4">
	<form:form modelAttribute="appeal" method="post" action="/home" cssClass="m-5">
		<div class="mb-3">
			<label class="form-label">ФИО заявителя</label>
			<form:input path="applicantName" cssClass="form-control ${not empty applicantNameError ? 'is-invalid' : ''}" />
			<form:errors path="applicantName" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<label class="form-label">ФИО руководителя</label>
			<form:input path="managerName" cssClass="form-control ${not empty managerNameError ? 'is-invalid' : ''}" />
			<form:errors path="managerName" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<label class="form-label">Адрес</label>
			<form:input path="address" cssClass="form-control ${not empty addressError ? 'is-invalid' : ''}" />
			<form:errors path="address" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<label class="form-label">Тематика</label>
			<form:input path="theme" cssClass="form-control ${not empty themeError ? 'is-invalid' : ''}" />
			<form:errors path="theme" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<label class="form-label">Содержание</label>
			<form:textarea path="content" cssClass="form-control form-control-lg ${not empty contentError ? 'is-invalid' : ''}" rows="4" />
			<form:errors path="content" cssClass="text-danger" />
		</div>
		<button type="submit" class="btn btn-primary">Подтвердить</button>
	</form:form>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>