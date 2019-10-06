<?php
include("database_config.php");
$cat = @$_POST['cat'];
$sql="SELECT * FROM `upanasyas` WHERE `category`='$cat'";
$result=mysqli_query($con,$sql);
$data=array();
while($row=mysqli_fetch_assoc($result)){
$data["data"][]=$row;
}
	header('Content-Type:Application/json');
			
	echo json_encode($data);
?>