<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign up - MiniReddit</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="${pageContext.request.contextPath}/signup" method="post" class="user-form">
        <h2 class="user-form-title">Register on MiniReddit</h2>
        <input name="username" placeholder="username" required>
        <input name="email" placeholder="email" type="email" required>
        <input name="password" placeholder="password" type="password" required>
        <button class="user-form-button">Sign up</button>
    </form>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>