package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String [] heading = {"本月底起逐步增面授課堂時數 但維持半日上課原則"};
        String [] time = {"2021年2月1日週一 下午4:28"};
        String [] content = {"本港疫情反覆，全港各類學校都受到影響，教育局上月初宣布將暫停面授課堂延長至學校農曆新年假期。教育局局長楊潤雄今日(1日)表示，明白網課難以取代面授課，故決定在農曆新年假期後、即本月底起，循序漸進地增加面授課堂的時數，但仍會以半日上課為原則，以免學生需要除下口罩而增加感染風險。\n\n" +
                "\n" +
                "楊潤雄表示，當局一直務求於防疫抗疫與學生學習需要中作出平衡，學校亦於暫停面授課堂期間不斷優化安排，當局明白相關措施難以完全替代面對面交流的校園生活，但同時亦有家長擔心面授課堂的安全情況，而過去一個月學校有限度的復課安排運作大致良好，亦未有出現大型爆發，故將按照此方向逐步增加面授課堂的時間。\n\n" +
                "\n" +
                "被問及教師是否需要病毒檢測，他指出所有復課的教職員若能進行檢測較為理想，因他們在課堂上會面對不少學生，當局稍後會與學界開會商討，期望本周內有具體安排。"};

        TextView txtheading = findViewById(R.id.heading);
        TextView txttime = findViewById(R.id.time);
        TextView txtcontent = findViewById(R.id.content);

        Bundle bundle = getIntent().getExtras();
        int no = bundle.getInt("id");

        txtheading.setText(heading[no]);
        txttime.setText(time[no]);
        txtcontent.setText(content[no]);

    }
}