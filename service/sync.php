<?PHP
error_reporting(0);
if(!isset($_POST['json']) ||
	!isset($_POST['android_id'])) die("sem permissao");

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

$visits = json_decode($_POST['json']);

foreach ($visits as $visit) {
	
	$visitor = $visit->user_name;
	$kiosk = $visit->kiosk_code;
	$date = $visit->date;
	$device = $_POST['android_id'];
	
	$query = "INSERT INTO visits VALUES(null, '$kiosk', '$date', '$visitor', '$device')";
	mysql_query($query);
	mysql_error();
}

echo "Sincronização OK!";