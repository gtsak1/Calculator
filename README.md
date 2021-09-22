This android app implements a simple Calculator (non-scientific). It also has the ability to convert currencies. FixerIO (https://fixer.io/) currency conversion JSON API is used for that purpose.

Arithmetic operations are made using MathParser library: http://mathparser.org/mxparser-downloads/. Calculations history is kept using SQLite database.

Currency Converter follows MVP architecture and exchange rates are requested from FixerIO using Volley android library.

![Alt text](https://camo.githubusercontent.com/765ef1686c3f350b5c0b7e05ec4f5f7dba94473f6150b03b02b7a38ef4eb3647/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6d696e53646b56657273696f6e2d32312d7265642e7376673f7374796c653d74727565?raw=true "Title")
