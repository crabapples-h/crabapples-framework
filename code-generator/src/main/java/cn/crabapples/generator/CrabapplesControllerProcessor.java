package cn.crabapples.generator;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import com.sun.tools.javac.api.JavacTrees;

@AutoService(Processor.class)
public class CrabapplesControllerProcessor extends AbstractProcessor {

    //支持的注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(CrabapplesController.class.getCanonicalName());
        return annotataions;
    }

    private Filer filer;
    private Messager messager;
    private ClassLoader classLoader;
    private JavacTrees javacTrees;

    //支持的java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
        this.classLoader = this.getClass().getClassLoader();
        super.init(processingEnv);
        Trees trees = Trees.instance(processingEnv);
        if (trees instanceof JavacTrees) {
            javacTrees = (JavacTrees) trees;
        } else {
            throw new IllegalStateException("Unexpected Trees implementation");
        }

    }

    /*

        mvn -X -U -DskipTests=true clean compile package install -f code-generator/pom.xml &&  mvn -X -U -DskipTests=true clean compile package install -f serve/pom.xml&& javap -c src.main.java.org.example.application.system.template.entity.DemoController
        mvn  -DskipTests=true clean compile package install -f code-generator/pom.xml &&  mvn  -DskipTests=true clean compile package install -f serve/pom.xml&& javap -c src.main.java.org.example.application.system.template.entity.DemoController

     */

    @Override
//    @Getter
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "执行process()方法");
        System.err.println("start EnableTestAnno.process()");
//        for (Element rootElement : roundEnv.getRootElements()) {
//            System.err.println(rootElement);
//        }

        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(CrabapplesController.class);
        for (Element element : elementsAnnotatedWith) {
            try {
                TypeElement typeElement = ((TypeElement) element);
                String className = typeElement.getQualifiedName().toString();
//                System.err.println(className);
//                createByByteBuddy(className);
                createByByteBuddy(typeElement);
                System.err.println("---------------------------end");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    private void createByByteBuddy(TypeElement typeElement) throws IOException, ClassNotFoundException {
        String className = typeElement.getQualifiedName().toString();
//        String newFileName = className + "AAAAA";
        Trees trees = Trees.instance(processingEnv);
        System.err.println(className);
//        Class<?> aClass = classLoader.loadClass("target/classes/"+className);
//        System.err.println(aClass);
//        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, aClass.getName());

        new ByteBuddy()
//                .subclass(typeElement)
                .subclass(Class.forName(className))
//                .rebase(BaseController.class)
//                .subclass(BaseController.class)
//                .rebase(Object.class)
//                .subclass(Object.class)
//                .redefine(Object.class)
//                .subclass(BaseController.class)
//                .rebase(ControllerTemplate.class)
//                .rebase(Class.forName(newFileName))
//                .name(newFileName)
                // 添加属性
                .defineField("field2", String.class, Visibility.PRIVATE)

                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.CHILD_FIRST)
                .saveIn(new File("src/main/java/"));
//                .saveIn(new File("target/classes/"));
    }

    private void createByByteBuddy(String className) throws IOException, ClassNotFoundException {
//        String newFileName = className + "AAAAA";
        System.err.println(className);
//        Class<?> aClass = classLoader.loadClass("target/classes/"+className);
//        System.err.println(aClass);
//        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, aClass.getName());

        new ByteBuddy()
                .subclass(Class.forName(className))
//                .rebase(BaseController.class)
//                .subclass(BaseController.class)
//                .rebase(Object.class)
//                .subclass(Object.class)
//                .redefine(Object.class)
//                .subclass(BaseController.class)
//                .rebase(ControllerTemplate.class)
//                .rebase(Class.forName(newFileName))
//                .name(newFileName)
                // 添加属性
                .defineField("field2", String.class, Visibility.PRIVATE)

                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.CHILD_FIRST)
                .saveIn(new File("src/main/java/"));
//                .saveIn(new File("target/classes/"));
    }

}
