<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            display: flex;
            flex-direction: column; /* Stack elements vertically */
            align-items: center; /* Center elements horizontally */
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .form-row {
            display: flex;
            flex-direction: column; /* Stack form elements vertically */
            width: 100%;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        .error {
            color: red;
            margin: 10px 0;
        }
        .link {
            margin-top: 10px;
            display: block;
            text-decoration: none;
            color: #007bff;
        }
        .link:hover {
            text-decoration: underline;
        }
        .horizontal-layout {
            display: flex;
            flex-direction: row; /* Align items in a row */
            justify-content: space-between; /* Space out items */
            align-items: center; /* Center items vertically */
            width: 100%; /* Make it responsive */
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <s:form action="loginAction" method="post" class="form-row">

            <label for="username">Username:</label>
            <s:textfield name="username" label="Username" required="true"/>

            <label for="password">Password:</label>
            <s:password name="password" label="Password" required="true"/>

            <div class="horizontal-layout">
                <s:submit value="Login"/>
            </div>
        </s:form>

        <s:if test="hasActionErrors()">
            <div class="error">
                <s:actionerror />
            </div>
        </s:if>

        <a class="link" href="<s:url action='listTodos'/>">Go to Todo List</a>
    </div>
</body>
</html>
