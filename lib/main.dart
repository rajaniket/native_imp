import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _hour = 0;
  int _min = 0;
  static const platform = MethodChannel('com.relife/alarm');

  // Future<void> _setAlarm() async {
  //   try {
  //     await platform.invokeMethod('setAlarm');
  //     print("Testing setAlarm _setAlarm___________________________");
  //   } on PlatformException catch (e) {
  //     print("error");
  //   }
  // }
  Future<void> _setAlarm() async {
    try {
      await platform
          .invokeMethod('setAlarm', <String, int>{'hour': _hour, 'min': _min});
      print("Testing setAlarm _setAlarm___________________________ ");
    } on PlatformException catch (e) {
      print("error");
    }
  }

  @override
  void initState() {
    _setAlarm();
    print("Testing setAlarm initState___________________________");
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "hour : $_hour \nminute : $_min",
              style: TextStyle(fontSize: 28),
            ),
            const SizedBox(
              height: 20,
            ),
            SizedBox(
                width: 250,
                child: TextField(
                  keyboardType: TextInputType.number,
                  onChanged: (val) {
                    setState(() {
                      _hour = int.parse(val);
                    });
                  },
                )),
            const SizedBox(
              height: 20,
            ),
            SizedBox(
                width: 250,
                child: TextField(
                  keyboardType: TextInputType.number,
                  onChanged: (val) {
                    setState(() {
                      _min = int.parse(val);
                    });
                  },
                )),
            const SizedBox(
              height: 120,
            ),
            ElevatedButton(
                onPressed: () {
                  _setAlarm();
                },
                child: Text("Done"))
          ],
        ),
      ),
    );
  }
}
