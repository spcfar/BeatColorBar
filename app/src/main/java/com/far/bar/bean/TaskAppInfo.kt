package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data

@Data
@Keep
class TaskAppInfo() {
    val gmt_create: String? = null
    val gmt_modified: String? = null
    val task_id: String? = null
    val target_code: String? = null
    val app_code: String? = null
    val app_name: String? = null
    val app_version: String? = null
    val crawl_server: Int? = null
    val default_version: String? = null
    val device_code: String? = null
    val app_icon: String? = null
    val app_parent_code: String? = null
    val cookie_type: Int? = null
    val bound_status: Int? = null
    val status: String? = null
    val sub_status: String? = null
    val reason: String? = null
    val execute_count: Int? = null
    val start_crawling_time: String? = null
    val keep_alive: Int? = null
    val total: Int? = null
    var iSelect: Boolean = false
    override fun toString(): String {
        return "TaskAppInfo(gmt_create=$gmt_create, gmt_modified=$gmt_modified, task_id=$task_id, target_code=$target_code, app_code=$app_code, app_name=$app_name, app_version=$app_version, crawl_server=$crawl_server, default_version=$default_version, device_code=$device_code, app_icon=$app_icon, app_parent_code=$app_parent_code, cookie_type=$cookie_type, bound_status=$bound_status, status=$status, sub_status=$sub_status, reason=$reason, execute_count=$execute_count, start_crawling_time=$start_crawling_time, keep_alive=$keep_alive, total=$total, iSelect=$iSelect)"
    }
}