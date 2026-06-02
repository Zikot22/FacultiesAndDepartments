<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Факультеты и кафедры</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script defer src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
  <div class="container-fluid p-0"> 
	<jsp:include page="/views/header.jsp" />
    <main class="container my-5">
      <section class="text-center">
        <h2 class="mb-4">Функции системы</h2>

        <div class="list-group d-inline-block text-start" style="min-width:220px;">
          <a href="faculty" class="list-group-item list-group-item-action">Факультеты</a>
          <a href="department" class="list-group-item list-group-item-action">Кафедры</a>
        </div>
      </section>
    </main>
  </div>
  <jsp:include page="/views/footer.jsp" />
</html>