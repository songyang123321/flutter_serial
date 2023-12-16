package dev.ak.flutter_serial;




import android.content.Context;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterSerialPlugin */
public class FlutterSerialPlugin implements FlutterPlugin, MethodCallHandler {

  private MethodChannel methodChannel;
  private EventChannel eventChannel;
OpenCommunication communication = new OpenCommunication();
  private CustomEventHandler receiver;
 final private  String methodChannelName="dev.ak.flutter_serial/embedded_serial_method_channel";
  final private  String eventChannelName="dev.ak.flutter_serial/embedded_serial_event_channel";
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    setupChannels(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), methodChannelName);
    methodChannel.setMethodCallHandler(this);

  }


  private void setupChannels(BinaryMessenger messenger, Context context) {
    eventChannel = new EventChannel(messenger, eventChannelName);
    receiver = new CustomEventHandler();
    eventChannel.setStreamHandler(receiver);
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    methodChannel.setMethodCallHandler(null);
    teardownChannels();
  }


  private void teardownChannels() {
    methodChannel.setMethodCallHandler(null);
    eventChannel.setStreamHandler(null);
    receiver.onCancel(null);
    eventChannel = null;
    receiver = null;
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    Map<String, String> argments = ((Map<String, String>) call.arguments());
    switch (call.method) {
      case "embeddedSerial/availablePorts":
        final List<String> list = new ArrayList<String>();
        list.addAll(communication.sendDeviceData());
        result.success(list);
        break;
      case "embeddedSerial/open":
        communication.open(argments.get("serialPort"), Boolean.parseBoolean(argments.get("dataFormat")),Integer.parseInt(argments.get("baudRate")));
        break;
      case "embeddedSerial/close":
        communication.close();
        break;
      case "embeddedSerial/send":
        communication.send(argments.get("message"));
        break;
      case "embeddedSerial/clearLog":
        communication.logChannel = "";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("LogChannel", communication.logChannel);
        dataMap.put("readChannel", communication.readChannel);


        receiver.sendEvent( dataMap);
        break;
      case "embeddedSerial/clearRead":
        communication.readChannel = "";
        Map<String, String> dataMap1 = new HashMap<String, String>();
        dataMap1.put("LogChannel", communication.logChannel);
        dataMap1.put("readChannel", communication.readChannel);
        receiver.sendEvent(dataMap1);
        break;
      case "embeddedSerial/destroy":
        communication.destroyResources();
        break;
      default:
        result.notImplemented();
    }
  }


}
