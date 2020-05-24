package com.wjc.annotation_compiler;

import com.google.auto.service.AutoService;
import com.wjc.annotations.BindView;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;


/**
 * ClassName: com.wjc.annotation_compiler
 * Description: 注解处理器的功能，用来生成代码，使用前需要注册
 * JcChen on 2020.05.23.23:48
 */
@AutoService(Processor.class)
public class AnnotationsCompiler extends AbstractProcessor {


  //1.支持的java版本
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  //2.当前APT能用来处理哪些注解
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> types = new HashSet<>();
    types.add(BindView.class.getCanonicalName());
//        types.add(Override.class.getCanonicalName());
    return types;
  }

  //3.需要一个用来生成文件的对象
  Filer filer;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
  }

  /**
   * 这个方法中，就完成APT的功能（根据我们的需要，随便写）
   */
  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindView.class);
    //类    TypeElement
    //方法  ExecutableElement
    //属性  VariableElement
    Map<String, List<VariableElement>> map = new HashMap<>();// 这个类，里面有多少个注解
    for (Element element : elementsAnnotatedWith) {
      VariableElement variableElement = (VariableElement) element;
      String className = variableElement.getEnclosingElement().getSimpleName().toString();
      List<VariableElement> variableElementList = map.get(className);
      if (variableElementList == null) { //一个类的注解集合
        variableElementList = new ArrayList<>();
        map.put(className, variableElementList);
      }
      variableElementList.add(variableElement);

//        package com.example.butterknife_framework_demo;
//        import com.example.butterknife_framework_demo.IBinder;
//        public class MainActivity_ViewBinding implements IBinder<com.example.butterknife_framework_demo.MainActivity> {
//            @Override
//            public void bind(com.example.butterknife_framework_demo.MainActivity target) {
//                target.textView = (android.widget.TextView) target.findViewById(2131165359);
//
//            }
//        }
      if (map.size() > 0) {
        Writer writer = null;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {  //类里的属性
          //开始生成对应的文件
          String className1 = iterator.next();
          List<VariableElement> variableElements = map.get(className1);// 属性集合
          //得到包名
          TypeElement enclosingElement = (TypeElement) variableElements.get(0).getEnclosingElement();
          String packageName = processingEnv.getElementUtils().getPackageOf(enclosingElement).toString();
          //写入文件
          try {
            JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + className1 + "_ViewBinding");
            writer = sourceFile.openWriter();
            //package com.example.butterknife_framework_demo;
            writer.write("package " + packageName + ";\n");
            //import com.example.butterknife_framework_demo.IBinder;
            writer.write("import " + packageName + ".IBinder;\n");
            //public class MainActivity_ViewBinding implements IBinder<com.example.butterknife_framework_demo.MainActivity> {
            writer.write("public class " + className1 + "_ViewBinding implements IBinder<" + packageName + "." + className1 + ">{\n");
            //@Override
            writer.write("@Override\n");
            //public void bind(com.example.butterknife_framework_demo.MainActivity target) {
            writer.write("public void bind(" + packageName + "." + className1 + " target){\n");
            //target.textView = (android.widget.TextView) target.findViewById(2131165359);
            for (VariableElement variableElement1 : variableElements) {
              //得到名字
              String variableName = variableElement1.getSimpleName().toString();
              //得到ID
              int id = variableElement1.getAnnotation(BindView.class).value();
              //得到类型
              TypeMirror typeMirror = variableElement1.asType();
              //target.textView = (android.widget.TextView) target.findViewById(2131165359);
              writer.write("target." + variableName + "=(" + typeMirror + ")target.findViewById(" + id + ");\n");
            }
            writer.write("\n}\n}");

          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            if (writer != null) {
              try {
                writer.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          }
        }
      }


    }
    return false;
  }


}
