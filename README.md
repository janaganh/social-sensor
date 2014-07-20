shiny-computing-machine
=======================

XMPP based Data Abstraction Layer for Smart Phone Sensors

A data capture abstraction layer for Smart Phone sensors where we abstract out data capture logic from native API, 
and provide Structured Query Language(SQL) like language syntax to query data, we utilize the user�s
social contacts to connect to nodes and to communicate data using XMMP chat protocol .

Installation

This applciation needs to installed using Android platform tools since this application available in Android market place. The Application is installed on the phone using eclipse. The source folder contains the Android project, which can build and uploaded or the binary folder contains binary file which can installed on to the phone directly below the two methods are given 
Eclispse method
1.	Import the Android project from the CD into to eclipse workspace
2.	Connect your Android device to your computer. On the device turn on debugging
mode (Settings �> Applications �> Developement �> USB debugging). Eclipse
should recognize your phone and the application can be started on the mobile device
3.	Repeat step for other devices available. 

Binary APK file install
1.	Connect your Android device to your computer. On the device turn on debuggingmode (Settings �> Applications �> Developement �> USB debugging).
2.	Check whether device was attached to adb,by command adb devices
3.	Executethe command adb install <path_to_apk>

