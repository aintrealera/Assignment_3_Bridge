public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        }
    }

    private static void runDemo() {
        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        Renderer ascii = new AsciiRenderer();

        int passed = 0;

        Circle circle1 = new Circle("C1", 2, vector);
        String t1 = circle1.execute();
        passed += check(
                "T1",
                t1.equals("VECTOR circle radius=2"),
                "Circle + VectorRenderer",
                t1,
                "VECTOR circle radius=2"
        );

        Circle circle2 = new Circle("C1", 2, raster);
        String t2 = circle2.execute();
        passed += check(
                "T2",
                t2.equals("RASTER circle radius=2"),
                "Circle + RasterRenderer",
                t2,
                "RASTER circle radius=2"
        );

        Square square1 = new Square("S1", 3, vector);
        String t3 = square1.execute();
        passed += check(
                "T3",
                t3.equals("VECTOR square side=3"),
                "Square + VectorRenderer",
                t3,
                "VECTOR square side=3"
        );

        Square square2 = new Square("S1", 3, raster);
        String t4 = square2.execute();
        passed += check(
                "T4",
                t4.equals("RASTER square side=3"),
                "Square + RasterRenderer",
                t4,
                "RASTER square side=3"
        );

        Circle circle5 = new Circle("C5", 2, vector);
        Circle original = circle5;

        String before = circle5.execute();

        circle5.setImplementation(raster);

        String after = circle5.execute();

        boolean sameObject = original == circle5;
        boolean stateUnchanged = circle5.getId().equals("C5")
                && circle5.getRadius() == 2;

        boolean t5Passed =
                sameObject
                        && stateUnchanged
                        && before.equals("VECTOR circle radius=2")
                        && after.equals("RASTER circle radius=2");

        passed += check(
                "T5",
                t5Passed,
                "runtime switch",
                "sameObject=" + sameObject
                        + " | stateUnchanged=" + stateUnchanged
                        + " | before=" + before
                        + " | after=" + after,
                "sameObject=true | stateUnchanged=true | before=VECTOR circle radius=2 | after=RASTER circle radius=2"
        );

        Circle circle6 = new Circle("C6", 2, ascii);
        String t6 = circle6.execute();

        passed += check(
                "T6",
                t6.equals("ASCII circle radius=2"),
                "Circle + AsciiRenderer",
                t6,
                "ASCII circle radius=2"
        );

        Square square7 = new Square("S7", 3, ascii);
        String t7 = square7.execute();

        passed += check(
                "T7",
                t7.equals("ASCII square side=3"),
                "Square + AsciiRenderer",
                t7,
                "ASCII square side=3"
        );

        System.out.println("SUMMARY: " + passed + "/7 PASS");
    }

    private static int check(
            String id,
            boolean passed,
            String classes,
            String actual,
            String expected
    ) {
        if (passed) {
            System.out.println(
                    id + " PASS | " + classes + " | result=" + actual
            );
            return 1;
        }

        System.out.println(
                id + " FAIL | " + classes
                        + " | result=" + actual
                        + " | expected=" + expected
        );

        return 0;
    }
}