<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<h3>Select Updated file for Dimension Security Import</h3>

<form method="POST" action="/sec/upload" enctype="multipart/form-data" onsubmit="return validation(this)">
    <input type="file" name="file" id="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

<script language="javascript">
function validation(thisform) {
    var fup = document.getElementById("file")
    var filename = fup.value;
    //alert(filename);
    if (filename.substr(12,25).toLowerCase() === "security.csv") {
        //alert("you uploaded correct file");
        return true;
    } else {
        alert("you uploaded wrong file, filename should be security.csv");
        fup.focus();
        return false;
    }
}
</script>
</body>
</html>
