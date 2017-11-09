package model;

import shapes.Shapes;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DynamicLoading {

	private String classPath; // the loaded Class path

	public void SetClassPath(String s) { // path setter
		classPath = s;
	}

	@SuppressWarnings("null")
	public Class<?> loadClass() throws ClassNotFoundException, IOException {
		// a function return the loaded Class

		Class<?> loadedClass = null;
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(classPath);
		} catch (IOException e1) {
			jarFile.close();
			return null;
		}

		Enumeration<JarEntry> e = jarFile.entries();
		URL[] urls = { new URL("jar:file:" + classPath + "!/") };
		// the loaded class path as url array

		URLClassLoader cl = URLClassLoader.newInstance(urls);
		while (e.hasMoreElements()) {
			JarEntry je = e.nextElement();
			if (je.isDirectory() || !je.getName().endsWith(".class")) {
				continue;
			}
			String className = je.getName().substring(0, je.getName().length() - 6);

			className = className.replace('/', '.');
			Class<?> temp = cl.loadClass(className);
			if (Shapes.class.isAssignableFrom(temp)) {
				temp.getResource("/");
				loadedClass = temp;
			}
		}
		jarFile.close();
		return loadedClass;
	}
}
