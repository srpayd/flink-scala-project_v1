package package1


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.logging.log4j.scala.Logging
import org.apache.logging.log4j._

object trendyol_case {
  def main(args: Array[String]): Unit = {


    println("Start ...")

    // This variable allows to read data and start application execution
    val env = ExecutionEnvironment.getExecutionEnvironment
    val tEnv = BatchTableEnvironment.create(env)
    println("env done ...")

    // read a CSV file with four fields
    val csvInput = env.readCsvFile[(Long, Long, String, Long)]("C:\\Users\\srpayd\\Desktop\\TrendyolCase\\Case 1\\flink scala\\case.csv", "\n", "|",quoteCharacter = '"',ignoreFirstLine = true)
    println("csv input done ...")

    csvInput.print()
    println("\n")


    // convert the Dataset into Table under the name "table1"
    val table1 = csvInput.toTable(tEnv, 'date, 'productId, 'eventName, 'userId)

    println(" Count of records: " + table1.count())
    println(table1.printSchema())
    println("Elements of table: " + table1.collect())
    val explanation: String = tEnv.explain(table1)
    println(explanation)
    println("csv to table done ...")
    println("\n")


    // 1. Unique Product View counts by ProductId
    val result1 = tEnv.sqlQuery(s"SELECT distinct productId, COUNT(*) as count_unique FROM $table1 GROUP BY productId ORDER BY productId")
    println("sql1 done ...")

    val dsTuple1: DataSet[(Long, Long)] = tEnv.toDataSet[(Long, Long)](result1)

    dsTuple1.writeAsCsv("C:\\Users\\srpayd\\Desktop\\TrendyolCase\\Case 1\\flink scala\\result1\\result1.txt","\n","|").setParallelism(1)
    println("writing1 done ...")
    println("\n")


    // 2- Unique Event counts
    val result2 = tEnv.sqlQuery(s"SELECT eventName, COUNT(*) as count_unique_events FROM $table1 GROUP BY eventName")
    println("sql2 done ...")

    // convert into tuple
    val dsTuple2: DataSet[(String, Long)] = tEnv.toDataSet[(String, Long)](result2)

    dsTuple2.writeAsCsv("C:\\Users\\srpayd\\Desktop\\TrendyolCase\\Case 1\\flink scala\\result2\\result2.txt", "\n", "|").setParallelism(1)
    println("writing2 done ...")
    println("\n")



    // 3- Top 5 Users who fulfilled all the events (view,add,remove,click)
    val result3 = tEnv.sqlQuery(s"SELECT * FROM (SELECT userId FROM (SELECT userId, count(distinct eventName) as distinct_eventName FROM $table1 GROUP BY userId,eventName) GROUP BY userId,distinct_eventName  HAVING sum(distinct_eventName)=4) ORDER BY userId LIMIT 5")
    println("sql3 done ...")

    // convert into dataset
    val ds3: DataSet[Long] = tEnv.toDataSet(result3)

    ds3.writeAsText("C:\\Users\\srpayd\\Desktop\\TrendyolCase\\Case 1\\flink scala\\result3\\result3.txt").setParallelism(1)
    println("writing3 done ...")
    println("\n")



    // 4- All events of #UserId : 47
    val result4 = tEnv.sqlQuery(s"SELECT eventName,count(*) FROM $table1 WHERE userId=47 GROUP BY eventName")
    println("sql4 done ...")

    // convert into tuple
    val dsTuple4: DataSet[(String, Long)] = tEnv.toDataSet[(String, Long)](result4)

    dsTuple4.writeAsCsv("C:\\Users\\srpayd\\Desktop\\TrendyolCase\\Case 1\\flink scala\\result4\\result4.txt", "\n", "|").setParallelism(1)
    println("writing4 done ...")
    println("\n")



    // 5- Product Views of #UserId : 47
    val result5 = tEnv.sqlQuery(s"SELECT distinct productId FROM $table1 WHERE userId=47 GROUP BY productId")
    println("sql5 done ...")

    // convert into dataset
    val ds5: DataSet[Long] = tEnv.toDataSet(result5)


    ds5.writeAsText("C:\\Users\\srpayd\\Desktop\\TrendyolCase\\Case 1\\flink scala\\result5\\result5.txt").setParallelism(1)
    println("writing5 done ...")
    println("\n")



    // execute program
    env.execute("Execution of Example")
  }
}
