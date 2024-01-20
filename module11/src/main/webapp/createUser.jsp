<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>User-create</title>
    <meta charset="utf-8">
</head>
<body style="background-color: skyblue">
<div style="text-align: center;">
    <tr>
        <form method="POST" action="">
            <tr>
                <td>Имя:</td>
                <td><label>
                    <input name="name" style="background-color:gray">
                </label></td>
            </tr>
            <tr>
                <td>Возраст:</td>
                <td><label>
                    <input name="age" style="background-color:gray"><br><hr>
                </label></td>
            </tr>

            <br>

            <input type="submit" value="Зарегистрировать" style="background-color:blue ;width: 150px; height: 30px;">

</div>
</body>
</html>