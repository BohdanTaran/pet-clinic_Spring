<html lang="en" xmlns:th="http://thymeleaf.org">
<head>
    <title>Veterinarians</title>
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

    <main>
        <table>
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Specialty</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <tr th:each="vet : ${allVets}">
                <td style="width: 10%;">
                    <form th:method="delete" th:action="@{/veterinarians/{idVet} (idVet=${vet.getId()})}">
                        <input style="margin-right: 20px; border: 1px solid #ff0000; padding: 5px; color: #ff0000; cursor: pointer; margin-top: 15px;"
                               value="X" type="submit">
                    </form>
                </td>
                <td th:text="${vet.getFullName()}"> Name </td>
                <td th:text="${vet.getSpecialty()}" style="width: 25%;">Specialty</td>
                <td>
                    <form style="height: 15px;" th:methon="patch" th:action="@{/veterinarians/edit/{idVet} (idVet=${vet.getId()})}">
                        <input value="Edit" style="padding: 6px;
                                                   width: 80px;
                                                   border: 3px solid #6DB33E;
                                                   background: #34302D;
                                                   color: white;"
                               type="submit">
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
        <a th:href="@{/veterinarians/new}">
            <button style="margin-top: 40px; margin-left: 25%;
                               padding: 10px;
                               border: 3px solid #6DB33E;
                               background: #34302D;
                               color: white;"
            >ADD VET</button>
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
            width: 50%;
            margin-left: 25%;
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
