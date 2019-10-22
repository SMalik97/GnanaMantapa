<?php

	include("database_config.php");
	
	$sql="SELECT * FROM `tblcomments` WHERE `status`='1'";
	
	
	$result=mysqli_query($con,$sql);
	
	$data=array();
	
	if(mysqli_num_rows($result) > 0){

	while($row=mysqli_fetch_assoc($result)){
	$data["data"][]=$row;

 
	}

	header('Content-Type:Application/json');
			
	echo json_encode($data);
	
	}
	else
	{
		echo "No";
	}
	

?>