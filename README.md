This android app implements a simple Calculator (non-scientific). It also has the ability to convert currencies. FixerIO (https://fixer.io/) currency conversion JSON API is used for that purpose.

Arithmetic operations are made using MathParser library: http://mathparser.org/mxparser-downloads/. Calculations history is kept using SQLite database.

Currency Converter follows MVP architecture and exchange rates are requested from FixerIO using Volley android library.

Replace fixerio_access_key in strings.xml with your fixerio access key.


![Webp net-resizeimages](https://user-images.githubusercontent.com/58042128/134430395-9fde7299-53be-4902-b308-ce537f357648.jpg)
![Webp net-resizeimage](https://user-images.githubusercontent.com/58042128/134430410-96a8e0f7-ae0d-46ce-ad79-4b2d7cd26da7.jpg)
