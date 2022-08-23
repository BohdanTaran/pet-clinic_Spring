<html xmlns:th="http://thymeleaf.org">
<head>
    <title>New vet</title>
</head>
<body>
    <header>
        <div class="header_left-side">
            <span>PET CLINIC</span>
        </div>
        <div class="header_right-side">
            <a th:href="@{/home}">HOME</a>
            <a th:href="@{/owners}">OWNERS</a>
            <a th:href="@{/veterinarians}" style="background: #6db33e;">VETERINARIANS</a>
        </div>
    </header>

    <form th:method="post" th:action="@{/veterinarians/new}" th:object="${vet}">
        <div class="form-inputs">

            <input type="text" th:field="*{first_name}" placeholder="First name" />
            <div style="color: red" th:if="${#fields.hasErrors('first_name')}" th:errors="*{first_name}">first name error</div>
            <br/>

            <input type="text" th:field="*{last_name}" placeholder="Last name" />
            <div style="color: red" th:if="${#fields.hasErrors('last_name')}" th:errors="*{last_name}">last name error</div>
            <br/>

            <input type="text" th:field="*{specialty}" placeholder="Specialty" />
            <div style="color: red" th:if="${#fields.hasErrors('specialty')}" th:errors="*{specialty}">spec error</div>

        </div>
        <br/><br/>
        <input id="submitForm" type="submit" value="Add" />
    </form>

    <style>
        body { padding: 0;  margin: 0; }
        a {text-decoration: none;}
        header
        {
            background: #34302D;
            height: 70px;
            display: flex;
            justify-content: space-between;
            border-top: 3px solid #6DB33E;
        }
        .header_left-side
        {
            margin-top: 20px;
            margin-left: 30px;
            float: left;
            color: white;
            font-size: 25px;
        }
        .header_right-side
        {
            margin-top: 25px;
            margin-right: 40px;
        }
        .header_right-side a {margin-top: 25px; margin-right: 30px; color: white; padding: 10px}
        .header_right-side a:hover { background: #6DB33E; }

        form
        {
            width: 100%;
            margin-left: 32%;
        }
        .form-inputs
        {
            width: 32%;
            margin-top: 40px;
            border: 3px solid #6DB33E;
            border-radius: 20px;
            padding: 50px;
        }
        .form-inputs input
        {
            width: 500px;
            height: 40px;
            margin-top: 5px;
        }
        #submitForm {margin-bottom: 30px;}
    </style>
</body>
</html>
