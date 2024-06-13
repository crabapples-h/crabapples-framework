//package cn.crabapples.generator;
//
//import cn.crabapples.common.Dict;
//import com.google.auto.service.AutoService;
//import com.sun.source.util.Trees;
//import com.sun.tools.javac.api.JavacTrees;
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.description.modifier.Visibility;
//import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
//
//import javax.annotation.processing.*;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import javax.lang.model.element.VariableElement;
//import javax.tools.Diagnostic;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.StandardOpenOption;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//
//@AutoService(Processor.class)
//public class CrabapplesControllerProcessor1 extends AbstractProcessor {
//
//    //支持的注解类型
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> annotataions = new LinkedHashSet<>();
//        annotataions.add(CrabapplesController.class.getCanonicalName());
//        return annotataions;
//    }
//
//    private Filer filer;
//    private Messager messager;
//    private ClassLoader classLoader;
//    private JavacTrees javacTrees;
//
//    //支持的java版本
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }
//
//    public synchronized void init(ProcessingEnvironment processingEnv) {
//        this.filer = processingEnv.getFiler();
//        this.messager = processingEnv.getMessager();
//        this.classLoader = this.getClass().getClassLoader();
//        super.init(processingEnv);
////        Trees trees = Trees.instance(processingEnv);
////        Class<?> treesClass = Class.forName("com.sun.source.util.Trees");
////        Method get = treesClass.getMethod("instance", ProcessingEnvironment.class);
////        Object treesInstance = get.invoke(null, processingEnv);
////        if (trees instanceof JavacTrees) {
////            javacTrees = (JavacTrees) trees;
////        } else {
////            throw new IllegalStateException("Unexpected Trees implementation");
////        }
//
//    }
//
//    /*
//
//        mvn -X -U -DskipTests=true clean compile package install -f code-generator/pom.xml &&  mvn -X -U -DskipTests=true clean compile package install -f serve/pom.xml&& javap -c src.main.java.org.example.application.system.template.entity.DemoController
//        mvn  -DskipTests=true clean compile package install -f code-generator/pom.xml &&  mvn  -DskipTests=true clean compile package install -f serve/pom.xml&& javap -c src.main.java.org.example.application.system.template.entity.DemoController
//
//     */
//
//    @Override
////    @Getter
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "执行process()方法");
//        System.err.println("start EnableTestAnno.process()");
////        for (Element rootElement : roundEnv.getRootElements()) {
////            System.err.println(rootElement);
////        }
//
//        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(CrabapplesController.class);
//        for (Element element : elementsAnnotatedWith) {
//            try {
//                TypeElement typeElement = ((TypeElement) element);
//                List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
//                for (Element enclosedElement : enclosedElements) {
//                    if (enclosedElement instanceof VariableElement) {
//                        Dict annotation = enclosedElement.getAnnotation(Dict.class);
//                        if (Objects.nonNull(annotation)) {
//                            System.err.println(enclosedElement.getSimpleName());
//                            System.err.println(annotation);
//                            String demoField = "public String demo;";
//                            String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
//                            String className = typeElement.getSimpleName().toString();
//
//                            File classFile = new File(packageName.replace('.', '/') + "/" + className + ".java");
//
//                            try (BufferedReader reader = new BufferedReader(new FileReader(classFile))) {
//                                String line;
//                                StringBuilder content = new StringBuilder();
//                                boolean inClassBody = false;
//                                while ((line = reader.readLine()) != null) {
//                                    if (line.trim().startsWith("public class " + className)) {
//                                        inClassBody = true;
//                                    }
//                                    if (inClassBody && !line.trim().isEmpty()) {
//                                        content.append(line).append("\n");
//                                        if (line.trim().startsWith("}")) {
//                                            content.append("\n").append(demoField).append("\n}\n");
//                                            break;
//                                        }
//                                    }
//                                }
//
//                                // 如果没有找到类结束符，将新属性追加到文件末尾
//                                if (!inClassBody) {
//                                    content.append("\n").append(demoField).append("\n}\n");
//                                }
//
//                                // 写回文件
//                                Files.write(classFile.toPath(), content.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
////
////                                JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(packageName + "." + className);
////                            try (PrintWriter writer = new PrintWriter(fileObject.openWriter())) {
////                                writer.println("package " + packageName + ";");
////                                writer.println("public class " + className + " extends " + typeElement.getQualifiedName() + " {");
////                                writer.println(demoField);
////                                writer.println("}");
////                            }
//                        }
//                    }
//                }
//                //                String className = typeElement.getQualifiedName().toString();
//////                System.err.println(className);
//////                createByByteBuddy(className);
////                createByByteBuddy(typeElement);
//                System.err.println("---------------------------end");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//
//    private void createByByteBuddy(TypeElement typeElement) throws IOException, ClassNotFoundException {
//        String className = typeElement.getQualifiedName().toString();
////        String newFileName = className + "AAAAA";
//        Trees trees = Trees.instance(processingEnv);
//        System.err.println(className);
////        Class<?> aClass = classLoader.loadClass("target/classes/"+className);
////        System.err.println(aClass);
////        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, aClass.getName());
//
//        new ByteBuddy()
////                .subclass(typeElement)
//                .subclass(Class.forName(className))
////                .rebase(BaseController.class)
////                .subclass(BaseController.class)
////                .rebase(Object.class)
////                .subclass(Object.class)
////                .redefine(Object.class)
////                .subclass(BaseController.class)
////                .rebase(ControllerTemplate.class)
////                .rebase(Class.forName(newFileName))
////                .name(newFileName)
//                // 添加属性
//                .defineField("field2", String.class, Visibility.PRIVATE)
//
//                .make()
//                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.CHILD_FIRST)
//                .saveIn(new File("src/main/java/"));
////                .saveIn(new File("target/classes/"));
//    }
//
//    private void createByByteBuddy(String className) throws IOException, ClassNotFoundException {
////        String newFileName = className + "AAAAA";
//        System.err.println(className);
////        Class<?> aClass = classLoader.loadClass("target/classes/"+className);
////        System.err.println(aClass);
////        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, aClass.getName());
//
//        new ByteBuddy()
//                .subclass(Class.forName(className))
////                .rebase(BaseController.class)
////                .subclass(BaseController.class)
////                .rebase(Object.class)
////                .subclass(Object.class)
////                .redefine(Object.class)
////                .subclass(BaseController.class)
////                .rebase(ControllerTemplate.class)
////                .rebase(Class.forName(newFileName))
////                .name(newFileName)
//                // 添加属性
//                .defineField("field2", String.class, Visibility.PRIVATE)
//
//                .make()
//                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.CHILD_FIRST)
//                .saveIn(new File("src/main/java/"));
////                .saveIn(new File("target/classes/"));
//    }
//
//}
