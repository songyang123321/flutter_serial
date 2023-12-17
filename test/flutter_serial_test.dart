// import 'package:flutter_test/flutter_test.dart';
// import 'package:flutter_serial/flutter_serial.dart';
// import 'package:flutter_serial/flutter_serial_platform_interface.dart';
// import 'package:flutter_serial/flutter_serial_method_channel.dart';
// import 'package:plugin_platform_interface/plugin_platform_interface.dart';
//
// class MockFlutterSerialPlatform
//     with MockPlatformInterfaceMixin
//     implements FlutterSerialPlatform {
//
//
//
//   @override
//   Future<String?> clearLog() {
//     // TODO: implement clearLog
//     throw UnimplementedError();
//   }
//
//   @override
//   Future<String?> clearRead() {
//     // TODO: implement clearRead
//     throw UnimplementedError();
//   }
//
//   @override
//   Future<String?> closePort() {
//     // TODO: implement closePort
//     throw UnimplementedError();
//   }
//
//   @override
//   Future<String?> destroyResources() {
//     // TODO: implement destroyResources
//     throw UnimplementedError();
//   }
//
//   @override
//   Future<List<String>?> getAvailablePorts() {
//     // TODO: implement getAvailablePorts
//     throw UnimplementedError();
//   }
//
//   @override
//   Future<String?> openPort({required DataFormat dataFormat, required String serialPort, required int baudRate}) {
//     // TODO: implement openPort
//     throw UnimplementedError();
//   }
//
//   @override
//   Future<String?> sendCommand({required String message}) {
//     // TODO: implement sendCommand
//     throw UnimplementedError();
//   }
//
//   @override
//   Stream<SerialResponse> startSerial() {
//     // TODO: implement startSerial
//     throw UnimplementedError();
//   }
// }
//
// void main() {
//   final FlutterSerialPlatform initialPlatform = FlutterSerialPlatform.instance;
//
//   test('$MethodChannelFlutterSerial is the default instance', () {
//     expect(initialPlatform, isInstanceOf<MethodChannelFlutterSerial>());
//   });
//
//   test('getBaudRateListTest', () async {
//     FlutterSerial flutterSerialPlugin = FlutterSerial();
//     MockFlutterSerialPlatform fakePlatform = MockFlutterSerialPlatform();
//     FlutterSerialPlatform.instance = fakePlatform;
//
//     expect( flutterSerialPlugin.baudRateList.isNotEmpty, true);
//   });
// }
