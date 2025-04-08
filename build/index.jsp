<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg,rgb(21, 53, 212), #49a09d);
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
            padding: 10px;
            margin:10px auto;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color:rgb(22, 168, 9);
            color: white;
            padding: 12px 20px;
            margin:10px auto;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color:rgb(19, 51, 209);
        }

        a {
            color:rgb(7, 151, 36);
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
</head>
<body>
<form action="/ETU003390/saveCredit" method="post">
    <label for="nom">Libelle</label>
    <input type="text" name="libelle" id="nom" placeholder="Entrer un libelle" required>
    <label for="montant">Montant credit :</label>
    <input type="text" name="montantCredit" id="montant" placeholder="Entrer un montant" required>
    <input type="submit" value="Enregistrer">
    <a href="/ETU003390/Credit">Ajouter depense</a>
    <br>
    <a href="/ETU003390/dash">dash</a>
</form>
</body>
</html>
