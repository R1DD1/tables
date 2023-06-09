<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Tables</title>
</head>
<body>
    <h1>Таблицы</h1><hr>
    <#list tables as table>
        <div>
            <p><b>${table.name}</b> | <a href="/tables/${table.id}">Открыть</a></p>
        </div>
    </#list>
    <hr>
    <h3>Создать новую таблицу</h3>
    <form action="/tables/create" method="post">
        Название таблицы: <label><input type="text" name="name"/></label><br>

        <input type="submit" value="Создать таблицу"/>
    </form>
</body>
</html>