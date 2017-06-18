<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="analyzer"
             class="ee.netgroup.su.diagnostic.web.Analyzer" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>

<html xml:lang="en" xmlns="http://www.w3.org/1999/xhtml" lang="en-US">

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>Forum analyzer</title>
</head>

<body>

<center>
    <form action="index.jsp" method="post">

        <textarea name="input" rows="30" cols="100">Insert program input CSV here...</textarea>

        <br/>
        <input type="submit" value="Analyze"/>
    </form>


    <%= analyzer.analyze(request) %>

    <br/><br/>
    <img src="hospital.png" alt="hospital image"/>
</center>
</body>
</html>