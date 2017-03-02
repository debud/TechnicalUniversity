<!DOCTYPE html>
<html>
<head>
    <title>4</title>
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

    if (isset($_GET["id"]))
    {
        $strada = $_GET["id"];
        echo $_GET["id"];
        $lungime = strlen($strada);
        $sql = "SELECT P.nume AS nume_proprietar, A.adresa as adresa , C.an AS an, C.luna AS luna, C.valoare AS valoare
    FROM proprietar P
    JOIN APARTAMENT A on P.id_proprietar = A.id_proprietar
    JOIN Consum C on A.id_ap = C.id_ap
    where SUBSTR(adresa,1," . $lungime . " ) = '".$strada."'";

        $result = $conn->query($sql);

        if ($result->num_rows > 0) {

            echo "<table><thead>
  <tr>
     <th>Nume Proprietar</th>
     <th>Adresa</th>
     <th>An</th>
     <th>Luna</th>
     <th>Valoare</th>
  </tr>";
            while ($row = $result->fetch_assoc()) {
                echo "<tr><td>" . $row["nume_proprietar"] . "</td><td>" . $row["adresa"] . "</td><td>" . $row["an"] . "</td><td>" . $row["luna"] . "</td><td>" . $row["valoare"] . "</td></tr>";
            }
            echo "</table>";

        } else {
            echo "0 results";
        }
        $conn->close();
    } else
        echo "<h2>input invalid</h2>";
    ?>

    <h2>
        <form action="index.html">
            <input type="submit" value="Inapoi">
        </form>
    </h2>
    </body>
</html>
