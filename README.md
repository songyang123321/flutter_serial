
# Flutter Serial!

An Android Plugin for Serial Communication which allow you to read
and write the data through the available ports
The supported features are:
* Listing the available serial ports on the device, including USB to serial adapters
* Configuring serial ports (baud rate, input type)
* Providing standard InputStream and OutputStream
# Description
This PlugIn enables the communication with the
**USB**
**RS232**
**RS485**
**UART**

**Supported platforms:**
* Android :
- arm65-v8a
- armeabi
- armeabi-v7a
- mips
- mips64
- x86
- x86_64

## Connection Setup
- User Interface is connected to rs232 converter which sending data to machine receiving the data(You can use any Serial data receiving software for testing purposes.I have used Realterm : Serial Capture Program that is available for windows)
- USB-A to micro USB cable  used in the image  to connect Commercial Android intelligent display terminal running Android 11 operating system with laptop for debugging purposes only.
  ![alt text](https://github.com/arunkumarchauhan/flutter_serial/blob/main/example/assets/tablet.png?raw=true)
  ![alt text](https://github.com/arunkumarchauhan/flutter_serial/blob/main/example/assets/tablet_connection.jpeg?raw=true)






## Features
- Doesn't require root in Android
- Easy to use
- Using listener for reading message
- 
Add a dependency to your pubspec.yaml
~~~
dependencies:
	flutter_serial: latest_version
~~~
include the usbserial package at the top of your dart file.
~~~
import  'package:flutter_serial/flutter_serial.dart';
~~~
## ❓ Usage
If you encounter any issues please refer to the API docs and the sample code in the  `example`  directory.

The  [`example`](https://github.com/arunkumarchauhan/flutter_serial/blob/main/example/lib/main.dart)  directory has a sample application that demonstrates the features of this plugin.
***

## 🔧 Android Setup #



**Initialisation**  
The first step is to call the startSerial() method and subscribe the
StreamSubscription

## **Start Serial**

startSerial method  will open the transaction stream
~~~
FlutterSerial flutterSerial = FlutterSerial();

  @override
  void initState() {
    super.initState();
   flutterSerial.startSerial().listen(_updateConnectionStatus);
    getSerialList();
  }
  
 void  _updateConnectionStatus(SerialResponse? result) async {
logData = result!.logChannel ?? "";
receivedData = result.readChannel ?? "";
});
}
~~~
By calling the startSerial() it will provide  you with the SerialResponse in the form of stream data

**SerialResponse**
In Serial Response you will get the following type  
1)  Log Channel (type:String)  
2)  Read Channel  (type:String)

1) Log Channel:
   In the log channel you wll get the repsone when you open any port
   ,close any port , transmit data (TX).

2) Read Channel:
   In the Read channel you wll get the Recived data (RX)

## **Available Ports**

The getAvailablePorts() method  will return you all the  
available ports on a device
~~~
serialList = await  flutterSerial.getAvailablePorts();
~~~

## **Open**

openPort method  will open the serial communication
Its has 3 required parameter
{ **DataFormat** dataFormat, **String** serialPort, **int** baudRate }
~~~
flutterSerial.openPort(
dataFormat: DataFormat.ASCII,
serialPort: serialList.first,
baudRate: flutterSerial.baudRateList.first)
~~~

## **Close**

closePort method  will close the port if you have opened any port
~~~
flutterSerial.closePort();
~~~

## **Send Command**
sendCommand method  will send your message
Its has 1 required parameter  {**String**  message}
~~~
flutterSerial.sendCommand(message: "message");
~~~


## Clear

**clearLog** method  will clear the Log channel
**clearRead** method  will clear the Read channel

~~~
flutterSerial.clearLog();
flutterSerial.clearRead();
~~~

## Destroy
**destroy** method  will eliminate the resources
~~~
@override
void  dispose() {
flutterSerial.destroy();
super.dispose();
}
~~~

* * *
*  **Baud Rate**
   To get the Standard baud rates list
   call the `FlutterSerial().baudRateList`
   it will return the integer list of standard baud rate

*  **Data Format**
   The Data format is used to convert the data type
   To pass the data format in the open()  method parameter
   For ascii format
   call the `DataFormat.ASCII`

   For hex_String format
   call the `DataFormat.HEX_STRING`



     







