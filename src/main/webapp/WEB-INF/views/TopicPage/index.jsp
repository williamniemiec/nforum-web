<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="header">
		<h1 class="container">${topic.title}</h1>
	</div>
	<div class="container content">
		<c:if test="${error != null}">
			<div class="alert alert-danger" role="alert">
				<button 
					class="close" 
					data-dismiss="alert" 
					aria-label="close"
				>
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="alert-heading">Error!</h4>
				<p>${error}</p>
			</div>
		</c:if>
		<div class="panel">
			<div class="topic-message">
				<h2>${topic.author.name}</h2>
				<p>${topic.content}<</p>
			</div>
			
			<h1>Answers</h1>
			<c:forEach var="comment" items="${topic.comments}">
				<div class="topic-message">
					<h2>${comment.author.login}</h2>
					<p>${comment.content}</p>
				</div>
			</c:forEach>
			
			<form method="POST">
				<div class="form-group">
					<label for="body">Message</label>
					<textarea id="body" class="form-control" name="body"></textarea>
				</div>
				<input class="btn btn-block" type="submit" value="Send" />
			</form>
		</div>
	</div>
</div>
