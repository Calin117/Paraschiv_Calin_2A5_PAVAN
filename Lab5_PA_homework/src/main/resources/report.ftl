<!DOCTYPE html>
<html>
<head>
    <title>Raport Catalog</title>
</head>
<body>
<h1>Catalog: ${catalogName}</h1>
<table border="1">
    <tr>
        <th>Titlu</th>
        <th>Autor</th>
        <th>An</th>
        <th>Link</th>
    </tr>
    <#list items as item>
        <tr>
            <td>${item.title}</td>
            <td>${item.author}</td>
            <td>${item.year?c}</td>
            <td><a href="${item.location}">Deschide</a></td>
        </tr>
    </#list>
</table>
</body>
</html>