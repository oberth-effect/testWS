package test

import io.laminext.websocket.upickle._
import test.OptionPickler.*

case class Data(s: String)
implicit val dataReader: Reader[Data] = reader[Map[String, String]].map(m => Data(m("s")))
implicit val dataWriter: Writer[Data] = writer[Map[String, String]].comap(d => Map("s" -> d.s))

def main(args: Array[String]): Unit = {
  val ws = WebSocket.url("wss://echo.websocket.events").upickle(OptionPickler).json[Data, Data].build()
}