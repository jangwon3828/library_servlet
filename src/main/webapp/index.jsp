<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Hahmlet&display=swap');
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            font-family: 'Hahmlet', sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .item {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px; /* 아이템 간의 간격 조정을 위한 마진 설정 */
        }

        h1 {
            font-size: 5vw; /* 페이지의 너비에 대한 비율로 텍스트 크기 조정 */
            text-align: center; /* 가운데 정렬 */
            margin-top: 10vh; /* 상단 여백 조정 */
        }

        select {
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        /* 텍스트 입력 스타일 */
        input[type="text"] {
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        /* 제출 버튼 스타일 */
        .submit {
            padding: 8px 16px;
            font-size: 14px;
            background-color: #05507D;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .submit:hover, button:hover{
            background-color: #00A5E5;
        }

        button {
            padding: 8px 16px;
            font-size: 14px;
            background-color: #05507D;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin:10px;
        }

    </style>
    <title>도서관</title>
</head>
<body>

<div class="container">
    <h1>
        PoscoDX 도서관 관리
    </h1>
    <div class="item">
        <form action="/library_servlet/find" method="get">
            <select name="search">
                <option value="전체">전체</option>
                <option value="제목">제목</option>
                <option value="저자">저자</option>
                <option value="출판사">출판사</option>
            </select>
            <input type="text" name="searchData" required/>
            <button type="submit" class="submit">검색</button>
            <br>
        </form>
    </div>
    <div class="item">
        <button type="button" onclick="location.href='/library_servlet/findRecommandServlet'">추천 도서</button>
        <button type="button" onclick="location.href='/library_servlet/html/application.html'">도서 신청</button>
        <button type="button" onclick="location.href='/library_servlet/html/application.html'">도서 관리</button>
    </div>
</div>
</body>
</html>