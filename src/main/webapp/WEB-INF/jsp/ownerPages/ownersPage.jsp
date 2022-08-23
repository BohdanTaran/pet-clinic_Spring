<html xmlns:th="http://thymeleaf.org">
<head>
    <title>Owners</title>
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

    <main>
        <table>
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Address</th>
                <th>City</th>
                <th>Phone</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="owner : ${allOwners}">
                <td>
                    <form style="height: 10px;" th:method="delete" th:action="@{owners/{id} (id=${owner.getId()})}">
                        <input style="margin-right: 20px; border: 1px solid red; padding: 5px; color: red; cursor: pointer;"
                               value="X" type="submit">
                    </form>
                </td>
                <td>
                    <a th:href="@{/owners/{id}(id=${owner.getId()})}"  th:text="${owner.getFirst_last_name()}"
                       style="color: #6DB33E; font-weight: 500;">Name</a>
                </td>
                <td th:text="${owner.getAddress()}">Address</td>
                <td th:text="${owner.getCity()}">City</td>
                <td th:text="${owner.getPhone()}">Phone</td>
            </tr>
            </tbody>
        </table>

        <a th:href="@{/owners/new}">
            <button style="margin-top: 40px;
                           margin-left: 270px;
                           padding: 10px;
                           border: 3px solid #6DB33E;
                           background: #34302D;
                           color: white;" >
                ADD OWNER
            </button>
        </a>
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

        table
        {
            width: 65%;
            margin-left: 270px;
            font-size: 20px;
            text-align: left;
            margin-top: 60px;
            border-collapse: collapse;
        }
        th, td {padding: 10px;}
        th
        {
            font-size: 20px;
            background: #34302D;
            color: white;
        }
        td
        {
            font-size: 17px;
            border-bottom: 1px solid black;
        }
    </style>
</body>
</html>
