<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Department"%>
<%@ page import="domain.Faculty"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Редактирование кафедры</title>
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
					<h3>Список кафедр</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Код</th>
								<th scope="col">Название</th>
								<th scope="col">Короткое название</th>
								<th scope="col">Заведующий</th>
								<th scope="col">Факультет</th>
								<th scope="col">Номер телефона</th>
								<th scope="col">Редактировать</th>
								<th scope="col">Удалить</th>
							</tr>
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
									<td width="20"><a
										href="<c:url value='/editdepartment?id=${department.getId()}'/>"
										role="button" class="btn btn-outline-primary">Редактировать</a>
									</td>
									<td width="20">
										<form method="post"
											action="<c:url value='/deletedepartment'/>"
											style="display: inline;">
											<input type="hidden" name="id" value="${department.getId()}" />
											<button type="submit" class="btn btn-outline-danger">Удалить</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-4 border px-4">
					<form method="POST" action="">
						<h3 class="mt-2">Редактировать кафедру</h3>

						<c:if test="${editDepartment != null}">
							<div class="row">
								<label class="col-sm-5 col-form-label">Код</label>
								<div class="col-sm-7">
									<input type="text" name="id" class="form-control" readonly
										value="${editDepartment.getId()}" />
								</div>
							</div>
						</c:if>

						<div class="mb-1">
							<label for="depName" class="col-sm-7 col-form-label">Название</label>
							<div class="col-sm-7">
								<input type="text" name="name" class="form-control"
									id="departmentName"
									value="${editDepartment != null ? editDepartment.getName() : ''}"
									required />
							</div>
						</div>

						<div class="mb-1">
							<label for="shortName" class="col-sm-7 col-form-label">Короткое
								название</label>
							<div class="col-sm-7">
								<input type="text" name="shortName" class="form-control"
									id="departmentShortName"
									value="${editDepartment != null ? editDepartment.getShortName() : ''}" />
							</div>
						</div>

						<div class="mb-1">
							<label for="head" class="col-sm-7 col-form-label">Заведующий</label>
							<div class="col-sm-7">
								<input type="text" name="head" class="form-control"
									id="departmentHead"
									value="${editDepartment != null ? editDepartment.getHead() : ''}" />
							</div>
						</div>

						<div class="mb-1">
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

						<div class="mb-1">
							<label for="phoneNumber" class="col-sm-7 col-form-label">Номер
								телефона</label>
							<div class="col-sm-7">
								<input type="text" name="phoneNumber" class="form-control"
									id="departmentPhoneNumber"
									value="${editDepartment != null ? editDepartment.getPhoneNumber() : ''}" />
							</div>
						</div>

						<div class="mb-2">
							<button type="submit" class="btn btn-primary">Редактировать</button>
							<a href="<c:url value='/department'/>" role="button"
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