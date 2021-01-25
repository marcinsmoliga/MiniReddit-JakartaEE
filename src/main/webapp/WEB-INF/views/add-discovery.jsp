<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add new discovery - MiniReddit</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/discovery-form.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="#" method="post" class="discovery-form">
        <h2 class="discovery-form-title">Add new discovery</h2>
        <input name="title" placeholder="title" required>
        <input name="url" placeholder="url" type="url" required>
        <select>
            <option>Business</option>
            <option>Entertainment</option>
            <option>Politics</option>
        </select>
        <textarea name="description" placeholder="Description"></textarea>
        <button class="discovery-form-button">Add</button>
    </form>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>