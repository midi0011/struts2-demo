<%@ taglib uri = "/struts-tags" prefix="s" %>
<html>
    <head>
        <title> Todo list </title>
    </head>
<body>
    <h2>Todo</h2>
    <table border="1">
        <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
        </tr>
        <s:iterator value="todos" var="todo">
                <tr>
                    <td><s:property value="#todo.id"/></td>
                    <td><s:property value="#todo.title"/></td>
                    <td><s:property value="#todo.description"/></td>
                    <td><s:property value="#todo.status"/></td>
                </tr>
            </s:iterator>
    </table>
</body>
</html>