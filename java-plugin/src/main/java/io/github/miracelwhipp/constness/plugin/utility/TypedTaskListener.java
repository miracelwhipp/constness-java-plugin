package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;

public class TypedTaskListener implements TaskListener {

    @Override
    public void started(TaskEvent event) {

        switch (event.getKind()) {
            case PARSE -> onParse(event);
            case ENTER -> onEnter(event);
            case ANALYZE -> onAnalyze(event);
            case GENERATE -> onGenerate(event);
            case ANNOTATION_PROCESSING -> onAnnotationProcessing(event);
            case ANNOTATION_PROCESSING_ROUND -> onAnnotationProcessingRound(event);
            case COMPILATION -> onCompilation(event);
        }
    }

    @Override
    public void finished(TaskEvent event) {

        switch (event.getKind()) {
            case PARSE -> afterParse(event);
            case ENTER -> afterEnter(event);
            case ANALYZE -> afterAnalyze(event);
            case GENERATE -> afterGenerate(event);
            case ANNOTATION_PROCESSING -> afterAnnotationProcessing(event);
            case ANNOTATION_PROCESSING_ROUND -> afterAnnotationProcessingRound(event);
            case COMPILATION -> afterCompilation(event);
        }
    }

    public void afterCompilation(TaskEvent event) {

    }

    public void afterAnnotationProcessingRound(TaskEvent event) {

    }

    public void afterAnnotationProcessing(TaskEvent event) {

    }

    public void afterGenerate(TaskEvent event) {

    }

    public void afterAnalyze(TaskEvent event) {

    }

    public void afterEnter(TaskEvent event) {

    }

    public void afterParse(TaskEvent event) {

    }

    public void onCompilation(TaskEvent event) {

    }

    public void onAnnotationProcessingRound(TaskEvent event) {

    }

    public void onAnnotationProcessing(TaskEvent event) {

    }

    public void onGenerate(TaskEvent event) {

    }

    public void onAnalyze(TaskEvent event) {

    }

    public void onEnter(TaskEvent event) {

    }

    public void onParse(TaskEvent event) {

    }

}
