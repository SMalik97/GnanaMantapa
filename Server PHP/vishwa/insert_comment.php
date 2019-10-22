<?php
	
	include("database_config.php");
	
	$typecomments=@$_POST['typecomments'];
	$comment_name=@$_POST['comment_name'];
	$comment_email=@$_POST['comment_email'];
	$postid=@$_POST['postid'];
	$postcatagory=@$_POST['postcatagory'];
	
	date_default_timezone_set('Asia/Kolkata');
	$current_date = date('Y/m/d H:i:s');

	
	$sql="INSERT INTO `tblcomments`(`postId`, `name`, `email`, `comment`, `postingDate`, `postcatagory`)
			VALUES('$postid','$comment_name','$comment_email','$typecomments','$current_date','$postcatagory');";
			
	$result=mysqli_query($con,$sql);
	
//	echo "$result";
	
	
	if($result > 0)
	{
		echo" Your Comment Has Been Posted ";
	}
	else
	{
		echo" Cannot be Posted ";
	}
	
?>