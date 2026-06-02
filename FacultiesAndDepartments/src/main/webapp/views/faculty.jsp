<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ page import="domain.Faculty"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Факультеты</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" href="css/bootstrap.min.css">
<script defer src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid p-0">
		<jsp:include page="/views/header.jsp" />
		<div class="container-fluid">
			<div class="row justify-content-start ">
				<div class="col-8 border bg-light px-4">
					<h3>Список факультетов</h3>
					<table class="table">
						<thead>
							<th scope="col">Код</th>
							<th scope="col">Название</th>
							<th scope="col">Короткое название</th>
							<th scope="col">Декан</th>
							<th scope="col">Номер телефона</th>
							<th scope="col">Редактировать</th>
							<th scope="col">Удалить</th>
						</thead>
						<tbody>
							<c:forEach var="faculty" items="${faculties}">
								<tr>
									<td>${faculty.getId()}</td>
									<td>${faculty.getName()}</td>
									<td>${faculty.getShortName()}</td>
									<td>${faculty.getDean()}</td>
									<td>${faculty.getPhoneNumber()}</td>
									<td width="20"><a href="editfaculty?id=${faculty.getId()}" role="button"
										class="btn btn-outline-primary"> Редактировать </a></td>
									<td width="20"><a href="deletefaculty?id=${faculty.getId()}" role="button"
										onclick="return confirm('Удалить факультет с кодом: '+${faculty.getId()}+'?')" 
										class="btn btn-outline-primary">
										 Удалить </a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-4 border px-4">
					<form method="POST" action="">
						<h3 class="mt-2">Новый факультет</h3>
						<div class="mb-2">
							<label for="facName" class="col-sm-7 col-form-label">Название</label>
							<div class="col-sm-7">
								<input type="text" name="facName" class="form-control"
									id="facultyName" />
							</div>
						</div>
						<div class="mb-2">
							<label for="shortName" class="col-sm-7 col-form-label">Короткое название</label>
							<div class="col-sm-7">
								<input type="text" name="shortName" class="form-control"
									id="facultyShortName" />
							</div>
						</div>
						<div class="mb-2">
							<label for="dean" class="col-sm-7 col-form-label">Декан</label>
							<div class="col-sm-7">
								<input type="text" name="dean" class="form-control"
									id="facultyDean" />
							</div>
						</div>
						<div class="mb-4">
							<label for="phoneNumber" class="col-sm-7 col-form-label">Номер Телефона</label>
							<div class="col-sm-7">
								<input type="text" name="phoneNumber" class="form-control"
									id="facultyPhoneNumber" />
							</div>
						</div>
						<p>
							<button type="submit" class="btn btn-primary">Добавить</button>
						</p>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/views/footer.jsp" />
	</div>
</body>
</html>