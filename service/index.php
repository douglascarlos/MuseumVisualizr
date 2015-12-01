<?PHP
error_reporting(0);
date_default_timezone_set('America/Sao_Paulo');
$user = 'u621408458_user';
$password = 'museumvisualizr@2015';
$db = 'u621408458_museu';
$host = 'mysql.hostinger.com.br';
$port = 3306;

$link = mysql_connect(
   "$host:$port", 
   $user, 
   $password
);

$db_selected = mysql_select_db(
   $db, 
   $link
);

$query = "SELECT 
				id, kiosk, 
				DATE_FORMAT(visit_date,'%d/%m/%Y %H:%i:%s') visit_date, 
				user_name, device 
		  	FROM 
		  		visits 
	  		ORDER BY 
	  			id DESC";
$rs = mysql_query($query);
mysql_error();
?>
<table border=1>
	<tr>
		<td>#</td>
		<td>Kiosk</td>
		<td>Date</td>
		<td>User Name</td>
		<td>Device</td>
	</tr>

	<?php
	while($visit = mysql_fetch_array($rs)) {
	?>
	<tr>
		<td><?= $visit['id'] ?></td>
		<td><?= $visit['kiosk'] ?></td>
		<td><?= $visit['visit_date'] ?></td>
		<td><?= $visit['user_name'] ?></td>
		<td><?= $visit['device'] ?></td>
	</tr>
	<?php
	}
	?>
</table>