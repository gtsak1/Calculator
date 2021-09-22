This android app implements a simple Calculator (non-scientific). It also has the ability to convert currencies. FixerIO (https://fixer.io/) currency conversion JSON API is used for that purpose.

Arithmetic operations are made using MathParser library: http://mathparser.org/mxparser-downloads/. Calculations history is kept using SQLite database.

Currency Converter follows MVP architecture and exchange rates are requested from FixerIO using Volley android library.


Screenshots:


![Screenshot_20210923-010835_Calculator](https://user-images.githubusercontent.com/58042128/134429020-92775c48-7ab4-4fbe-8820-1baaec8f4a1a.jpg)


