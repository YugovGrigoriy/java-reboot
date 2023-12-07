<%@page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>FinanceDeposit</title>
    <meta charset="utf-8">
    <style>
    font-family: 'monospace';
    font-size: 150px;
</style>
</head>
<body style="font-size:20px">
<center>
    <h1>Результат</h1>
    Итоговая сумма:
    <%=request.getAttribute("calculation")%>
     рублей
</center>
</body>
</html>