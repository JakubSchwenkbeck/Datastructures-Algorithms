package algorithms.dynamic

import algorithms.dynamic.LCS

/// Prints a git-style diff to transform file A into file B using LCS.
/// Shows context lines, added (`+`), and removed (`-`) lines.
///
/// @param a First file (original)
/// @param b Second file (modified)
def printGitDiff(a: Seq[String], b: Seq[String]): Unit = {
  val (_, _, lcsStr) = LCS(a.mkString("\n").toArray, b.mkString("\n").toArray)
  val lcsSet = lcsStr.split("\n").toSet

  println("--- Original")
  println("+++ Modified")

  var i = 0
  var j = 0

  while (i < a.length || j < b.length) {
    if (i < a.length && j < b.length && a(i) == b(j)) {
      // Context line (unchanged)
      println(s"  ${a(i)}")
      i += 1
      j += 1
    } else if (i < a.length && !lcsSet.contains(a(i))) {
      // Removed line
      println(s"- ${a(i)}")
      i += 1
    } else if (j < b.length && !lcsSet.contains(b(j))) {
      // Added line
      println(s"+ ${b(j)}")
      j += 1
    } else {
      // Move to next match in LCS
      i += 1
      j += 1
    }
  }
}

@main def main(): Unit = {
  // Longer C++ programs as strings
  val cppProgram1 =
    """#include <iostream>
      |using namespace std;
      |
      |void greet() {
      |    cout << "Hello, World!" << endl;
      |}
      |
      |int main() {
      |    greet();
      |    return 0;
      |}""".stripMargin

  val cppProgram2 =
    """#include <iostream>
      |#include <vector>  // Added new header
      |using namespace std;
      |
      |void greet() {
      |    cout << "Hello, Universe!" << endl;  // Modified output
      |}
      |
      |int main() {
      |    vector<int> nums = {1, 2, 3};  // Added new feature
      |    for (int num : nums) {
      |        cout << num << " ";
      |    }
      |    cout << endl;
      |    
      |    greet();
      |    return 0;
      |}""".stripMargin

  // Convert C++ programs into line sequences
  val fileA = cppProgram1.split("\n").toSeq
  val fileB = cppProgram2.split("\n").toSeq

  // Compute and print the git diff-style output
  printGitDiff(fileA, fileB)
}
