package annotation.hucanhui.com.libprocessor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import annotation.hucanhui.com.libannotation.ApplicationModule;


/**
 * Created by hucanhui on 2018/6/5.
 */


@AutoService(Processor.class)
@SupportedAnnotationTypes({"annotation.hucanhui.com.libannotation.ApplicationModule"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ModuleProcessor extends AbstractProcessor {

    List<String> classNames = new ArrayList<>();

    private Filer filer;
    Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 1、
        collectInfo(roundEnvironment);
        // 2、
        writeToFile();
        return true;
    }

    private void collectInfo(RoundEnvironment roundEnvironment) {
        classNames.clear();

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ApplicationModule.class);
        for (Element element : elements) {

            // 代表被注解的元素
            TypeElement typeElement = (TypeElement) element;

            // Class的完整路径
            String classFullName = typeElement.getQualifiedName().toString();

            classNames.add(classFullName);
        }
    }

    private void writeToFile() {
        try {
                if (classNames.size() == 0) return;
                String[] nameArray = new String[classNames.size()];
                nameArray = classNames.toArray(nameArray);
                StringBuilder arrayValue = new StringBuilder();
                for (int i = 0; i < classNames.size(); i++) {
                    arrayValue.append("\""+ classNames.get(i) + "\"");
                    if (classNames.size() > 1 && i != classNames.size()-1){
                        arrayValue.append(",");
                    }
                }

                FieldSpec.Builder field = FieldSpec.builder(nameArray.getClass(), "classNames", Modifier.PRIVATE).initializer("new String[]{"+arrayValue+"}");
                MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterSpec.builder(List.class, "classNames").build());
                        constructor.addStatement("classNames.addAll(java.util.Arrays.asList(this.classNames))");

                // 构建Class
                TypeSpec typeSpec = TypeSpec.classBuilder("Application$$Module")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(constructor.build())
                        .addField(field.build())
                        .build();

                // 与目标Class放在同一个包下，解决Class属性的可访问性
                String packageFullName = "com.androidframwork.module";
                JavaFile javaFile = JavaFile.builder(packageFullName, typeSpec)
                        .build();
                // 生成class文件
                javaFile.writeTo(filer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
