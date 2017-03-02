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

$result = mysqli_query($conn, "call procedure2(".$_GET["id"].")")
    or die("Query fail: ".mysqli_error($conn));

/* FARA PROCEDURA STOCATA
$sql = "SELECT id_ap, suprafata
FROM Apartament
WHERE suprafata > " . $_GET["id"];

$result = $conn->query($sql); */

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th>ID APARTAMENT</th>
     <th>SUPRAFATA</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["id_ap"]. "</td><td>" . $row["suprafata"] . "</td></tr>";
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