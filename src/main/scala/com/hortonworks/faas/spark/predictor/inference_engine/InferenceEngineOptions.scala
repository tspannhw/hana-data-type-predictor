package com.hortonworks.faas.spark.predictor.inference_engine

import com.hortonworks.faas.spark.predictor.inference_engine.analytic.common._
import com.hortonworks.faas.spark.predictor.inference_engine.configuration.ConfigurationOptionMap
import com.hortonworks.faas.spark.predictor.inference_engine.task.inference_engine_master

/**
  * Created by njayakumar on 5/16/2018.
  */
class InferenceEngineOptions(val task:String, val o:String, val w:String ) extends CommonDataFrameWriterOption(o,w) {
  def this( t:String, cdfw:CommonDataFrameWriterOption ) {
    this( t, cdfw.output, cdfw.write_mode)
  }

  def isValid(): Boolean = {
    true
  }
}

object InferenceEngineOptions {
  val TASK_KEY="task"

  def apply(args: Array[String]): InferenceEngineOptions = {
    apply(ConfigurationOptionMap(args))
  }

  def apply(options: ConfigurationOptionMap): InferenceEngineOptions = {

    val cdfw:CommonDataFrameWriterOption = CommonDataFrameWriterOption(options)

    val t:String = if(options.opts.contains(TASK_KEY) && options.opts(TASK_KEY).nonEmpty) options.opts(TASK_KEY)(0) else inference_engine_master.TASK

    new InferenceEngineOptions(t, cdfw)
  }

  def printUsage(): Unit = {
    CommonDataFrameWriterOption.printUsage()

    println(s"${TASK_KEY} | Task to perform : { ${inference_engine_master.TASK}, ${inference_engine_master.TASK}, ${inference_engine_master.TASK} }; Default: ${inference_engine_master.TASK}")
  }
}
