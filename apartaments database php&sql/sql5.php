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

$sql = "SELECT A.adresa as adresa , C.luna as luna
FROM Apartament A
JOIN Consum C 
 ON (A.id_ap= C.id_ap)
WHERE C.an = " .  $_GET["id"] . " AND C.valoare <= ALL (SELECT C2.VALOARE
FROM Consum C2
WHERE C2.an = " .  $_GET["id"] . " AND C2.id_ap = C.id_ap ) "  ;

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th>Adresa</th>
     <th>Luna</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["adresa"]. "</td><td>" . $row["luna"] . "</td></tr>";
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
</html>
