package com.robertsmieja.example.annotations;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Set;

import static java.util.Collections.singleton;

public class SpiAnnotationProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;

    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.RELEASE_8;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return singleton(HelloWorld.class.getCanonicalName());
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(HelloWorld.class);

        if (elementsAnnotatedWith.isEmpty()) {
            return false;
        }

        for (Element element : elementsAnnotatedWith){
            switch (element.getKind()){
                case CLASS:
                    TypeElement classElement = (TypeElement) element;
                    messager.printMessage(Diagnostic.Kind.WARNING, "@HelloWorld on " + classElement.getSimpleName().toString());
                    generateCode(classElement);
                    break;
                case METHOD:
                    ExecutableElement methodElement = (ExecutableElement) element;
                    messager.printMessage(Diagnostic.Kind.WARNING, "@HelloWorld on " + methodElement.getSimpleName().toString());
                    generateCode(methodElement);
                    break;
                default:
                    messager.printMessage(Diagnostic.Kind.ERROR, "Unsupported element", element);
                    break;
            }
        }

        return true;
    }

    public void generateCode(Element element) {
        try {
            FileObject resource = filer.createResource(StandardLocation.SOURCE_OUTPUT, "com.robertsmieja.example.annotations", element.getSimpleName().toString() + ".txt", element);
            Writer writer = resource.openWriter();
            writer.append("@HelloWorld: " + element.getSimpleName().toString() + System.lineSeparator());
            writer.close();
        } catch (IOException e){
            messager.printMessage(Diagnostic.Kind.ERROR, "Unexpected exception: " + e, element);
        }
    }

}
