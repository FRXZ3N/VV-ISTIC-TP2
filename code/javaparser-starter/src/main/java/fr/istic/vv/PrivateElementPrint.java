package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrivateElementPrint extends VoidVisitorWithDefaults<Void> {

  private final StringBuilder output = new StringBuilder();
  private String currentVariable = "";
  private boolean hasGetter;

  @Override
  public void visit(CompilationUnit unit, Void arg) {
    for(TypeDeclaration<?> type : unit.getTypes()) {
      type.accept(this, null);
    }
  }

  public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {

    for (FieldDeclaration field : declaration.getFields()) {
      field.accept(this, arg);
      for (MethodDeclaration method : declaration.getMethods()) {
        method.accept(this, arg);
      }
      write(declaration, currentVariable);
    }

  }

  private void write(TypeDeclaration<?> declaration,String variable){
    if (!hasGetter && !variable.equals("")) {
      System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
      output.append("* ").append(declaration.getFullyQualifiedName().orElse("[Anonymous]")).append("\n");
      System.out.println(currentVariable);
      output.append("  * ").append(currentVariable).append("\n");
      currentVariable = "";
    }

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
    hasGetter = false;
    currentVariable = variableDeclarator.getNameAsString();
  }

  @Override
  public void visit(MethodDeclaration declaration, Void arg) {
    if (declaration.isPublic() && declaration.getNameAsString().toLowerCase().equals("get" + currentVariable.toLowerCase())) {
      hasGetter = true;
    }
  }

  @Override
  public void visit(FieldDeclaration fieldDeclaration, Void arg) {
    if (fieldDeclaration.isPrivate() && !fieldDeclaration.isClassOrInterfaceDeclaration()) {
      for (VariableDeclarator variable : fieldDeclaration.getVariables()) {
        variable.accept(this, arg);
      }

    }
  }

  /**
   * Write the output to a file
   */
  public void writeToFile() throws IOException {
    Files.writeString(Paths.get("code/javaparser-starter/src/main/java/fr/istic/vv/result.md"), output);
  }
}
