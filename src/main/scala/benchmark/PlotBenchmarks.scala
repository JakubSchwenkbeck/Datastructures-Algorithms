package benchmark

import scala.io.Source
import scala.util.{Try, Using}

object PlotBenchmarks {
  def main(args: Array[String]): Unit = {
    Using.resource(Source.fromFile("jmh-result.csv")) { source =>
      val lines = source.getLines().toList.drop(1) // Skip header

      val data = lines.flatMap { line =>
        val cols = line.split(",").map(_.trim.replaceAll("^\"|\"$", "")) // Remove quotes
        Try {
          val benchmark = cols(0).split('.').last.replace("benchmark", "")
          val score = cols(4).toDouble // Correct column index for Score
          (benchmark, score)
        }.toOption
      }

      val maxScore = data.map(_._2).max
      val scale = 50 / maxScore

      println("Benchmark Results (ms/op)\n" + "=" * 50)
      data.foreach { case (algo, score) =>
        val bar = "#" * (score * scale).toInt
        printf("%-15s | %-50s | %.3f%n", algo, bar, score)
      }
    }
  }
}
