<?php
include("database_config.php");
$ask_name = @$_POST['ask_name'];
$ask_phone=@$_POST['ask_phone'];
$ask_email=@$_POST['ask_email'];
$ask_ques=@$_POST['ask_ques'];


$sql="INSERT INTO `asking`(`askname`, `askphone`, `askemail`, `askques`) 
		VALUES ('$ask_name','$ask_phone','$ask_email','$ask_ques');";

$result=mysqli_query($con,$sql);

if($result>0)
{
	echo "Your Question Has Been Recorded";
}
else
{
	echo "Sorry , Cant Record";
	
}
?>