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

$sql = "SELECT P.nume AS nume_proprietar, A1.adresa AS adresa_apartament1, A2.adresa AS adresa_apartament2
FROM Proprietar P 
JOIN Apartament A1 ON (P.id_proprietar = A1.id_proprietar)	
JOIN Apartament A2 where (A1.adresa < A2.adresa AND A1.id_proprietar = A2.id_proprietar)" ;

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th>Nume Proprietar</th>
     <th>Adresa Apartament 1</th>
     <th>Adresa Apartament 2</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["nume_proprietar"]. "</td><td>" . $row["adresa_apartament1"] . "</td><td>" . $row["adresa_apartament2"]."</td></tr>";
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