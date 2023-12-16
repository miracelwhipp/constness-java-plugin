package io.github.miracelwhipp.constness.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import io.github.miracelwhipp.constness.plugin.utility.CompilerTaskContext;
import io.github.miracelwhipp.javac.extension.compiler.plugin.*;

@AutoService(Plugin.class)
@PluginName("constness")
public class ConstnessJavacPlugin extends ReflectiveCompilerPlugin {

    @Override
    public boolean autoStart() {

        return true;
    }

    @After(TaskEvent.Kind.ANALYZE)
    public void beforeAnalyze(TaskEvent event) {

        CompilerTaskContext context = CompilerTaskContext.fromTask(getTask(), event);

        ConstnessEvaluationTreeScanner.checkSatisfaction(context);
    }
}
