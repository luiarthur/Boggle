name := "BoggleScalaJS"

version := "0.1.0"

scalaVersion := "2.12.8" // or any other Scala version >= 2.10.2

libraryDependencies ++= Seq(
  "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
)

enablePlugins(ScalaJSPlugin)

// This is an application with a main method
// scalaJSUseMainModuleInitializer := true


val taskA = taskKey[Unit]("This is taskA. It depends on compile.")
val taskB = taskKey[Unit]("This is taskB. It depends on taskA.")

taskA := {
  (Compile / fastOptJS).value
  println("Doing taskA.")
  Thread.sleep(500)
}

taskB := {
  taskA.value
  println("Doing taskB.")
  Thread.sleep(500)
}
