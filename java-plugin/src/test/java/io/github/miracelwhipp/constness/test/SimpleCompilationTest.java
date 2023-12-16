package io.github.miracelwhipp.constness.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.tools.Diagnostic;

public class SimpleCompilationTest {

    private final InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();

//    @Test
    public void testSimpleFailure() {

        try {
            compiler.compile("io.github.miracelwhipp.constness.test.Something", "package io.github.miracelwhipp.constness.test;\n" +
                    "\n" +
                    "import io.github.miracelwhipp.constness.annotation.Const;\n" +
                    "\n" +
                    "public class Something {\n" +
                    "\n" +
                    "    private static class Other {\n" +
                    "\n" +
                    "        public int a;\n" +
                    "        public long b;\n" +
                    "        public Object c;\n" +
                    "\n" +
                    "        public void doIt() {\n" +
                    "        }\n" +
                    "\n" +
                    "        @Const\n" +
                    "        public void doItConst() {\n" +
                    "        }\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    private final Object nowhere;\n" +
                    "\n" +
                    "    public Something(Object nowhere) {\n" +
                    "        @Const\n" +
                    "        Object huhu = nowhere;\n" +
                    "\n" +
                    "        huhu = nowhere;\n" +
                    "\n" +
                    "        this.nowhere = nowhere;\n" +
                    "    }\n" +
                    "\n" +
                    "    @Const\n" +
                    "    public void constMethod() {\n" +
                    "\n" +
                    "        something();\n" +
                    "    }\n" +
                    "\n" +
                    "    public void something() {\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void something(@Const Other value) {\n" +
                    "\n" +
                    "        value.doIt();\n" +
                    "\n" +
                    "        value.a = 6;\n" +
                    "        value.b = 6;\n" +
                    "        value.c = 6;\n" +
                    "    }\n" +
                    "\n" +
                    "}\n");

            Assert.fail("exception expected");

        } catch (InMemoryJavaCompiler.CompilationException e) {

            Assert.assertEquals(e.getDiagnostics().size(), 3);
            Assert.assertEquals(e.getDiagnostics().get(0).getColumnNumber(), 17);
            Assert.assertEquals(e.getDiagnostics().get(0).getLineNumber(), 47);
            Assert.assertEquals(e.getDiagnostics().get(0).getKind(), Diagnostic.Kind.ERROR);
            Assert.assertEquals(e.getDiagnostics().get(1).getColumnNumber(), 17);
            Assert.assertEquals(e.getDiagnostics().get(1).getLineNumber(), 48);
            Assert.assertEquals(e.getDiagnostics().get(1).getKind(), Diagnostic.Kind.ERROR);
            Assert.assertEquals(e.getDiagnostics().get(2).getColumnNumber(), 17);
            Assert.assertEquals(e.getDiagnostics().get(2).getLineNumber(), 49);
            Assert.assertEquals(e.getDiagnostics().get(2).getKind(), Diagnostic.Kind.ERROR);
        }

    }

}
