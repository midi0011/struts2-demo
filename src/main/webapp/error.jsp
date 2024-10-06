<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Error Occurred</h2>
    <s:actionerror/>
    <a href="<s:url action='listTodos'/>">Back to Todo List</a>
</body>
</html>
