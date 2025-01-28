/*
package benchmark

import algorithms.sort.returnBubbleSort
import algorithms.sort.heapSort

import org.openjdk.jmh.annotations.*

import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.util.Random

// sbt jmh / run -i 5 -wi 2 -f1 -t1
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 2)   // Reduce warm-up iterations (default is 5)
@Measurement(iterations = 5) // Reduce total benchmark iterations (default is 10)
@Fork(1) // Reduce forks (default is 3)
class SortingBenchmark {

  var data: Array[Int] = new Array[Int](5000)

  @Setup(Level.Iteration) // Runs before each benchmark iteration
  def prepare(): Unit = {
    data = Array.fill(5000)(Random.nextInt(1000000)) // Large random dataset
  }

  @Benchmark
  def benchmarkBubbleSort(): Array[Int] = {
    returnBubbleSort(data.clone())
  }

  @Benchmark
  def benchmarkHeapSort(): Array[Int] = {
    heapSort(data.clone())
  }
}
*/