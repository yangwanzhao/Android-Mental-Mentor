<?php
function My_login($username, $password)
    {

      $con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT id, full_name FROM users WHERE id = '{$username}' and PASSWORD = '{$password}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      if($num_rows>0){
        $row = mysqli_fetch_array($result);
        $username = $row[0];
        $fullname = $row[1];
        echo 'Succeed: ' . $username . ';' . $fullname ;
      }
      else{
        echo "Failed";
      }
      mysqli_close($con_db);
    }
    function Get_Music($type)
    {
      $con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }

      $sql_command = "SELECT URL FROM music WHERE type = '{$type}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      if($num_rows>0){
        while($row = mysqli_fetch_array($result)){

        $url = $row[0];

        echo  $url . '<';
      }
      }
      else{
        echo "Failed in getting the url";
      }
      mysqli_close($con_db);
    }
    function My_registration($id, $password, $full_name, $areas_of_stress, $stress_level){
   		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
  		if(mysqli_connect_errno($con_db)){
    			echo "Failed to connect to MYSql: " . mysqli_connect_error();
  		}
    	$sql_command = "INSERT INTO users (id, password, full_name, areas_of_stress, stress_level) VALUES ('{$id}', '{$password}', '{$full_name}', '{$areas_of_stress}', '{$stress_level}')";
  		$result = mysqli_query($con_db, $sql_command);
  		if($result){
  			echo $result . ' result';
		}
		else{
  			echo 'Failed';
		}
  		mysqli_close($con_db);
    }
	function Get_Forum($id){
		 $con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT areas_of_stress FROM users WHERE id = '{$id}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      
      if($num_rows>0){
        $row = mysqli_fetch_array($result);
        $area = $row[0];
       	Get_Posts($area);
      }
      else{
        echo "Failed with id";
      }
      mysqli_close($con_db);
	}
	function Get_Posts($area){
	$con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
		$sql_command = "SELECT post, id FROM forum WHERE areas_of_stress = '{$area}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      if($num_rows>0){
        while($row = mysqli_fetch_array($result)){

        $post = $row[0];
        $name = $row[1]; 

        echo  $post . '<' . $name . '>';
      	}
      }
      else if($num_rows == 0){
      	echo '';
      }
      else{
        echo "Failed in getting the posts";
      }
	mysqli_close($con_db);
	}
	function Get_Area($id){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT areas_of_stress FROM users WHERE id = '{$id}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      
      if($num_rows>0){
        $row = mysqli_fetch_array($result);
        $area = $row[0];
        return $area; 
		}
      else{
        echo "Failed with id";
      }
      mysqli_close($con_db);
	
	}
	function Add_Post($id, $post, $area){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
  		if(mysqli_connect_errno($con_db)){
    			echo "Failed to connect to MYSql: " . mysqli_connect_error();
  		}
    	$sql_command = "INSERT INTO forum (id, post, areas_of_stress) values ('{$id}', '{$post}', '{$area}')";
  		$result = mysqli_query($con_db, $sql_command);
  		if($result){
  			echo $result . ' result';
		}
		else{
  			echo 'Failed';
		}
  		mysqli_close($con_db);
	
	}
	function Update_Reg($id, $area, $level){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
  		if(mysqli_connect_errno($con_db)){
    		echo "Failed to connect  to MYSql:" . mysqli_connect_error();
  		}
  		$sql_command =  "UPDATE users SET areas_of_stress = '{$area}', stress_level = '{$level}' WHERE id = '{$id}'";
  		$result = mysqli_query($con_db, $sql_command);
  		echo "Success "  . $result . ';';
  		mysqli_close($con_db);
		
	}
	function GetFriend($id){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT friends FROM users WHERE id = '{$id}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      $row = mysqli_fetch_array($result);
      $friend = $row[0];
        mysqli_close($con_db);
        return $friend; 
		
    
	}
	function Add_Friend($id, $friend){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
  		if(mysqli_connect_errno($con_db)){
    		echo "Failed to connect  to MYSql:" . mysqli_connect_error();
  		}
  		$sql_command =  "UPDATE users SET friends = '{$friend}' WHERE id = '{$id}'";
  		$result = mysqli_query($con_db, $sql_command);
  		
  		mysqli_close($con_db);
	}
	function Check_Friend_Exists($id, $temp_friend){
		
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT * FROM users WHERE id = '{$id}' and friends like '%{$temp_friend}%'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      
      if($num_rows>0){
        echo 'Failed: This friend already exists';
      	mysqli_close($con_db);
        return '0';
		}
		else{
			mysqli_close($con_db);
			return '1';
		}
	}
	function Add_Music($url, $r_or_nr){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
  		if(mysqli_connect_errno($con_db)){
    			echo "Failed to connect to MYSql: " . mysqli_connect_error();
  		}
    	$sql_command = "INSERT INTO music (url,type) values ('{$url}', '{$r_or_nr}')";
  		$result = mysqli_query($con_db, $sql_command);
  		if($result){
  			echo $result . ' result';
		}
		else{
  			echo 'Failed';
		}
  		mysqli_close($con_db);
	
	}
	function Get_Level($id){
	$con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT stress_level FROM users WHERE id = '{$id}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      
      if($num_rows>0){
        $row = mysqli_fetch_array($result);
        $level = $row[0];
        return $level; 
		}
      else{
        echo "Failed with levels";
      }
      mysqli_close($con_db);
	
	}
	function Add_Act($id, $level, $post){
	$con_db = mysqli_connect("localhost", "root", "root", "final_project");
  		if(mysqli_connect_errno($con_db)){
    			echo "Failed to connect to MYSql: " . mysqli_connect_error();
  		}
    	$sql_command = "INSERT INTO activity (id, post, stress_level) values ('{$id}', '{$post}', '{$level}')";
  		$result = mysqli_query($con_db, $sql_command);
  		if($result){
  			echo $result . ' result';
		}
		else{
  			echo 'Failed';
		}
  		mysqli_close($con_db);
	
	}
	function Get_Act($id){
		 $con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
      $sql_command = "SELECT stress_level FROM users WHERE id = '{$id}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      
      if($num_rows>0){
        $row = mysqli_fetch_array($result);
        $level = $row[0];
       	Post_Act($level);
      }
      else{
        echo "Failed with id";
      }
      mysqli_close($con_db);
	
	}
	function Post_Act($level){
		$con_db = mysqli_connect("localhost", "root", "root", "final_project");
      if(mysqli_connect_errno($con_db)){
        echo "Failed to connect  to MYSql:" . mysqli_connect_error();
      }
		$sql_command = "SELECT post, id FROM activity WHERE stress_level = '{$level}'";
      $result = mysqli_query($con_db, $sql_command);
      $num_rows = mysqli_num_rows($result);
      if($num_rows>0){
        while($row = mysqli_fetch_array($result)){

        $post = $row[0];
        $name = $row[1]; 

        echo  $post . '<' . $name . '>';
      	}
      }
      else if($num_rows == 0){
      	echo '';
      }
      else{
        echo "Failed in getting the posts";
      }
	mysqli_close($con_db);
	
	}
	
	
    $method = $_POST['method'];
    switch ($method) {
      case 'login':
            $username = $_POST['username'];
            $password = $_POST['password'];
            My_login($username, $password);
            break;
	  case 'get_music':
          $type = $_POST['type'];
          Get_Music($type);
          break;
      case 'register':
      		$id = $_POST['id'];
            $password = $_POST['password'];
            $full_name = $_POST['full_name'];
            $areas_of_stress = $_POST['areas_of_stress'];
            $stress_level = $_POST['stress_level'];
            My_registration($id, $password, $full_name, $areas_of_stress, $stress_level);
      		break;
      case 'forum':
      		$id = $_POST['id'];
      		Get_Forum($id);
      		break;
      case 'add_post':
      		$id = $_POST['id'];
      		$area = Get_Area($id);
      		$post = $_POST['message'];
      		Add_Post($id, $post, $area);
      		break;
      case 'extra_reg':
      		$id = $_POST['id'];
      		$area = $_POST['area'];
      		$level = $_POST['level'];
      		Update_Reg($id, $area, $level);
      		break;
      case 'add_friends':
      		$id = $_POST['id'];
      		$temp_friend = $_POST['friend'];
      		
      		if($id == $temp_friend){
      			echo 'You cannot add yourself as a friend';
      			break;
      		}
      		$check = Check_Friend_Exists($id, $temp_friend);
      		if($check != '0'){
      		$friend = GetFriend($id).'_'.$temp_friend;
      		echo $friend;
      		Add_Friend($id, $friend);
      		}
      		break;
      case 'get_friends':
      		$id = $_POST['id'];
      		$friends = GetFriend($id);
      		echo $friends;
      		break;
      case 'add_music':
      		$url = $_POST['url'];
      		$r_or_nr = $_POST['r_or_nr'];
      		Add_Music($url, $r_or_nr);
      		break;
      case 'add_act':
      		$id = $_POST['id'];
      		$level = Get_Level($id);
      		$post = $_POST['message'];
      		Add_Act($id, $level, $post);
      		break;
      case 'get_act':
      		$id = $_POST['id'];
      		Get_Act($id);
      		break;
      default:
            break;
    }
?>