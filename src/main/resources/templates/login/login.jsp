<html>
<head>
<title>Form Login</title>
 <meta charset="UTF-8"> 
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 </head> 
 <body>
<script>
	function validate()
	{
		var username = document.form.username.value;
		var password = document.form.password.value;
		
		if (username==null )
		{
		alert ("Username can not be blank");
		return false
		}
		else if (password==null)
		{
		alert ("pasword can no be blanl");
		return false
	}
	}
</script>
</head>
<body>
<form name="form" action="Login" method="post" onsubmit="return validate()"></form>
<table align="Center"></table>
	<tr>
		<td>Username</td>
		<td><input type="varchar" name ="username"/> </td>
	</tr>
	<tr>
		<td>Username</td>
		<td><input type="varchar" name ="username"/> </td>
		</tr>
		<tr><span style="coler:red"><% request.get.Attribute("wah") ==null)</span></tr>
	<tr>
		
	</tr>

</body>
	