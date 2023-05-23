<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서관</title>
</head>
<body>
<div>
    <button type="button" onclick="location.href='/library_servlet/html/findbook.html'">도서 검색</button>
    <button type="button" onclick="location.href='/library_servlet/findRecommandServlet'">추천 도서</button>
    <button type="button" onclick="location.href='/library_servlet/html/application.html'">도서 신청</button>
</div>
</body>
</html>