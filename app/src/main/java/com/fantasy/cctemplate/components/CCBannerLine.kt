package com.fantasy.cctemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.cctemplate.R
import com.fantasy.components.aamedium.outerShadow
import com.fantasy.components.extension.compose.Image
import com.fantasy.components.extension.local
import com.fantasy.components.theme.CCColor
import com.fantasy.components.theme.CCFont
import com.fantasy.components.widget.PreviewScreen

@Composable
fun CCBannerLine(text: String = R.string.app_name.local()) {
    Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
                id = R.mipmap.ic_launcher,
                modifier =
                        Modifier.outerShadow(shape = RoundedCornerShape(12.dp), radius = 12)
                                .clip(RoundedCornerShape(12.dp))
                                .size(36.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.weight(1f)) {
            Text(text = text, style = CCFont.f2b.v2)
            Text(text = "your app", style = CCFont.f3.v2)
        }
//        Image(id = R.drawable.qrcode, modifier = Modifier.size(36.dp))
    }
}

@Preview
@Composable
private fun preview() {
    PreviewScreen(modifier = Modifier.background(CCColor.b2).padding(top = 100.dp)) {
        CCBannerLine()
    }
}
