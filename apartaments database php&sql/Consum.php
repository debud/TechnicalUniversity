<!DOCTYPE html>
<html>
<head>
    <title>Consum</title>
    <link rel=stylesheet type="text/css" href="index.css">
    <style>

    </style>
</head>
<body>
<div>
    <?php
    echo "<table>";
    echo "<tr><th>Id_ap</th><th>An</th><th>Luna</th><th>Nrpers</th><th>Cantitate</th><th>Valoare</th></tr>";

    class TableRows extends RecursiveIteratorIterator {
        function __construct($it) {
            parent::__construct($it, self::LEAVES_ONLY);
        }

        function current() {
            return "<td>" . parent::current(). "</td>";
        }

        function beginChildren() {
            echo "<tr>";
        }

        function endChildren() {
            echo "</tr>" . "\n";
        }
    }
    try {
        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "colocviu_final";

        $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $stmt = $conn->prepare("SELECT *
                             FROM Consum");
        $stmt->execute();
        $result1 = $stmt->setFetchMode(PDO::FETCH_ASSOC);
        foreach(new TableRows(new RecursiveArrayIterator($stmt->fetchAll())) as $k=>$v) {
            echo $v;
        }
    }
    catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;
    echo "</table>";
    ?>
    <br><br>
    <a href="tables.html" class="button">Inapoi</a>
</div>
</body>
</html>




