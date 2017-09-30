package com.example;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Created by san on 2017/9/29.
 */
@SupportedAnnotationTypes("com.example.BindView")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ButterKnifeProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        StringBuilder builder = new StringBuilder()
//                .append("package com.chiclaim.processor.generated;\n\n")
//                .append("public class GeneratedClass {\n\n") // open class
//                .append("\tpublic String getMessage() {\n") // open method
//                .append("\t\treturn \"");

        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
//        for (Element element : roundEnv.getElementsAnnotatedWith(BindView.class)) {
//            String objectType = element.getSimpleName().toString();
//            // this is appending to the return statement
//            builder.append(objectType).append(" says hello!\\n");
//        }



        System.out.println("=======================process()");
//        parseRoundEnvironment(roundEnv);
        return true;
    }
}
