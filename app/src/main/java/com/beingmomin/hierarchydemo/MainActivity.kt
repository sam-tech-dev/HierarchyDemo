package com.beingmomin.hierarchydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.beingmomin.hierarchicalview.BinderView
import com.beingmomin.hierarchydemo.Children
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import com.beingmomin.hierarchydemo.R
import kotlinx.android.synthetic.main.layout_hierarchy_child.view.*

class MainActivity : AppCompatActivity(), BinderView<Children> {

    lateinit var children: Children

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json = " {\n" +
                "        \"personId\": 2,\n" +
                "        \"fatherId\": 0,\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"wifeId\": 5,\n" +
                "                \"name\": \"Bhura Khan\",\n" +
                "                \"personId\": 3,\n" +
                "                \"fatherId\": 2,\n" +
                "                \"gender\": \"Male\",\n" +
                "                \"children\": [\n" +
                "                    {\n" +
                "                        \"personId\": 6,\n" +
                "                        \"fatherId\": 3,\n" +
                "                        \"children\": [\n" +
                "                            {\n" +
                "                                \"wifeId\": 18,\n" +
                "                                \"name\": \"Alladin Mohammad\",\n" +
                "                                \"personId\": 13,\n" +
                "                                \"fatherId\": 6,\n" +
                "                                \"gender\": \"Male\",\n" +
                "                                \"children\": [],\n" +
                "                                \"wifeName\": \"Chhamman Bai\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"personId\": 14,\n" +
                "                                \"fatherId\": 6,\n" +
                "                                \"children\": [],\n" +
                "                                \"name\": \"Imamuddin \",\n" +
                "                                \"gender\": \"Male\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"personId\": 15,\n" +
                "                                \"fatherId\": 6,\n" +
                "                                \"children\": [],\n" +
                "                                \"name\": \"Nizamuddin\",\n" +
                "                                \"gender\": \"Male\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"personId\": 16,\n" +
                "                                \"fatherId\": 6,\n" +
                "                                \"children\": [],\n" +
                "                                \"name\": \"Ismail Khan\",\n" +
                "                                \"gender\": \"Male\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"personId\": 17,\n" +
                "                                \"fatherId\": 6,\n" +
                "                                \"children\": [],\n" +
                "                                \"name\": \"Salim Khan\",\n" +
                "                                \"gender\": \"Male\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"name\": \"Sundra Kha\",\n" +
                "                        \"gender\": \"Male\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"personId\": 7,\n" +
                "                        \"fatherId\": 3,\n" +
                "                        \"children\": [],\n" +
                "                        \"name\": \"Makbul Kha\",\n" +
                "                        \"gender\": \"Male\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"wifeId\": 9,\n" +
                "                        \"name\": \"Jumma Kha\",\n" +
                "                        \"personId\": 8,\n" +
                "                        \"fatherId\": 3,\n" +
                "                        \"gender\": \"Male\",\n" +
                "                        \"children\": [],\n" +
                "                        \"wifeName\": \"Ghishi Bai \"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"wifeName\": \"Dini Bai\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"personId\": 4,\n" +
                "                \"fatherId\": 2,\n" +
                "                \"children\": [\n" +
                "                    {\n" +
                "                        \"personId\": 10,\n" +
                "                        \"fatherId\": 4,\n" +
                "                        \"children\": [],\n" +
                "                        \"name\": \"Chhotu Kha\",\n" +
                "                        \"gender\": \"Male\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"wifeId\": 12,\n" +
                "                        \"name\": \"Bashir Kha\",\n" +
                "                        \"personId\": 11,\n" +
                "                        \"fatherId\": 4,\n" +
                "                        \"gender\": \"Male\",\n" +
                "                        \"children\": [],\n" +
                "                        \"wifeName\": \"Khatoon\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"name\": \"Allabanda Khan\",\n" +
                "                \"gender\": \"Male\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"name\": \"Modu Khan\",\n" +
                "        \"gender\": \"Male\"\n" +
                "    }"
        children = Gson().fromJson<Children>(json, Children::class.java)
    }

    override fun onCreateViewHolder(obj: Children): View {
        val view = layoutInflater.inflate(R.layout.layout_hierarchy_child, null)
        view.tv_person_name.setText(obj.name)
        return view
    }

    override fun getHierarchyData(): Children {
        return children
    }

    override fun getLayoutMaximumWidth(): Float {
        return 60f
    }

    override fun onResume() {
        super.onResume()
        hcl.setBinderView(this)
    }
}
