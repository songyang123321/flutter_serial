import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_serial/flutter_serial.dart';
import 'package:flutter_serial/flutter_serial_platform_interface.dart';
import 'package:flutter_serial/flutter_serial_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterSerialPlatform
    with MockPlatformInterfaceMixin
    implements FlutterSerialPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterSerialPlatform initialPlatform = FlutterSerialPlatform.instance;

  test('$MethodChannelFlutterSerial is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterSerial>());
  });

  test('getPlatformVersion', () async {
    FlutterSerial flutterSerialPlugin = FlutterSerial();
    MockFlutterSerialPlatform fakePlatform = MockFlutterSerialPlatform();
    FlutterSerialPlatform.instance = fakePlatform;

    expect(await flutterSerialPlugin.getPlatformVersion(), '42');
  });
}
