<html xmlns:th="http://thymeleaf.org">
<head>
    <title>Edit</title>
</head>
<body>
    <header>
        <div class="header_left-side">
            <span>PET CLINIC</span>
        </div>
        <div class="header_right-side">
            <a th:href="@{/home}">HOME</a>
            <a th:href="@{/owners}" style="background: #6db33e;">OWNERS</a>
            <a th:href="@{/veterinarians}">VETERINARIANS</a>
        </div>
    </header>

    <form id="ownerForm" th:method="patch" th:action="@{/owners/{id}/edit(id=${owner.getId()})}" th:object="${owner}">
        <div class="form-inputs">
            <input type="text" th:field="*{first_last_name}" placeholder="First name" value="${owner.getFirst_last_name()}"/>
            <div style="color: red" th:if="${#fields.hasErrors('first_last_name')}" th:errors="*{first_last_name}">first name error</div>
            <br/>
            <input type="text" th:field="*{address}" placeholder="Address" value="${owner.getAddress()}"/>
            <div style="color: red" th:if="${#fields.hasErrors('address')}" th:errors="*{address}">address error</div>
            <br/>
            <input type="text" th:field="*{city}" placeholder="City" value="${owner.getCity()}"/>
            <div style="color: red" th:if="${#fields.hasErrors('city')}" th:errors="*{city}">city error</div>
            <br/>
            <input type="text" th:field="*{phone}" placeholder="Phone" value="${owner.getPhone()}"/>
            <div style="color: red" th:if="${#fields.hasErrors('phone')}" th:errors="*{phone}">phone error</div>
        </div>
        <br/><br/>
        <input style="padding: 10px;
                          border: 3px solid #6DB33E;
                          background: #34302D;
                          color: white;"
               id="submitForm" type="submit" value="Edit" />
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
        #ownerForm
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
