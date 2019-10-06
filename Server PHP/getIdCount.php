<?php

	include("database_config.php");
	
	$sql="SELECT COUNT(id) FROM 'info';";
	$result=mysqli_query($con,$sql);
	
	if($result>0)
	{
		echo "$result";
	}
	else
	{
		echo "No one present";
	}

?>