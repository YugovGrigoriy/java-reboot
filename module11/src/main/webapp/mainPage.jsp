<%@page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>User-store</title>
    <meta charset="utf-8">
</head>
<body style="background-color: bisque">
<div style="text-align: center;"><h1>Каталог пользователей</h1></div>
<hr>
<br>
<label>
    <div style="position: absolute;left: 450px;top: 200px;">
        <form action="/createUser.jsp">
            <input type="submit" value="Создать пользователя" style="background-color:#e85454 ;width: 300px; height: 80px;">
        </form>
    </div>
    <div style="position: absolute;right: 450px;top: 200px;">
        <input type="submit" value="Удалить пользователя" style="background-color:whitesmoke ;width: 300px; height: 80px;">
    </div>
</label>
<label style="position: absolute;top: 350px;left: 700px">
    <div style="text-align: center;"><h2>Уже зарегистрированные пользователи</h2></div>
    <div style="width: 500px; height: 500px;border: 3px solid #000"><div style="text-align: center;">
        <h3>
            &#9733 <%=request.getAttribute("user1")%> <br><br>
            &#9733 <%=request.getAttribute("user2")%><br><br>
            &#9733 <%=request.getAttribute("user3")%> <br><br>
            &#9733 <%=request.getAttribute("user4")%> <br><br>
            &#9733 <%=request.getAttribute("user5")%> <br><br>
            &#9733 <%=request.getAttribute("user6")%> <br><br>
            &#9733 <%=request.getAttribute("user7")%> <br><br>
            &#9733 <%=request.getAttribute("user8")%> <br><br>
            &#9733 <%=request.getAttribute("user9")%> <br><br>
            &#9733 <%=request.getAttribute("user10")%>
        </h3>


    </div>
    </div>
</label>
</body>
</html>