<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>User-store</title>
    <meta charset="utf-8">
    <style>
        body { background-color: bisque; }
        .user-form-table { margin: 0 auto; }
        .user-list {
            display: block;
            width: 600px;
            height: 600px;
            border: 3px solid #000;
            margin: 0 auto;
        }
    </style>
</head>
<body>

<div style="text-align: center;"><h1>Каталог пользователей</h1></div>
<hr>
<form method="GET" action="/main-user-page/createUser">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table class="user-form-table">
        <tr>
            <td>Имя:</td>
            <td><input name="name" style="background-color:gray"></td>
        </tr>
        <tr>
            <td>Возраст:</td>
            <td><input name="age" style="background-color:gray"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Создать пользователя" style="background-color:whitesmoke; width: 300px; height: 80px;"></td>
        </tr>
    </table>
</form>


<form method="POST" action="/admin/updateUser">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <center>
        ID пользователя:<br>
        <input name="idUser" required style="background-color:gray"><br><br>
        Новое имя:<br>
        <input name="newName" style="background-color:gray"><br><br>
        Новый возраст:<br>
        <input name="newAge" style="background-color:gray"><br><br>
        <input type="submit" value="Изменить пользователя" style="background-color:whitesmoke; width: 300px; height: 80px; display: block; margin: 20px auto;">
    </center>
</form>

<form method="POST" action="/admin/deleteUser">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <center>
        ID пользователя:
        <input name="idUser" required style="background-color:gray"><br><br>

        <input type="submit" value="Удалить пользователя" style="background-color:whitesmoke; width: 300px; height: 80px; display: block; margin: 20px auto;">
    </center>
</form>

<div class="user-list">
    <div style="text-align: center;"><h2>Уже зарегистрированные пользователи</h2></div>
    <div>
        <h3 style="text-align: center;">
            &#9733 <%=request.getAttribute("user1")%><br><br>
            &#9733 <%=request.getAttribute("user2")%><br><br>
            &#9733 <%=request.getAttribute("user3")%><br><br>
            &#9733 <%=request.getAttribute("user4")%><br><br>
            &#9733 <%=request.getAttribute("user5")%><br><br>
            &#9733 <%=request.getAttribute("user6")%><br><br>
            &#9733 <%=request.getAttribute("user7")%><br><br>
            &#9733 <%=request.getAttribute("user8")%><br><br>
            &#9733 <%=request.getAttribute("user9")%><br><br>
            &#9733 <%=request.getAttribute("user10")%>
        </h3>
    </div>
</div>

</body>
</html>