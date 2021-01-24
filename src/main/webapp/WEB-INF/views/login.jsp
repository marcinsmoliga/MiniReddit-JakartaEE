<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sing in - MiniReddit</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="j_security_check" method="post" class="user-form">
        <h2 class="user-form-title">Sign in to MiniReddit</h2>
        <input name="j_username" placeholder="username" required>
        <input name="j_password" placeholder="password" type="password" required>
        <button class="user-form-button">Sign in</button>
        <p>You do not have an account?<a href="${pageContext.request.contextPath}/signup">Register</a></p>
    </form>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>