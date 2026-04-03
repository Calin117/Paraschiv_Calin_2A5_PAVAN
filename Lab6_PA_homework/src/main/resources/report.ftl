<!DOCTYPE html>
<html>
<head>
    <title>Movies Report</title>
</head>
<body>
<h1>Database Movies Report</h1>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Release Date</th>
        <th>Score</th>
        <th>Genre</th>
    </tr>
    <#list movies as movie>
        <tr>
            <td>${movie.title}</td>
            <td>${movie.releaseDate!"N/A"}</td>
            <td>${movie.score}</td>
            <td>${movie.genreName}</td>
        </tr>
    </#list>
</table>
</body>
</html>