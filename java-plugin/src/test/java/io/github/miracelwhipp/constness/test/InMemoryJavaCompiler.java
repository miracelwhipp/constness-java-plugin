package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.plugin.ConstnessJavacPlugin;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InMemoryJavaCompiler {

    public class SimpleSourceFile extends SimpleJavaFileObject {
        private String content;

        public SimpleSourceFile(String qualifiedClassName, String testSource) {
            super(URI.create(String.format(
                    "file://%s%s", qualifiedClassName.replaceAll("\\.", "/"),
                    Kind.SOURCE.extension)), Kind.SOURCE);
            content = testSource;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }

    public class SimpleClassFile extends SimpleJavaFileObject {

        private ByteArrayOutputStream out;

        public SimpleClassFile(URI uri) {
            super(uri, Kind.CLASS);
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return out = new ByteArrayOutputStream();
        }

        public byte[] getCompiledBinaries() {
            return out.toByteArray();
        }

        // getters
    }

    public class SimpleFileManager
            extends ForwardingJavaFileManager<StandardJavaFileManager> {

        private List<SimpleClassFile> compiled = new ArrayList<>();

        /**
         * Creates a new instance of ForwardingJavaFileManager.
         *
         * @param fileManager delegate to this file manager
         */
        protected SimpleFileManager(StandardJavaFileManager fileManager) {
            super(fileManager);
        }

        // standard constructors/getters

        @Override
        public JavaFileObject getJavaFileForOutput(Location location,
                                                   String className, JavaFileObject.Kind kind, FileObject sibling) {
            SimpleClassFile result = new SimpleClassFile(
                    URI.create("string://" + className));
            compiled.add(result);
            return result;
        }

        public List<SimpleClassFile> getCompiled() {
            return compiled;
        }
    }

    public static class CompilationException extends Exception {

        private final List<Diagnostic<?>> diagnostics;

        public CompilationException(List<Diagnostic<?>> diagnostics) {
            super(diagnostics.stream().map(diagnostic -> diagnostic.getKind() +
                    "(" + diagnostic.getLineNumber() + ", " + diagnostic.getColumnNumber() + ") : " +
                    diagnostic.getMessage(Locale.getDefault())).reduce("", (x, y) -> x + "\n" + y));
            this.diagnostics = diagnostics;
        }

        public List<Diagnostic<?>> getDiagnostics() {
            return diagnostics;
        }

    }

    public byte[] compile(String qualifiedClassName, String testSource) throws CompilationException {

        StringWriter output = new StringWriter();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        SimpleFileManager fileManager = new SimpleFileManager(
                compiler.getStandardFileManager(null, null, null));
        List<SimpleSourceFile> compilationUnits
                = List.of(new SimpleSourceFile(qualifiedClassName, testSource));

        List<String> arguments = new ArrayList<>(List.of("-classpath", System.getProperty("java.class.path"),
                "-Xplugin:constness"));

        List<Diagnostic<?>> diagnostics = new ArrayList<>();

        JavaCompiler.CompilationTask task
                = compiler.getTask(
                output,
                fileManager,
                diagnostics::add,
                arguments,
                null,
                compilationUnits
        );

        if (!task.call()) {

            if (diagnostics.isEmpty()) {

                throw new IllegalStateException();
            }

            throw new CompilationException(diagnostics);
        }

        return fileManager.getCompiled().iterator().next().getCompiledBinaries();
    }


}
