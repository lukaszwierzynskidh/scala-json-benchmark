import java.io.{File, PrintWriter}

import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.io._

/**
 * Created by nlw on 18/04/15.
 *
 */
object ScalaJsonBenchmark extends App {

  val data_restaurant_1: Array[String] = Source.fromFile("src/main/resources/restaurant_1.json").getLines().toArray
  val data_restaurant_10: Array[String] = Source.fromFile("src/main/resources/restaurant_10.json").getLines().toArray
  val data_restaurant_50: Array[String] = Source.fromFile("src/main/resources/restaurant_50.json").getLines().toArray
  val data_restaurant_100: Array[String] = Source.fromFile("src/main/resources/restaurant_100.json").getLines().toArray
  val data_restaurant_500: Array[String] = Source.fromFile("src/main/resources/restaurant_500.json").getLines().toArray
  val data_restaurant_1000: Array[String] = Source.fromFile("src/main/resources/restaurant_1000.json").getLines().toArray

  // Do some stupid work to "warm up" the JVM, no idea if this makes any difference at all
  var yy = 0L
  for (x <- 1L to 100000L) {
    yy += x * x
  }

  /** Play, Json4s, Spray and lift serialization for 1 restaurant */
  def TestReadRestaurant1[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data_restaurant_1 map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  /** Play, Json4s, Spray and lift serialization for 10 restaurants */
  def TestReadRestaurant10[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data_restaurant_10 map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  /** Play, Json4s, Spray and lift serialization for 50 restaurants */
  def TestReadRestaurant50[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data_restaurant_50 map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  /** Play, Json4s, Spray and lift serialization for 100 restaurants */
  def TestReadRestaurant100[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data_restaurant_100 map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  /** Play, Json4s, Spray and lift serialization for 500 restaurants */
  def TestReadRestaurant500[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data_restaurant_500 map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  /** Play, Json4s, Spray and lift serialization for 1000 restaurants */
  def TestReadRestaurant1000[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data_restaurant_1000 map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }


  /** Play deserialization for 1, 10, 50, 100, 500 and 1000 restaurants */
  def TestWritePlay1Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1_play_write.json"))
    val NUM_ROWS = 1
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWritePlay10Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_10_play_write.json"))
    val NUM_ROWS = 10
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWritePlay50Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_50_play_write.json"))
    val NUM_ROWS = 50
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWritePlay100Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_100_play_write.json"))
    val NUM_ROWS = 100
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWritePlay500Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_500_play_write.json"))
    val NUM_ROWS = 500
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWritePlay1000Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1000_play_write.json"))
    val NUM_ROWS = 1000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }


  /** Lift deserialization for 1, 10, 50, 100, 500 and 1000 restaurants */
  def TestWriteLift1Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1_lift_write.json"))
    val NUM_ROWS = 1
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteLift10Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_10_lift_write.json"))
    val NUM_ROWS = 10
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteLift50Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_50_lift_write.json"))
    val NUM_ROWS = 50
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteLift100Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_100_lift_write.json"))
    val NUM_ROWS = 100
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteLift500Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_500_lift_write.json"))
    val NUM_ROWS = 500
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteLift1000Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1000_lift_write.json"))
    val NUM_ROWS = 1000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }


  /** Spray deserialization for 1, 10, 50, 100, 500 and 1000 restaurants */
  def TestWriteSpray1Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1_spray_write.json"))
    val NUM_ROWS = 1
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteSpray10Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_10_spray_write.json"))
    val NUM_ROWS = 10
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteSpray50Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_50_spray_write.json"))
    val NUM_ROWS = 50
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteSpray100Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_100_spray_write.json"))
    val NUM_ROWS = 100
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteSpray500Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_500_spray_write.json"))
    val NUM_ROWS = 500
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteSpray1000Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1000_spray_write.json"))
    val NUM_ROWS = 1000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }


  /** Json4s deserialization for 1, 10, 50, 100, 500 and 1000 restaurants */
  def TestWriteJson4s1Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1_json4s_write.json"))
    val NUM_ROWS = 1
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteJson4s10Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_10_json4s_write.json"))
    val NUM_ROWS = 10
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteJson4s50Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_50_json4s_write.json"))
    val NUM_ROWS = 50
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteJson4s100Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_100_json4s_write.json"))
    val NUM_ROWS = 100
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteJson4s500Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_500_json4s_write.json"))
    val NUM_ROWS = 500
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteJson4s1000Restaurant: Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_1000_json4s_write.json"))
    val NUM_ROWS = 1000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def theSprayFuncs: (String, MyParser) = {
    ("spray", new SprayParser)
  }

  def theJson4sFuncs: (String, MyParser) = {
    ("json4s", new Json4sParser)
  }

  def theLiftFuncs: (String, MyParser) = {
    ("lift", new LiftParser)
  }

  def thePlayFuncs: (String, MyParser) = {
    ("play", new PlayParser)
  }

  var totalSprayReadTime1Restaurant = 0L
  var totalSprayWriteTime1Restaurant = 0L
  var totalJson4sReadTime1Restaurant = 0L
  var totalJson4sWriteTime1Restaurant = 0L
  var totalPlayReadTime1Restaurant = 0L
  var totalPlayWriteTime1Restaurant = 0L
  var totalLiftReadTime1Restaurant = 0L
  var totalLiftWriteTime1Restaurant = 0L

  var totalSprayReadTime10Restaurant = 0L
  var totalSprayWriteTime10Restaurant = 0L
  var totalJson4sReadTime10Restaurant = 0L
  var totalJson4sWriteTime10Restaurant = 0L
  var totalPlayReadTime10Restaurant = 0L
  var totalPlayWriteTime10Restaurant = 0L
  var totalLiftReadTime10Restaurant = 0L
  var totalLiftWriteTime10Restaurant = 0L

  var totalSprayReadTime50Restaurant = 0L
  var totalSprayWriteTime50Restaurant = 0L
  var totalJson4sReadTime50Restaurant = 0L
  var totalJson4sWriteTime50Restaurant = 0L
  var totalPlayReadTime50Restaurant = 0L
  var totalPlayWriteTime50Restaurant = 0L
  var totalLiftReadTime50Restaurant = 0L
  var totalLiftWriteTime50Restaurant = 0L

  var totalSprayReadTime100Restaurant = 0L
  var totalSprayWriteTime100Restaurant = 0L
  var totalJson4sReadTime100Restaurant = 0L
  var totalJson4sWriteTime100Restaurant = 0L
  var totalPlayReadTime100Restaurant = 0L
  var totalPlayWriteTime100Restaurant = 0L
  var totalLiftReadTime100Restaurant = 0L
  var totalLiftWriteTime100Restaurant = 0L

  var totalSprayReadTime500Restaurant = 0L
  var totalSprayWriteTime500Restaurant = 0L
  var totalJson4sReadTime500Restaurant = 0L
  var totalJson4sWriteTime500Restaurant = 0L
  var totalPlayReadTime500Restaurant = 0L
  var totalPlayWriteTime500Restaurant = 0L
  var totalLiftReadTime500Restaurant = 0L
  var totalLiftWriteTime500Restaurant = 0L

  var totalSprayReadTime1000Restaurant = 0L
  var totalSprayWriteTime1000Restaurant = 0L
  var totalJson4sReadTime1000Restaurant = 0L
  var totalJson4sWriteTime1000Restaurant = 0L
  var totalPlayReadTime1000Restaurant = 0L
  var totalPlayWriteTime1000Restaurant = 0L
  var totalLiftReadTime1000Restaurant = 0L
  var totalLiftWriteTime1000Restaurant = 0L


  /** Write 1 restaurants in play, json4s, spray and lift */
  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1(thePlayFuncs._1)(thePlayFuncs._2.apply)
    totalPlayReadTime1Restaurant += serializeTime
    var deserializeTime = TestWritePlay1Restaurant
    totalPlayWriteTime1Restaurant += deserializeTime
  }
  println(f"Play serializes 1 restaurant in $totalPlayReadTime1Restaurant%05d and deserializes in $totalPlayWriteTime1Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1(theJson4sFuncs._1)(theJson4sFuncs._2.apply)
    totalJson4sReadTime1Restaurant += serializeTime
    var deserializeTime = TestWriteJson4s1Restaurant
    totalJson4sWriteTime1Restaurant += deserializeTime
  }
  println(f"Json4s serializes 1 restaurant in $totalJson4sReadTime1Restaurant%05d and deserializes in $totalJson4sWriteTime1Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1(theSprayFuncs._1)(theSprayFuncs._2.apply)
    totalSprayReadTime1Restaurant += serializeTime
    var deserializeTime = TestWriteSpray1Restaurant
    totalSprayWriteTime1Restaurant += deserializeTime
  }
  println(f"Spray serializes 1 restaurant in $totalSprayReadTime1Restaurant%05d and deserializes in $totalSprayWriteTime1Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1(theLiftFuncs._1)(theSprayFuncs._2.apply)
    totalLiftReadTime1Restaurant += serializeTime
    var deserializeTime = TestWriteLift1Restaurant
    totalLiftWriteTime1Restaurant += deserializeTime
  }
  println(f"Lift serializes 1 restaurant in $totalLiftReadTime1Restaurant%05d and deserializes in $totalLiftWriteTime1Restaurant%05d for 10000 iterations\n")


  /** Write 10 restaurants in play, json4s, spray and lift */
  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant10(thePlayFuncs._1)(thePlayFuncs._2.apply)
    totalPlayReadTime10Restaurant += serializeTime
    var deserializeTime = TestWritePlay10Restaurant
    totalPlayWriteTime10Restaurant += deserializeTime
  }
  println(f"Play serializes 10 restaurants in $totalPlayReadTime10Restaurant%05d and deserializes in $totalPlayWriteTime10Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant10(theJson4sFuncs._1)(theJson4sFuncs._2.apply)
    totalJson4sReadTime10Restaurant += serializeTime
    var deserializeTime = TestWriteJson4s10Restaurant
    totalJson4sWriteTime10Restaurant += deserializeTime
  }
  println(f"Json4s serializes 10 restaurants in $totalJson4sReadTime10Restaurant%05d and deserializes in $totalJson4sWriteTime10Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant10(theSprayFuncs._1)(theSprayFuncs._2.apply)
    totalSprayReadTime10Restaurant += serializeTime
    var deserializeTime = TestWriteSpray10Restaurant
    totalSprayWriteTime10Restaurant += deserializeTime
  }
  println(f"Spray serializes 10 restaurants in $totalSprayReadTime10Restaurant%05d and deserializes in $totalSprayWriteTime10Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant10(theLiftFuncs._1)(theSprayFuncs._2.apply)
    totalLiftReadTime10Restaurant += serializeTime
    var deserializeTime = TestWriteLift10Restaurant
    totalLiftWriteTime10Restaurant += deserializeTime
  }
  println(f"Lift serializes 10 restaurants in $totalLiftReadTime10Restaurant%05d and deserializes in $totalLiftWriteTime10Restaurant%05d for 10000 iterations\n")


  /** Write 50 restaurants in play, json4s, spray and lift */
  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant50(thePlayFuncs._1)(thePlayFuncs._2.apply)
    totalPlayReadTime50Restaurant += serializeTime
    var deserializeTime = TestWritePlay50Restaurant
    totalPlayWriteTime50Restaurant += deserializeTime
  }
  println(f"Play serializes 50 restaurants in $totalPlayReadTime50Restaurant%05d and deserializes in $totalPlayWriteTime50Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant50(theJson4sFuncs._1)(theJson4sFuncs._2.apply)
    totalJson4sReadTime50Restaurant += serializeTime
    var deserializeTime = TestWriteJson4s50Restaurant
    totalJson4sWriteTime50Restaurant += deserializeTime
  }
  println(f"Json4s serializes 50 restaurants in $totalJson4sReadTime50Restaurant%05d and deserializes in $totalJson4sWriteTime50Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant50(theSprayFuncs._1)(theSprayFuncs._2.apply)
    totalSprayReadTime50Restaurant += serializeTime
    var deserializeTime = TestWriteSpray50Restaurant
    totalSprayWriteTime50Restaurant += deserializeTime
  }
  println(f"Spray serializes 50 restaurants in $totalSprayReadTime50Restaurant%05d and deserializes in $totalSprayWriteTime50Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant50(theLiftFuncs._1)(theSprayFuncs._2.apply)
    totalLiftReadTime50Restaurant += serializeTime
    var deserializeTime = TestWriteLift50Restaurant
    totalLiftWriteTime50Restaurant += deserializeTime
  }
  println(f"Lift serializes 50 restaurants in $totalLiftReadTime50Restaurant%05d and deserializes in $totalLiftWriteTime50Restaurant%05d for 10000 iterations\n")


  /** Write 100 restaurants in play, json4s, spray and lift */
  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant100(thePlayFuncs._1)(thePlayFuncs._2.apply)
    totalPlayReadTime100Restaurant += serializeTime
    var deserializeTime = TestWritePlay100Restaurant
    totalPlayWriteTime100Restaurant += deserializeTime
  }
  println(f"Play serializes 100 restaurants in $totalPlayReadTime100Restaurant%05d and deserializes in $totalPlayWriteTime100Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant100(theJson4sFuncs._1)(theJson4sFuncs._2.apply)
    totalJson4sReadTime100Restaurant += serializeTime
    var deserializeTime = TestWriteJson4s100Restaurant
    totalJson4sWriteTime100Restaurant += deserializeTime
  }
  println(f"Json4s serializes 100 restaurants in $totalJson4sReadTime100Restaurant%05d and deserializes in $totalJson4sWriteTime100Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant100(theSprayFuncs._1)(theSprayFuncs._2.apply)
    totalSprayReadTime100Restaurant += serializeTime
    var deserializeTime = TestWriteSpray100Restaurant
    totalSprayWriteTime100Restaurant += deserializeTime
  }
  println(f"Spray serializes 100 restaurants in $totalSprayReadTime100Restaurant%05d and deserializes in $totalSprayWriteTime100Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant100(theLiftFuncs._1)(theSprayFuncs._2.apply)
    totalLiftReadTime100Restaurant += serializeTime
    var deserializeTime = TestWriteLift100Restaurant
    totalLiftWriteTime100Restaurant += deserializeTime
  }
  println(f"Lift serializes 100 restaurants in $totalLiftReadTime100Restaurant%05d and deserializes in $totalLiftWriteTime100Restaurant%05d for 10000 iterations\n")


  /** Write 500 restaurants in play, json4s, spray and lift */
  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant500(thePlayFuncs._1)(thePlayFuncs._2.apply)
    totalPlayReadTime500Restaurant += serializeTime
    var deserializeTime = TestWritePlay500Restaurant
    totalPlayWriteTime500Restaurant += deserializeTime
  }
  println(f"Play serializes 500 restaurants in $totalPlayReadTime500Restaurant%05d and deserializes in $totalPlayWriteTime500Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant500(theJson4sFuncs._1)(theJson4sFuncs._2.apply)
    totalJson4sReadTime500Restaurant += serializeTime
    var deserializeTime = TestWriteJson4s500Restaurant
    totalJson4sWriteTime500Restaurant += deserializeTime
  }
  println(f"Json4s serializes 500 restaurants in $totalJson4sReadTime500Restaurant%05d and deserializes in $totalJson4sWriteTime500Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant500(theSprayFuncs._1)(theSprayFuncs._2.apply)
    totalSprayReadTime500Restaurant += serializeTime
    var deserializeTime = TestWriteSpray500Restaurant
    totalSprayWriteTime500Restaurant += deserializeTime
  }
  println(f"Spray serializes 500 restaurants in $totalSprayReadTime500Restaurant%05d and deserializes in $totalSprayWriteTime500Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant500(theLiftFuncs._1)(theSprayFuncs._2.apply)
    totalLiftReadTime500Restaurant += serializeTime
    var deserializeTime = TestWriteLift500Restaurant
    totalLiftWriteTime500Restaurant += deserializeTime
  }
  println(f"Lift serializes 500 restaurants in $totalLiftReadTime500Restaurant%05d and deserializes in $totalLiftWriteTime500Restaurant%05d for 10000 iterations\n")


  /** Write 1000 restaurants in play, json4s, spray and lift */
  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1000(thePlayFuncs._1)(thePlayFuncs._2.apply)
    totalPlayReadTime1000Restaurant += serializeTime
    var deserializeTime = TestWritePlay1000Restaurant
    totalPlayWriteTime1000Restaurant += deserializeTime
  }
  println(f"Play serializes 1000 restaurants in $totalPlayReadTime1000Restaurant%05d and deserializes in $totalPlayWriteTime1000Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1000(theJson4sFuncs._1)(theJson4sFuncs._2.apply)
    totalJson4sReadTime1000Restaurant += serializeTime
    var deserializeTime = TestWriteJson4s1000Restaurant
    totalJson4sWriteTime1000Restaurant += deserializeTime
  }
  println(f"Json4s serializes 1000 restaurants in $totalJson4sReadTime1000Restaurant%05d and deserializes in $totalJson4sWriteTime1000Restaurant%05d for 10000 iterations")

  for (i <- 1 to 100) {
    var serializeTime = TestReadRestaurant1000(theSprayFuncs._1)(theSprayFuncs._2.apply)
    totalSprayReadTime1000Restaurant += serializeTime
    var deserializeTime = TestWriteSpray1000Restaurant
    totalSprayWriteTime1000Restaurant += deserializeTime
  }
  println(f"Spray serializes 1000 restaurants in $totalSprayReadTime1000Restaurant%05d and deserializes in $totalSprayWriteTime1000Restaurant%05d for 10000 iterations")

  for (i <- 1 to 10000) {
    var serializeTime = TestReadRestaurant1000(theLiftFuncs._1)(theSprayFuncs._2.apply)
    totalLiftReadTime1000Restaurant += serializeTime
    var deserializeTime = TestWriteLift1000Restaurant
    totalLiftWriteTime1000Restaurant += deserializeTime
  }
  println(f"Lift serializes 1000 restaurants in $totalLiftReadTime1000Restaurant%05d and deserializes in $totalLiftWriteTime1000Restaurant%05d for 10000 iterations\n")
}


trait MyParser {
  def apply(s: String): Restaurant
}

class Json4sParser extends MyParser {
  implicit val formats = org.json4s.DefaultFormats

  def apply(s: String) = {
    org.json4s.jackson.JsonMethods.parse(s).extract[Restaurant]
  }
}

class LiftParser extends MyParser {
  implicit val formats = net.liftweb.json.DefaultFormats

  def apply(s: String) = {
    net.liftweb.json.parse(s).extract[Restaurant]
  }
}

class PlayParser extends MyParser {
  implicit val pointReads = play.api.libs.json.Json.reads[Point]
  implicit val listingScoresReads = play.api.libs.json.Json.reads[ListingScores]
  implicit val deliveryConditionReads = play.api.libs.json.Json.reads[DeliveryCondition]
  implicit val deliveryReads = play.api.libs.json.Json.reads[Delivery]
  implicit val ratingDetailsReads = play.api.libs.json.Json.reads[RatingDetails]
  implicit val ratingReads = play.api.libs.json.Json.reads[Rating]
  implicit val cuisineReads = play.api.libs.json.Json.reads[Cuisine]
  implicit val menuItemReads = play.api.libs.json.Json.reads[MenuItem]
  implicit val menuReads = play.api.libs.json.Json.reads[Menu]
  implicit val foodCharacteristicReads = play.api.libs.json.Json.reads[FoodCharacteristic]
  implicit val restaurantReads = play.api.libs.json.Json.reads[Restaurant]

  def apply(s: String) = {
    play.api.libs.json.Json.parse(s).as[Restaurant]
  }
}

class SprayParser extends MyParser {
  implicit val pointFormat = jsonFormat2(Point)
  implicit val listingScoresFormat = jsonFormat2(ListingScores)
  implicit val dcFormat = jsonFormat3(DeliveryCondition)
  implicit val dFormat = jsonFormat11(Delivery)
  implicit val raFormat = jsonFormat5(RatingDetails)
  implicit val rdFormat = jsonFormat4(Rating)
  implicit val cuisFormat = jsonFormat3(Cuisine)
  implicit val menuFormat = jsonFormat5(MenuItem)
  implicit val meFormat = jsonFormat4(Menu)
  implicit val fcFormat = jsonFormat3(FoodCharacteristic)
  implicit val rowdssdFormat = jsonFormat22(Restaurant)

  def apply(s: String) = {
    s.parseJson.convertTo[Restaurant]
  }
}
