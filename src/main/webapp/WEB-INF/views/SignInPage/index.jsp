<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="header">
		<h1 class="container">Sign In</h1>
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
			<form method="POST" action="/auth/signin">
				<div class="form-group">
					<label for="login">Login:</label>
					<input 
						id="login" 
						class="form-control" 
						type="text" 
						name="login" 
					/>
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<input 
						id="password" 
						class="form-control" 
						type="password" 
						name="password" 
					/>
				</div>
				<input 
					class="btn btn-block" 
					type="submit" 
					value="Sign In" 
				/>
			</form>
			<button 
				class="btn btn-block btn-link" 
				onclick="window.location.href='/auth/signup'"
			>
				Sign Up
			</button>
		</div>
	</div>
</div>
