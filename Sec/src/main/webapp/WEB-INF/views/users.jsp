<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<HTML>
<HEAD>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#mygrpInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#mygrpTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });


        });

        function createDynamicURL()
        {
            var URL;
            var id = "${requestScope.id}";

            URL = "/sec/" + id;

            return URL;
        }

        function RedirectURL()
        {
            window.location = "/sec/" + "${requestScope.id}";
        }
    </script>
    <TITLE>EY_PB Application Security</TITLE>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a, .dropbtn {
            display: inline-block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        }

        li a:hover, .dropdown:hover .dropbtn {
            background-color: red;
        }

        li.dropdown {
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        #customers {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #customers tr:hover {
            background-color: #ddd;
        }

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
    </style>

</HEAD>

<BODY>
<ul>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Home</a>
        <div class="dropdown-content">
            <a href="#" onclick="RedirectURL();return false;" >Dimensions</a>
            <a href="usersGroups">UsersGroups</a>
        </div>
    </li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Security</a>
        <div class="dropdown-content">
            <a href="ExpSec">Export</a>
            <a href="DwnldSec">Download</a>
            <a href="upload">Import</a>
        </div>
    </li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Users&Groups</a>
        <div class="dropdown-content">
            <a href="Expgrpsec">Export</a>
            <a href="DwnldgrpSec">Download</a>
            <a href="grpupload">Import</a>
        </div>
    </li>

</ul>
<h3>EY_PB Application Security</h3>
<input id="mygrpInput" type="text" placeholder="Live Search...">
<br><br>
<div id="dvData">
    <table id="customers">
        <thead>
        <tr>
            <th>Group</th>
            <th>User</th>
        </tr>
        </thead>
        <tbody id="mygrpTable">
        <c:forEach items="${requestScope.userSecList}" var="usr" varStatus="loop">
            <c:if test="${not loop.first}">
                <tr>
                <td style="font-size: 12px">${usr.id}</td>
                <td style="font-size: 12px">${usr.user_id}</td>
            </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</BODY>
</HTML>
