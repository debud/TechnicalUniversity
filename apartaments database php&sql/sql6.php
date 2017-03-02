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

    $result = mysqli_query($conn, 'call procedure6()')
    or die("Query fail: ".mysqli_error($conn));

/*  fara procdura stocata
$sql = "SELECT DISTINCT P.nume  as nume
FROM Proprietar P
JOIN Apartament A1
ON P.id_proprietar = A1.id_proprietar
WHERE EXISTS (SELECT A2.id_proprietar
FROM Apartament A2
WHERE A1.id_proprietar = A2.id_proprietar
AND A1.id_ap <> A2.id_ap) "  ;

$result = $conn->query($result);*/

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th>Nume Proprietar</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["nume"]. "</td></tr>";
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
