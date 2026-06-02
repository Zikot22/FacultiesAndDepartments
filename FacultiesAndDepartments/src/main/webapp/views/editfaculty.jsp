<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Faculty"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<div class="row justify-content-start">
				<div class="col-8 border bg-light px-4">
					<h3>Список факультетов</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Код</th>
								<th scope="col">Название</th>
								<th scope="col">Короткое название</th>
								<th scope="col">Декан</th>
								<th scope="col">Номер телефона</th>
								<th scope="col">Редактировать</th>
								<th scope="col">Удалить</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="faculty" items="${faculties}">
								<tr>
									<td>${faculty.getId()}</td>
									<td>${faculty.getName()}</td>
									<td>${faculty.getShortName()}</td>
									<td>${faculty.getDean()}</td>
									<td>${faculty.getPhoneNumber()}</td>
									<td width="20"><a
										href="<c:url value='/editfaculty?id=${faculty.getId()}'/>"
										role="button" class="btn btn-outline-primary">Редактировать</a>
									</td>
									<td width="20">
										<form method="post" action="<c:url value='/deletefaculty'/>"
											style="display: inline;">
											<input type="hidden" name="id" value="${faculty.getId()}" />
											<button type="submit" class="btn btn-outline-danger">Удалить</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-4 border px-4">
					<form method="POST"
						action="">
						<h3 class="mt-2">Редактировать факультет</h3>

						<c:if test="${facultyEdit != null}">
							<div class="mb-2 row">
								<label class="col-sm-5 col-form-label">Код</label>
								<div class="col-sm-7">
									<input type="text" name="id" class="form-control" readonly
										value="${facultyEdit.getId()}" />
								</div>
							</div>
						</c:if>

						<div class="mb-2">
							<label for="facName" class="col-sm-7 col-form-label">Название</label>
							<div class="col-sm-7">
								<input type="text" name="name" class="form-control"
									id="facultyName"
									value="${facultyEdit != null ? facultyEdit.getName() : ''}"
									required />
							</div>
						</div>

						<div class="mb-2">
							<label for="shortName" class="col-sm-7 col-form-label">Короткое
								название</label>
							<div class="col-sm-7">
								<input type="text" name="shortName" class="form-control"
									id="facultyShortName"
									value="${facultyEdit != null ? facultyEdit.getShortName() : ''}" />
							</div>
						</div>

						<div class="mb-2">
							<label for="dean" class="col-sm-7 col-form-label">Декан</label>
							<div class="col-sm-7">
								<input type="text" name="dean" class="form-control"
									id="facultyDean"
									value="${facultyEdit != null ? facultyEdit.getDean() : ''}" />
							</div>
						</div>

						<div class="mb-4">
							<label for="phoneNumber" class="col-sm-7 col-form-label">Номер
								телефона</label>
							<div class="col-sm-7">
								<input type="text" name="phoneNumber" class="form-control"
									id="facultyPhoneNumber"
									value="${facultyEdit != null ? facultyEdit.getPhoneNumber() : ''}" />
							</div>
						</div>

						<div class="mb-3">
							<button type="submit" class="btn btn-primary">Редактировать</button>
							<a href="<c:url value='/faculty'/>" role="button"
								class="btn btn-secondary">Отменить</a>
						</div>
					</form>
				</div>

			</div>
		</div>
		<jsp:include page="/views/footer.jsp" />
	</div>
</body>
</html>