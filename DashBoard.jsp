<%@ page import="main.Java.Model.* ,java.util.List" %>

<%
    List<Dashboard> dash = (List<Dashboard>) request.getAttribute("listeDash");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: white;
            color: white;
            
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        table {
            margin : 10px auto;
            width: 90%;
            border-collapse: collapse;
            box-shadow: 0 0 20px rgba(40, 8, 219, 0.84);
            background: rgba(27, 11, 201, 0.79);
            border-radius: 10px;
            overflow: hidden;
      
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background: rgba(22, 8, 207, 0.81);
        }
        tr:nth-child(even) {
            background: rgba(61, 16, 222, 0.9);
        }
        tr:hover {
            background: rgba(19, 13, 216, 0.83);
            transition: 0.3s;
        }
        a{
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>
    <div class="titre">
        <h1>Dashboard</h1>
    </div>
    <table>
        <tr>
            <th>idCredit</th>
            <th>Libelle</th>
            <th>Montant Credit</th>
            <th>Montant Depense</th>
            <th>Reste</th>
        </tr>
        <% if (dash != null && !dash.isEmpty()) { %>
            <% for (Dashboard da : dash) { %>
                <tr>
                    <td><%= da.getIdCredit() %></td>
                    <td><%= da.getLibelle() %></td>
                    <td><%= da.getMontantCredit() %></td>
                    <td><%= da.getMontantDepense() %></td>
                    <td><%= da.getReste() %></td>
                </tr>

            <% } %>
        <% } else { %>
            <tr>
                <td colspan="2" style="text-align: center;">Aucun Credit et depense inserer</td>
            </tr>
        <% } %>
        <a href="/Depense/">Retour</a>
    </table>
</body>
</html>
