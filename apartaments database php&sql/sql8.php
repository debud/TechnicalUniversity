<!DOCTYPE html>
<html>
<head>
    <title>3</title>
    <link rel=stylesheet type="text/css" href="index.css">
</head>
    <?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "colocviu_final";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
    echo "<h2>Afisare rezultat</h2>";

$sql = "SELECT  P.nume as nume , A.id_ap as id_ap
FROM Proprietar P
JOIN Apartament A
ON A.id_proprietar = P.id_proprietar
WHERE A.id_ap IN
(SELECT C.id_ap
FROM Consum C 
GROUP BY C.id_ap
HAVING SUM(C.valoare) > 
( SELECT IFNULL(SUM(T.valoare), 0)
FROM CHITANTA T
WHERE T.id_ap = C.id_ap)
)"  ;

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th>Nume Restantier</th>
     <th>Apartament Restant</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["nume"]. "</td><td>" . $row["id_ap"]."</td></tr>";
    }
    echo "</table>";

} else {
    echo "0 results";
}
$conn->close();
?>
    <h2><form action="index.html" class="button">
    <input type="submit" value="Inapoi">
</form></h2>
</body>
</html>>
