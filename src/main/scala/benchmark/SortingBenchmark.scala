package benchmark

import algorithms.sort.*
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit
import scala.util.Random

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@Fork(1)
class SortingBenchmark {

  var data: Array[Int] = new Array[Int](500) // Smaller dataset for quick benchmarking

  @Setup(Level.Iteration)
  def prepare(): Unit = {
    data = Array.fill(500)(Random.nextInt(500)) // Smaller random dataset
  }

  @Benchmark
  def benchmarkBubbleSort(): Array[Int] = {
    returnBubbleSort(data.clone())
  }

  @Benchmark
  def benchmarkHeapSort(): Array[Int] = {
    heapSort(data.clone())
  }

  @Benchmark
  def benchmarkCountingSort(): Array[Int] = {
    countingSort(data.clone(), 500)
  }

  @Benchmark
  def benchmarkInsertionSort(): Array[Int] = {
    returnInsertionSort(data.clone())
  }

}
