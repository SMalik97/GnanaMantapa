<?php

	include("database_config.php");
	
	$sql="SELECT * FROM `info` WHERE 1";
	$result=mysqli_query($con,$sql);
	
	$data=array();

	while($row=mysqli_fetch_assoc($result)){
	$data["data"][]=$row;

 
	}

	header('Content-Type:Application/json');
			
	echo json_encode($data);
	

?>