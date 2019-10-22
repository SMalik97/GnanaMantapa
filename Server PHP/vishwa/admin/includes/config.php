<?php
define('DB_SERVER','localhost');
define('DB_USER','vpcoke_vishwa');
define('DB_PASS' ,'vpcoke_vishwa');
define('DB_NAME','vpcoke_vishwa');
$con = mysqli_connect(DB_SERVER,DB_USER,DB_PASS,DB_NAME);
// Check connection
if (mysqli_connect_errno())
{
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
?>