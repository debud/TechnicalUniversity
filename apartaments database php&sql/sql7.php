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

$sql = "SELECT C1.valoare as valoare, C1.id_ap as id_ap
FROM Consum C1
WHERE C1.valoare = 
(SELECT MAX(C2.valoare)
FROM Consum C2
WHERE C2.id_ap = C1.id_ap)";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th/>Valoare Maxima</th>
     <th>Id Apartament</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["valoare"]. "</td><td>" . $row["id_ap"]. "</td></tr>";
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
