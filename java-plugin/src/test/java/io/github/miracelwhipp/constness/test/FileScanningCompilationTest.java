package io.github.miracelwhipp.constness.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.tools.Diagnostic;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FileScanningCompilationTest {

    private final InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();

    @DataProvider
    public static Object[][] errors() {

//        File directory = new File("src/test/cases/one");
        File directory = new File("src/test/cases/error");

        File[] files = directory.listFiles();

        if (files == null) {

            throw new IllegalStateException("directory " + directory.getAbsolutePath() + " does not exist");
        }

        return Arrays.stream(files).map(file -> new Object[]{file}).collect(Collectors.toList()).toArray(Object[][]::new);
    }

    @DataProvider
    public static Object[][] okay() {

//        File directory = new File("src/test/cases/one");
        File directory = new File("src/test/cases/okay");

        File[] files = directory.listFiles();

        if (files == null) {

            throw new IllegalStateException("directory " + directory.getAbsolutePath() + " does not exist");
        }

        return Arrays.stream(files).map(file -> new Object[]{file}).collect(Collectors.toList()).toArray(Object[][]::new);
    }

    @Test(dataProvider = "errors")
    public void erroneous(File sourceFile) throws IOException {

        String fileName = sourceFile.getName();

        String className = fileName.replaceAll("-.*", "");

        String positions = fileName.substring(className.length() + 1);

        int line = Integer.parseInt(positions.replaceAll("-.*", ""));
        int column = Integer.parseInt(positions.replaceAll(".*-|\\.java", ""));

        try {

            compiler.compile(className, Files.readString(sourceFile.toPath()));

            Assert.fail("exception expected");

        } catch (InMemoryJavaCompiler.CompilationException exception) {

            exception.getDiagnostics().forEach(diagnostic -> System.out.println(
                    diagnostic.getKind() +
                            "(" + diagnostic.getLineNumber() + ", " + diagnostic.getColumnNumber() + ") : " +
                            diagnostic.getMessage(Locale.getDefault())
            ));

            List<Diagnostic<?>> errors = exception.getDiagnostics().stream().filter(diagnostic -> diagnostic.getKind() == Diagnostic.Kind.ERROR).toList();

            Assert.assertEquals(errors.size(), 1);

//            Assert.assertEquals(errors.get(0).getMessage(Locale.getDefault()), "");

            Assert.assertEquals(errors.get(0).getKind(), Diagnostic.Kind.ERROR);
            Assert.assertEquals(errors.get(0).getLineNumber(), line);
            Assert.assertEquals(errors.get(0).getColumnNumber(), column);
        }
    }

    @Test(dataProvider = "okay")
    public void successful(File sourceFile) throws Exception {

        String fileName = sourceFile.getName();

        String className = fileName.replaceAll("\\..*", "");


        compiler.compile(className, Files.readString(sourceFile.toPath()));
    }

}
