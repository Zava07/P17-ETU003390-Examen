<%@ page import="main.Java.Model.* ,java.util.List" %>
<%
    List<Credit> credit = (List<Credit>) request.getAttribute("liste");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion depense</title>
    <style>
        <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg,rgb(16, 18, 184), #49a09d);
            color: white;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }

        form {
            background-color: rgba(0, 0, 0, 0.6);
            padding: 30px;
            border-radius: 8px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        label {
            font-size: 18px;
            margin-bottom: 10px;
            display: block;
        }

        input[type="text"] {
            width: 100%;
            margin:10px auto;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #49a09d;
            margin:10px auto;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color:rgb(33, 183, 13);
        }
          select {
            background-color: #fff;
            color: #333;
        }

        a {
            color:rgb(11, 201, 68);
            text-decoration: none;
            font-size: 16px;
            margin-top: 15px;
            display: inline-block;
            transition: color 0.3s ease;
        }

        a:hover {
            color: white;
        }
    </style>

    </style>
</head>
<body>
    <h1>Insertion depense</h1>
    <form action="/Depense/depense" method="post">
       <label for="credit">Credit</label>
        <select name="credit" id="credit" required>
            <% if(credit != null && !credit.isEmpty()){ %>
                <% for(Credit de : credit){ %>
                    <option value="<%=de.getId()%>"><%= de.getLibelle()%></option>
                <% } %>
            <% } %>
        </select>
        <label for="montant">Montant depense :</label>
        <input type="text" name="montantDepense" id="montant" placeholder="Entrer un montant" required>
        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
