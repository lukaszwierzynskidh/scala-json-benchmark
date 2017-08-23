import RestaurantGen.{delivery, point}
import spray.json.DefaultJsonProtocol._
import spray.json._


case class Restaurant(
  id: String,
  code: Option[String],
  location: Point,
  name: String,
  description: Option[String],
  tags: List[String],
  compact_schedules: Option[String],
  listing_scores: Option[ListingScores],
  payment_types: Option[Array[String]],
  deliveries: Option[List[Delivery]],
  delivery_paths_ids: Option[Array[String]],
  city_id: Option[Int],
  commission: Option[Float],
  compact_discount_times: Option[String],
  compact_schedules_with_menus: Option[String],
  compact_special_days: Option[String],
  country_code: Option[String],
  customer_type: Option[String],
  external_code: Option[String],
  external_id: Option[String],
  minimum_delivery_fee: Option[Int],
  minimum_delivery_minutes: Option[Int]
//  minimum_pickup_minutes: Option[Int]
//  quality_grade: Option[Int],
//  url_slug: Option[String]
//  plugin_tags: Option[Array[String]],
//  delivery_fee: Option[Int],
//  minimum_order_value: Option[Int],
//  rating: Option[Rating],
//  budget: Option[String],
//  image_url: Option[String],
//  logo_url: Option[String]
//  event_ids: List[Int],
//  cuisines: List[Cuisine],
//  food_characteristics: List[FoodCharacteristic],
//  menus: Option[List[Menu]],
//  timezone: Option[String]
)

case class ListingScores(
  scoreA: Int,
  scoreB: Int
)

case class Delivery(
  closing_time: String,
  delivery_provider_id: Int,
  delivery_time_minutes: Int,
  express_delivery_enabled: Boolean,
  id: Long,
  opening_time: String,
  weekdays_bitmask: Int,
  weekdays: Array[Int],
  area_ids: Array[String],
  delivery_conditions: Array[DeliveryCondition],
  polygons: Option[Array[String]]
)

case class DeliveryCondition(
  delivery_fee: Int,
  delivery_fee_type: String,
  minimum_order_value: Int
)

case class Menu(
  menu_type: String,
  opening_time: String,
  closing_time: String,
  items: Option[List[MenuItem]]
)

case class MenuItem(
  id: String,
  name: String,
  description: Option[String],
  price: Int,
  image_url: Option[String]
)

case class Cuisine(
  id: String,
  name: String,
  url_slug: String
)

case class Rating(
  count: Option[Int],
  average: Option[Float],
  ranking: Option[Int],
  details: Option[RatingDetails]
)

case class RatingDetails(
  star1: Option[Int],
  star2: Option[Int],
  star3: Option[Int],
  star4: Option[Int],
  star5: Option[Int]
)

case class FoodCharacteristic(
  id: String,
  name: String,
  urlSlug: Option[String]
)

case class Point(
  latitude: Float,
  longitude: Float
)

object RestaurantGen {
  def apply() = {
    val id = "id"
    val code = None
    val location = point
    val name = "name"
    val description = Some("description")
    val tags = List("tags", "tags1")
    val compact_schedules = None
    val listing_scores = None
    val payment_types = Some(Array("sdsads", "sdsdsad", "sadsad"))
    val deliveries = Some(List(delivery, delivery))
    val delivery_path_ids = Some(Array("5ewrewrewr", "erewrewrew", "erewrewr"))
    val city_id = Some(2)
    val commission = Some(4534F)
    val compact_discount_times = Some("compact_discount_times")
    val compact_schedules_with_menus = Some("compact_schedules_with_menus")
    val compact_special_days = Some("compact_special_days")
    val country_code = Some("country_code")
    val customer_type = Some("customerType")
    val external_code = Some("external_code")
    val external_id = Some("external_id")
    val minimum_delivery_fee = Some(12)
    val minimum_delivery_minutes = Some(32)
//    val minimum_pickup_minutes = Some(32)
//    val quality_grade = Some(32)
//    val url_slug = Some("url_slug")
//    val plugin_tags = Some(Array("plugin_tags", "plugin_tags"))
//    val delivery_fee = None
//    val minimum_order_value = Some(4)
//    val rating: Rating = ratingFunc
//    val budget = Some("budget")
//    val image_url = Some("sdsdsdsadsadsa")
//    val logo_url = Some("logoUrl")
//    val event_ids = List(2, 35, 3)
//    val cuisines = List(cuisine)
//    val food_characteristics = List(foodCharacteristicsFunc, foodCharacteristicsFunc)
//    val menus = None
//    val timezone = None
    Restaurant(id, code, location, name, description, tags,
      compact_schedules, listing_scores, payment_types, deliveries, delivery_path_ids,
      city_id, commission, compact_discount_times, compact_schedules_with_menus, compact_special_days, country_code, customer_type,
      external_code, external_id, minimum_delivery_fee, minimum_delivery_minutes)//, minimum_pickup_minutes)
      //quality_grade, url_slug) //plugin_tags, delivery_fee, minimum_order_value, Some(rating), budget, image_url, logo_url)//, event_ids, cuisines, food_characteristics, menus, timezone)
  }

  def foodCharacteristicsFunc = {
    val id = "food_char_id"
    val name = "food_char_name"
    val urlSlug = Some("food_url_slug")
    FoodCharacteristic(id, name, urlSlug)
  }

  def cuisine = {
    val id = "id"
    val name = "name_cuisine"
    val urlSlug = "url_slug"
    Cuisine(id, name, urlSlug)
  }

  def ratingFunc = {
    val count = Some(32)
    val average = Some(4F)
    val ranking = Some(3)
    val details = None
    Rating(count, average, ranking, details)
  }

  def delivery = {
    val closingTime = "closingTime"
    val deliveryProviderId = 5
    val deliveryTimeMinutes = 45
    val expressDeliveryEnabled = true
    val id = 54L
    val opeingTime = "openingTime"
    val weekdaysBitmask = 4
    val weekdays = Array(3, 3, 5)
    val areaIds = Array("areaIDs", "fdsfds", "dfsdfdsfdsf")
    val deliveryConditions = Array(deliveryCondition, deliveryCondition)
    val polygons = Some(Array("sdfsdf"))
    Delivery(closingTime, deliveryProviderId, deliveryTimeMinutes, expressDeliveryEnabled, id, opeingTime, weekdaysBitmask,
      weekdays, areaIds, deliveryConditions, polygons)
  }

  def deliveryCondition = {
    val deliveryFee = 43543
    val deliveryFeeType = "dfdsfsfdsfsf"
    val minimumOrderValue = 123
    DeliveryCondition(deliveryFee, deliveryFeeType, minimumOrderValue)
  }

  def point = {
    val latitude = 32432.343F
    val longitude = 3423.324F
    Point(latitude, longitude)
  }
}

object RestaurantDeserializerLift {
  implicit val formats = net.liftweb.json.Serialization.formats(net.liftweb.json.NoTypeHints)

  def apply(): String = net.liftweb.json.Serialization.write(RestaurantGen())
}

object RestaurantDeserializerJson4s {
  implicit val formats = org.json4s.jackson.Serialization.formats(org.json4s.NoTypeHints)

  def apply(): String = org.json4s.jackson.Serialization.write(RestaurantGen())
}

object RestaurantDeserializerPlay {
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

  implicit val pointWrites = play.api.libs.json.Json.writes[Point]
  implicit val listingScoresWrites = play.api.libs.json.Json.writes[ListingScores]
  implicit val deliveryConditionWrites = play.api.libs.json.Json.writes[DeliveryCondition]
  implicit val deliveryWrites = play.api.libs.json.Json.writes[Delivery]
  implicit val ratingDetailsWrites = play.api.libs.json.Json.writes[RatingDetails]
  implicit val ratingWrites = play.api.libs.json.Json.writes[Rating]
  implicit val cuisineWrites = play.api.libs.json.Json.writes[Cuisine]
  implicit val menuItemWrites = play.api.libs.json.Json.writes[MenuItem]
  implicit val menuWrites = play.api.libs.json.Json.writes[Menu]
  implicit val foodCharacteristicWrites = play.api.libs.json.Json.writes[FoodCharacteristic]
  implicit val restaurantWrites = play.api.libs.json.Json.writes[Restaurant]

  def apply(): String = play.api.libs.json.Json.toJson(restaurant).toString
}

object RestaurantDeserializerSpray {
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

  def apply(): String = restaurant.toJson.toString
}