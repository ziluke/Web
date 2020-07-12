<?php
    $con = mysqli_connect("localhost", "root", "", "NewsService");
    if(!$con){
        die("Could not connect: " . mysqli_error());
    }
?>