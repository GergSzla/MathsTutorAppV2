package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.abel533.echarts.axis.CategoryAxis
import com.github.abel533.echarts.axis.ValueAxis
import com.github.abel533.echarts.code.Symbol
import com.github.abel533.echarts.code.Trigger
import com.github.abel533.echarts.data.LineData
import com.github.abel533.echarts.data.PieData
import com.github.abel533.echarts.json.GsonOption
import com.github.abel533.echarts.series.Line
import com.github.abel533.echarts.series.Pie
import kotlinx.android.synthetic.main.activity_mta.*
import kotlinx.android.synthetic.main.activity_mta_stats.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import vip.frendy.chart.EChartView
import vip.frendy.chart.EChartWebView

class MTAStatsActivity: FragmentActivity(), EChartWebView.DataSource {


    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta_stats)


        chartView.setType(1)
        chartView.setDataSource(this)
        app = application as MainApp
    }

    override fun markChartOptions(): GsonOption {
        longToast("Loading Page...") //to hide original foreign "Loading Page..." toast which was not removable
        return getPieChartOptions()
    }

    override fun markChartOptions01(): GsonOption {
        return getPieChartOptions01()
    }

    fun getPieChartOptions01(): GsonOption {
        val option = GsonOption()
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)")
        option.legend().data("Correct", "Wrong" );

        val pie = getPie01().center("50%", "45%").radius("50%")
        pie.label().normal().show(true).formatter("{b}{c}({d}%)")
        option.series(pie)
        return option
    }

    fun getPie01(): Pie {
        return Pie().name("Questions Answered").data(
            PieData("Correct", 3),
            PieData("Wrong", 4)

        )
    }

    fun getPieChartOptions(): GsonOption {
        val option = GsonOption()
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)")
        option.legend().data("Passed","Failed" );

        val pie = getPie().center("50%", "45%").radius("50%")
        pie.label().normal().show(true).formatter("{b}{c}({d}%)")
        option.series(pie)
        return option
    }

    fun getPie(): Pie {
        return Pie().name("Sessions Pass/Fail").data(
            PieData("Passed", 3),
            PieData("Failed", 1)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_views, menu)
        if (menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_back -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}