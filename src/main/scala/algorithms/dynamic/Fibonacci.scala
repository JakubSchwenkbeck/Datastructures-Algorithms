package algorithms.dynamic

// top down :

def recFibonacci(n: Int): (Int, Array[Int]) = {
  val A: Array[Int] = Array.fill(n + 1)(0)
  def recHelper(c: Int): Int = {
    if c <= 2 then return 1;
    if A(c) != 0 then return A(c);

    val fib = recHelper(c - 1) + recHelper(c - 2)
    A(c) = fib
    fib
  }

  (recHelper(n), A)
}

// bottom up

def DPFibonacci(n: Int): (Int, Array[Int]) = {
  val A: Array[Int] = Array.fill(n + 1)(0)
  A(0) = 1
  A(1) = 1
  for (i <- 2 until n) {
    A(i) = A(i - 1) + A(i - 2)
    val s = A(i)

  }

  (A(n - 1), A)
}

@main
def MainFib(): Unit = {
  val (recFib1, _) = recFibonacci(5)
  val (recFib2, _) = recFibonacci(10)
  val (recFib3, _) = recFibonacci(15)

  val (dpFib1, _) = DPFibonacci(5)
  val (dpFib2, _) = DPFibonacci(10)
  val (dpFib3, _) = DPFibonacci(15)

  println("Fibonacci Results:")
  println(s"Recursive Fibonacci(5)  = $recFib1")
  println(s"Recursive Fibonacci(10) = $recFib2")
  println(s"Recursive Fibonacci(15) = $recFib3")
  println("----------------------------------")
  println(s"DP Fibonacci(5)  = $dpFib1")
  println(s"DP Fibonacci(10) = $dpFib2")
  println(s"DP Fibonacci(15) = $dpFib3")
}
