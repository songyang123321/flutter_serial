// import 'package:flutter/services.dart';
// import 'package:flutter_test/flutter_test.dart';
// import 'package:flutter_serial/flutter_serial_method_channel.dart';
//
// void main() {
//   TestWidgetsFlutterBinding.ensureInitialized();
//
//   MethodChannelFlutterSerial platform = MethodChannelFlutterSerial();
//   const MethodChannel channel = MethodChannel('flutter_serial');
//
//   setUp(() {
//     TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(
//       channel,
//       (MethodCall methodCall) async {
//         return '42';
//       },
//     );
//   });
//
//   tearDown(() {
//     TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(channel, null);
//   });
//
//   test('getPlatformVersion', () async {
//     expect(await platform.getPlatformVersion(), '42');
//   });
// }
