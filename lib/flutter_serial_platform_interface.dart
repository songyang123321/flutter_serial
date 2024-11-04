import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_serial.dart';
import 'flutter_serial_method_channel.dart';

abstract class FlutterSerialPlatform extends PlatformInterface {
  /// Constructs a FlutterSerialPlatform.
  FlutterSerialPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterSerialPlatform _instance = MethodChannelFlutterSerial();

  /// The default instance of [FlutterSerialPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterSerial].
  static FlutterSerialPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterSerialPlatform] when
  /// they register themselves.
  static set instance(FlutterSerialPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Stream<SerialResponse> startSerial() {
    throw UnimplementedError('startSerial() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Future<bool?> openPort(
      {required DataFormat dataFormat,
      required String serialPort,
      required int baudRate}) {
    throw UnimplementedError('openSerial() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Future<List<String>?> getAvailablePorts() {
    throw UnimplementedError('getAvailablePorts() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Future<String?> closePort() {
    throw UnimplementedError('closeSerial() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Future<String?> sendCommand({required String message}) {
    throw UnimplementedError('sendCommand() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.

  Future<String?> clearLog() {
    throw UnimplementedError('clearLog() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Future<String?> clearRead() {
    throw UnimplementedError('clearRead() has not been implemented.');
  }

  /// Thrown by operations that have not been implemented yet.
  /// a [UnsupportedError] all things considered. This mistake is just planned for
  /// use during improvement.
  Future<String?> destroyResources() {
    throw UnimplementedError('destroyResources() has not been implemented.');
  }
}
