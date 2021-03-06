package com.hortonworks.faas.spark.predictor.inference_engine.task

import java.sql.Timestamp
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Created by njayakumar on 5/16/2018.
  */
object inference_engine_master {

  val TASK: String = "inference_engine_master"

  val engine_series_query: String = "SELECT SERLZD_ENG_SER_NUM as se_esn, serlzd_eng_stat_cd, vld_eng_srs_num," +
    " eng_cnfg_start_dt, eng_cnfg_end_dt, trim(icao_cd) as icao_cd FROM s3_data.phm_serialized_engine"


  def getData(spark: SparkSession,  current_time: Timestamp): DataFrame = {
     spark
      .read
      .format("com.hortonworks.faas.spark.connector")
      .options(Map("query" -> ("SELECT * FROM \"" + "" + "\"." + "\"DataLake.Deltaviews.TransactionViews/InstallationOwnershipTS\" " )))
      // .options(Map("query" -> ("SELECT * FROM \"" + dbName + "\"." + "DFKKOPK" )))
      .load()
  }
}
