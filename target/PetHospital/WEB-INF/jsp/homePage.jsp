<html>
<head>
    <title>Home</title>
</head>
<body>
    <header>
        <div class="header_left-side" >
            <span>PET CLINIC</span>
        </div>
        <div class="header_right-side">
            <a th:href="@{/home}" style="background: #6db33e;">HOME</a>
            <a th:href="@{/owners}">OWNERS</a>
            <a th:href="@{/veterinarians}">VETERINARIANS</a>
        </div>
    </header>

    <h1 style="text-align:center; font-size:250px; margin-top:150px; color:#6db33e;">
        Welcome!
    </h1>

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
    </style>
</body>
</html>
