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

    $result = mysqli_query($conn,
        "call procedure1(".$_GET["id"].")")
    or die("Query fail: ".mysqli_error($conn));

    /*  FARA PROCEDURA:
     while ($row = mysqli_fetch_array($result)){
           echo $row[0] . " - " . + $row[1];
       }
   $sql = "SELECT nr, valoare, data
   FROM Chitanta
   WHERE id_ap = " . $_GET["id"] . " ORDER BY data";

   $result = $conn->query($sql);
   */

if ($result->num_rows > 0) {
    // output data of each row
    echo "<table><thead>
  <tr>
     <th>NR</th>
     <th>VALOARE</th>
     <th>DATA</th>
  </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["nr"]. "</td><td>" . $row["valoare"] . "</td><td>" . $row["data"] . "</td></tr>";
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