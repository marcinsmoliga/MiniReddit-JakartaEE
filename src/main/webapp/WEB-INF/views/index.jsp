<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>MiniReddit</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="../../styles/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <nav class="navbar">
        <a href="#" class="logo">
            <i class="fas fa-share-alt-square"></i>
            MiniReddit
        </a>
        <a href="#" class="login-button">SignIn</a>
    </nav>

    <aside class="categories">
        <ul>
            <c:forEach var="category" items="${requestScope.categories}">
                <li><a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </aside>

    <main>
        <c:forEach var="discovery" items="${requestScope.discoveries}">
            <article class="discovery">
                <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>
                <p class="discovery-details">Added by John, ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
                <a href="<c:out value="${discovery.url}"/>" target="_blank" class="discovery-link"><c:out
                        value="${discovery.url}"/></a>
                <p><c:out value="${discovery.description}"/></p>

                <section class="discovery-bar">
                    <a href="#" class="discovery-link upvote">
                        <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>
                    </a>
                    <p class="discovery-votes">32</p>
                    <a href="#" class="discovery-link downvote">
                        <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>
                    </a>
                </section>

            </article>
        </c:forEach>
    </main>
    <footer>MiniReddit, developed by example.com</footer>
</div>
</body>
</html>