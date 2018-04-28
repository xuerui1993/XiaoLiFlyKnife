package com.alex.lib;

import com.alex.xiaoliknife.BindLayout;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/*
 *  @项目名：  APTDemo
 *  @包名：    com.alex.lib
 *  @文件名:   XiaoLiFlyKnifeProcessor
 *  @创建者:   xuerui
 *  @创建时间:  2018/4/27 14:01
 *  @描述：    TODO
 */

public class XiaoLiFlyKnifeProcessor extends AbstractProcessor {

	private Filer mFiler; //文件相关的辅助类
	private Elements mElementUtils; //元素相关的辅助类
	private Messager mMessager; //日志相关的辅助类
	//	private Map<String, AnnotatedClass> mAnnotatedClassMap = new HashMap<>();

	@Override
	public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		mFiler = processingEnv.getFiler();
		mElementUtils = processingEnv.getElementUtils();
		mMessager = processingEnv.getMessager();
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> types = new LinkedHashSet<>();
		types.add(BindLayout.class.getCanonicalName());
		return types;
	}

	private String getPackageName(Element type) {
		return mElementUtils.getPackageOf(type).getQualifiedName().toString();
	}

	@Override
	public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		Set<? extends Element> bindLayoutSet = roundEnvironment.getElementsAnnotatedWith(BindLayout.class);
		for (Element element : bindLayoutSet) {
			int mLayoutId = element.getAnnotation(BindLayout.class).value();
			try {
				generateMethod(element,mLayoutId).writeTo(mFiler);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true; // stop process
	}

	/**
	 * @return 指定使用的 Java 版本。通常返回 SourceVersion.latestSupported()。
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	private JavaFile generateMethod(Element element, int layoutResId) {
		String packageName = getPackageName(element);
		String className = getClassName((TypeElement) element, packageName);
		ClassName bindingClassName = ClassName.get(packageName, className);

		MethodSpec.Builder inject = MethodSpec.constructorBuilder()
			.addModifiers(Modifier.PUBLIC)
			.addParameter(TypeName.get(element.asType()), "target");
		// setContentView
		if(layoutResId != 0){
			inject.addStatement("target.setContentView($L)", layoutResId);
		}
		TypeSpec clazzType = TypeSpec.classBuilder( bindingClassName.simpleName()+"$$Injector")
			.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
			.addMethod(inject.build())
			.build();
		return JavaFile.builder(getPackageName(element), clazzType).build();
	}

	private static String getClassName(TypeElement type, String packageName) {
		int packageLen = packageName.length() + 1;
		return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
	}

	private void error(String msg, Object... args) {
		mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
	}

	private void info(String msg, Object... args) {
		mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
	}
}
