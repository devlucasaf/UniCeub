import 'package:flutter/material.dart';

void main() {
  runApp(AlignmentDemo());
}

class AlignmentDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(title: Text("Row vs Column - Alinhamentos")),
        body: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            children: [
              Text(
                "Row (horizontal)",
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              Container(
                height: 120,
                color: Colors.black,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: buildItems(),
                ),
              ),

              SizedBox(height: 30),

              Text(
                "Column (vertical)",
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              Container(
                height: 200,
                color: Colors.black,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: buildItems(),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  List<Widget> buildItems() {
    return [
      Container(width: 30, height: 30, color: Colors.blue),
      Icon(Icons.star, color: Colors.pink, size: 30),
      Icon(Icons.star, color: Colors.purple, size: 30),
      Icon(Icons.star, color: Colors.green, size: 30),
      Container(width: 30, height: 30, color: Colors.orange),
      Icon(Icons.star, color: Colors.cyan, size: 30),
    ];
  }
}