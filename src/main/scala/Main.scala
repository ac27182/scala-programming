import cats.syntax._
import cats.effect._
import cats.implicits._
object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    IO(println("app running")).as(ExitCode.Success)
}
