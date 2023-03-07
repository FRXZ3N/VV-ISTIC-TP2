package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PrivateElementPrint extends VoidVisitorWithDefaults<Void> {

  private final String path = "C:\\Users\\dms20\\workspace2022-2023\\VV\\VV-ISTIC-TP2\\code\\javaparser-starter\\src\\main\\java\\fr\\istic\\vv\\output.md";
  private StringBuilder output = new StringBuilder();

  @Override
  public void visit(CompilationUnit unit, Void arg) {
    for(TypeDeclaration<?> type : unit.getTypes()) {
      type.accept(this, null);
    }
  }

  public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {
    List<MethodDeclaration> methods = declaration.getMethods();
    for (FieldDeclaration field : declaration.getFields()) {
      if (field.isPrivate()) {
        for (VariableDeclarator variable : field.getVariables()) {
          if (!hasGetter(methods, variable.getNameAsString())){
            System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
            output.append("\n"+ "* " + declaration.getFullyQualifiedName().orElse("[Anonymous]"));
            variable.accept(this, arg);
          }

        }
      }
    }

  }

  private boolean hasGetter(List<MethodDeclaration> methods, String name) {
    for (MethodDeclaration method : methods) {
      if (method.getNameAsString().toLowerCase().equals("get" + name.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
    visitTypeDeclaration(declaration, arg);
  }

  @Override
  public void visit(EnumDeclaration declaration, Void arg) {
    visitTypeDeclaration(declaration, arg);
  }

  @Override
  public void visit(VariableDeclarator variableDeclarator, Void arg) {
    System.out.println("  " + variableDeclarator.getNameAsString());
    output.append("\n" +"  * " + variableDeclarator.getNameAsString());
  }

  public void writeToFile() throws IOException {
    Files.writeString(Path.of(path), output);
  }
}
