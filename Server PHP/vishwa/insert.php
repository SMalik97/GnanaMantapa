<?php
include("database_config.php");
$names = @$_POST['names'];
$citys=@$_POST['citys'];
$mobiles=@$_POST['mobiles'];
$emails=@$_POST['emails'];
$pass=@$_POST['pass'];

$sql="INSERT INTO `user_registration`(`name`, `mobile`, `city`, `email`,`password`) VALUES ('$names','$mobiles','$citys','$emails','$pass');";

$result=mysqli_query($con,$sql);

if($result>0)
{
	echo "1";
}
else
{
	echo "0";
	
}
?>

