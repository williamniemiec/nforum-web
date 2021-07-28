<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="header">
		<h1 class="container">New topic</h1>
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
			<form method="POST">
				<div class="form-group">
					<label for="title">Title</label>
					<input 
						id="title" 
						class="form-control" 
						type="text" 
						name="title" 
						placeholder="Title"
						required 
					/>
				</div>
				<div class="form-group">
					<label for="body">Body</label>
					<textarea 
						id="body" 
						class="form-control" 
						name="body" 
						placeholder="Something..."
						required
					>
					</textarea>
				</div>
				<input class="btn btn-block" type="submit" value="Create" />
			</form>
		</div>
	</div>
</div>
