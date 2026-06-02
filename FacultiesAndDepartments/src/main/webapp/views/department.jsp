<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Department"%>
<%@ page import="domain.Faculty"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Кафедры</title>
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
					<h3>Список кафедр</h3>
					<table class="table">
						<thead>
							<th scope="col">Код</th>
							<th scope="col">Название</th>
							<th scope="col">Короткое название</th>
							<th scope="col">Заведующий</th>
							<th scope="col">Факультет</th>
							<th scope="col">Номер телефона</th>
							<th scope="col">Редактировать</th>
							<th scope="col">Удалить</th>
						</thead>
						<tbody>
							<c:forEach var="department" items="${departments}">
								<tr>
									<td>${department.getId()}</td>
									<td>${department.getName()}</td>
									<td>${department.getShortName()}</td>
									<td>${department.getHead()}</td>
									<td>${department.getFaculty()}</td>
									<td>${department.getPhoneNumber()}</td>
									<td width="20"><a href="#" role="button"
										class="btn btn-outline-primary"> Редактировать </a></td>
									<td width="20"><a href="#" role="button"
										class="btn btn-outline-primary"> Удалить </a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-4 border px-4">
					<form method="POST" action="">
						<h3 class="mt-2">Новая кафедра</h3>
						<div class="mb-2">
							<label for="depName" class="col-sm-7 col-form-label">Название</label>
							<div class="col-sm-7">
								<input type="text" name="depName" class="form-control"
									id="departmentName" />
							</div>
						</div>
						<div class="mb-2">
							<label for="shortName" class="col-sm-7 col-form-label">Короткое
								название</label>
							<div class="col-sm-7">
								<input type="text" name="shortName" class="form-control"
									id="departmentShortName" />
							</div>
						</div>
						<div class="mb-2">
							<label for="head" class="col-sm-7 col-form-label">Заведующий</label>
							<div class="col-sm-7">
								<input type="text" name="head" class="form-control"
									id="departmentHead" />
							</div>
						</div>
						<div class="mb-2">
							<label for="selectFaculty" class="col-sm-7 col-form-label">Факультет</label>
							<div class="col-sm-7">
								<select name="selectFaculty" class="form-control">
									<option>Выберите факультет</option>
									<c:forEach var="faculty" items="${faculties}">
										<option value="${faculty}">
											<c:out value="${faculty.getName()}"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="mb-4">
							<label for="phoneNumber" class="col-sm-7 col-form-label">Номер
								Телефона</label>
							<div class="col-sm-7">
								<input type="text" name="phoneNumber" class="form-control"
									id="departmentPhoneNumber" />
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