<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

		<link rel="stylesheet" type="text/css" href="../css/style.css">

		<title>Events</title>
	</head>

	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xl-1 col-lg-1 col-med-1"></div>
				<div class="col-xl-6 col-lg-6 col-med-6">
					<h1>${event.name}</h1>
					<p>Edit Event</p>
					<form action="/events/edit/process" class="form" method="POST">
						<label for="eventName">Name</label>
						<input type="text" id="eventName" name="eventName" value='${event.name}'>
						<label for="eventDate">Date</label>
						<input type="date" id="eventDate" name="eventDate" value='${fmteventDate}'>
						<!-- <input type="date" id="eventDate" name="eventDate" value='2018-12-01'> -->
						<label for="eventLocation">Location</label>
						<input type="text" id="eventLocation" name="eventLocation" value='${event.location}'>
						<select name="eventState" value="${event.state}">
							<option value="AL"<c:if test="${event.state == 'AL'}"><c:out value=" selected"/></c:if>>AL<c:if test="${event.state == 'AL'}"><c:out value=" selected"/></c:if>>AL</option>
							<option value="AK"<c:if test="${event.state == 'AK'}"><c:out value=" selected"/></c:if>>AK</option>
							<option value="AZ"<c:if test="${event.state == 'AZ'}"><c:out value=" selected"/></c:if>>AZ</option>
							<option value="AR"<c:if test="${event.state == 'AR'}"><c:out value=" selected"/></c:if>>AR</option>
							<option value="CA"<c:if test="${event.state == 'CA'}"><c:out value=" selected"/></c:if>>CA</option>
							<option value="CO"<c:if test="${event.state == 'CO'}"><c:out value=" selected"/></c:if>>CO</option>
							<option value="CT"<c:if test="${event.state == 'CT'}"><c:out value=" selected"/></c:if>>CT</option>
							<option value="DE"<c:if test="${event.state == 'DE'}"><c:out value=" selected"/></c:if>>DE</option>
							<option value="DC"<c:if test="${event.state == 'DC'}"><c:out value=" selected"/></c:if>>DC</option>
							<option value="FL"<c:if test="${event.state == 'FL'}"><c:out value=" selected"/></c:if>>FL</option>
							<option value="GA"<c:if test="${event.state == 'GA'}"><c:out value=" selected"/></c:if>>GA</option>
							<option value="HI"<c:if test="${event.state == 'HI'}"><c:out value=" selected"/></c:if>>HI</option>
							<option value="ID"<c:if test="${event.state == 'ID'}"><c:out value=" selected"/></c:if>>ID</option>
							<option value="IL"<c:if test="${event.state == 'IL'}"><c:out value=" selected"/></c:if>>IL</option>
							<option value="IN"<c:if test="${event.state == 'IN'}"><c:out value=" selected"/></c:if>>IN</option>
							<option value="IA"<c:if test="${event.state == 'IA'}"><c:out value=" selected"/></c:if>>IA</option>
							<option value="KS"<c:if test="${event.state == 'KS'}"><c:out value=" selected"/></c:if>>KS</option>
							<option value="KY"<c:if test="${event.state == 'KY'}"><c:out value=" selected"/></c:if>>KY</option>
							<option value="LA"<c:if test="${event.state == 'LA'}"><c:out value=" selected"/></c:if>>LA</option>
							<option value="ME"<c:if test="${event.state == 'ME'}"><c:out value=" selected"/></c:if>>ME</option>
							<option value="MD"<c:if test="${event.state == 'MD'}"><c:out value=" selected"/></c:if>>MD</option>
							<option value="MA"<c:if test="${event.state == 'MA'}"><c:out value=" selected"/></c:if>>MA</option>
							<option value="MI"<c:if test="${event.state == 'MI'}"><c:out value=" selected"/></c:if>>MI</option>
							<option value="MN"<c:if test="${event.state == 'MN'}"><c:out value=" selected"/></c:if>>MN</option>
							<option value="MS"<c:if test="${event.state == 'MS'}"><c:out value=" selected"/></c:if>>MS</option>
							<option value="MO"<c:if test="${event.state == 'MO'}"><c:out value=" selected"/></c:if>>MO</option>
							<option value="MT"<c:if test="${event.state == 'MT'}"><c:out value=" selected"/></c:if>>MT</option>
							<option value="NE"<c:if test="${event.state == 'NE'}"><c:out value=" selected"/></c:if>>NE</option>
							<option value="NV"<c:if test="${event.state == 'NV'}"><c:out value=" selected"/></c:if>>NV</option>
							<option value="NH"<c:if test="${event.state == 'NH'}"><c:out value=" selected"/></c:if>>NH</option>
							<option value="NJ"<c:if test="${event.state == 'NJ'}"><c:out value=" selected"/></c:if>>NJ</option>
							<option value="NM"<c:if test="${event.state == 'NM'}"><c:out value=" selected"/></c:if>>NM</option>
							<option value="NY"<c:if test="${event.state == 'NY'}"><c:out value=" selected"/></c:if>>NY</option>
							<option value="NC"<c:if test="${event.state == 'NC'}"><c:out value=" selected"/></c:if>>NC</option>
							<option value="ND"<c:if test="${event.state == 'ND'}"><c:out value=" selected"/></c:if>>ND</option>
							<option value="OH"<c:if test="${event.state == 'OH'}"><c:out value=" selected"/></c:if>>OH</option>
							<option value="OK"<c:if test="${event.state == 'OK'}"><c:out value=" selected"/></c:if>>OK</option>
							<option value="OR"<c:if test="${event.state == 'OR'}"><c:out value=" selected"/></c:if>>OR</option>
							<option value="PA"<c:if test="${event.state == 'PA'}"><c:out value=" selected"/></c:if>>PA</option>
							<option value="RI"<c:if test="${event.state == 'RI'}"><c:out value=" selected"/></c:if>>RI</option>
							<option value="SC"<c:if test="${event.state == 'SC'}"><c:out value=" selected"/></c:if>>SC</option>
							<option value="SD"<c:if test="${event.state == 'SD'}"><c:out value=" selected"/></c:if>>SD</option>
							<option value="TN"<c:if test="${event.state == 'TN'}"><c:out value=" selected"/></c:if>>TN</option>
							<option value="TX"<c:if test="${event.state == 'TX'}"><c:out value=" selected"/></c:if>>TX</option>
							<option value="UT"<c:if test="${event.state == 'UT'}"><c:out value=" selected"/></c:if>>UT</option>
							<option value="VT"<c:if test="${event.state == 'VT'}"><c:out value=" selected"/></c:if>>VT</option>
							<option value="VA"<c:if test="${event.state == 'VA'}"><c:out value=" selected"/></c:if>>VA</option>
							<option value="WA"<c:if test="${event.state == 'WA'}"><c:out value=" selected"/></c:if>>WA</option>
							<option value="WV"<c:if test="${event.state == 'WV'}"><c:out value=" selected"/></c:if>>WV</option>
							<option value="WI"<c:if test="${event.state == 'WI'}"><c:out value=" selected"/></c:if>>WI</option>
							<option value="WY"<c:if test="${event.state == 'WY'}"><c:out value=" selected"/></c:if>>WY</option>
						</select>
						<input type="hidden" name="eventId" value="${event.id}">
						<input class="btn btn-primary" type="submit" value="Edit">
					</form>
				</div>
			</div>
		</div>	
	</body>
</html>