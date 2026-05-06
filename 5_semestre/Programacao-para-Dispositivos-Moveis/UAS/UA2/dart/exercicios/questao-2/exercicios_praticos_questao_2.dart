// Alternativa A

Row(
  children: [
    Row(
      children: [
          Text("A"),
          Text("B")
      ],
    ),
    Row(
      children: [
          Text("C"),
          Text("D")
      ],
    ),
  ],
),

// Alternativa B

Column(
  children: [
    Column(
      children: [
          Text("A"),
          Text("B")
      ],
    ),
    Column(
      children: [
          Text("C"),
          Text("D")
      ],
    ),
  ],
),

// Alternativa C

Column(
  children: [
    Row(
      children: [
          Text("A"),
          Text("B")
          Text("C"),
          Text("D")
      ],
    ),
  ],
),  

// Alternativa D

Row(
  children: [
    Column(
      children: [
          Text("A"),
          Text("B")
      ],
    ),
    Column(
      children: [
          Text("C"),
          Text("D")
      ],
    ),
  ],
),

// Alternativa E

Column(
  children: [
    Row(
      children: [
          Text("A"),
          Text("B")
      ],
    ),
    Row(
      children: [
          Text("C"),
          Text("D")
      ],
    ),
  ],
),
