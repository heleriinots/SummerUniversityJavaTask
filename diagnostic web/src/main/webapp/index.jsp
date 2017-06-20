<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="analyzer"
             class="ee.netgroup.su.diagnostic.web.Analyzer" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>

<html xml:lang="en" xmlns="http://www.w3.org/1999/xhtml" lang="en-US">

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>Forum analyzer</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<ul class="menu">
    <li><a class="active">Forum analyzer</a></li>
    <li><a href="diagnosis.jsp">Diagnose by symptoms</a></li>
    <li><a href="interactive-diagnosis.jsp">Interactive diagnosis</a></li>
</ul>

<center>
    <form action="index.jsp" method="post">

        <textarea class="input" name="input" rows="30" cols="100">Insert program input CSV here...</textarea>

        <br/>
        <input class="button" type="submit" value="Analyze"/>
    </form>

    <div class="answers">
        <%= analyzer.analyze(request) %>
    </div>


    <br/><br/>
    <img src="hospital.png" alt="hospital image"/>
</center>
</body>
</html>