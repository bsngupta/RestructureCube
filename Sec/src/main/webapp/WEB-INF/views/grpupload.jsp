<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<h3>Select Updated file for Group Security Import</h3>

<form method="POST" action="/sec/grpupload" enctype="multipart/form-data" onsubmit="return grpvalidation(this)">
    <input type="file" name="grpfile" id="grpfile" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

<script language="javascript">
    function grpvalidation(thisform) {
        var grpfup = document.getElementById("grpfile")
        var grpfilename = grpfup.value;
        //alert(grpfilename.substr(12,21));
        if (grpfilename.substr(12,21).toLowerCase() === "groups.csv") {
            //alert("you uploaded correct file");
            return true;
        } else {
            alert("you uploaded wrong file, filename should be Groups.csv");
            grpfup.focus();
            return false;
        }
    }
</script>
</body>
</html>
