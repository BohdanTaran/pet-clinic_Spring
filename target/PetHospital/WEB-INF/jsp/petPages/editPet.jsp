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

    <main style="margin-left: 30px;" th:object="${owner}">
        <h1>Edit pet</h1>

        <form th:method="patch" th:action="@{/owners/{id}/editPet/{idPet} (id=${owner.getId()}, idPet=${pet.getId()})}" th:object="${pet}">
            <div class="inputs-form">
                <input type="text" th:field="*{name}" placeholder="Name" value="${pet.getName()}"/>
                <div style="color: red" th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name error</div>

                <input type="text" th:field="*{type}" placeholder="Type" value="${pet.getType()}"/>
                <div style="color: red" th:if="${#fields.hasErrors('type')}" th:errors="*{type}">type error</div>

                <input type="text" th:field="*{breed}" placeholder="Breed" value="${pet.getBreed()}"/>
                <div style="color: red" th:if="${#fields.hasErrors('breed')}" th:errors="*{breed}">breed error</div>

                <input type="date" th:field="*{visit_date}" placeholder="Date of visit" value="${pet.getVisit_date()}"/>

                <input style="height: 40px;" type="text" th:field="*{description}" placeholder="Description" value="${pet.getDescription()}"/>
                <div style="color: red" th:if="${#fields.hasErrors('description')}" th:errors="*{description}">desc error</div>
            </div>
            <input style="margin-top: 10px;
                              padding: 10px;
                              border: 3px solid #6DB33E;
                              background: #34302D;
                              color: white;"
                   type="submit" value="Edit"/>
        </form>
    </main>

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

        .inputs-form
        {
            width: 30%;
            display: flex;
            flex-direction: column;
        }
        .inputs-form input
        {
            margin-top: 10px;
            height: 30px;
        }

    </style>
</body>
</html>
