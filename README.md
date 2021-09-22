This android app implements a simple Calculator (non-scientific). It also has the ability to convert currencies. FixerIO (https://fixer.io/) currency conversion JSON API is used for that purpose.

Arithmetic operations are made using MathParser library: http://mathparser.org/mxparser-downloads/. Calculations history is kept using SQLite database.

Currency Converter follows MVP architecture and exchange rates are requested from FixerIO using Volley android library.


![Webp net-resizeimage](https://user-images.githubusercontent.com/58042128/134429881-840e827b-f012-47ff-965e-3232a8244778.jpg)
