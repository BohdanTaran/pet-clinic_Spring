<html xmlns:th="http://thymeleaf.org">
<head>
    <title>Information</title>
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
        <h1>Owner information</h1>
        <table>
            <thead>
            <tr>
                <td>Name</td>
                <td>Address</td>
                <td>City</td>
                <td>Phone</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th th:text="${owner.getFirst_last_name()}">Name</th>
                <th th:text="${owner.getAddress()}">Address</th>
                <th th:text="${owner.getCity()}">City</th>
                <th th:text="${owner.getPhone()}">Phone</th>
            </tr>
            </tbody>
        </table>
        <div style="margin-top: 20px;" class="inputs-table">
            <a th:href="@{/owners/{id}/edit(id=${owner.getId()})}">
                <input style="padding: 10px;
                              border: 3px solid #6DB33E;
                              background: #34302D;
                              color: white;"
                       type="submit" value="Edit">
            </a>
            <a th:href="@{/owners/{id}/newPet(id=${owner.getId()})}">
                <input style="padding: 10px;
                              border: 3px solid #6DB33E;
                              background: #34302D;
                              color: white;"
                       type="submit" value="Add new pet">
            </a>
        </div>

        <h1 style="margin-top:40px;">Pets and visits</h1>
        <div class="all-item_visits" style="display: flex;flex-direction: column;">
            <div class="pv-item" th:each="pet : ${visit_of_pets}">
                <div class="pv-item-left">
                    <div class="item-name">
                        <span >Name: </span>
                        <span style="color: #6DB33E;" th:text="${pet.getName()}">Name</span>
                    </div>
                    <div class="item-type">
                        <span>Type: </span>
                        <span th:text="${pet.getType()}">Type</span>
                    </div>
                    <div class="item-breed">
                        <span>Breed: </span>
                        <span th:text="${pet.getBreed()}">Breed</span>
                    </div>
                </div>
                <div class="pv-item-right">
                    <div class="item-visit">
                        <span>Visit date: </span>
                        <span th:text="${pet.getVisit_date()}">Date</span>
                    </div>
                    <div class="item-desc" style="margin-top: 10px;">
                        <span>Description: </span>
                        <span th:text="${pet.getDescription()}">Desc</span>
                    </div>

                    <div class="pv-actions" style="display: flex;">
                        <a th:href="@{/owners/{id}/editPet/{idPet} (id=${owner.getId()}, idPet=${pet.getId()})}">
                            <input style="padding: 5px;
                                          border: 3px solid #6DB33E;
                                          background: #34302D;
                                          color: white;"
                                   type="submit" value="Edit"/>
                        </a>
                        <form th:method="delete" th:action="@{/owners/{id}/deletePet/{idPet} (id=${owner.getId()}, idPet=${pet.getId()})}">
                            <input style="padding: 5px;
                                          border: 3px solid #6DB33E;
                                          background: #34302D;
                                          color: white;"
                                   type="submit" value="Delete">
                        </form>
                    </div>
                </div>
            </div>
        </div>
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
            width: 35%;
            text-align: left;
            border-collapse: collapse;
        }
        .pv-item
        {
            width: 40%;
            border-top: 1px solid black;
        }
        .pv-item-left
        {
            float: left;
            margin-top: 10px;
        }
        .item-name, .item-type, .item-breed { margin-bottom: 7px; }

        .pv-item-right
        {
            float: right;
            text-align: right;
            margin-top: 10px;
        }

        .pv-actions
        {
            margin-top: 10px;
            margin-bottom: 10px;
            float: right;
        }
    </style>
</body>
</html>
