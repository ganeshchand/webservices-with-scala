package com.gc.learning.scala.webservices.json4s.example

import org.json4s.JsonAST._
import org.json4s.jackson.JsonMethods._



/**
  * Created by ganeshchand on 9/14/16.
  */
object Json4sQueryExample {

  def main(args: Array[String]): Unit = {
    lazy val json = parse(
      """
    { "data_center": [
      {
        "name": "cluster1",
        "servers": [
          {"ip": "192.168.1.125", "uptime": 150123, "specs": {"cpus":  8, "ram": 2048}},
          {"ip": "192.168.1.126", "uptime": 189822, "specs": {"cpus": 16, "ram": 4096}},
          {"ip": "192.168.1.127", "uptime": 901214, "specs": {"cpus":  8, "ram": 4096}}
        ],
        "links": [
          {"href": "http://www.example.com/admin", "name": "admin"},
          {"href": "http://www,example.com/home", "name": "home"}
        ],
        "admins": ["jim12", "joe", "maddog"]
      },
      {
        "name": "cluster2",
        "servers": [
          {"ip": "192.168.2.125", "uptime": 453423, "specs": {"cpus":  4, "ram": 2048}},
          {"ip": "192.168.2.126", "uptime": 214312, "specs": {"cpus":  4, "ram": 2048}}
        ],
        "links": [
          {"href": "http://www.example2.com/admin", "name": "admin"},
          {"href": "http://www,example2.com/home", "name": "home"}
        ],
        "admins": ["joe", "liza"]
      }
   ]}
  """)

    // get list of ips and count total number of IP Address found

    val ips = (for {JString(ip) <- json \\ "ip"} yield ip) // works without .asInstanceOf too
    /*
    ips: org.json4s.JValue = JObject(List((ip,JString(192.168.1.125)), (ip,JString(192.168.1.126)), (ip,JString(192.168.1.127)), (ip,JString(192.168.2.125)), (ip,JString(192.168.2.126))))

     */
    println(s"total IPs found: ${ips.size}")

    // get list of ips found in cluster 2

    /*
    for { keyword@JObject(x) <- json \ "data_center" } yield keyword  \\ "ip"

     */
    val ipsInCluster2 = for {
      cluster@JObject(x) <- json \ "data_center"
      if (x contains JField("name", JString("cluster2")))
        JString(ip) <- cluster \\ "ip"
    } yield ip

    println(s"Ips in cluster 2: ${ipsInCluster2.mkString(",")}")

    //Total number of cpus in data center

    val totalCpus = (for { JInt(x) <- json \\ "cpus" } yield x) reduceLeft(_ + _)

    println(s"Total CPUs in Data Center: $totalCpus")


    //Servers sorted by uptime in descending order

    case class Server(ip: String, uptime: Long)

    val servers = for {
      JArray(servers) <- json \\ "servers"
      JObject(server) <- servers
      JField("ip", JString(ip)) <- server
      JField("uptime", JInt(uptime)) <- server
    } yield Server(ip, uptime.longValue)

    val serversSortedByUptime = servers.sortWith(_.uptime > _.uptime)

    serversSortedByUptime.foreach(s => println(s"IP: ${s.ip}  Uptime: ${s.uptime}"))

    //Clusters administered by liza

    val clustersAdminLiza = for {
      JObject(cluster) <- json
      JField("admins", JArray(admins)) <- cluster
      if admins contains JString("liza")
      JField("name", JString(name)) <- cluster
    } yield name



  }

}
