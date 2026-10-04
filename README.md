# Assignment 3 | Bridge Pattern

Name: Yerasyl Makhanbetali
Group: SE-2522
Topic: A - Drawing
Repository: https://github.com/aintrealera/Assignment_3_Bridge
Base commit: 854e5deaec2bc038290ec69ea5e1701fd3c2d520

## Role Map

| Role | Class | Source |
|---|---|---|
| Abstraction | Shape | src/Shape.java |
| A1 | Circle | src/Circle.java |
| A2 | Square | src/Square.java |
| Implementor | Renderer | src/Renderer.java |
| I1 | VectorRenderer | src/VectorRenderer.java |
| I2 | RasterRenderer | src/RasterRenderer.java |
| I3 | AsciiRenderer | src/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Bridge Structure

The bridge field is stored in Shape:

Renderer renderer;

The abstraction receives the implementation through its constructor.

The execute() method delegates work to the Renderer interface.

The implementation can be replaced at runtime using:

setImplementation(Renderer renderer)

T5 demonstrates runtime switching from VectorRenderer to RasterRenderer while keeping the same Circle object, ID, and radius.

## Build and Run

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected Results

T1: Circle with VectorRenderer
Expected: VECTOR circle radius=2

T2: Circle with RasterRenderer
Expected: RASTER circle radius=2

T3: Square with VectorRenderer
Expected: VECTOR square side=3

T4: Square with RasterRenderer
Expected: RASTER square side=3

T5: Runtime implementation switch
Expected: same object, unchanged domain data, implementation changes from VectorRenderer to RasterRenderer

T6: Circle with AsciiRenderer
Expected: ASCII circle radius=2

T7: Square with AsciiRenderer
Expected: ASCII square side=3

Expected summary:

## SUMMARY: 7/7 PASS