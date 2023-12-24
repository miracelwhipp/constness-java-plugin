package io.github.miracelwhipp.constness.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import io.github.miracelwhipp.constness.plugin.api.ConstnessApi;
import io.github.miracelwhipp.javac.extension.compiler.plugin.*;

import java.io.IOException;

@AutoService(Plugin.class)
@PluginName("constness")
public class ConstnessJavacPlugin extends ReflectiveCompilerPlugin {

    @Override
    public boolean autoStart() {

        return true;
    }

    @After(TaskEvent.Kind.ANALYZE)
    public void afterAnalyze(TaskEvent event) {

        ConstnessApi context = ConstnessApi.fromTask(getTask(), event);

        ConstnessEvaluationTreeScanner.checkSatisfaction(context);
    }
}
