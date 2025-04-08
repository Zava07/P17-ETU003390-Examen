CREATE DATABASE depense;
use depense;

CREATE TABLE credit(
    idCredit INT AUTO_INCREMENT PRIMARY KEY ,
    libelle VARCHAR(50),
    montantCredit INT
);
CREATE TABLE depense(
    idDepense INT AUTO_INCREMENT PRIMARY KEY ,
    idCredit INT,
    montantDepense INT,
    FOREIGN KEY(idCredit) REFERENCES credit(idCredit)
);

select (SUM(c2.montantCredit) - SUM(depense.montantDepense)) as reste
from depense join credit c2 on c2.idCredit = depense.idCredit where c2.idCredit = 1;