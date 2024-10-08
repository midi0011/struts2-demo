<%@ taglib uri = "/struts-tags" prefix="s" %>
<html>
    <head>
        <title> Todo list </title>
        <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    background-color: #f4f4f4;
                }
                h2 {
                    color: #333;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                    background-color: #fff;
                }
                th, td {
                    padding: 12px;
                    border: 1px solid #ddd;
                    text-align: left;
                }
                th {
                    background-color: #A9A9A9;
                    color: white;
                }
                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }
                tr:hover {
                    background-color: #ddd;
                }
                input[type="submit"] {
                    background-color: #A9A9A9;
                    color: white;
                    border: none;
                    padding: 10px 15px;
                    text-align: center;
                    text-decoration: none;
                    display: inline-block;
                    margin: 4px 2px;
                    cursor: pointer;
                    border-radius: 5px;
                    transition: background-color 0.3s ease;
                }
                input[type="submit"]:hover {
                    background-color: #000000;
                }
                form {
                    display: inline; /* Makes the forms inline */
                }
            </style>
    </head>
<body>
    <h2>Todo</h2>
    <h3>Welcome to the Todo list <s:property value="#session.user" /></h3>

    <s:if test="#session.user != null">
            <!-- Logout button -->
            <form action="<s:url action='logout'/>" method="post">
                <input type="submit" value="Logout" />
            </form>
        </s:if>

    <form action="<s:url action='addTodo'/>" method="get">
        <input type="submit" value="Add Todo" />
    </form>
    <table border="1">
        <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Action</th>
        </tr>
        <s:iterator value="todos" var="todo">
            <tr>
                <td><s:property value="#todo.id"/></td>
                <td><s:property value="#todo.title"/></td>
                <td><s:property value="#todo.description"/></td>
                <td><s:property value="#todo.status"/></td>
                <td>
                    <!-- Button to edit the todo -->
                    <form action="<s:url action='editTodo'/>" method="get">
                        <input type="hidden" name="id" value="<s:property value='#todo.id'/>" />
                        <input type="submit" value="Edit" />
                    </form>

                    <!-- Button to delete the todo -->
                    <form action="<s:url action='deleteTodo'/>" method="post">
                        <input type="hidden" name="id" value="<s:property value='#todo.id'/>" />
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this todo?');" />
                    </form>
                </td>
            </tr>
        </s:iterator>
    </table>
</body>
</html>