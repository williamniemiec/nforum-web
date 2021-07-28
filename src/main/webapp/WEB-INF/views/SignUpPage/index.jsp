<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="header">
		<h1 class="container">Sign Up</h1>
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
			<form method="POST" action="/auth/signup">
				<div class="form-group">
					<label for="name">Name:</label>
					<input 
						id="name" 
						class="form-control" 
						type="text" 
						name="name"
						required
					/>
				</div>
				<div class="form-group">
					<label for="login">Login:</label>
					<input 
						id="login" 
						class="form-control" 
						type="text" 
						name="login"
						required 
					/>
				</div>
				<div class="form-group">
					<label for="email">Email:</label>
					<input 
						id="email" 
						class="form-control" 
						type="text" 
						name="email"
						required 
					/>
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<input 
						id="password" 
						class="form-control" 
						type="password" 
						name="password"
						required
					/>
				</div>
				<input 
					class="btn btn-block" 
					type="submit" 
					value="Sign Up" 
				/>
			</form>
		</div>
	</div>
</div>
