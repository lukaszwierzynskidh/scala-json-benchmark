import java.io.{File, PrintWriter}

import RestaurantGen.{delivery, point}
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.io._

/**
 * Created by nlw on 18/04/15.
 *
 */
object ScalaJsonBenchmark extends App {

  val data: Array[String] = Source.fromFile("src/main/resources/restaurant.json").getLines().toArray

  // Do some stupid work to "warm up" the JVM, no idea if this makes any difference at all
  var yy = 0L
  for (x <- 1L to 100000L) {
    yy += x * x
  }

  val restaurant = Restaurant(
    id = "id",
    code = None,
    location = point,
    name = "name",
    description = Some("description"),
    tags = List("tags", "tags1"),
    compact_schedules = None,
    listing_scores = None,
    payment_types = Some(Array("sdsads", "sdsdsad", "sadsad")),
    deliveries = Some(List(delivery, delivery)),
    delivery_paths_ids = Some(Array("5ewrewrewr", "erewrewrew", "erewrewr")),
    city_id = Some(2),
    commission = Some(4534F),
    compact_discount_times = Some("compact_discount_times"),
    compact_schedules_with_menus = Some("compact_schedules_with_menus"),
    compact_special_days = Some("compact_special_days"),
    country_code = Some("country_code"),
    customer_type = Some("customerType"),
    external_code = Some("external_code"),
    external_id = Some("external_id"),
    minimum_delivery_fee = Some(12),
    minimum_delivery_minutes = Some(32)
  )

  def TestRead[A](name: String)(f: String => A): Long = {
    val start_time = System.currentTimeMillis()
    val parsed_data = data map f
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWritePlay[A](name: String): Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_play_write.json"))
    val NUM_ROWS = 25000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerPlay() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteLift[A](name: String): Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_lift_write.json"))
    val NUM_ROWS = 25000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerLift() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteSpray[A](name: String): Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_spray_write.json"))
    val NUM_ROWS = 25000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerSpray() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def TestWriteJson4s[A](name: String): Long = {
    val start_time = System.currentTimeMillis()
    val pw = new PrintWriter(new File("src/main/resources/restaurant_json4s_write.json"))
    val NUM_ROWS = 25000
    (0 until NUM_ROWS) foreach { _ =>
      pw.write(RestaurantDeserializerJson4s() + "\n")
    }
    pw.close()
    val end_time = System.currentTimeMillis()
    end_time - start_time
  }

  def theSprayFuncs: Map[String, MyParser] = Map(
    "spray" -> new SprayParser
  )

  def theJson4sFuncs: Map[String, MyParser] = Map(
    "json4s" -> new Json4sParser
  )

  def theLiftFuncs: Map[String, MyParser] = Map(
    "lift" -> new LiftParser
  )

  def thePlayFuncs: Map[String, MyParser] = Map(
    "play" -> new PlayParser
  )

  var totalSprayReadTime = 0L
  var totalSprayWriteTime = 0L
  var totalJson4sReadTime = 0L
  var totalJson4sWriteTime = 0L
  var totalPlayReadTime = 0L
  var totalPlayWriteTime = 0L
  var totalLiftReadTime = 0L
  var totalLiftWriteTime = 0L

  val resultsJson4s = for {
    iteration <- (0 to 100).iterator
    (name, func) <- theJson4sFuncs.iterator
  } yield {
    var serializeTime = TestRead(name)(func.apply)
    totalJson4sReadTime += serializeTime
    var deserializeTime = TestWriteJson4s(name)
    totalJson4sWriteTime += deserializeTime
    f"$name%7s serializes in $totalJson4sReadTime%05d and deserializes in $totalJson4sWriteTime%05d for $iteration%02d iterations"
  }

  val resultsPlay = for {
    iteration <- (0 to 100).iterator
    (name, func) <- thePlayFuncs.iterator
  } yield {
    var serializeTime = TestRead(name)(func.apply)
    totalPlayReadTime += serializeTime
    var deserializeTime = TestWritePlay(name)
    totalPlayWriteTime += deserializeTime
    f"$name%7s serializes in $totalPlayReadTime%05d and deserializes in $totalPlayWriteTime%05d for $iteration%02d iterations"
  }

  val resultsSpray = for {
    iteration <- (0 to 100).iterator
    (name, func) <- theSprayFuncs.iterator
  } yield {
    var serializeTime = TestRead(name)(func.apply)
    totalSprayReadTime += serializeTime
    var deserializeTime = TestWriteSpray(name)
    totalSprayWriteTime += deserializeTime
    f"$name%7s serializes in $totalSprayReadTime%05d and deserializes in $totalSprayWriteTime%05d for $iteration%02d iterations"
  }

  val resultsLift = for {
    iteration <- (0 to 100).iterator
    (name, func) <- theLiftFuncs.iterator
  } yield {
    var serializeTime = TestRead(name)(func.apply)
    totalLiftReadTime += serializeTime
    var deserializeTime = TestWriteLift(name)
    totalLiftWriteTime += deserializeTime
    f"$name%7s serializes in $totalLiftReadTime%05d and deserializes in $totalLiftWriteTime%05d for $iteration%02d iterations"
  }

  resultsSpray foreach println
  resultsPlay foreach println
  resultsJson4s foreach println
  resultsLift foreach println
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
