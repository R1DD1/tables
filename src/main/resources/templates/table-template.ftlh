<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Table</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/css/style.css">
</head>
<body>
<script src="../static/js/script.js" type="text/javascript"></script>
<h1>Таблица</h1><hr>
${table.name}
${table.id}
<hr>
<#--<table>-->
<#--    <#list 0..3 as row>-->
<#--        <tr>-->
<#--            <#list 0..3 as col>-->
<#--                <td contenteditable>-->
<#--                    <div class="cell" data-row="${row}" data-col="${col}">-->
<#--                        <#assign cell = table.getCellByCoordinates(row, col)>-->
<#--                        <h6>${cell.value}</h6>-->
<#--                    </div>-->
<#--                </td>-->
<#--            </#list>-->
<#--        </tr>-->
<#--    </#list>-->
<#--</table>-->
<table id="table">
    <thead>
    <tr>
        <th></th>
        <#list ['A', 'B', 'C', 'D'] as col>
            <th>${col}</th>
        </#list>
    </tr>
    </thead>
    <tbody>
    <#list 0..3 as row>
        <tr>
            <th>${row + 1}</th>
            <#list 0..3 as col>
                <td contenteditable>
                    <#assign cell = table.getCell(row, col)>
                    ${cell.value}
                </td>
            </#list>
        </tr>
    </#list>
    </tbody>
</table>
<form action="/tables/delete/${table.id}" method="post">
    <input type="submit" value="Удалить таблицу"/>
</form>
</body>
</html>