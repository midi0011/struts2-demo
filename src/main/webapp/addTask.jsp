<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Add Todo</title>
    <style>
            /* Basic styling for the form */
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            form {
                max-width: 400px;
                margin: auto;
            }
            label {
                display: block;
                margin: 10px 0 5px;
            }
            input, textarea, select {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .error {
                color: red;
                font-size: 0.9em;
            }
            .message {
                color: green;
                font-size: 1em;
            }
        </style>
</head>
<body>
    <h2>Add Todo</h2>

    <!-- Display any action errors or messages -->
    <s:actionerror cssClass="error" />
    <s:actionmessage cssClass="message" />

    <s:form action="addTodo" method="post">

        <label for="title">Title:</label>
        <s:textfield name="title" label="Title" required="true" />
        <s:fielderror name="title" cssClass="error" />

        <label for="description">Description:</label>
        <s:textarea name="description" label="Description"/>
        <s:fielderror name="description" cssClass="error" />

        <label for="status">Status:</label>
        <s:select name="status" label="Status" list="statusOptions" required="true" />
        <s:fielderror name="status" cssClass="error" />

        <s:submit value="Add Todo"/>
    </s:form>
    <a href="<s:url action='listTodos'/>">Back to Todo List</a>
</body>
</html>
