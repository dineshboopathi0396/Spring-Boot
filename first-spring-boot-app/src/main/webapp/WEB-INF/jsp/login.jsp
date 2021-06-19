<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="login">
	<div class="login__container">

		<font color="red">${errorMessage}</font>
		<form method="post">
			Name : <input type="text" name="name" /> Password : <input
				type="password" name="password" /> <input type="submit" />
		</form>
	</div>
</div>
<%@ include file="common/footer.jspf"%>