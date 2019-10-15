<?php
	
	include("database_config.php");
	
	$issue_name=@$_POST['issue_name'];
	$issue_phone=@$_POST['issue_phone'];
	$issue_email=@$_POST['issue_email'];
	$issue_issue=@$_POST['issue_issue'];

	
	$sql="INSERT INTO `issue`(`issuename`, `issuephone`, `issueemail`, `issueissue`) 
			VALUES ('$issue_name','$issue_phone','$issue_email','$issue_issue');";
			
	$result=mysqli_query($con,$sql);
	
//	echo "$result";
	
	
	if($result > 0)
	{
		echo" Your issue Has Been Successfully Recorded";
	}
	else
	{
		echo" Cannot be recorded";
	}
	
?>