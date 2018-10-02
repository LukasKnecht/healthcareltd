<?php 

//Creating database connection.
define('DB_SERVER', 'healthcareltd-mysqldbserver.mysql.database.azure.com');
define('DB_USERNAME', 'mysqldbuser@healthcareltd-mysqldbserver"');
define('DB_PASSWORD', 'sepUTSspring2018*');
define('DB_DATABASE', 'mysqldatabase39150');
$connection = mysql_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD) or die(mysql_error());
$database   = mysql_select_db(DB_DATABASE) or die(mysql_error());



//Selecting events records from events table
$query = mysql_query("SELECT * FROM Appointment");
$data  = array(); 
$resp  = array();
$i     = 0;
$row   = mysql_num_rows($query);
if($row > 0){
    while($data['Appointment'] = mysql_fetch_assoc($query))
    {
        $i++;
        //Geting event days
        $start = date("Y-m-d",strtotime($data['Appointment']['Appointmentstartday']));//die;
                $timestamp_start = strtotime($start);
                $end = date("Y-m-d",strtotime($data['Appointment']['Appointmentstartday']));
                $timestamp_end = strtotime($end);
                $diff = abs($timestamp_end - $timestamp_start); // that's it!
            
                $days = floor($diff/(60*60*24));
                $days = $days+1;
                //Defining colors to events
                if($days == 1){
                        $color='#FFDAB9';
                }elseif($days > 1 and $days <= 15){
                        $color='#8FBC8F';
                }elseif($days > 15 and $days <= 30){
                        $color='#C0C0C0';
                }elseif($days > 30 and $days <= 60){
                        $color='#90EE90';
                }else{
                        $color='#F4A460';
                }
                //Creating event short name with ...
                if(!empty($data['Appointment']['Appointmenttitle'])){
                        for ($i = 1; $i <= $days; $i++) {
                            $add_day = $i - 1;
                                $start = date('Y-m-d', strtotime("+{$add_day} day", $timestamp_start));
                                $event_short_name = substr($data['Appointment']['Appointmenttitle'] , 0, 15);
                                $sub='th';
                                if($i < 4){
                                        switch ($i){
                                                case 1:
                                                $sub='st';
                                                break;
                                                case 2:
                                                $sub='nd';
                                                break;
                                                case 3:
                                                $sub='rd';
                                                break;
                                        }
                                }
                                $event_short_name .= ' - ('.$i.$sub.' Day)';
                    
                                $startDate = strtotime($start);
                                //Colecting data in array         
                                $resp[$start . '_' . $data['Appointment']['Appointmentid'] . '_' . $i] = array(
                                        'id'    => $data['Appointment']['Appointmentid'],
                                        'title' => $event_short_name,
                                        'url'   => 'http://www.google.com',
                                        'start' => $start,
                                        'color' => $color,
                                );
                        }
                }            
            
    }
    $resp = array_values($resp);
}


?> 


<!DOCTYPE html> 
<html>
<head>
<link href='../fullcalendar.min.css' rel='stylesheet' />
<link href='../fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='../lib/moment.min.js'></script>
<script src='../lib/jquery.min.js'></script>
<script src='../fullcalendar.min.js'></script>

<script>

$(document).ready(function() {
    $('#calendar').fullCalendar({
        editable: false,
        events: <?php echo json_encode($resp) ?>,
        
    });
    
});


</script>

<style>

   body {
        margin: 40px 10px;
        padding: 0;
        font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
        font-size: 14px;
    }
    #calendar {
        max-width: auto;
        margin: 0 auto;
    }


</style>
</head>

<body>

<div id='calendar'></div>

</body>

</html>

